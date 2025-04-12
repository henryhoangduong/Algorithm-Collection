package PriorityQueue.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import PriorityQueue.PriorityQueue;

public class Heap<T extends Comparable<T>> implements PriorityQueue {

    private static final int DEFAULT_BRANCHING_FACTOR = 2;

    public static final int MAX_BRANCHING_FACTOR = 10;

    private List<T> elements;

    private Map<T, Integer> elementsPositions;

    private ReentrantReadWriteLock.ReadLock readLock;
    private ReentrantReadWriteLock.WriteLock writeLock;

    private int branchingFactor;

    public Heap() {
        this(DEFAULT_BRANCHING_FACTOR)
    }

    public Heap(int branchingFactor) throws IllegalArgumentException {
        elements = new ArrayList<>();
        elementsPositions = new HashMap<>();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
        validateBranchingFactor(branchingFactor);
        this.branchingFactor = branchingFactor;
    }

    public Heap(List<T> elements, int branchingFactor) throws IllegalArgumentException {
        validateBranchingFactor(branchingFactor);
        this.branchingFactor = branchingFactor;
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();

        if (elements == null) {
            throw new NullPointerException("Null argument(s)");
        }
        int n = elements.size();

        this.elements = new ArrayList<>(elements);

        elementsPositions = new HashMap<>();

        // Now performs a heapify initialization
        for (int i = getParentIndex(n - 1) + 1; i < n; i++) {
            // Sets the positions for the second half of the array
            elementsPositions.put(this.elements.get(i), i);
        }

        for (int i = getParentIndex(n - 1); i >= 0; i--) {
            // Performs a push-down for every element with at least one children, starting
            // from last
            // This way each sub-tree rooted at index i will be a valid sub-heap
            pushDown(i);
        }
    }

    @Override
    public Optional<T> top() {
        writeLock.lock();
        try {
            if (this.isEmpty()) {
                return Optional.empty();
            }
            int n = elements.size();
            T top = elements.get(0);

            if (n > 1) {
                // Replaces the top element with the last element in the heap
                elements.set(0, elements.remove(n - 1));
                this.pushDown(0);
            } else {
                elements.remove(0);
            }
            elementsPositions.remove(top);
            // INVARIANT: top is non null at this point
            return Optional.of(top);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<T> peek() {
        readLock.lock();
        try {
            return elements.isEmpty() ? Optional.empty() : Optional.of(elements.get(0));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(T element) {
        readLock.lock();
        try {
            return elementsPositions.containsKey(element);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean add(T element) {
        writeLock.lock();
        try {
            if (this.contains(element)) {
                return false;
            } // else {

            elements.add(element);
            this.bubbleUp(elements.size() - 1);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean remove(T element) {
        writeLock.lock();
        try {
            if (this.isEmpty() || !this.contains(element)) {
                return false;
            } // else

            int n = this.size();
            int position = elementsPositions.get(element);
            if (position == n - 1) {
                // This also covers the case n == 1
                elements.remove(position);
                elementsPositions.remove(element);
            } else {
                elements.set(position, elements.get(n - 1));
                elements.remove(n - 1);
                elementsPositions.remove(element);
                this.pushDown(position);
            }
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean updatePriority(T oldElement, T newElement) {
        writeLock.lock();
        try {
            if (this.isEmpty() || !this.contains(oldElement)) {
                return false;
            } // else

            int position = elementsPositions.get(oldElement);
            elements.set(position, newElement);

            if (hasHigherPriority(newElement, oldElement)) {
                bubbleUp(position);
            } else {
                pushDown(position);
            }

            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return elements.size();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            elements.clear();
            elementsPositions.clear();
        } finally {
            writeLock.unlock();
        }
    }

    protected boolean hasHigherPriority(T element, T withRespectToElement) {
        return element.compareTo(withRespectToElement) < 0;
    }

    protected int getFirstChildIndex(int index) {
        return branchingFactor * index + 1;
    }

    protected int getParentIndex(int index) {
        return (index - 1) / branchingFactor;
    }

    private void validateBranchingFactor(int branchingFactor) throws IllegalArgumentException {
        if (branchingFactor < DEFAULT_BRANCHING_FACTOR || branchingFactor > MAX_BRANCHING_FACTOR) {
            throw new IllegalArgumentException(
                    String.format("Branching factor needs to be an int between {} and {}", DEFAULT_BRANCHING_FACTOR,
                            MAX_BRANCHING_FACTOR));
        }
    }

    private void pushDown(int index) {
        // INVARIANT: index < n
        int n = elements.size();
        int smallestChildrenIndex = getFirstChildIndex(index);
        T element = elements.get(index);

        while (smallestChildrenIndex < n) {
            int lastChildrenIndexGuard = Math.min(getFirstChildIndex(index) + branchingFactor, n);
            // Find all
            for (int childrenIndex = smallestChildrenIndex; childrenIndex < lastChildrenIndexGuard; childrenIndex++) {
                if (hasHigherPriority(elements.get(childrenIndex), elements.get(smallestChildrenIndex))) {
                    smallestChildrenIndex = childrenIndex;
                }
            }
            T child = elements.get(smallestChildrenIndex);

            if (hasHigherPriority(child, element)) {
                elements.set(index, child);
                elementsPositions.put(child, index);
                index = smallestChildrenIndex;
                smallestChildrenIndex = getFirstChildIndex(index);
            } else {
                // The element is already in the right place
                break;
            }
        }
        elements.set(index, element);
        elementsPositions.put(element, index);
    }

    private void bubbleUp(int index) {
        // INVARIANT: 0 <= index < n
        int parentIndex;
        T element = elements.get(index);

        while (index > 0) {
            parentIndex = getParentIndex(index);
            T parent = elements.get(parentIndex);
            if (hasHigherPriority(element, parent)) {
                elements.set(index, parent);
                elementsPositions.put(parent, index);
                index = parentIndex;
            } else {
                // The element is already in the right position
                break;
            }
        }
        elements.set(index, element);
        elementsPositions.put(element, index);
    }

    @VisibleForTesting
    protected boolean checkHeapInvariants() {
        readLock.lock();
        try {
            for (int i = 0, n = size(); i < n; i++) {
                T parent = elements.get(i);

                for (int j = getFirstChildIndex(i), last = getFirstChildIndex(i + 1); j < last; j++) {
                    if (j < n && hasHigherPriority(elements.get(j), parent)) {
                        return false;
                    }
                }
            }
            return true;
        } finally {
            readLock.unlock();
        }
    }
}
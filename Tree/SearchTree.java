package Tree;

public interface SearchTree<T extends Comparable<T>> extends ReadOnlySearchTree<T> {
    boolean add(final T element);

    boolean remove(final T element);

    boolean remove(final T element);


    void clear();
}

package cache

import (
	"container/list"
	"math"
)

type LFU struct {
	len     int
	cap     int
	minFreq int
	itemMap map[string]*list.Element
	freqMap map[int]*list.List
}

func NewLFU(capacity int) LFU {
	return LFU{
		len:     0,
		cap:     capacity,
		minFreq: math.MaxInt,
		itemMap: make(map[string]*list.Element),
		freqMap: make(map[int]*list.List),
	}
}

func initItem(k string, v any, f int) item {
	return item{
		key:   k,
		value: v,
		freq:  f,
	}
}

func (c *LFU) Get(key string) any {

}

func (c *LFU) Put(key string, value any) {

}

func (c *LFU) increaseFreq(e *list.Element) {

}

func (c *LFU) insertMap(obj item) {

}

func (c *LFU) eliminate() {
	l := c.freqMap[c.minFreq]
	e := l.Back()
	obj := e.Value.(item)
	l.Remove(e)
	delete(c.itemMap, obj.key)
}

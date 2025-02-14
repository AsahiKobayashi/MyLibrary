class TreeMultiSet<E> extends MultiSet<E> {
    
    TreeMultiSet() {
        super(new TreeMap<>());
    }
    
    TreeMultiSet(Comparator<? super E> comparator) {
        super(new TreeMap<>(comparator));
    }
    
    public E peekFirst() {
        return ((TreeMap<E,Long>) map).firstKey();
    }
    
    public E peekLast() {
        return ((TreeMap<E,Long>) map).lastKey();
    }
    
    public E pollFirst() {
        E e = peekFirst();
        remove(e , -1l);
        return e ;
    }
    
    public E pollFirst(long amount) {
        E e = peekFirst();
        remove(e , -amount);
        return e ;
    }
    
    public E pollLast() {
        E e = peekLast();
        remove(e , -1l);
        return e ;
    }
    
    public E pollLast(long amount) {
        E e = peekLast();
        remove(e , -amount);
        return e ;
    }
    // e <= X
    public E ceilingKey(E e) {
        return ((TreeMap<E,Long>) map).ceilingKey(e) ;
    }
    // e < X
    public E higherKey(E e) {
        return ((TreeMap<E,Long>) map).higherKey(e) ;
    }
    // X <= e
    public E floorKey(E e) {
        return ((TreeMap<E,Long>) map).floorKey(e) ;
    }
    // X < e 
    public E lowerKey(E e) {
        return ((TreeMap<E,Long>) map).lowerKey(e) ;
    }
    
}

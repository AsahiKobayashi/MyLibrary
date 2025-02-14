class MultiSet<E> {
    
    private long size;
    private Map<E,Long> map;
    
    MultiSet() {
        this(new HashMap<>());
    }
    
    MultiSet(Map<E,Long> map) {
        this.map = map;
        this.size = 0 ;
    }
    
    public long size(boolean u) {
        return u ? (long) this.map.size() : this.size ;
    }
    
    public long size(E e) {
        return this.map.get(e);
    }
    
    public void add(E e) {
        add(e , 1l);
    }
    
    public void add(E e , long amount) {
        this.map.put(e , this.map.getOrDefault(e , 0l) + amount);
        size += amount;
        if(this.map.get(e) == 0l) this.map.remove(e);
    }
    
    public void remove(E e) {
        add(e , -1l);
    }
    
    public void remove(E e , long amount) {
        add(e , amount);
    }
    
    @Override
    public String toString() {
        return this.map.toString();
    }
    
}

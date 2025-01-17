class DualSegmentTree<T> {
    
    private Monoid<T> monoid;
    private int n;
    private Object[] f;
  
    public DualSegmentTree(int n, Monoid<T> monoid) {
        this.monoid = monoid;
        this.n = 1;
        while (this.n < n) this.n *= 2;
        this.f = new Object[2 * this.n - 1];
        Arrays.fill(f, monoid.e());
    }
    
    public void set(int index , T g) {
        apply(index , index + 1 , g);
    }

    public T get(int i) {
        assert (0 <= i && i < n);
        T acc = monoid.e();
        for (i += n; i > 0; i /= 2) {
            acc = monoid.op((T) f[i - 1], acc);
        }
        return acc;
    }

    public void apply(int l, int r, T g) {
        assert (0 <= l && l <= r && r <= n);
        rangeApply(0, 0, n, l, r, g);
    }

    private void rangeApply(int i, int il, int ir, int l, int r, T g) {
        if (l <= il && ir <= r) {
            f[i] = monoid.op(g, (T) f[i]);
        } else if (ir <= l || r <= il) {
            // 何もしない
        } else {
 
            rangeApply(2 * i + 1, il, (il + ir) / 2, l, r, g);
            rangeApply(2 * i + 2, (il + ir) / 2, ir, l, r, g);
        }
    }
  
}

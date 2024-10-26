interface MonoidAction<S, F> {
    public S e();
    public F id();
    public S op(S l, S r);
    public S mapping(F l, S r);
    public F composition(F l, F r);
}
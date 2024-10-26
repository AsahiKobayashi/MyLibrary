interface Monoid<T> {
    T e();
    T op(T a, T b);
}
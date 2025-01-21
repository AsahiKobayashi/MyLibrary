private static final long MOD = (1L << 61) - 1;
private static final long MASK0 = (1L << 30) - 1;
private static final long MASK1 = (1L << 31) - 1;
private static long[] pow;

public static long genHash(int N) {
    Random rnd = new Random();
    LongBinaryOperator gcd = (a, b) -> {
        while (a != 0) if ((b %= a) != 0) a %= b;
        else return a;
        return b;
    };
    while (true) {
        long e = mod(rnd.nextLong());
        if (gcd.applyAsLong(e, MOD - 1) == 1) {
            long base = 1;
            for (long x = 37; e > 0; e >>= 1, x = mul(x, x))
                if ((e & 1) != 0) base = mul(base, x);
            pow = new long[N + 1];
            pow[0] = 1;
            for (int i = 0; i < N; ++i) pow[i + 1] = mul(pow[i], base);
            return base;
        }
    }
}

private static long mod(final long x) {
    final long xu = x >>> 61;
    final long xd = x & MOD;
    long res = xu + xd;
    if (res >= MOD) res -= MOD;
    return res;
}

private static long mul(final long a, final long b) {
    final long au = a >>> 31;
    final long ad = a & MASK1;
    final long bu = b >>> 31;
    final long bd = b & MASK1;
    final long mid = ad * bu + au * bd;
    final long midu = mid >>> 30;
    final long midd = mid & MASK0;
    return mod((au * bu << 1) + midu + (midd << 31) + ad * bd);
}

static class RollingHash {

    int len;
    long hash;

    RollingHash() {
        len = 0;
        hash = 1;
    }
    RollingHash(char c) {
        len = 1;
        hash = c;
    }

    RollingHash(int len, long hash) {
        this.len = len;
        this.hash = hash;
    }

    static RollingHash merge(RollingHash l, RollingHash r) {
        return new RollingHash(l.len + r.len, mod(mul(l.hash, pow[r.len]) + r.hash));
    }

}

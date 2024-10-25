/**
 *  IntSparseTable
 * 
 *  build : O(NlogN)
 *  query : O(1)
 * 
 *  @author Asahi Kobayashi
 */

class IntSparseTable {

    private int n ;
    private int [] log ;
    private int [][] dp ;
    private BinaryOperator<Integer> op ;

    IntSparseTable(int [] array , BinaryOperator<Integer> op) {
        this.n = array.length;
        this.op = op;
        this.log = new int[n + 1];
        for(int i = 2 ; i <= n ; i ++)
            log[i] = log[i >> 1] + 1 ;
        this.dp = new int[n][log[n] + 1];
        for(int i = 0 ; i < n ; i ++)
            dp[i][0] = array[i];
        build();
    }

    private void build() {
        for(int i = 1 ; i < log[n] + 1; i ++) {
            for(int j = 0 ; j + (1 << (i - 1)) < n ; j ++)
                dp[j][i] = op.apply(dp[j][i - 1] , dp[j + (1 << (i - 1))][i - 1]);
        }
    }

    public int query(int l , int r) {
        int length = log[r - l];
        return op.apply(dp[l][length] , dp[r - (1 << length)][length]);
    }

}
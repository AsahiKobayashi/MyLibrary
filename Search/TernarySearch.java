public long Ternary_Search(long l , long r) {
    long L = l , R = r ;
    while(R - L > 1) {
        long M = (R + L) / 2 ;
        long LMID = (L + M) / 2 , RMID = (R + M) / 2 ;
        double leftVal = -1 , rightVal = -1 ;
        /*
         *  leftVal ,　rightValを定義する必要あり。
         */
        if(leftVal < rightVal) R = RMID ;
        else L = LMID + 1;
    }
    return L ;
}

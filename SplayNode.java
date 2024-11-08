class SplayNode<T,S>{

    int size ;
    Pair<T,S> value ;
    S agg ;
    SplayNode<T,S> left , right , parent ;
    BinaryOperator<S> op ;

    SplayNode(Pair<T,S> value , BinaryOperator<S> op) { 
        left = right = parent = null ; size = 1;
        this.value = value; 
        this.agg = value.s();
        this.op = op;
    }

    public void rotate() { 
        SplayNode<T,S> par = this.parent ; // 自分の親
        SplayNode<T,S> parpar = par.parent ; // 自分の親の親
        SplayNode<T,S> ch = null ; // 自分の子 (右回転、左回転かによって変わる)
        /**
         *  左回転
         */
        if(par.left == this) {
            ch = this.right ;
            this.right = par ; 
            par.left = ch ;
        }
        /**
         *  右回転
         */
        if(par.right == this) {
            ch = this.left ;
            this.left = par ;
            par.right = ch ;
        }
        /**
         * 　親の親が存在する場合、回転後自分の親を張り替える
         */
        if(parpar != null) { 
            if(parpar.left == par)  
                parpar.left = this;
            if(parpar.right == par)
                parpar.right = this;
        }
        this.parent = parpar; // 自分の親は元の親の親になる
        par.parent = this; // 元の親は自分の子になる
        if(ch != null)
            ch.parent = par ;
        /**
         * 一度の回転で部分木のサイズに影響があるのは自分とその親
         * ※　ボトムアップで計算する必要がある -> 元の親から
         */
        par.update();
        this.update();
    }
    /**
     * 自分は根になるまで（親が存在する間）
     * 
     * 自分の親、自分の親の親連続で同じ方向に生えている場合 : 親 -> 自分 の順番で上げていく
     * 自分の親、自分の親の親連続で同じ方向に生えていない場合 : 自分 -> 自分 の順番で上げていく
     */
    public void splay() { 
        while(!(this.state() == 0)) { // 自分の親がいる間
            if(this.parent.state() == 0) { // 自分の親の親が存在しない場合 -> 一度自分をあげるだけ
                this.rotate();
            }
            else if(this.parent.state() == this.state()) {
                this.parent.rotate();
                this.rotate();
            }
            else {
                this.rotate();
                this.rotate();
            }
        }
    }

    /**
     *  右の子、左の子の部分木の大きさの合算
     */
    public void update() { 
        this.size = 1 ;
        this.agg = this.value.s();
        if(this.left != null) {
            this.size += this.left.size;
            this.agg = op.apply(this.agg , this.left.agg);
        }
        if(this.right != null) {
            this.size += this.right.size;
            this.agg = op.apply(this.agg , this.right.agg);
        }
    }
    /**
     * 自分の親が存在するか、自分は親のどちらの子か
     * 
     * 自分の親、自分の親の親連続で同じ方向に生えている場合 : 親 -> 自分 の順番で上げていく
     * 自分の親、自分の親の親連続で同じ方向に生えていない場合 : 自分 -> 自分 の順番で上げていく
     * @return
     */
    private int state() {
        if(this.parent == null) return 0 ;
        if(this.parent.left == this) return 1 ;
        if(this.parent.right == this) return -1 ;
        return 0 ;
    }

}

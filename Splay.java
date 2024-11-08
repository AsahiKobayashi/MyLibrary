class Splay<T,S>{

    /**
     * 更新
     * @param <T>
     * @param <S>
     * @param index
     * @param value
     * @param root
     * @param op
     * @return
     */
    public static <T,S> SplayNode<T,S> update(int index , Pair<T,S> value , SplayNode<T,S> root , BinaryOperator<S> op) {
        SplayNode<T,S> insert = new SplayNode<>(value , op);
        root = insert(index, insert, root);
        var spl = delete(index + 1, root);
        return spl.f();
    }

    /**
     * 区間演算
     * @param <T>
     * @param <S>
     * @param l
     * @param r
     * @param root
     * @return
     */
    public static <T,S> Pair<SplayNode<T,S>,S> query(int l , int r , SplayNode<T,S> root) {
        SplayNode<T,S> lroot , croot , rroot ;
        var nodes = split(r , root);
        rroot = nodes.s();
        nodes = split(l, nodes.f());
        lroot = nodes.f();
        croot = nodes.s();
        S ans = croot.agg;
        return new Pair<>(merge(merge(lroot, croot),rroot) , ans);
    }

    /**
     * 挿入
     * @param <T>
     * @param <S>
     * @param index
     * @param insertRoot
     * @param baseRoot
     * @return
     */
    public static <T,S> SplayNode<T,S> insert(int index , SplayNode<T,S> insertRoot , SplayNode<T,S> baseRoot) {
        var nodes = split(index, baseRoot);
        SplayNode<T,S> lroot = nodes.f() , rroot = nodes.s();
        return merge(merge(lroot,insertRoot), rroot);
    }

    /**
     * 削除
     * @param <T>
     * @param <S>
     * @param index
     * @param root
     * @return
     */
    public static <T,S> Pair<SplayNode<T,S>,SplayNode<T,S>> delete(int index , SplayNode<T,S> root) {
        root = get(index,root);
        SplayNode<T,S> lroot = root.left , rroot = root.right;
        if(lroot != null) lroot.parent = null ;
        if(rroot != null) rroot.parent = null ;
        root.left = null ; root.right = null ;
        return new Pair<>(merge(lroot, rroot) , root);
    }

    /**
     * 統合
     * @param <T>
     * @param <S>
     * @param lroot
     * @param rroot
     * @return
     */
    public static <T,S> SplayNode<T,S> merge(SplayNode<T,S> lroot , SplayNode<T,S> rroot) {
        if(lroot == null) return rroot ;
        if(rroot == null) return lroot ;
        lroot = get(lroot.size - 1 , lroot); // 左の部分木の右端は根の部分木のサイズの-1（0-indexed）
        lroot.right = rroot; // 根の右を移し替える
        rroot.parent = lroot; // 右の根の親を張り替える
        lroot.update(); // 子が更新されたから
        return lroot;
    }

    /**
     * 分割
     * @param <T>
     * @param <S>
     * @param lsize
     * @param root
     * @return
     */
    public static <T,S> Pair<SplayNode<T,S>,SplayNode<T,S>> split(int lsize , SplayNode<T,S> root) {
        if(lsize == 0) return new Pair<>(null, root);
        if(lsize == root.size) return new Pair<>(root,null);
        root = get(lsize , root); // 左と右で分割する
        SplayNode<T,S> lroot , rroot ;
        lroot = root.left;
        rroot = root;
        rroot.left = null ;
        lroot.parent = null ;
        rroot.update();// 子が更新されたから
        return new Pair<>(lroot , rroot);
    }

    /**
     * 単一取得
     * @param <T>
     * @param <S>
     * @param index
     * @param root
     * @return
     */
    public static <T,S> SplayNode<T,S> get(int index , SplayNode<T,S> root) {
        SplayNode<T,S> now = root ;
        for(;;){
            int lsize = (now.left == null) ? 0 : now.left.size;
            if(index < lsize) // 左の部分木のサイズの方が目的の値より大きい -> 左の部分木に存在する
                now = now.left ;
            if(index == lsize) { // 目的の所に到達したらsplay()を呼び上に挙げる必要がある
                now.splay();
                return now ;
            }
            if(index > lsize) {  //　右の部分木に存在する 
                now = now.right ;
                index = index - lsize - 1 ;
            }
        }
    }

    /**
     * デバッグ用
     * @param node 
     * @return
     */
    public static <T,S> SplayNode<T,S> dump(SplayNode<T,S> node) {
        StringBuffer result = new StringBuffer();
        for(int i = 0 ; i < node.size ; i ++){
            node = get(i, node);
            result.append(node.value+" ");
        }
        System.out.println(result);
        return node;
    }

}

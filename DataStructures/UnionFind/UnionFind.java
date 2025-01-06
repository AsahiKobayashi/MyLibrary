class UnionFindTree {

    private int [] parents , size;
    private int n ;
    private int groups;
    private HashMap<Integer,List<Integer>> member;

    UnionFindTree(int n) {
        this.parents=new int[n];
        this.size=new int[n];
        this.groups=n;
        this.n = n ;
        this.member = new HashMap<>();
        Arrays.fill(size , 1);
        Arrays.setAll(parents,i->i);
    }

    public int size(){
        return groups;
    }

    public int count(int x){ 
        return size[root(x)] ; 
    }

    public boolean same(int x,int y){ 
        return root(x)==root(y) ; 
    }

    public int root(int x){
        if(x==parents[x]) return x ;
        else parents[x]=root(parents[x]);
        return parents[x];
    }

    public void unite(int l,int r){
        int x = root(l);
        int y = root(r);
        if(x == y) return ;
        if(x < y) {
            int tmp = x;
            x = y ;
            y = tmp;
        }
        int par=x,ch=y;
        groups--;
        size[par] += size[ch];
        parents[ch] = par;
    }

    public void split() {
        for(int i = 0 ; i < n ; i ++) {
            int root = root(i);
            if(!member.containsKey(root))
                member.put(root , new ArrayList<>());
            member.get(root).add(i);
        }
    }

    public List<Integer> getMembers(int x) {
        return member.get(root(x));
    }

    public HashMap<Integer,List<Integer>> getComponents() {
        return member ;
    }

}

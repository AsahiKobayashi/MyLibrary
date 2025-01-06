@SuppressWarnings("unchecked")
class ArraysUtil {

    public static final <T> T [] convert(Object obj) {
        int length = java.lang.reflect.Array.getLength(obj);
        IntFunction<T []> f = null ;
        if(obj instanceof int []) f = size -> (T []) new Integer[size];
        if(obj instanceof long []) f = size -> (T []) new Long[size];
        if(obj instanceof double []) f = size -> (T []) new Double[size];
        if(obj instanceof char []) f = size -> (T []) new Character[size];
        if(obj instanceof boolean []) f = size -> (T []) new Boolean[size];
        T [] result = f.apply(length);
        for (int i = 0 ; i < length ; i ++) result[i] = (T) java.lang.reflect.Array.get(obj, i);
        return result ;
    }
    
    public static final <T> void swap(T obj , int l , int r) {
        Object left = java.lang.reflect.Array.get(obj , l) , right = java.lang.reflect.Array.get(obj , r);
        java.lang.reflect.Array.set(obj , l , right); java.lang.reflect.Array.set(obj , r , left);
    }   

    public static final <T> void reverse(T obj , int l , int r) {
        for(int i = l , j = r - 1 ; i < j ; i ++ , j --) swap(obj ,i, j);
    }

    public static final <T,S> T insert(T obj , int index , S value) {
        T e = (T) java.lang.reflect.Array.newInstance(obj.getClass().getComponentType() , 1) ;
        java.lang.reflect.Array.set(e, 0 , value);
        T arrays = split(obj , index);
        return merge((T) java.lang.reflect.Array.get(arrays, 0) , merge(e , (T) java.lang.reflect.Array.get(arrays, 1)));
    }

    public static final <T> T delete(T obj , int index) {
        T arrays = split(obj , index);
        T arrays2 = split((T) java.lang.reflect.Array.get(arrays, 1) , 1);
        return merge((T) java.lang.reflect.Array.get(arrays, 0) , (T) java.lang.reflect.Array.get(arrays2, 1));
    }

    public static final <T> T split(T obj , int index) {
        T l = (T) java.lang.reflect.Array.newInstance(obj.getClass().componentType() , index);
        T r = (T) java.lang.reflect.Array.newInstance(obj.getClass().componentType() , java.lang.reflect.Array.getLength(obj) - index);
        T ret = (T) java.lang.reflect.Array.newInstance(obj.getClass().componentType() , 2);
        System.arraycopy(obj , 0 , l , 0 , index);
        System.arraycopy(obj , index , r, 0,  java.lang.reflect.Array.getLength(obj) - index);
        java.lang.reflect.Array.set(ret,0 , l);
        java.lang.reflect.Array.set(ret,0 , r);
        return ret ;
    }

    public static final <T> T merge(T l , T r) {
        T array = (T) java.lang.reflect.Array.newInstance(l.getClass().componentType() , java.lang.reflect.Array.getLength(l) + java.lang.reflect.Array.getLength(r));
        System.arraycopy(l , 0, array, 0 , java.lang.reflect.Array.getLength(l));
        System.arraycopy(r , 0, array,  java.lang.reflect.Array.getLength(l) , java.lang.reflect.Array.getLength(r));
        return array ;
    }

    public static final <T,S> void fill(T array, S value) {
        int length = java.lang.reflect.Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = java.lang.reflect.Array.get(array, i);
            if (element != null && element.getClass().isArray()) fill(element, value);
            else java.lang.reflect.Array.set(array, i, value);
        }
    }

}

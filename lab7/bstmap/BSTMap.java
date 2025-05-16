package bstmap;

import afu.org.checkerframework.checker.oigj.qual.O;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private K key;
    private V value;
    private BSTMap left;
    private BSTMap right;
    private int size = 0;
    private listnode list = null;

    private class listnode{
        private K key;
        private V value;
        private listnode next;
        public listnode(K key, V value, listnode n){
            this.key = key;
            this.value = value;
            this.next = n;
        }
        public void insert(K key, V value){
            listnode curr = this;
            while(curr.next != null) curr = curr.next;
            curr.next = new listnode(key, value, null);
        }
    }

    public BSTMap(K key, V value){
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.size = 1;
    }
    public BSTMap(){
    }

    @Override
    public int size(){
        return this.size;
    }

    private BSTMap put_helper(BSTMap T, K key, V value){
        if(T == null) return new BSTMap(key, value);
        T.size += 1;
        if(key.compareTo((K) T.key) < 0) {
            T.left = put_helper(T.left, key, value);
        }
        else {
            T.right = put_helper(T.right, key, value);
        }
        return T;
    }

    @Override
    public void put(K key, V value){
        if(this.key == null){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.size = 1;
            this.list = new listnode(key, value, null);
        }
        else {
            this.list.insert(key, value);
            put_helper(this, key, value);
        }
    }

    private boolean contains_helper(BSTMap T, K key){
        if(T == null || this.key == null) return false;
        else if (key.compareTo((K)T.key) == 0) return true;
        else if(key.compareTo((K)T.key) < 0) return contains_helper(T.left, key);
        else return contains_helper(T.right, key);
    }

    private V get_helper(BSTMap T, K key){
        if(T == null || this.key == null) return null;
        else if (key.compareTo((K)T.key) == 0) return (V)T.value;
        else if(key.compareTo((K)T.key) < 0) return get_helper(T.left, key);
        else return get_helper(T.right, key);
    }

    @Override
    public boolean containsKey(K key) {
        return contains_helper(this, key);
    }

    @Override
    public V get(K key){
        return get_helper(this, key);
    }

    @Override
    public void clear(){
        this.key = null;
        this.value = null;
        this.left = null;
        this.right = null;
        this.size = 0;
        this.list = null;
    }


    private void null_helper(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet(){
        null_helper();
        return null;
    }
    @Override
    public V remove(K key){
        null_helper();
        return null;
    }
    @Override
    public V remove(K key, V value){
        null_helper();
        return null;
    }

    @Override
    public Iterator<K> iterator(){
        return new BSTMapIter();
    }


    private class BSTMapIter implements Iterator<K>{
        private listnode curr;
        public BSTMapIter(){
            curr = list;
        }
        @Override
        public boolean hasNext(){
            return curr.next != null;
        }

        @Override
        public K next() {
            K ret = curr.key;
            curr = curr.next;
            return ret;
        }
    }
}

package deque;

public class LinkedListDeque<T> implements List<T>{
    private final Node sentinel;
    private int size;
    private  class Node{
        public T item;
        public Node prev;
        public Node next;
        public Node (T i,Node prev1,Node next1){
            item = i;
            prev = prev1;
            next = next1;
        }
    }
    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        size = 0;
    }
    public void addFirst(T item){
        Node tempnode = new Node(item,null,null);
        if(sentinel.next == null){
            sentinel.next = tempnode;
            sentinel.prev = tempnode;
            tempnode.next = sentinel;
            tempnode.prev = sentinel;
        }
        else{
            tempnode.next = sentinel.next;
            sentinel.next.prev = tempnode;
            tempnode.prev = sentinel;
            sentinel.next = tempnode;
        }
        size++;
    }

    public void addLast(T item){
        Node tempnode = new Node(item,null,null);
        if(sentinel.prev == null){
            sentinel.next = tempnode;
            sentinel.prev = tempnode;
            tempnode.next = sentinel;
            tempnode.prev = sentinel;
        }
        else{
            sentinel.prev.next = tempnode;
            tempnode.prev = sentinel.prev;
            tempnode.next = sentinel;
            sentinel.prev = tempnode;
        }
        size++;
    }

    public boolean isEmpty(){
        return sentinel.next == sentinel  ||  sentinel.next == null;
    }


    public int size(){
        return size;
    }


    public void printDeque(){
        Node curr = sentinel.next;
        while(curr.next != sentinel){
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println(curr.item);
        return;
    }


    public T removeFirst(){
        if(sentinel.next == null||sentinel.next == sentinel){
            return null;
        }
        Node temp = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return temp.item;
    }


    public T removeLast(){
        if(sentinel.prev == null||sentinel.next == sentinel){
            return null;
        }
        Node temp = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return temp.item;
    }


    public T get(int index){
        Node curr = sentinel.next;
        for(int i = 0;i<size;i++){
            if(i == index){
                return curr.item;
            }
            curr = curr.next;
        }
        return null;
    }


    public T getRecursive(int index){
        if(index>size-1){
            return null;
        }
        else{
            return recursiveHelper(sentinel.next,index);
        }
    }
    /*a helper method to do recursive to get method*/
    private T recursiveHelper(Node curr,int index){
        if(index == 0){
            return curr.item;
        }
        else{
            return recursiveHelper(curr.next,index-1);
        }
    }


    private class Iterator implements java.util.Iterator<T>{
        private Node ie;
        public Iterator(){
            ie = sentinel.next;
        }
        @Override
        public boolean hasNext(){
            if(ie.next!=sentinel && ie.next!=null){
                return true;
            }
            return false;
        }
        @Override
        public T next(){
            T temp = ie.item;
            ie =ie.next;
            return temp;
        }
    }

    public java.util.Iterator<T> iterator(){
        return new Iterator();
    }


    public boolean equals(Object o){
        LinkedListDeque temp = (LinkedListDeque) o;
        if(this == o){
            return true;
        }
        if(this.size!=temp.size()){
            return false;
        }
        else{
            Node curr1 = sentinel.next;
            Node curr2 = temp.sentinel.next;
            for(int i = 0;i<size;i++){
                if(curr1.item!=curr2.item){
                    return false;
                }
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }
        return true;
    }
}

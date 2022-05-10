import java.util.Iterator;

public class MyBST<K extends Comparable<K>, V> implements Iterable<K>{
    private Node base;
    private class Node{
        private V value;
        private K key;
        private Node right, left;
        public Node(V value, K key){
            this.value = value;
            this.key = key;
            left = right = null;
        }
    }

    public MyBST(){
        base = null;
    }

    public V get(K key){
        Node node = base;
        while (node != null){
            if (key.compareTo(node.key) == 0) return node.value;
            if (key.compareTo(node.key) < 0) node=node.left;
            else node=node.right;
        }
        return (V) "There is NO Key in BST";
    }

    public void delete(K key){
        delete(base, key);
    }

    private Node delete(Node node, K key){
        if (node == null) {
            System.out.println("There is NO Key in BST");
            return null;
        }

        if (key.compareTo(node.key) > 0){
            node.right = delete(node.right, key);
        }
        else if (key.compareTo(node.key) < 0){
            node.left = delete(node.left, key);
        }

        else {
            if (node.left == null){
                return node.right;
            }
            else if (node.right == null){
                return node.left;
            }
            node = getMax(node.left);
            node.left = delete(node.left, node.key);
        }

        return node;
    }

    public void put(V value, K key)
    {
        base = put(base, key, value);
    }

    private Node put(Node node, V value, K key){
        if (node == null)
        {
            node = new Node(key, value);
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }
        else if (key.compareTo(node.key) < 0){
            node.left = put(node.left, key, value);
        }
        else {
            node.value = value;
        }
        return node;
    }

    public V getMax()
    {
        return getMax(base).value;
    }
    private Node getMax(Node base){
        if (base == null) {
            return null;
        }
        Node node = base;
        while (node.right != null)
        {
            node = node.right;
        }
        return node;
    }

    public V getMin()
    {
        return getMin(base).value;
    }
    private Node getMin(Node base)
    {
        if (base == null){
            return null;
        }
        Node node = base;
        while (node.left != null)
        {
            node = node.left;
        }
        return node;
    }

    public Iterator<K> iterator()
    {
        NewQueue<K> NewQueue = new NewQueue<K>();
        inOrder(base, NewQueue);
        return NewQueue;
    }

    private void inOrder(Node current, NewQueue<K> NewQueue)
    {
        if (current == null){
            return;
        }
        inOrder(current.left, NewQueue);
        NewQueue.enqueue(current.key);
        inOrder(current.right, NewQueue);
    }
}

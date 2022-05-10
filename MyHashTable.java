public class MyHashTable<K, V> {
    private class HashNode<K, V>{
        private V value;
        private K key;
        private HashNode<K, V> next;
        public HashNode( V value, K key){
            this.value = value;
            this.key = key;
            next = null;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V> [] MyArray;
    private int a = 11;
    private int size;

    public MyHashTable(){
        MyArray = new HashNode[a];
    }

    public MyHashTable(int a){
        this.a = a;
        MyArray = new HashNode[a];
    }

    private int hash(K key)
    {
        return (key.hashCode() & 0x7fffffff) % a;
    }

    public void put(K key, V value){
        int hashIndex = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (MyArray[hashIndex] != null)
        {
            newNode.next = MyArray[hashIndex];
        }
        MyArray[hashIndex] = newNode;
    }

    public V get(K key){
        int hashIndex = hash(key);
        HashNode<K, V> current = MyArray[hashIndex];
        while (current != null){
            if (current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return (V) "NULL";
    }

    public V remove(K key)
    {
        int hashIndex = hash(key);
        HashNode<K, V> current = MyArray[hashIndex];
        HashNode<K, V> prev = null;

        while (current != null){
            if (key.equals(current.key))
            {
                if (MyArray[hashIndex].equals(current))
                {
                    MyArray[hashIndex] = current.next;
                }
                else {
                    prev.next = current.next;
                }
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return (V) "Null";
    }

    public boolean contains(V value){
        HashNode <K, V> node;
        for (int i = 0; i < a; i++){
            node = MyArray[i];
            while (node != null){
                if (node.value.equals(value)) return true;
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value){
        HashNode <K, V> node;
        for (int i = 0; i < a; i++){
            node = MyArray[i];
            while (node != null){
                if (node.value.equals(value)) return node.key;
                node = node.next;
            }
        }
        return (K) "Null";
    }
}
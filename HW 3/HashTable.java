/**
 * HashTable kinda cool.
 *
 * hashes via mod table size.
 * keeps only the most up to date data.
 *
 * @author Deven Ronquillo
 * @version 2/11/17
 *
 * @param <K>   The Type for your key.
 * @param <V>   The type for your value.
 */
public class HashTable<K,V> {

    private HashNode<K,V>[] nodes;
    
    /**
     * Constructor for HashTable
     *
     * @param size  The size fo your initial table.
     */
    public HashTable(int size){

        super();
        nodes = new HashNode[size];
    }

    /**
     * Hashing function.
     * 
     * simplistic aproach meaning mod table size.
     * 
     * @precondition Table size must be prime for best results.
     * @param key Key to determine the hash value of.
     * @return The hash value of the supplied key.
     */
    private int GetHash(K key){

        int hash = key.hashCode() % nodes.length;

        if(hash < 0){

            hash += nodes.length;
        }

        return hash;
    }

    /**
     * inserts the desired value into the table or updates the old value.
     * 
     * @param key   The key of the data to store.
     * @param data  value of data associated with the key.
     * @return  The old data if needed after replacement or null withought replacment.
     */
    public V Insert(K key, V data){

        int hash = GetHash(key);

        for(HashNode<K,V> node = nodes[hash]; node != null; node = node.next){

            if((hash == node.hash) && key.equals(node.key)){

                V oldData = node.data;
                node.data = data;

                return oldData;
            }
        }

        HashNode<K,V> node = new HashNode<K,V>(key, data, nodes[hash], hash);
        nodes[hash] = node;

        return null;
    }

    /**
     * It Removes the given key from hash table.
     * 1. Gets the hash using built in hash code method and by doing % to match to local index
     * 2. Look for the corresponding index in nodes array, if found then, we got the linked list
     *         a. Search for the given key, also match the hash
     *         b. If found, make previous.next = node.next; and return true.
     * @param key
     * @return
     */
    public boolean Remove(K key){

        int hash = GetHash(key);
        HashNode<K,V> previous = null;

        for(HashNode<K,V> node = nodes[hash]; node != null; node = node.next){

            if((hash == node.hash) && key.equals(node.key)){

                if(previous != null){

                    previous.next = node.next;
                }else{

                    nodes[hash] = node.next;
                }

                return true;
            }

            previous = node;
        }

        return false;
    }

    public V Get(K key){

        int hash = GetHash(key);

        for(HashNode<K,V> node = nodes[hash]; node != null; node = node.next){

            if(key.equals(node.key)) {

                return node.data;
            }
        }
        return null;
    }

    public void resize(int size){

        HashTable<K, V> newtbl = new HashTable<K, V>(size);

        for(HashNode<K,V> node : nodes){

            for(; node != null; node = node.next){

                newtbl.Insert(node.key, node.data);
                Remove(node.key);
            }
        }

        nodes = newtbl.nodes;
    }

    /**
     * Generic Hash node class, acts like a linked list node
     * @author IVY3508
     *
     * @param <K>
     * @param <V>
     */
    static class HashNode<K,V> {

        final K key;
        V data;

        HashNode<K,V> next;
        final int hash;

        public HashNode(K key, V value, HashNode<K,V> nextNode, int hash){

            this.key = key;
            this.data = value;
            this.next = nextNode;
            this.hash = hash;
        }
    }
}
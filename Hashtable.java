//FIRST CHECKPOINT REQUIRED

public abstract class Hashtable {

    protected int size;             //Number Of Elements In Array
    protected int capacity;         //Size Of The Array (m?)
    protected double loadFactor;    //Percentage Of Array That Is Filled. loadFactor = size/capacity;

    //An Array Of Hash Object
    protected HashObject[] table;

    //Default Constructor
    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new HashObject[capacity];
    }

    public abstract int h(Object key, int probe);

    //Need Insert w/Key
    public int insert(int key,int value) {

        int index = h(table, value);
        int i = 0;
        int probe;

        while (i != capacity) {

            probe = h(table, i);

            if (table[probe] == null) {
                table[probe] = key;
                return probe;
            } else {
                i++;
            }
        }

        System.err.println("Hash Table Overflow");
    }

    //Need Search
    public int hashSearch(int key) {

        int i = 0;
        int probe;

        while(table[probe] != null || i != capacity) {

            probe = h(table, i);

            if (table[probe] == key) {
                return probe;
            }
            i++;
        }
    }
    
}
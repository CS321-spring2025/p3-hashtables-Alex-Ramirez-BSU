//FIRST CHECKPOINT REQUIRED
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class Hashtable {

    protected int size;             //Number Of Elements In Array (k?)
    protected int capacity;         //Size Of The Array (m?)
    protected double loadFactor;    //Percentage Of Array That Is Filled. loadFactor = size/capacity;

    //An Array Of Hash Object
    protected HashObject[] table;

    //Default Constructor
    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new HashObject[capacity];
        this.size = 0;
    }

    public HashObject[] getTable() {
        return this.table;
    }

    public abstract int h(Object key, int probe);

    protected int positiveMod(int dividend, int divisor) {

        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }

        return quotient;
    }

    public int insert(Object key) {

        int i = 0;                          //Counter
        int probe;
    
        // Start with an initial probe index using the key and probe count i
        probe = h(key, i); // Assuming h takes the key and probe count i to calculate the index
    
        // Loop to handle collisions and insert the key-value pair
        while (i < capacity) {
            // If the slot is empty, insert the key-value pair
            if (table[probe] == null) {
                table[probe] = new HashObject(key);  // Assuming you want to store a HashObject with key and value
                //table[probe] = new HashObject(key, value); MAYKBE?
                size++;
                return probe;
            } else if (table[probe].getKey().equals(key)) {
                table[probe].incrementFrequency();
                return probe;
            } else {
                i++;
                table[probe].incrementProbeCount();;
                probe = h(key, i); // Recalculate the probe index with the new probe count
            }
        }
    
        // If the table is full, print an error
        System.err.println("Hash Table Overflow");
        return -1;  // Return -1 if insertion fails due to overflow
    }

    public int hashSearch(Object key) {
        int i = 0;
        int probe;
    
        // Start with an initial probe index
        probe = h(key, i); // Assuming h takes the key and probe index i

        // Loop until the key is found or the entire table has been searched
        while (table[probe] != null) {

            if (table[probe].getKey().equals(key)) {
                return probe;  // Return the probe index if the key is found
            }

            i++;  // Increment the probe count
            probe = h(key, i); // Recalculate the probe index with the new probe count
        }
    
        // Return -1 if the key was not found after the loop
        return -1;
    }

    public void dumpToFile(String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            
            // Loop through the table, and print non-null entries
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    // Use the toString method of HashObject to get the formatted output
                    out.println("table[" + i + "]: " + table[i].toString());
                }
            }

            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to write to file " + fileName);
        }
    }
    
}
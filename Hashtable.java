import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class Hashtable {

    protected int size;             //Number Of Elements In Array (k?)
    protected int capacity;         //Size Of The Array (m?)
    protected double loadFactor;    //Percentage Of Array That Is Filled. loadFactor = size/capacity;
    protected int duplicate;

    //An Array Of Hash Object
    protected HashObject[] table;

    //Default Constructor
    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new HashObject[capacity];
        this.size = 0;
        this.duplicate = 0;
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

    public int totalInserted() {
        return size + duplicate;
    }

    public int totalDuplicates() {
        return duplicate;
    }

    public double getAverageProbes() {

        double totalProbeCount = 0;

        for (int i = 0; i < size; i++) {

            if (table[i] != null) {
                totalProbeCount += table[i].getProbeCount();
            }
        }

        return (double)totalProbeCount/size;

    }

    // public void insert(HashObject key) {
    //     int i = 0;                      // Probe counter
    //     int probe = h(key, i);          // Start with the initial probe index

    //     //HashObject realKey = new HashObject(key);
    
    //     while (i < table.length) {

    //         key.incrementProbeCount();

    //         if (table[probe] == null) {
    //             table[probe] = new HashObject(key);                 // Insert the HashObject at this probe index
    //             size++;                                             // Increase size of the table only for new insertions
    //             return;                                             // Count probes only for new insertions
    //         } 

    //         if (table[probe].getKey().equals(key)) {

    //             // If the key already exists, increment the frequency of the first object
    //             table[probe].incrementFrequency();                  // Increment frequency for duplicates
    //             duplicate++;
    //             return;                                             // Count probes for the search, not the insertion
    //         }

    //         //If a collision occurs, increment the probe count and continue probing
    //         //table[probe].incrementProbeCount();                 // Increment the probe count for this slot
    //         i++;
    //         probe = h(key, i);                                  // Recalculate the probe index with the updated probe count
    //     }
    // }

    public void insert(HashObject key) {
        int i = 0;
        int probe = h(key, i);  // Initial probe index

        while (i < table.length) {
            key.incrementProbeCount();  // Increment probe count for every insertion attempt

            if (table[probe] == null) {
                table[probe] = new HashObject(key);  // Insert new key
                size++;
                return;
            }

            if (table[probe].getKey().equals(key)) {
                table[probe].incrementFrequency();  // Increment frequency for duplicate key
                duplicate++;  // Increment duplicate counter
                return;
            }

            i++;
            probe = h(key, i);  // Recalculate probe index only after increasing i
        }
    }   

    public HashObject hashSearch(HashObject key) {

        int i = 0;
        int probe = h(key, i);

        // Loop until the key is found or the entire table has been searched
        while (table[probe] != null) {
            if (table[probe].getKey().equals(key)) {
                return table[probe];
            }

            i++;
            if (i >= table.length) {
                break;  // Exit if we've checked all slots
            }
            probe = h(key, i);
        }

        return null;
    }

    public double loadFactor() {

        return (double)size/table.length;
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
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

    public double getLoadFactor() {
        return (double)size/table.length;
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

    public int getTotalProbes() {

        int totalProbeCount = 0;

        for (int i = 0; i < capacity; i++) {

            if (table[i] != null) {
                totalProbeCount += table[i].getProbeCount();
            }
        }

        return totalProbeCount;
    }

    public void insert(HashObject key) {

        int i = 0;                              //Probe counter
        int probe = h(key.getKey(), i);         //Start With The Initial Probe Index
    
        while (i < table.length) {

            
            if (table[probe] == null) {
                table[probe] = key;                 //Insert the HashObject at this probe index
                size++;                             //Increase size of the table only for new insertions
                return;
            }

            
            if (table[probe].equals(key)) {
                
                // If the key already exists, increment the frequency of the first object
                table[probe].incrementFrequency();                  //Increment frequency for duplicates
                duplicate++;
                return;
            } else {
                key.incrementProbeCount();
            }

            //If a collision occurs, increment the probe count and continue probing
            i++;
            probe = h(key.getKey(), i);                                      //Recalculate the probe index with the updated probe count
        }
    }

    public HashObject hashSearch(HashObject key) {

        int i = 0;
        int probe = h(key, i);

        //Loop until the key is found or the entire table has been searched
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

    public void dumpToFile(String fileName) {

        try {

            PrintWriter out = new PrintWriter(fileName);
            
            //Loop Through The Table, And Print Non-Null Entries
            for (int i = 0; i < table.length; i++) {

                if (table[i] != null) {
                    out.println("table[" + i + "]: " + table[i].toString());
                }
            }

            out.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to write to file " + fileName);
        }
    }
    
}
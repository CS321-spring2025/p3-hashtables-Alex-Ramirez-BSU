import java.io.BufferedWriter;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
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
        
        int i = 0;  // Probe counter
        int probe = h(key, i);  // Start with the initial probe index
        int totalProbes = 0;  // This will count probes for insertion or duplicate finding
    
        while (i < capacity) {
            // If the slot is empty, insert the key-value pair
            if (table[probe] == null) {
                table[probe] = new HashObject(key);  // Insert the HashObject at this probe index
                size++;  // Increase size of the table only for new insertions
                return totalProbes;  // Count probes only for new insertions
            } else if (table[probe].getKey().equals(key)) {
                // If the key already exists, increment the frequency
                table[probe].incrementFrequency();  // Increment frequency for duplicates
                return totalProbes;  // Count probes for the search, not the insertion
            } else {
                // If a collision occurs, increment the probe count and continue probing
                i++;
                table[probe].incrementProbeCount();  // Increment the probe count for this slot
                probe = h(key, i);  // Recalculate the probe index with the updated probe count
            }
            totalProbes++;  // Increment total probes after every check
        }
    
        // If the table is full, print an error and return -1
        System.err.println("Hash Table Overflow");
        return -1;
    }

    public int hashSearch(Object key) {
        int i = 0;
        int probe = h(key, i);
    
        // Loop until the key is found or the entire table has been searched
        while (table[probe] != null) {
            if (table[probe].getKey().equals(key)) {
                return probe;  // Return the probe index if the key is found
            }
    
            i++;  // Increment the probe count
            probe = h(key, i);  // Recalculate the probe index with the new probe count
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

    // public void dumpToFile(String filename) {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    //         // Iterate through each entry in the table
    //         for (int i = 0; i < table.length; i++) {
    //             if (table[i] != null) {  // Only print non-empty entries
    //                 HashObject hashObject = (HashObject) table[i];
    //                 String line = "table[" + i + "]: " + hashObject.toString();  // Prepend the index
    //                 writer.write(line);
    //                 writer.newLine();  // Write a new line after each entry
    //             }
    //         }
    //     } catch (IOException e) {
    //         System.err.println("Error writing to file: " + e.getMessage());
    //     }
    // }

    // public void dumpToFile(String fileName) {
    //     try {
    //         PrintWriter out = new PrintWriter(fileName);
            
    //         // Loop through the table, and print non-null entries
    //         for (int i = 0; i < table.length; i++) {
    //             if (table[i] != null) {
    //                 // Use the toString method of HashObject to get the formatted output
    //                 out.println("table[" + i + "]: " + table[i].toString());
    //             }
    //         }
    
    //         out.close();
    //     } catch (FileNotFoundException e) {
    //         System.err.println("Error: Unable to write to file " + fileName);
    //     }
    // }
    
}
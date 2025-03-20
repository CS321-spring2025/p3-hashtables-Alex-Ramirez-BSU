public class HashObject {
    private Object key;             //Generic key object
    private int frequencyCount;     //To Track Duplicates
    private int probeCount;         //To Track Probe Attempts

    //Constructor to initialize the key, frequency, and probe count
    public HashObject(Object key) {
        this.key = key;
        this.frequencyCount = 1;    //Frequency Starts At 1, As Created
        this.probeCount = 0;        //Probe Count Starts At 0
    }

    //Getter For The Key
    public Object getKey() {
        return this.key;
    }

    //Getter For The Frequency
    public int getFrequencyCount() {
        return this.frequencyCount;
    }

    //Increment Frequency
    public void incrementFrequency() {
        this.frequencyCount++;
    }

    //Getter For Probe Count
    public int getProbeCount() {
        return this.probeCount;
    }

    //Increment Probe Count
    public void incrementProbeCount() {
        this.probeCount++;
    }

    //Compare Keys
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HashObject other = (HashObject) obj;
        return this.key != null && this.key.equals(other.key);
    }

    //String Representation Of The Object
    @Override
    public String toString() {
        return  key + " " + frequencyCount + " " + probeCount;
    }

}
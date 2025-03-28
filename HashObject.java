public class HashObject {
    private Object key;          // Generic Key Object
    private int frequencyCount;  //To Track Duplicates
    private int probeCount;      //To Track Probe Attempts

    //Default Constructor
    public HashObject(Object key) {
        this.key = key;
        this.frequencyCount = 1;    //Frequency Starts At 1, As Created
        this.probeCount = 1;        //Probe Count Starts At 1
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

    //Override Equals To Compare Keys
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof HashObject hashObject) {
            return key.equals(hashObject.getKey());

        } else {
            return false;
        }
    }

    //String Representation Of The Object
    @Override
    public String toString() {
        return  key + " " + frequencyCount + " " + probeCount;
    }
}
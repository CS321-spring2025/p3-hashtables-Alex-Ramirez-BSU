//FIRST CHECKPOINT REQUIRED

public class DoubleHashing extends Hashtable {

    DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {
        int k = key.hashCode();

        return 1 + (k % (capacity - 2)) % capacity;
    }
    
}

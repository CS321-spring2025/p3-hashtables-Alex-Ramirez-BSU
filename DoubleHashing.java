//FIRST CHECKPOINT REQUIRED

public class DoubleHashing extends Hashtable {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {

        int k = key.hashCode();

        //Primary Hash Funcion
        int h1 = k % capacity;

        //Secondary Hash Function
        int h2 = 1 + (k % (capacity-2)) % capacity;

        return (h1 + probe * h2) % capacity;
    }
    
}

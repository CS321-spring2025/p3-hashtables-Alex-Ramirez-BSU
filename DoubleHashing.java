public class DoubleHashing extends Hashtable {

    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {
        
        int h1 = positiveMod(key.hashCode(), capacity);             //Primary Hash Function
        int h2 = 1 + positiveMod(key.hashCode(), capacity - 2);     //Secondary Hash Function

        return positiveMod(h1 + probe * h2, capacity);  //Double Hashing Function
    }
    
}

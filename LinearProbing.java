public class LinearProbing extends Hashtable {

    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {
        
        int k = key.hashCode();
        return positiveMod(k + probe, capacity);    //Primary Hashing Function
    }
    
}

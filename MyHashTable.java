import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
    	
        this.numBuckets = initialCapacity;
        ArrayList<LinkedList<HashPair<K,V>>> table = new ArrayList<LinkedList<HashPair<K,V>>>();
        for(int i = 0; i < initialCapacity; i++) {
        	table.add(new LinkedList<HashPair<K,V>>());
        }
        this.buckets = table;
    	this.numEntries = 0;
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public boolean isEmpty() {
        return this.numEntries == 0;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    	//find the related index first
    	int index = this.hashFunction(key);
    	LinkedList<HashPair<K,V>> target = this.buckets.get(index);
    	for(int i = 0; i < target.size(); i++) {
        	if(target.get(i).getKey().equals(key)) {
        		V result = target.get(i).getValue();
        		target.get(i).setValue(value);
        		return result;
        	}
        }
    	HashPair<K,V> toAdd = new HashPair<K,V>(key,value);
    	target.add(toAdd);
        this.numEntries ++;
        if(this.numEntries > this.numBuckets * MAX_LOAD_FACTOR) {
    		this.rehash();
    	}
    	return null;
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
        
    	int index = this.hashFunction(key);
    	LinkedList<HashPair<K,V>> target = this.buckets.get(index);
    	for(int i = 0; i < target.size(); i++) {
    		if(target.get(i).getKey().equals(key)) {
    			return target.get(i).getValue();
    		}
    	}
    	return null;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
        
    	int index = this.hashFunction(key);
    	LinkedList<HashPair<K,V>> target = this.buckets.get(index);
    	for(int i = 0; i < target.size(); i++) {
    		if(target.get(i).getKey().equals(key)) {
    			this.numEntries -= 1;
    			return target.remove(i).getValue();
    		}
    	}
    	return null;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /** 
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
    	
    	//modify the related filed first
    	//then double it and modify the field
    	int currentNumBuckets = this.numBuckets;
    	int newNumBuckets = 2 * currentNumBuckets;
    	this.numBuckets = newNumBuckets;
    	//copy the current table
    	ArrayList<HashPair<K, V>> copy = new ArrayList<HashPair<K, V>>(currentNumBuckets);
    	for(int i = 0; i < currentNumBuckets; i++) {
    		for(int j = 0; j < this.getBuckets().get(i).size(); j++) {
    			copy.add(this.getBuckets().get(i).get(j));
    		}
    	}
    	//create a new buckets with double length
    	ArrayList<LinkedList<HashPair<K, V>>> newBuckets = new ArrayList<LinkedList<HashPair<K, V>>>();
    	for(int m = 0; m < newNumBuckets; m++) {
    		newBuckets.add(new LinkedList<HashPair<K,V>>());
    	}
    	this.buckets = newBuckets;
    	this.numEntries = 0;
    	//copy the list right now
    	for(int i = 0; i < copy.size(); i++) {
    		//int index = this.hashFunction(copy.get(i).getKey());
    		//this.buckets.get(index).add(copy.get(i));
    		if(copy.get(i) != null) {
    			this.put(copy.get(i).getKey(), copy.get(i).getValue());
    		}
    	}
        //ADD YOUR CODE ABOVE HERE
        
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
        
    	//create a new array list as the result
    	ArrayList<K> result = new ArrayList<K>();
    	MyHashIterator iterator = new MyHashIterator();
    	while(iterator.hasNext()) {
    		HashPair<K,V> cur = iterator.next();
    		result.add(cur.getKey());
    	}
    	
    	return result;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	
    	MyHashTable<V, V> temp = new MyHashTable<V,V>(this.numBuckets);
    	MyHashIterator iterator = new MyHashIterator();
    	while(iterator.hasNext()) {
    		HashPair<K,V> cur = iterator.next();
    		temp.put(cur.getValue(), cur.getValue());
    	}
    	return temp.keys();
    	
        //ADD CODE ABOVE HERE
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort (MyHashTable<K, V> results) {
        ArrayList<K> sortedResults = new ArrayList<>();
        for (HashPair<K, V> entry : results) {
			V element = entry.getValue();
			K toAdd = entry.getKey();
			int i = sortedResults.size() - 1;
			V toCompare = null;
        	while (i >= 0) {
        		toCompare = results.get(sortedResults.get(i));
        		if (element.compareTo(toCompare) <= 0 )
        			break;
        		i--;
        	}
        	sortedResults.add(i+1, toAdd);
        }
        return sortedResults;
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to.
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    
    /*
     * To implement the merge sort method in fast sort method, write the helper method merge first
     */
    private static <K,V extends Comparable<V>>ArrayList<HashPair<K,V>> merge(ArrayList<HashPair<K,V>> list1, ArrayList<HashPair<K,V>>list2){
    		ArrayList<HashPair<K,V>> result = new ArrayList<HashPair<K,V>>();
    		while(!list1.isEmpty() && !list2.isEmpty()) {
    			V toCompare1 = list1.get(0).getValue();
    			V toCompare2 = list2.get(0).getValue();
    			if(toCompare1.compareTo(toCompare2) > 0) {
    				result.add(list1.remove(0));
    			}
    			else {
    				result.add(list2.remove(0));
    			}
    		}
    		while(!list1.isEmpty()) {
    			result.add(list1.remove(0));
    		}
    		while(!list2.isEmpty()) {
    			result.add(list2.remove(0));
    		}
    		return result;
    }
    
    private  static <K,V extends Comparable<V>> ArrayList<HashPair<K,V>> mergeSort(ArrayList<HashPair<K,V>> bucket){
    	int size = bucket.size();
    	if(size == 1) {
    		return bucket;
    	}
    	else {
    		int mid = (size - 1) / 2;
    		ArrayList<HashPair<K,V>> list1 = new ArrayList<HashPair<K,V>>();
    		for(int i = 0; i < mid+1; i++) {
    			list1.add(bucket.get(i));
    		}
    		ArrayList<HashPair<K,V>> list2 = new ArrayList<HashPair<K,V>>();
    		for(int i = mid+1; i < size; i++) {
    			list2.add(bucket.get(i));
    		}
    		list1 = mergeSort(list1);
    		list2 = mergeSort(list2);
    		return merge(list1,list2);
    	}
    }
   
    
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K, V> results) {
        //ADD CODE BELOW HERE
    	
    	ArrayList<HashPair<K,V>> temp = new ArrayList<HashPair<K,V>>();
    	for(int i = 0; i < results.numBuckets(); i++) {
    		for(int j = 0; j < results.getBuckets().get(i).size(); j++) {
    			temp.add(results.getBuckets().get(i).get(j));
    		}
    	}
    	ArrayList<HashPair<K,V>> temp2 = mergeSort(temp);
    	ArrayList<K> result = new ArrayList<K>();    	
		for(int k = 0; k < temp2.size(); k++) {
			result.add(temp2.get(k).getKey());
		}
		return result;
    	
        //ADD CODE ABOVE HERE
    }

    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        //ADD YOUR CODE BELOW HERE
    	
    	//use a LinkedList filed to store all of the element in the table
    	private LinkedList<HashPair<K,V>> allPairs;
        //ADD YOUR CODE ABOVE HERE
    	/**
    	 * Expected average runtime is O(m) where m is the number of buckets
    	 * @return 
    	 */
        private MyHashIterator(){
            //ADD YOUR CODE BELOW HERE
        	
        	 LinkedList<HashPair<K,V>> pairs = new LinkedList<HashPair<K,V>>();
        	 ArrayList<LinkedList<HashPair<K,V>>> tempBucket = buckets;
        	 for(int i = 0; i < tempBucket.size(); i++) {
        		 for(int j = 0; j < tempBucket.get(i).size(); j++) {
        			 pairs.add(tempBucket.get(i).get(j));
        		 }
        	 }
        	 this.allPairs = pairs;
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	
        	return this.allPairs.size() > 0;
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	return this.allPairs.remove();
        	
            //ADD YOUR CODE ABOVE HERE
        }
	}
}
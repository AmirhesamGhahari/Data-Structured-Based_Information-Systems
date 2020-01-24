import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class HashMap<K, V> {
	
	public static final double LOAD_FACTOR = 0.6;
	public static final int INCREASE_RATE = 2;
	
	private int numOfEntries;
	private int capacity;
	private int prime;
	private int scale;
	private int shift;
	
	private LinkedList<Entry<K, V>>[] mainList;
	
	
	
	
	protected static class Entry<K, V>{
		private K key;
		private V value;
		
		protected Entry(K key1, V value1)
		{
			this.key = key1;
			this.value = value1;
		}
		
		protected void setKey(K key1) {
			this.key = key1;
		}
		protected void setValue(V value1) {
			this.value = value1;
		}
		protected V getValue()
		{
			return this.value;
		}
		protected K getKey()
		{
			return this.key;
		}
		
	}
	
	
	
	public HashMap(int cap, int pr){
		this.capacity = cap;
		this.prime = pr;
		this.numOfEntries = 0;
		 
		Random random = new Random();
		this.shift = random.nextInt(this.prime);
		this.scale = random.nextInt(this.prime) +1;
		
		this.mainList = (LinkedList<Entry<K, V>>[]) new LinkedList[this.capacity];
		
	}
	
	public int getSize() {
		return this.numOfEntries;
	}
	
	public int getCapacity()
	{
		return this.capacity;
	}
	
	public int getPrime() {
		return this.prime;
	}
	
	
	protected int hashValue(K key) {
		int result;
		result = (Math.abs(key.hashCode() * this.scale + this.shift) % this.prime) % this.capacity;
		return result;
	}
	
	public V get(K key) {
		V result = null;
		int hashNum = this.hashValue(key);
		if(mainList[hashNum] == null) {
			result = null;
		}else {
			LinkedList<Entry<K, V>> temp = mainList[hashNum];
			for(Entry<K, V> e1 : temp) {
				K k1 = e1.getKey();
				if(k1.equals(key))
				{
					result = e1.getValue();
				}
			}
		}
		return result;
	}
	
	
	public V remove(K key) {
		V result = null;
		int hashNum = this.hashValue(key);
		if(mainList[hashNum] == null) {
			result = null;
		}else {
			LinkedList<Entry<K, V>> temp = mainList[hashNum];
			for(Entry<K, V> e1 : temp) {
				K k1 = e1.getKey();
				if(k1.equals(key)) {
					result = e1.getValue();
					temp.remove(e1);
					numOfEntries--;
				}
			}
			if(temp.isEmpty())
			{
				mainList[hashNum] = null;
			}
		}
		return result;
	}
	
	
	public V put(K k1, V v1)
	{
		if(k1 == null || v1 == null) 
		{
			throw new IllegalArgumentException("k1 or v1 cant be either null");
		}
		V result = null;
		int hashNum = this.hashValue(k1);
		boolean wasIn = false;
		Entry<K, V> newEntry = new Entry<>(k1, v1);
		
		if(mainList[hashNum] == null) {
			result = v1;
			LinkedList<Entry<K, V>> newList = new LinkedList<>();
			newList.add(newEntry);
			mainList[hashNum] = newList;
			numOfEntries++;
		} else {
			LinkedList<Entry<K, V>> temp = mainList[hashNum];
			
			for(Entry<K, V> e1 : temp) {
				if(e1.getKey().equals(k1)) {
					wasIn = true;
					result = e1.getValue();
					e1.setValue(v1);
				}
			}
			if(!wasIn) {
				numOfEntries++;
				result = v1;
				temp.add(newEntry);
			}
			
		}
		
		if(this.numOfEntries >= (this.capacity * HashMap.LOAD_FACTOR))
		{
			this.resize();
		}
		
		return result;
		
	}
	
	
	private void resize() {
		
		ArrayList<Entry<K, V>> buffer = new ArrayList<HashMap.Entry<K,V>>();
		for(int i = 0; i < this.capacity; i++)
		{
			if(mainList[i] != null) {
				LinkedList<Entry<K, V>> temp = mainList[i];
				for(Entry<K, V> e1 : temp) {
					buffer.add(e1);
				}
			}
		}
		this.capacity = this.capacity * HashMap.INCREASE_RATE;
		this.mainList = (LinkedList<Entry<K, V>>[]) new LinkedList[this.capacity];
		
		for(Entry<K, V> kv1 : buffer) {
			this.specialPut(kv1.getKey(), kv1.getValue());
		}
		
	}
	
	public void specialPut(K k1, V v1)
	{
		
		int hashNum = this.hashValue(k1);
		Entry<K, V> newEntry = new Entry<>(k1, v1);
		
		
		if(mainList[hashNum] == null) {
			LinkedList<Entry<K, V>> newList = new LinkedList<>();
			newList.add(newEntry);
			mainList[hashNum] = newList;
		} else {
			LinkedList<Entry<K, V>> temp = mainList[hashNum];		
			temp.add(newEntry);
			
		}
		
	}
	
	
	
	

}

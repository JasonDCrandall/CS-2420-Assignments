package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a Hash Table that creates a table
 * with a generic type K and a generic value V
 * 
 * @author Jason Crandall & Michael Hart
 * @version March 28, 2019
 */
public class HashTable<K, V> implements Map<K, V> {

	private int size = 0;
	private ArrayList<LinkedList<MapEntry<K, V>>> table = new ArrayList<LinkedList<MapEntry<K, V>>>();
	private int capacity = 100;
	private int maxLoadFactor = 10;
	private int collisionCount = 0;
	
	/**
	 * Constructor for the hash table
	 */
	public HashTable() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
	}
	
	@Override
	public void clear() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
			   table.add(new LinkedList<MapEntry<K, V>>());
		size = 0;
	}

	@Override
	public boolean containsKey(K key) {
		return find(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		for(LinkedList<MapEntry<K, V>> chain : table) {
			for(MapEntry<K, V> entry : chain) {
				if(value.equals(entry.getValue()))
					return true;
			}
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		ArrayList<MapEntry<K, V>> entries = new ArrayList<MapEntry<K, V>>();
		for(LinkedList<MapEntry<K, V>> chain : table) {
			entries.addAll(chain);
		}
		return entries;
	}

	@Override
	public V get(K key) {
		MapEntry<K, V> entry = find(key);
		if(entry != null)
			return entry.getValue();
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		MapEntry<K, V> entry = find(key);
		if (entry != null) {
			V oldValue = entry.getValue();
			entry.setValue(value);
			return oldValue;
		}
		if (size/capacity > maxLoadFactor) {
			growTable();
		}
		
		LinkedList<MapEntry<K, V>> chain = table.get(getHash(key));
		
		chain.add(new MapEntry<K, V>(key, value));
		size++;
		
		return null;
	}

	

	@Override
	public V remove(K key) {
		MapEntry<K, V> entry = find(key);
		if (entry == null) {
			return null;
		}
		table.get(getHash(key)).remove(entry);
		size--;
		
		return entry.getValue();
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Using the hash code, this method searches the table for the correct linkedlist to 
	 * traverse, and then finds the map entry with the matching key
	 * @param key
	 * @return Map Entry if it is in the list and null otherwise
	 */
	private MapEntry<K, V> find(K key) {
		int hash = getHash(key);
		LinkedList<MapEntry<K, V>> chain = table.get(hash);
		for (MapEntry<K, V> entry : chain) {
			collisionCount++;
			if(entry.getKey().equals(key)) {
				return entry;
			}
		}
		return null;
	}
	
	/**
	 * Method that calculates the hash code for a function and fits it into the 
	 * table using the modulus function
	 * @param key 
	 * @return int - hash code value
	 */
	private int getHash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/**
	 * Grows the table by putting all to the map entries into a list,
	 * doubling the original capacity, and then rehashing all of the
	 * entries in that list into the table
	 */
	private void growTable() {
		setCollisionCount(0);
		List<MapEntry<K, V>> entries = this.entries();
		capacity *= 2;
		size = 0;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < capacity; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
		for (MapEntry<K, V> entry : entries) {
			this.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Getter for the number of collisions
	 * @return collisionCount
	 */
	public int getCollisionCount() {
		return collisionCount;
	}

	/**
	 * Setter for the number of collisions
	 * @param collisionCount
	 */
	public void setCollisionCount(int collisionCount) {
		this.collisionCount = collisionCount;
	}

}

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Implementation of a simple LRU Cache.
 * Won't compile until you complete it...
 * @param <K> the type of keys 
 * @param <V> the type of values
 */
public class Cache<K,V> extends LinkedHashMap<K,V> {
	private int capacity;
	private long hits;
	private long lookups;

	public Cache(int cacheSize)
	{
		super(cacheSize+1, 0.75f, true); //use accessing order
		this.capacity = cacheSize;
		hits = 0;
		lookups = 0;
	}
	@Override
	public V get(Object key)
	{
		lookups++;
		if (super.get(key) != null)
		{
			hits++;
		}
		return super.get(key);

	}
	@Override
	public V put (K key, V value) 
	{
	    return super.put(key, value);
	}

	public long getHits()
	{
		return this.hits;
	}

	public long getLookups()
	{
		return this.lookups;
	}

	public void clear()
	{
		super.clear();
		this.lookups = 0;
		this.hits = 0;
	}

	public double getHitRatio()
	{
		if (this.hits> 0 && this.lookups > 0)
			return (double) (((double) this.hits) / ((double) this.lookups));
		else
			return 0;
	}

	/**
    * removeEldestEntry() is overridden to remove the oldest object.
    */
   @Override
   protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
      return this.size() > this.capacity;
   }
}

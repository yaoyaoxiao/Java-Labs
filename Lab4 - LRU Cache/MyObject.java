import java.math.BigInteger;


public class MyObject implements Element
{
	
	private Long key;
	private BigInteger data;
	
	public MyObject (Long key, BigInteger data)
	{
		this.key = key;
		this.data = data;
	}
	
	public Comparable getKey()
	{
		return key;
	}
	
	public Object getValue() 
	{
		return data;
	}
	
	public int compareTo(Object obj)
	{
		if (obj instanceof MyObject) {
			MyObject other = (MyObject) obj;
			Long otherKey = (Long) other.getKey();
			return key.compareTo(otherKey);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String toString()
	{
		return key+":"+data;
	}
}

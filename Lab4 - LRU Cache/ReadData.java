import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;


/**
 * Illustrates two different ways of reading a binary data file
 * @author amit
 *
 */
public class ReadData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("Usage: java ReadData <n> <data file>");
			System.exit(1);
		}
		
		int n = Integer.parseInt(args[0]);
		readRandomAccessFile(n, args[1]);
		System.out.println();
		readBinaryFile(n, args[1]);
		
	}
	
	/**
	 * Shows how to access a binary data file as a RandomAccessFile and seek to an arbitrary location.
	 * @param n
	 * @param filename
	 */
	private static void readRandomAccessFile(int n, String filename)
	{
		try {
			RandomAccessFile in = new RandomAccessFile(filename, "r");

			// go read a random object from the data file
			long index = 10; //an arbitrary choice for the sake of an example
			in.seek(index * (GlobalConstants.DATA_LENGTH + GlobalConstants.KEY_LENGTH + 1));
			long key = in.readLong();
			byte[] buffer = new byte[GlobalConstants.DATA_LENGTH+1];
			in.read(buffer);
			MyObject obj = new MyObject(new Long(key), new BigInteger(buffer));
			System.out.println(obj);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Shows how to read a binary data file sequentially using DataInputStream
	 * @param n
	 * @param filename
	 */
	private static void readBinaryFile(int n, String filename) {
	
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(filename));
			for (int i=0; i<n; i++) {
				long key = in.readLong();
				byte[] buffer = new byte[GlobalConstants.DATA_LENGTH+1];
				in.read(buffer);
				MyObject obj = new MyObject(new Long(key), new BigInteger(buffer));
				System.out.println(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

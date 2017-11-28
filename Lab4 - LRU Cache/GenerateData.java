import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 * 
 */

/**
 * @author amit
 *
 */
public class GenerateData {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Usage: java GenerateData <n> <data file> [<seed>]");
			System.exit(1);
		}
		
		int n = Integer.parseInt(args[0]);
		long seed = 0;
		if (args.length ==3) {
			seed = Long.parseLong(args[2]);
		}
		
		generateBinaryFile(n, args[1], seed);
	}

	private static void generateBinaryFile(int n, String filename, long seed) {
		
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
			Random generator;
			if (seed == 0) {
				generator = new Random();
			} else {
				generator = new Random(seed);
			}
		
			BigInteger value = BigInteger.probablePrime(GlobalConstants.DATA_LENGTH * 8, generator);
			byte[] buffer = value.toByteArray();		
			long offset = 0;
			
			for (int i=0; i<n; i++) {
				out.writeLong(i);
				// For ease in testing, we write out the data to be the same as offset
				// In a real app, we would have an object here of some kind
				out.write(buffer, 0, buffer.length);
				
				offset += GlobalConstants.KEY_LENGTH + GlobalConstants.DATA_LENGTH + 1;
				value = value.add(BigInteger.ONE);
				buffer  = value.toByteArray();
				System.out.println(i + " " + buffer.length);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

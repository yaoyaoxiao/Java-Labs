

import java.util.Random;

/**
 * Show how to generate half a normal distribution using Random class
 * To see the distribution better, try the following command
 * 
 * 	java HalfNormal 10000 100 | sort -n | uniq -c
 * 
 * You can also redirect it to a file and plot it using a spreadsheet or gnuplot
 * 
 * 	java HalfNormal 10000 100 | sort -n | uniq -c > data
 * 
 * Then startup gnuplot
 * 
 * 	gnuplot
 * 
 * and type
 * 
 * 	plot "data"
 * 
 * @author amit
 *
 */
public class HalfNormal
{


	public static void main(String args[])
	{
		if (args.length != 2) {
			System.err.println("Usage: java HalfNormal <number of data points> <standard deviation>");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		int range = Integer.parseInt(args[1]);

		Random generator = new Random();
		for (int i=0; i<n; i++) {
			long next = (long) Math.floor(generator.nextGaussian()*range);
			System.out.println(Math.abs(next));
		}
	}

}

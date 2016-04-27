package java5.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * Simplify the following code using new java5 features: 
 * - Generic Types 
 * - Autoboxing 
 * - Static import
 * - varargs 
 * - foreach
 */
public class Exercise1 {

	public static void main(String[] args) {

		List doubles = getList(new double[] { 1.1, 2.5, 4.5, 5.5 });

		List longs = new ArrayList();

		// Transform doubles to long and add them to longs List
		for (int i = 0; i < doubles.size(); i++) {
			Double num = (Double) doubles.get(i);
			long rounded = Math.round(num.doubleValue());
			longs.add(new Long(rounded));
		}

		// Print longs list
		for (int i = 0; i < longs.size(); i++) {
			Long num = (Long) longs.get(i);
			System.out.println(num);
		}
	}

	/**
	 * Transform values to a List of Doubles
	 * @param values
	 * @return
	 */
	private static List getList(double[] values) {
		List doubles = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			doubles.add(new Double(values[i]));
		}
		return doubles;
	}
}

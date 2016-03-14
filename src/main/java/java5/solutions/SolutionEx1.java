package main.java.java5.solutions;

import static java.lang.Math.round;

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
public class SolutionEx1 {

	public static void main(String[] args) {

		SolutionEx1.compute();
	}

	public static void compute() {
		List<Double> doubles = getList(1.1, 2.5, 4.5, 5.5);

		List<Long> longs = new ArrayList<Long>();

		for (Double num : doubles) {
			longs.add(round(num.doubleValue()));
		}

		for (Long num : longs) {
			System.out.println(num);
		}
	}

	private static List<Double> getList(double... values) {
		List<Double> doubles = new ArrayList<Double>();
		for (Double num : doubles) {
			doubles.add(num);
		}
		return doubles;
	}
}

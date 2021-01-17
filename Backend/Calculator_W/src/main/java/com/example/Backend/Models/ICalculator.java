package com.example.Backend.Models;

/**
 * An Interface for a calculator includes basic math operations
 */
public interface ICalculator {
	/**
	 * perform one operation of(+ , - , / , *)
	 * 
	 * @param first    ,the first number
	 * @param second   ,the second number
	 * @param operator ,of the chosen operation
	 * @return the result
	 */
	String Calculate(double first, double second, String operator);

	/**
	 * Return the reminder of a number
	 * 
	 * @param num ,the number
	 * @return the result
	 */
	String reminder(double num);

	/**
	 * Return the root of a number
	 * 
	 * @param num, the number
	 * @return the result
	 */
	String root(double num);

	/**
	 * Return the square of a number
	 * 
	 * @param num, the number
	 * @return the result
	 */
	String square(double num);

	/**
	 * Return 1/number
	 * 
	 * @param num, the number
	 * @return the result
	 */
	String oneOverX(double num);

}
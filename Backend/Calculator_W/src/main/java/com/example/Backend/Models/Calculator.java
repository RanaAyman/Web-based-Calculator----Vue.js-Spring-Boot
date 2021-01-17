package com.example.Backend.Models;

import java.math.BigDecimal;

public class Calculator implements ICalculator {

	/*
	 * Calculate method is used to calculate the result of an operation of 4
	 * operations (+, -,* ,/) , this method has 3 parameters first and second
	 * operand and the operator of the operation.
	 */
	@Override
	public String Calculate(double first, double second, String operator) {
		if (operator.equals("+")) {
			return String.valueOf(first + second);
		} else if (operator.equals("-")) {
			return String.valueOf(first - second);
		} else if (operator.equals("*")) {
			return String.valueOf(first * second);
		} else if (operator.equals("/")) {
			// To Handle case of dividing zero by zero OR any number by zero.
			if (second == 0) {
				if (first == 0) {
					return "Result is undefined !";
				} else {
					return "Cannot divide by zero !";
				}
			}
			return String.valueOf(first / second);
		}
		return "Not supported operation !";
	}

	/*
	 * reminder method is used to calculate the reminder of a number. by dividing
	 * this number by 100.
	 */
	@Override
	public String reminder(double num) {
		if (num == 0) {
			return String.valueOf(0);
		}
		return String.valueOf(num / 100);
	}

	/*
	 * root method is used to calculate the root of a number.
	 */
	@Override
	public String root(double num) {
		// To Handle the square root of -ve numbers.
		if (num < 0)
			return "Invalid input !";
		return String.valueOf(Math.sqrt(num));
	}

	/*
	 * square method is used to calculate the square of a number.
	 */
	@Override
	public String square(double num) {
		return String.valueOf(Math.pow(num, 2));
	}

	/*
	 * oneOverX method is used to calculate 1/number.
	 */
	@Override
	public String oneOverX(double X) {
		// To Handle case of 1/zero .
		if (X == 0)
			return "Cannot divide by zero !";
		return String.valueOf(1 / X);
	}
}
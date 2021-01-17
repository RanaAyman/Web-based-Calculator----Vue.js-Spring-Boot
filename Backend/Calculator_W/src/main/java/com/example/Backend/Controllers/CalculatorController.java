package com.example.Backend.Controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.Models.Calculator;

@SpringBootApplication
@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:8080") // URL of vue project
public class CalculatorController {

	/*
	 * Calculate method is used to get the result of an operation that has been
	 * chosen by a string containing the operation's operator. this method is
	 * dealing with 4 operations (+ , - , / ,*). and has 3 parameters : firstNumber
	 * ,secondNumber & Operator.
	 */
	@GetMapping("/Calculate")
	public String Calculate(@RequestParam(value = "firstNumber", defaultValue = "0") double firstNumber,
			@RequestParam(value = "secondNumber", defaultValue = "0") double secondNumber,
			@RequestParam(value = "operator") String operator) {
		Calculator calculator = new Calculator();
		String result = calculator.Calculate(firstNumber, secondNumber, operator);
		return result;
	}

	/*
	 * reminder method is used to get the reminder of a Number. this method has 1
	 * parameter : Number.
	 */
	@GetMapping("/reminder")
	public String reminder(@RequestParam(value = "Number", defaultValue = "0") double Number) {
		Calculator calculator = new Calculator();
		String result = calculator.reminder(Number);
		return result;
	}

	/*
	 * root method is used to get the square root of a Number. this method has 1
	 * parameter : Number.
	 */
	@GetMapping("/root")
	public String root(@RequestParam(value = "Number", defaultValue = "0") double Number) {
		Calculator calculator = new Calculator();
		String result = calculator.root(Number);
		return result;
	}

	/*
	 * square method is used to get the square of a Number. this method has 1
	 * parameter : Number.
	 */
	@GetMapping("/square")
	public String square(@RequestParam(value = "Number", defaultValue = "0") double Number) {
		Calculator calculator = new Calculator();
		String result = calculator.square(Number);
		return result;
	}

	/*
	 * OneOverX method is used to get 1/Number of a Number. this method has 1
	 * parameter : Number.
	 */
	@GetMapping("/OneOverX")
	public String OneOverX(@RequestParam(value = "Number", defaultValue = "0") double Number) {
		Calculator calculator = new Calculator();
		String result = calculator.oneOverX(Number);
		return result;
	}

}

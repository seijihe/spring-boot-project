package com.seijihe.rest_with_spring_boot.service;

import com.seijihe.rest_with_spring_boot.exception.UnsupportedMathOperationException;
import com.seijihe.rest_with_spring_boot.math.MathOperations;
import com.seijihe.rest_with_spring_boot.math.NumberConverter;

public class MathService {
    
    MathOperations operation = new MathOperations();

    public Double sum(String firstNumber, String secondNumber) {
        if (!NumberConverter.isNumeric(firstNumber) || !NumberConverter.isNumeric(secondNumber))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return operation.sum(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    public Double subtraction(String firstNumber, String secondNumber) {
        if (!NumberConverter.isNumeric(firstNumber) || !NumberConverter.isNumeric(secondNumber))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return operation.subtraction(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    public Double multiplication(String firstNumber, String secondNumber) {
        if (!NumberConverter.isNumeric(firstNumber) || !NumberConverter.isNumeric(secondNumber))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return operation.multiplication(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    public Double division(String firstNumber, String secondNumber) {
        if (!NumberConverter.isNumeric(firstNumber) || !NumberConverter.isNumeric(secondNumber))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return operation.division(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    public Double mean(String firstNumber, String secondNumber) {
        if (!NumberConverter.isNumeric(firstNumber) || !NumberConverter.isNumeric(secondNumber))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return operation.mean(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    public Double squareRoot(String number) {
        if (!NumberConverter.isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return operation.squareRoot(NumberConverter.convertToDouble(number));
    }
}

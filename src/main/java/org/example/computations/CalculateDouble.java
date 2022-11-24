package org.example.computations;

public class CalculateDouble implements Calculator<Double>{
    @Override
    public Double add(Double operandOne, Double operandTwo) {
        return operandOne + operandTwo;
    }

    @Override
    public Double subtract(Double operandOne, Double operandTwo) {
        return operandOne - operandTwo;
    }

    @Override
    public Double multiply(Double operandOne, Double operandTwo) {
        return operandOne * operandTwo;
    }

    @Override
    public Double divide(Double operandOne, Double operandTwo) {
        return operandOne / operandTwo;
    }
}

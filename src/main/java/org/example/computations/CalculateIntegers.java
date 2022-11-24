package org.example.computations;

public class CalculateIntegers implements Calculator<Integer>{

    public Integer add(Integer operandOne, Integer operandTwo) {
        return operandOne + operandTwo;
    }

    @Override
    public Integer subtract(Integer operandOne, Integer operandTwo) {
        return operandOne - operandTwo;
    }

    @Override
    public Integer multiply(Integer operandOne, Integer operandTwo) {
        return operandOne * operandTwo;
    }

    @Override
    public Integer divide(Integer operandOne, Integer operandTwo) {
        return operandOne / operandTwo;
    }
}

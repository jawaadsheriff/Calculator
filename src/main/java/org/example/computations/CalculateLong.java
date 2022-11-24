package org.example.computations;

public class CalculateLong implements Calculator<Long>{
    @Override
    public Long add(Long operandOne, Long operandTwo) {
        return operandOne + operandTwo;
    }

    @Override
    public Long subtract(Long operandOne, Long operandTwo) {
        return operandOne - operandTwo;
    }

    @Override
    public Long multiply(Long operandOne, Long operandTwo) {
        return operandOne * operandTwo;
    }

    @Override
    public Long divide(Long operandOne, Long operandTwo) {
        return operandOne / operandTwo;
    }
}

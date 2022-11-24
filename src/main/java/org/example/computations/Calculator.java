package org.example.computations;

public interface Calculator <T extends Number> {
    public T add(T operandOne, T operandTwo);
    public T subtract(T operandOne, T operandTwo);
    public T multiply(T operandOne, T operandTwo);
    public T divide(T operandOne, T operandTwo);
}

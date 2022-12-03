package org.example;

import org.example.computations.CalculateDouble;
import org.example.computations.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    public static final String[][] BTN_TEXTS = {
            {"C","DEL","%","+/-"},
            {"7","8","9","*"},
            {"4","5","6","-"},
            {"1","2","3","+"},
            {".","0","/","="}};
    JFrame jFrame = new JFrame("Calculator");
    JPanel jPanel1 = new JPanel(new GridLayout(2,1));
    JPanel jPanel2 = new JPanel(new GridLayout(5,4));
    JButton[] btn = new JButton[10];
    JTextField jTextField = new JTextField();
    JTextArea jTextArea = new JTextArea();
    private Double result = null;
    private Double operandOne = null;
    private Double operandTwo= null;
    private char operator = ' ';
    private String calcHistory = "";
    private boolean clearFlag = false;
    int counter = 0;
    public Main(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 300);
        for (String[] btnText : BTN_TEXTS) {
            for (String s : btnText) {
                switch (s) {
                    case "+", "-", "*", "/", "=", ".", "C", "DEL", "+/-", "%" -> arrangeOperatorButtons(s);
                    default -> arrangeNumberButtons(s);
                }
            }
        }
        jTextField.setEditable(false);
        jTextArea.setEditable(false);
        jPanel1.add(jTextArea);
        jPanel1.add(jTextField);
        jFrame.getContentPane().add(BorderLayout.NORTH, jPanel1);
        jFrame.getContentPane().add(BorderLayout.CENTER, jPanel2);
        jFrame.setVisible(true);
    }

    public void arrangeNumberButtons(String buttonText){
        int index = Integer.parseInt(buttonText);
        btn[index] = new JButton(buttonText);
        jPanel2.add(btn[index]);
        btn[index].addActionListener(this);
    }

    public void arrangeOperatorButtons(String buttonText){
        JButton jbutton = new JButton(buttonText);
        jPanel2.add(jbutton);
        jbutton.addActionListener(this);
    }

    public void compute(){
        /*if(operator != '='){
            calcHistory += operator;
            jTextField.setText("");
            jTextArea.setText(calcHistory);
        }*/
        //Double operand = Double.parseDouble(operandOne);
        Calculator<Double> doubleObj = new CalculateDouble();
        switch (operator) {
            case '+' -> {
                result = doubleObj.add(operandOne, operandTwo);
                System.out.println(result);
            }
            //operand = null;
            case '-' -> {
                result = doubleObj.subtract(operandOne, operandTwo);
                jTextField.setText(result.toString());
            }
            //operand = null;
            case '*' -> {
                result = doubleObj.multiply(operandOne, operandTwo);
                jTextField.setText(result.toString());
            }
            //operand = null;
            case '/' -> {
                result = doubleObj.divide(operandOne, operandTwo);
                jTextField.setText(result.toString());
            }
            //operand = null;
        }
    }

    public void checkCounterAndCompute(){
        if(counter == 0){
            result = operandOne;
            operandOne = null;
            counter++;
            //calcHistory += operator;
            jTextField.setText("");
            //jTextArea.setText(calcHistory);
            System.out.println(result);
        }
        else{
            compute();
            System.out.println(result);
        }
    }

    public void setCalcHistory(String text){
        calcHistory += text;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        System.out.println(pressedButton.toString());
        String input = pressedButton.getText();
        /*if(operandOne != null && operator != ' ' && nextOperator != ' '){
            checkCounterAndCompute(operator);
            operator = nextOperator;
            nextOperator = ' ';
        }*/
        if(input.equals("+")){
            String text = jTextField.getText();
            if(operandOne == null && !text.equals("")){
                operator = '+';
                setCalcHistory(text);
                operandOne = Double.parseDouble(text);
                setCalcHistory(String.valueOf(operator));
                jTextArea.setText(calcHistory);
                jTextField.setText("");
                //counter++;
            }
            else if(operandTwo == null && !text.equals("")){
                if(result == null){
                    operandTwo = Double.parseDouble(text);
                }
                else{
                    operandOne = result;
                    operandTwo = Double.parseDouble(text);
                }
                compute();
                jTextField.setText(result.toString());
                operandTwo = null;
                setCalcHistory(text);
                operator = '+';
                setCalcHistory(String.valueOf(operator));
                jTextArea.setText(calcHistory);
                clearFlag = true;
            }
        }
        else if(input.equals("-")){
            operator = '-';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        else if(input.equals("*")){
            operator = '*';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        else if(input.equals("/")){
            operator = '/';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        else if(input.equals("=")){
            //calcHistory += text;
            jTextArea.setText(calcHistory);
            checkCounterAndCompute();
            jTextField.setText(result.toString());
        }
        else if(input.equals("C")){
            jTextArea.setText("");
            jTextField.setText("");
            result = 0d;
            operandOne = null;
            operandTwo = null;
            operator = ' ';
            calcHistory = "";
            counter = 0;
        }
        else if(input.equals(".")){
            String text = jTextField.getText();
            if(!text.contains(".")){
                text += input;
                jTextField.setText(text);
                setCalcHistory(input);
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                if (clearFlag) {
                    jTextField.setText("");
                    clearFlag = false;
                }
                if (input.equals(btn[i].getText())) {
                    String text = jTextField.getText();
                    text += input;
                    jTextField.setText(text);
                    //setCalcHistory(input);
                    //operand = Double.parseDouble(text);
                }
            }
        }
    }

    public static void main(String[] args) {
       /* Calculator<Integer> obj = new CalculateIntegers();
        Calculator<Long> longObj = new CalculateLong();
        Calculator<Double> doubleObj = new CalculateDouble();
        System.out.println(obj.add(10, 20));
        System.out.println(doubleObj.subtract(20.0, 10.0));
        System.out.println(longObj.add(10L, 20L));
        System.out.println(longObj.multiply(10L, 20L));
        System.out.println(doubleObj.add(10.0, 20.0));
        System.out.println(doubleObj.divide(18.0, 3.0));*/
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        new Main();
    }
}
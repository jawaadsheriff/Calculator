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
    private boolean clearTextFieldFlag = false;
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
        Calculator<Double> doubleObj = new CalculateDouble();
        switch (operator) {
            case '+' -> result = doubleObj.add(operandOne, operandTwo);
            case '-' -> result = doubleObj.subtract(operandOne, operandTwo);
            case '*' -> result = doubleObj.multiply(operandOne, operandTwo);
            case '/' -> result = doubleObj.divide(operandOne, operandTwo);
        }
    }

    public void setCalcHistory(String text){
        calcHistory += text;
        //System.out.println(calcHistory);
    }

    public void deleteOperatorInCalcHistory(){
        if (calcHistory.length() > 0){
            String lastCharacter = String.valueOf(calcHistory.charAt(calcHistory.length() - 1));
            if (lastCharacter.matches("[^0-9]")) {
                calcHistory = calcHistory.substring(0, calcHistory.length() - 1);
            }
        }
    }
    public void performOperation(char clickedOperator){
        String text = jTextField.getText();
        if(operator == ' ' && text.equals("")){
            operator = clickedOperator;
            setCalcHistory(String.valueOf(operator));
        }
        else if(operandOne == null && !text.equals("")){
            operator = clickedOperator;
            setCalcHistory(text);
            operandOne = Double.parseDouble(text);
            setCalcHistory(String.valueOf(operator));
            jTextArea.setText(calcHistory);
            jTextField.setText("");
            //counter++;
        }
        else if(operandTwo == null && !text.equals("")){
            if (result != null) {
                operandOne = result;
            }
            operandTwo = Double.parseDouble(text);
            compute();
            jTextField.setText(result.toString());
            operandTwo = null;
            setCalcHistory(text);
            operator = clickedOperator;
            setCalcHistory(String.valueOf(operator));
            jTextArea.setText(calcHistory);
            clearTextFieldFlag = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        //System.out.println(pressedButton.toString());
        String input = pressedButton.getText();
        if(input.equals("+")){
            performOperation('+');
        }
        else if(input.equals("-")){
            performOperation('-');
        }
        else if(input.equals("*")){
            performOperation('*');
        }
        else if(input.equals("/")){
            performOperation('/');
        }
        else if(input.equals("=")){
            performOperation('=');
            jTextArea.setText(calcHistory);
            jTextField.setText(result.toString());
            operandOne = null;
            operandTwo = null;
            result = null;
            operator = ' ';
            clearTextFieldFlag = false;
        }
        else if(input.equals("C")){
            jTextArea.setText("");
            jTextField.setText("");
            result = null;
            operandOne = null;
            operandTwo = null;
            operator = ' ';
            calcHistory = "";
            clearTextFieldFlag = false;
        }
        else if(input.equals(".")){
            String text = jTextField.getText();
            if(!text.contains(".")){
                text += input;
                jTextField.setText(text);
                setCalcHistory(input);
            }
        }
        else if(input.equals("DEL")){
            if(!jTextField.getText().equals("")){
                jTextField.setText("");
            }
            else if(jTextField.getText().equals("")){
                deleteOperatorInCalcHistory();
                jTextArea.setText(calcHistory);
                operator = ' ';
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                if (clearTextFieldFlag) {
                    jTextField.setText("");
                    clearTextFieldFlag = false;
                }
                if (input.equals(btn[i].getText())) {
                    String text = jTextField.getText();
                    text += input;
                    jTextField.setText(text);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        new Main();
    }
}
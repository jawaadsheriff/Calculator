package org.example;

import org.example.computations.CalculateDouble;
import org.example.computations.CalculateIntegers;
import org.example.computations.CalculateLong;
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
    JButton addButton;
    JButton subButton;
    JButton multButton;
    JButton divButton;
    JButton decimalButton;
    JButton equalButton;
    JButton clearButton;
    JButton deleteButton;
    JButton plusMinusButton;
    JButton moduloButton;
    JTextField jTextField = new JTextField();
    JTextArea jTextArea = new JTextArea();
    Double result = 0d;
    Double operand = null;
    char operator = ' ';
    char nextOperator = ' ';
    String calcHistory = "";
    int counter = 0;
    public Main(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 300);
        for(int i = 0; i < BTN_TEXTS.length; i++){
            for(int j = 0;j < BTN_TEXTS[i].length; j++){
                if(BTN_TEXTS[i][j] == "+"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], addButton);
                }
                else if(BTN_TEXTS[i][j] == "-"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], subButton);
                }
                else if(BTN_TEXTS[i][j] == "*"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], multButton);
                }
                else if(BTN_TEXTS[i][j] == "/"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], divButton);
                }
                else if(BTN_TEXTS[i][j] == "="){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], equalButton);
                }
                else if(BTN_TEXTS[i][j] == "."){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], decimalButton);
                }
                else if(BTN_TEXTS[i][j] == "C"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], clearButton);
                }
                else if(BTN_TEXTS[i][j] == "DEL"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], deleteButton);
                }
                else if(BTN_TEXTS[i][j] == "+/-"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], plusMinusButton);
                }
                else if(BTN_TEXTS[i][j] == "%"){
                    arrangeOperatorButtons(BTN_TEXTS[i][j], moduloButton);
                }
                else{
                    arrangeNumberButtons(BTN_TEXTS[i][j]);
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

    public void arrangeOperatorButtons(String buttonText, JButton jbutton){
        jbutton = new JButton(buttonText);
        jPanel2.add(jbutton);
        jbutton.addActionListener(this);
    }

    public void compute(char operator){
        /*if(operator != '='){
            calcHistory += operator;
            jTextField.setText("");
            jTextArea.setText(calcHistory);
        }*/
        //Double operand = Double.parseDouble(operandOne);
        Calculator<Double> doubleObj = new CalculateDouble();
        switch (this.operator){
            case '+':
                result = doubleObj.add(result, operand);
                jTextField.setText(result.toString());
                operand = null;
                break;
            case '-':
                result = doubleObj.subtract(result, operand);
                jTextField.setText(result.toString());
                operand = null;
                break;
            case '*':
                result = doubleObj.multiply(result,operand);
                jTextField.setText(result.toString());
                operand = null;
                break;
            case '/':
                result = doubleObj.divide(result, operand);
                jTextField.setText(result.toString());
                operand = null;
                break;
        }
    }

    public void checkCounterAndCompute(char operator){
        if(counter == 0){
            result = operand;
            operand = null;
            counter++;
            //calcHistory += operator;
            jTextField.setText("");
            //jTextArea.setText(calcHistory);
            System.out.println(result);
        }
        else{
            compute(operator);
            System.out.println(result);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        System.out.println(pressedButton.toString());
        if(operand != null && operator != ' ' && nextOperator != ' '){
            checkCounterAndCompute(operator);
            operator = nextOperator;
            nextOperator = ' ';
        }
        if(pressedButton.getText().equals("+")){
            operator = '+';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        if(pressedButton.getText().equals("-")){
            operator = '-';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        if(pressedButton.getText().equals("*")){
            operator = '*';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        if(pressedButton.getText().equals("/")){
            operator = '/';
            jTextField.setText("");
            calcHistory += operator;
            jTextArea.setText(calcHistory);
        }
        if(pressedButton.getText() == "="){
            //calcHistory += text;
            jTextArea.setText(calcHistory);
            checkCounterAndCompute('=');
            jTextField.setText(result.toString());
        }
        if(pressedButton.getText() == "C"){
            jTextArea.setText("");
            jTextField.setText("");
            result = 0d;
            operand = null;
            operator = ' ';
            calcHistory = "";
            counter = 0;
        }
        for(int i = 0; i < 10; i++) {
            if (pressedButton == btn[i]) {
                String text = jTextField.getText();
                calcHistory += pressedButton.getText();
                text += pressedButton.getText();
                jTextField.setText(text);
                operand = Double.parseDouble(text);
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
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        new Main();
    }
}
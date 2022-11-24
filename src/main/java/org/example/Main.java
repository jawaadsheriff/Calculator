package org.example;

import org.example.computations.CalculateDouble;
import org.example.computations.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    public static final String[][] BTN_TEXTS = {
                    {"7","8","9","*"},
                    {"4","5","6","-"},
                    {"1","2","3","+"},
                    {".","0","/","="}};
    JFrame jFrame = new JFrame("Calculator");
    JPanel jPanel1 = new JPanel(new GridLayout(2,1));
    JPanel jPanel2 = new JPanel(new GridLayout(4,4));
    JButton[] btn = new JButton[10];
    JButton addButton;
    JButton subButton;
    JButton multButton;
    JButton divButton;
    JButton decimalButton;
    JButton equalButton;
    JTextField jTextField = new JTextField();
    JTextArea jTextArea = new JTextArea();
    Double result = 0d;
    String calcHistory = "";
    public Main(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(200, 200);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        System.out.println(pressedButton.toString());
        String text = jTextField.getText();
        if(pressedButton.getText() == "+"){
            jTextField.setText("");
            calcHistory += "+";
            jTextArea.setText(calcHistory);
            //System.out.println(result);
            Double operandOne = Double.parseDouble(text);
            Calculator<Double> doubleObj = new CalculateDouble();
            result = doubleObj.add(result, operandOne);
            //jTextField.setText(result.toString());
            //System.out.println(result);
        }
        if(pressedButton.getText() == "="){
            jTextField.setText(result.toString());
        }
        for(int i = 0; i < 10; i++) {
            if (pressedButton == btn[i]) {
                calcHistory += pressedButton.getText();
                text += pressedButton.getText();
                jTextField.setText(text);
            }
        }
    }

    public static void main(String[] args) {
        /*Calculator<Integer> obj = new CalculateIntegers();
        Calculator<Long> longObj = new CalculateLong();
        Calculator<Double> doubleObj = new CalculateDouble();
        System.out.println(obj.add(10, 20));
        System.out.println(obj.subtract(20, 10));
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
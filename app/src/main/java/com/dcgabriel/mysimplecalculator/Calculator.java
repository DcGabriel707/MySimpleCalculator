package com.dcgabriel.mysimplecalculator;

import android.util.Log;
import java.util.ArrayList;


//Model
public class Calculator {
    private static final String TAG = "Calculator";
    private ArrayList<Double> operandList;
    private ArrayList<String> operatorList;
    private String expression;
    private String tempOperand; //temporary operand.
    private String result;

    public Calculator() {
        operandList = new ArrayList<>();
        operatorList = new ArrayList<>();
        expression = "";
        tempOperand = "";
        result = "";
    }

    public String getTempOperand() {
        return tempOperand;
    }

    public String getExpression() {
        return expression;
    }

    public String getResult() {
        return result;
    }

    public ArrayList<Double> getOperandList(){
        return operandList;
    }

    public void setTempOperand(String tempOperand) {
        this.tempOperand = tempOperand;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //adds a number to a temporary operand.  allows user to input a number to the operand before it is pushed into the operand list for calculation
    public void appendTempOperand(String tempOperand) {
        this.tempOperand += tempOperand;
        Log.d(TAG, "appendTempOperand: tempOperand=" + tempOperand);
    }

    //adds a number or an operator to the expression string
    public void appendExpression(String temp) {
        this.expression += temp;
    }

    //adds an operand to the operands queue list
    public void pushOperand(double operand) {
        operandList.add(operand);
    }

    //adds an operator to the operators queue list
    public void pushOperator(String operator) {
        operatorList.add(operator);
    }


    //calculates the entire expression and returns the result
    public double equals() {
        double result = 0;
        double operand1 = operandList.get(0);
        Log.d(TAG, "equals:" + operandList.toString());
        for (int i = 0; i < operandList.size() - 1; i++) {
            String operator = operatorList.get(i);
            double operand2 = operandList.get(i + 1);
            Log.d(TAG, "equals: operand1=" + operandList.get(0) + " operand2=" + operand2 + " operator" + operator);
            operand1 = evaluate(operand1, operand2, operator);
        }

        result = operand1;
        setResult(Double.toString(result));
        return result;
    }

    //calculates the result of operand1 and operand2 depending on the operator
    private double evaluate(double operand1, double operand2, String operator) {
        double initialResult = 0;
        switch (operator) {
            case "+":
                initialResult = operand1 + operand2;
                break;
            case "-":
                initialResult = operand1 - operand2;
                break;
            case "*":
                initialResult = operand1 * operand2;
                break;
            case "/":
                initialResult = operand1 / operand2;
                break;
        }

        return initialResult;
    }

    //deletes the previous character in operand or the expression
    public void delete() {
        StringBuilder string = new StringBuilder(tempOperand);
        string.deleteCharAt(tempOperand.length() - 1);
        tempOperand = string.toString();

        string = new StringBuilder(expression);
        string.deleteCharAt(expression.length() - 1);
        expression = string.toString();

        Log.d(TAG, "delete: tempOperand=" + tempOperand + " expression=" + expression);
    }

    //resets all the operands, operators and expression.
    public void reset() {
        operandList = new ArrayList<>();
        operatorList = new ArrayList<>();
        tempOperand = "";
        expression = "";
    }

    //switchs operands between positive or negative signs
    public void switchSign() {
        String prevOperand = tempOperand;
        tempOperand = Double.toString(Double.valueOf(tempOperand) * -1);

        //todo: fix
        expression = expression.replace(prevOperand, tempOperand);
    }


}

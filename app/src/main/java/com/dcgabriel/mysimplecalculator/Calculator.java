package com.dcgabriel.mysimplecalculator;

import android.util.Log;

import java.util.ArrayList;


//Model
public class Calculator {
    private static final String TAG = "Calculator";
    private ArrayList<Double> operandList;
    private ArrayList<String> operatorList;

    public Calculator() {
        operandList = new ArrayList<>();
        operatorList = new ArrayList<>();
    }

    public void pushOperand(double operand) {
        operandList.add(operand);
    }

    public void pushOperator(String operator) {
            operatorList.add(operator);
    }


    public double equals() {

        double result = 0;
        double operand1 = operandList.get(0);
        Log.d(TAG, "0000000000000000000000000000000000000000000000000000equals:" + operandList.toString());
        for (int i = 0; i < operandList.size() - 1; i++) {
            String operator = operatorList.get(i);
            double operand2 = operandList.get(i + 1);
            Log.d(TAG, "0000000000000000000000000000000000000000000000000000equals: operand1=" + operandList.get(0) + " operand2=" + operand2 + " operator" + operator);
            operand1 = evaluate(operand1, operand2, operator);
        }

        result = operand1;
        return result;
    }

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

    public void reset() {
        operandList = new ArrayList<>();
        operatorList = new ArrayList<>();

    }

    public void switchSign() {
        double lastOperand = operandList.get(operandList.size()-1);
        lastOperand = -lastOperand;
        operandList.remove(operandList.size()-1);
        pushOperand(lastOperand);
    }
}

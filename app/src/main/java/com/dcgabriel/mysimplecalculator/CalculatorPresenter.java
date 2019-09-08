package com.dcgabriel.mysimplecalculator;

import android.util.Log;

import java.util.ArrayList;

//Presenter
public class CalculatorPresenter {
    private static final String TAG = "CalculatorPresenter";
    private StringBuilder expression;
    private StringBuilder tempOperand;
    private Calculator calculatorModel;
    private String result;

    public CalculatorPresenter() {
        expression = new StringBuilder();
        tempOperand = new StringBuilder();
        calculatorModel = new Calculator();
        result = "";
    }

    public String onNumberPressed(String temp) {
        tempOperand.append(temp);
        expression.append(temp);

        return expression.toString();
    }

    public String onOperatorClicked(String operator) {
        if (tempOperand.length() != 0) {
            expression.append(operator);
            calculatorModel.pushOperand(Double.valueOf(tempOperand.toString()));
            calculatorModel.pushOperator(operator);
            tempOperand = new StringBuilder();
        }
        return expression.toString();
    }

    public String equals() {
        if (tempOperand.length() != 0) {
            calculatorModel.pushOperand(Double.valueOf(tempOperand.toString()));
            result = Double.toString(calculatorModel.equals());
        }
        return result;
    }

    public String reset() {
        calculatorModel.reset();
        expression = new StringBuilder();
        tempOperand = new StringBuilder();
        return expression.toString();
    }

    public String delete() {
        if (tempOperand.length() != 0) {
            tempOperand.deleteCharAt(tempOperand.length() - 1);
            expression.deleteCharAt(expression.length() - 1);
        }
        return expression.toString();
    }

    public void switchSign(){
        calculatorModel.switchSign();
    }

    /*
    public ArrayList<String> onResume(){
        Log.d(TAG, "***************************************************************onResume: expression=" + expression.toString() + " result=" + result );
        ArrayList<String> outputText = new ArrayList<>();
        outputText.add(expression.toString());
        outputText.add(result);
        return outputText;
    }
*/
}

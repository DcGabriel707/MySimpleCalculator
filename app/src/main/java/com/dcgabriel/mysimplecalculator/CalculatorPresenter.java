package com.dcgabriel.mysimplecalculator;

import android.util.Log;

import java.util.ArrayList;

//Presenter
public class CalculatorPresenter {
    private static final String TAG = "CalculatorPresenter";

    private Calculator calculatorModel;


    public CalculatorPresenter() {
        calculatorModel = new Calculator();
    }

    public String onNumberPressed(String temp) {
        calculatorModel.appendTempOperand(temp);
        calculatorModel.appendExpression(temp);
        Log.d(TAG, "888888onNumberPressed: expression=" + calculatorModel.getExpression());
        return calculatorModel.getExpression();
    }

    public String onOperatorClicked(String operator) {
        if (!calculatorModel.getTempOperand().isEmpty()) {
            calculatorModel.appendExpression(operator);
            calculatorModel.pushOperand(Double.valueOf(calculatorModel.getTempOperand()));
            calculatorModel.pushOperator(operator);
            calculatorModel.setTempOperand("");
        }
        return calculatorModel.getExpression();
    }

    public String equals() {
        String result = "";
        if (!calculatorModel.getTempOperand().isEmpty()) {
            calculatorModel.pushOperand(Double.valueOf(calculatorModel.getTempOperand()));
            result = Double.toString(calculatorModel.equals());

        }
        return result;
    }

    public String reset() {
        calculatorModel.reset();
        return calculatorModel.getExpression();
    }

    public String delete() {
        if (!calculatorModel.getTempOperand().isEmpty()) {
            calculatorModel.delete();
        }
        return calculatorModel.getExpression();
    }

    public String switchSign(){
        if (!calculatorModel.getTempOperand().isEmpty()) {
            //calculatorModel.pushOperand(Double.valueOf(tempOperand.toString()));
            calculatorModel.switchSign();

        }

        return calculatorModel.getTempOperand();
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

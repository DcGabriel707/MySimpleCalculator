package com.dcgabriel.mysimplecalculator;

import android.util.Log;
import android.view.View;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

//Presenter
public class CalculatorPresenter {
    private static final String TAG = "CalculatorPresenter";

    private Calculator calculatorModel;
    ViewInterface calculatorView;


    public CalculatorPresenter() {
        calculatorModel = new Calculator();
    }

    public void attachView(ViewInterface view) {
        calculatorView = view;
        calculatorView.updateView(calculatorModel.getExpression(), calculatorModel.getResult());
    }

    public void detachView() {
        calculatorView = null;
    }

    public String onNumberPressed(String temp) {
        calculatorModel.appendTempOperand(temp);
        calculatorModel.appendExpression(temp);
        Log.d(TAG, "888888888888888onNumberPressed: expression=" + calculatorModel.getExpression());
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
            double tempResult = calculatorModel.equals();
            result = Double.toString(tempResult);
            //result = String.format("%.0f",tempResult).split("\\.")[0];


            /*DecimalFormat decimalFormat = new DecimalFormat("0.#");
            result = decimalFormat.format(tempResult).toString();
            */
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

    public String switchSign() {
        Log.d(TAG, "switchSign: expression=" + calculatorModel.getExpression());
        if (!calculatorModel.getTempOperand().isEmpty()) {
            //calculatorModel.pushOperand(Double.valueOf(tempOperand.toString()));
            calculatorModel.switchSign();
        }
        Log.d(TAG, "switchSign: expression=" + calculatorModel.getExpression() + " operandList=" + calculatorModel.getOperandList().toString());
        return calculatorModel.getExpression();
    }

}

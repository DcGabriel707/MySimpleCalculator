package com.dcgabriel.mysimplecalculator;

import android.util.Log;

//Presenter
public class CalculatorPresenter {
    private static final String TAG = "CalculatorPresenter";

    private Calculator calculatorModel;
    ViewInterface calculatorView;


    public CalculatorPresenter() {
        calculatorModel = new Calculator();
    }

    //attaches the view
    public void attachView(ViewInterface view) {
        calculatorView = view;
        calculatorView.updateView(calculatorModel.getExpression(), calculatorModel.getResult()); //updates the textViews so they can display the saved values after the activity is resumed
    }

    public void detachView() {
        calculatorView = null;
    }

    //adds the input number to the temporary operand and the expression.
    //returns the updated expression string
    public String onNumberPressed(String temp) {
        calculatorModel.appendTempOperand(temp);
        calculatorModel.appendExpression(temp);
        Log.d(TAG, "888888888888888onNumberPressed: expression=" + calculatorModel.getExpression());
        return calculatorModel.getExpression();
    }

    //adds the input operator to the expression string. pushes the temporary operand to the operands list, pushes the operator to the operators list, then resets the temporary operand to start a new operand
    //returns the updated expression string
    public String onOperatorClicked(String operator) {
        if (!calculatorModel.getTempOperand().isEmpty()) {
            calculatorModel.appendExpression(operator);
            calculatorModel.pushOperand(Double.valueOf(calculatorModel.getTempOperand()));
            calculatorModel.pushOperator(operator);
            calculatorModel.setTempOperand("");
        }
        return calculatorModel.getExpression();
    }

    //pushes the last operand to the operands list. performs the calculation using the operands and operators
    //returns the result string
    public String equals() {
        String result = "";
        if (!calculatorModel.getTempOperand().isEmpty()) {
            calculatorModel.pushOperand(Double.valueOf(calculatorModel.getTempOperand()));
            double tempResult = calculatorModel.equals();
            result = Double.toString(tempResult);
        }

        return result;
    }

    //resets the model and returns the updated expression string
    public String reset() {
        calculatorModel.reset();
        return calculatorModel.getExpression();
    }

    //deletes the previous character in the expression and returns the updated expression
    public String delete() {
        if (!calculatorModel.getTempOperand().isEmpty()) {
            calculatorModel.delete();
        }
        return calculatorModel.getExpression();
    }

    //switches the sign of the previous operand and returns the updated expression
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

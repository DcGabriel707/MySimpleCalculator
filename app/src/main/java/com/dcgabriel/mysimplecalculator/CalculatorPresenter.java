package com.dcgabriel.mysimplecalculator;

public class CalculatorPresenter {
    private static final String TAG = "CalculatorPresenter";
    private StringBuilder expression;
    private StringBuilder tempOperand;
    private Calculator calculatorModel;

    public CalculatorPresenter() {
        expression = new StringBuilder();
        tempOperand = new StringBuilder();
        calculatorModel = new Calculator();
    }

    public String onNumberPressed(String temp) {
        tempOperand.append(temp);
        expression.append(temp);

        return expression.toString();
    }

    public String onOperatorClicked(String operator) {
        expression.append(operator);
        calculatorModel.pushOperand(Double.valueOf(tempOperand.toString()));
        calculatorModel.pushOperator(operator);
        tempOperand = new StringBuilder();
        return expression.toString();
    }

    public String equals() {
        calculatorModel.pushOperand(Double.valueOf(tempOperand.toString()));
        return Double.toString(calculatorModel.equals());
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

}

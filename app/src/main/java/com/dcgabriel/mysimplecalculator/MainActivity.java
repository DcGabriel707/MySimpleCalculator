package com.dcgabriel.mysimplecalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dcgabriel.mysimplecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private StringBuilder expression;
    private double operandOne;
    private double operandTwo;
    private boolean isFirstOperand;
    private String operator;
    private StringBuilder tempOperand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        reset();
    }

    //why is it unrecognized if onClick is used inside styles.xml. Somehow, still works
    public void onNumberPressed(View view) {
        tempOperand.append(view.getTag().toString());
        expression.append(view.getTag().toString());
        binding.textExpression.setText(expression);
        if (isFirstOperand)
            operandOne = (Double.valueOf(tempOperand.toString()));
        else
            operandTwo = (Double.valueOf(tempOperand.toString()));

    }

    public void onOperatorClicked(View view) {
        expression.append(view.getTag().toString());
        binding.textExpression.setText(expression);
        isFirstOperand = !isFirstOperand;
        tempOperand = new StringBuilder();
        operator = view.getTag().toString();
    }

    private void reset() {
        expression = new StringBuilder();
        tempOperand = new StringBuilder();
        isFirstOperand = true;
    }

    public void equals(View view) {
        double result = 0;
        Toast.makeText(this, operandOne + " " + operandTwo, Toast.LENGTH_SHORT).show();
        switch (operator) {
            case "+":
                result = operandOne + operandTwo;
                break;
            case "-":
                result = operandOne - operandTwo;
                break;
            case "*":
                result = operandOne * operandTwo;
                break;
            case "/":
                result = operandOne / operandTwo;
                break;
        }
        binding.textResult.setText(String.valueOf(result));
        reset();
    }


}

package com.dcgabriel.mysimplecalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.dcgabriel.mysimplecalculator.databinding.ActivityMainBinding;

import java.util.ArrayList;


//View
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        calculatorPresenter = new CalculatorPresenter();

        getSupportActionBar().hide();

    }

    //why is it unrecognized if onClick is used inside styles.xml. Somehow, still works
    public void onNumberPressed(View view) {
        String expression = calculatorPresenter.onNumberPressed(view.getTag().toString());
        binding.textExpression.setText(expression);
    }

    public void onOperatorClicked(View view) {
        Log.d(TAG, "onOperatorClicked: 0000000000000000000000000000000000000000000000000000000000000000000000000000000" + view.getTag().toString());
        String expression = calculatorPresenter.onOperatorClicked(view.getTag().toString());
        binding.textExpression.setText(expression);
    }

    public void equals(View view) {
        String result = calculatorPresenter.equals();
        binding.textResult.setText(result);
        calculatorPresenter.reset();

    }

    public void reset(View view) {
        String expression = calculatorPresenter.reset();
        binding.textExpression.setText(expression);
        binding.textResult.setText("");
    }

    public void delete(View view) {
        String expression = calculatorPresenter.delete();
        binding.textExpression.setText(expression);
    }

    public void switchSign(View view) {
        String switchedOperand = calculatorPresenter.switchSign();
        binding.textExpression.setText(switchedOperand);
    }

    @Override
    protected void onResume() {
       /* ArrayList<String> outputText = calculatorPresenter.onResume();
        binding.textExpression.setText(outputText.get(0));
        binding.textResult.setText(outputText.get(1));*/
        super.onResume();

    }

    @Override
    protected void onPause() {
        //calculatorPresenter.onPause();
        super.onPause();
    }
}

package com.dcgabriel.mysimplecalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dcgabriel.mysimplecalculator.databinding.ActivityMainBinding;

import java.util.ArrayList;


//View
public class MainActivity extends AppCompatActivity implements ViewInterface {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().hide();
        attachPresenter();
    }

    private void attachPresenter(){
        calculatorPresenter = (CalculatorPresenter) getLastCustomNonConfigurationInstance();
        if (calculatorPresenter == null) {
            calculatorPresenter = new CalculatorPresenter();
        }
        calculatorPresenter.attachView(this);
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
        if (switchedOperand.isEmpty())
            Toast.makeText(this, "enter value first", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        calculatorPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return calculatorPresenter;
    }

    //shh!
    private int k = 0;
    public void yayYouFoundIt(View view) {
        k++;
        if (k == 7) {
            Toast.makeText(this, "Yay!! You found it!", Toast.LENGTH_SHORT).show();
        }
        if (k == 15) {
            Toast.makeText(this, "By dcgabriel", Toast.LENGTH_SHORT).show();
        }
        if (k >= 20) {
            Toast.makeText(this, "You can stop now", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateView(String expression, String result) {
        binding.textExpression.setText(expression);
        binding.textResult.setText(result);
    }
}

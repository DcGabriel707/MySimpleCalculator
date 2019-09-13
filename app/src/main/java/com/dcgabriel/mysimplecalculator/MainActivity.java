package com.dcgabriel.mysimplecalculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dcgabriel.mysimplecalculator.databinding.ActivityMainBinding;


//View
public class MainActivity extends AppCompatActivity implements ViewInterface {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main); //uses Data Binding to rid all findViewById's on each of the 19 "buttons"
        getSupportActionBar().hide(); //removes the toolbar for improved UI
        attachPresenter();
    }

    //retrieves the presenter if it saved before otherwise a new presenter is created
    private void attachPresenter(){
        calculatorPresenter = (CalculatorPresenter) getLastCustomNonConfigurationInstance();
        if (calculatorPresenter == null) {
            calculatorPresenter = new CalculatorPresenter();
        }
        calculatorPresenter.attachView(this);
    }

    //called when a number is pressed
    //why is it unrecognized if onClick is used inside styles.xml. Somehow, still works
    public void onNumberPressed(View view) {
        String expression = calculatorPresenter.onNumberPressed(view.getTag().toString());
        binding.textExpression.setText(expression);
    }

    //called when an operator is clicked
    public void onOperatorClicked(View view) {
        Log.d(TAG, "onOperatorClicked: 0000000000000000000000000000000000000000000000000000000000000000000000000000000" + view.getTag().toString());
        String expression = calculatorPresenter.onOperatorClicked(view.getTag().toString());
        binding.textExpression.setText(expression);
    }

    //called when the equals button is clicked.
    //Retrieves the result and updates the result TextView.
    public void equals(View view) {
        String result = calculatorPresenter.equals();
        binding.textResult.setText(result);
        calculatorPresenter.reset();
    }

    //called when the clear button is clicked
    //resets the expression and updates the expression and result TextView
    public void reset(View view) {
        String expression = calculatorPresenter.reset();
        binding.textExpression.setText(expression);
        binding.textResult.setText("");
    }

    //called when the delete button is clicked.
    //deletes the last character from the expression and updates the expression TextView
    public void delete(View view) {
        String expression = calculatorPresenter.delete();
        binding.textExpression.setText(expression);
    }

    //called when the +/- button is clicked
    //switches the sign of the last operand and updates the expression TextView
    public void switchSign(View view) {
        String switchedOperand = calculatorPresenter.switchSign();
        if (switchedOperand.isEmpty())
            Toast.makeText(this, "enter value first", Toast.LENGTH_SHORT).show();
        binding.textExpression.setText(switchedOperand);
    }

    //updates the textViews with the saved strings
    @Override
    public void updateView(String expression, String result) {
        binding.textExpression.setText(expression);
        binding.textResult.setText(result);
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
        calculatorPresenter.detachView(); //destroys the view so the data is not saved when the app is closed
        super.onDestroy();
    }

    //"allow your Activity the ability to return a single object which can be re-used across configuration changes."
    //saves the presenter so it is not destroyed when the activity is stopped.
    //saving the presenter will also save the data in the model
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


}

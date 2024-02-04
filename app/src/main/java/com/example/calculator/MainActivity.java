package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView stateTextView;
    private EditText numberEditText;

    private double operand1 = Double.NaN;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateTextView = findViewById(R.id.state);
        numberEditText = findViewById(R.id.number);

        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        int[] buttonIds = {
                R.id.digit0Button, R.id.digit1Button, R.id.digit2Button, R.id.digit3Button,
                R.id.digit4Button, R.id.digit5Button, R.id.digit6Button, R.id.digit7Button,
                R.id.digit8Button, R.id.digit9Button, R.id.decimalButton
        };

        for (int buttonId : buttonIds) {
            findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDigitButtonClick(((Button) v).getText().toString());
                }
            });
        }

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClick("+");
            }
        });

        findViewById(R.id.subtractButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClick("-");
            }
        });

        findViewById(R.id.multiplyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClick("*");
            }
        });

        findViewById(R.id.divideButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClick("/");
            }
        });

        findViewById(R.id.powerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClick("^");
            }
        });

        findViewById(R.id.moduloButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClick("%");
            }
        });

        findViewById(R.id.resultButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsButtonClick();
            }
        });

        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearButtonClick();
            }
        });

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });
    }

    private void onDigitButtonClick(String digit) {
        String currentText = numberEditText.getText().toString();
        numberEditText.setText(currentText + digit);
    }

    private void onOperatorButtonClick(String newOperator) {
        try {
            operand1 = Double.parseDouble(numberEditText.getText().toString());
            operator = newOperator;
            stateTextView.setText(operand1 + " " + operator);
            numberEditText.setText("");
        } catch (NumberFormatException e) {
            stateTextView.setText("Error");
        }
    }

    private void onEqualsButtonClick() {
        try {
            double operand2 = Double.parseDouble(numberEditText.getText().toString());
            double result = performOperation(operand1, operand2, operator);
            stateTextView.setText("");
            numberEditText.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            stateTextView.setText("Error");
        }
    }

    private void onClearButtonClick() {
        operand1 = Double.NaN;
        operator = "";
        stateTextView.setText("");
        numberEditText.setText("");
    }

    private void onBackButtonClick() {
        String currentText = numberEditText.getText().toString();
        if (!currentText.isEmpty()) {
            currentText = currentText.substring(0, currentText.length() - 1);
            numberEditText.setText(currentText);
        }
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    stateTextView.setText("Error");
                    return Double.NaN;
                }
            case "^":
                return Math.pow(operand1, operand2);
            case "%":
                if (operand2 != 0) {
                    return operand1 % operand2;
                } else {
                    stateTextView.setText("Error");
                    return Double.NaN;
                }
            default:
                return Double.NaN;
        }
    }
}
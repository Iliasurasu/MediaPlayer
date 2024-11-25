package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(button.getText().toString());
                }
            });
        }
    }

    private void onButtonClick(String value) {
        if (value.equals("=")) {
            String result = String.valueOf(Calculator.evaluateExpression(expression.toString()));
            display.setText(result);
            expression.setLength(0); // Очистить выражение после вычисления
        } else if (value.equals("C")) {
            expression.setLength(0); // Очистить выражение
            display.setText("");
        } else {
            expression.append(value);
            display.setText(expression.toString());
        }
    }
}

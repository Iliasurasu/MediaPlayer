package com.example.searchnumber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int attemptsLeft = 3;
    private int minRange = 1;
    private int maxRange = 20;
    private EditText guessInput;
    private TextView resultText;
    private TextView attemptsText;
    private TextView rangeText;
    private TextView targetNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessInput = findViewById(R.id.guessInput);
        resultText = findViewById(R.id.resultText);
        attemptsText = findViewById(R.id.attemptsText);
        rangeText = findViewById(R.id.rangeText);
        Button checkButton = findViewById(R.id.checkButton);

        resetGame();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String guessString = guessInput.getText().toString();

        if (guessString.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите число", Toast.LENGTH_SHORT).show();
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(guessString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Пожалуйста, введите корректное число", Toast.LENGTH_SHORT).show();
            return;
        }

        if (guess < minRange || guess > maxRange) {
            Toast.makeText(this, "Число должно быть в диапазоне от " + minRange + " до " + maxRange, Toast.LENGTH_SHORT).show();
            return;
        }

        attemptsLeft--;
        if (guess > randomNumber) {
            maxRange = guess - 1;
            resultText.setText("Меньше!");
        } else if (guess < randomNumber) {
            minRange = guess + 1;
            resultText.setText("Больше!");
        } else {
            resultText.setText("Поздравляю! Вы угадали число.");
            Toast.makeText(this, "Вы выиграли! Начните новую игру!", Toast.LENGTH_LONG).show();
            resetGame();
            return;
        }

        rangeText.setText("Диапазон: " + minRange + " - " + maxRange);
        attemptsText.setText("Осталось попыток: " + attemptsLeft);

        if (attemptsLeft == 0) {
            Toast.makeText(this, "Игра окончена! Число было: " + randomNumber, Toast.LENGTH_LONG).show();
            resetGame();
        }
    }

    private void resetGame() {
        randomNumber = new Random().nextInt(20) + 1;
        attemptsLeft = 3;
        minRange = 1;
        maxRange = 20;

        attemptsText.setText("Осталось попыток: " + attemptsLeft);
        rangeText.setText("Диапазон: " + minRange + " - " + maxRange);
        resultText.setText("");
        guessInput.setText("");
    }
}

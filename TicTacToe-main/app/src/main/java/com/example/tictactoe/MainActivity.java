package com.example.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3]; // Массив кнопок для игрового поля
    private boolean player1Turn = true; // Переменная для отслеживания хода игрока
    private int roundCount = 0; // Счетчик раундов
    private boolean isMultiplayer = true; // Режим игры
    private Handler handler = new Handler(); // Обработчик для задержки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализация кнопок
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + (i * 3 + j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new ButtonClickListener(i, j));
            }
        }

        // Установка режима игры
        RadioGroup gameModeGroup = findViewById(R.id.gameModeGroup);
        gameModeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            isMultiplayer = checkedId == R.id.radioMultiplayer; // Установка режима игры
            resetGame(); // Сброс игры при изменении режима
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> resetGame());
    }

    private class ButtonClickListener implements View.OnClickListener {
        private int row;
        private int col;

        ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if (!((Button) v).getText().toString().equals("")) {
                return; // Если кнопка уже нажата
            }

            if (player1Turn) {
                ((Button) v).setText("X"); // Ход игрока 1
            } else {
                ((Button) v).setText("O"); // Ход игрока 2 (или бота)
            }

            roundCount++;
            if (checkForWin()) {
                showToast("Игрок " + (player1Turn ? "1" : "2") + " выиграл!"); // Объявление победителя
            } else if (roundCount == 9) {
                showToast("Ничья!"); // Ничья
            } else {
                if (!isMultiplayer) {
                    player1Turn = false; // Переключение на бота
                    // Задержка перед ходом бота
                    handler.postDelayed(MainActivity.this::botMove, 1000); // 1000 мс = 1 секунда
                } else {
                    player1Turn = !player1Turn; // Переключение игроков
                }
            }
        }
    }

    private void botMove() {
        if (!player1Turn) { // Если ход бота
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().toString().equals("")) {
                        buttons[i][j].setText("O"); // Ход бота
                        roundCount++;
                        if (checkForWin()) {
                            showToast("Игрок 2 выиграл!"); // Победа бота
                        } else if (roundCount == 9) {
                            showToast("Ничья!"); // Ничья
                        } else {
                            player1Turn = true; // Вернуться к ходу игрока
                        }
                        return;
                    }
                }
            }
        }
    }

    private boolean checkForWin() {
        // Проверка горизонтальных, вертикальных и диагональных линий на победу
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString())
                    && buttons[i][0].getText().toString().equals(buttons[i][2].getText().toString())
                    && !buttons[i][0].getText().toString().equals("")) {
                highlightWinningCombination(i, 0, i, 1, i, 2);
                return true; // Горизонтальная победа
            }
            if (buttons[0][i].getText().toString().equals(buttons[1][i].getText().toString())
                    && buttons[0][i].getText().toString().equals(buttons[2][i].getText().toString())
                    && !buttons[0][i].getText().toString().equals("")) {
                highlightWinningCombination(0, i, 1, i, 2, i);
                return true; // Вертикальная победа
            }
        }
        if (buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString())
                && buttons[0][0].getText().toString().equals(buttons[2][2].getText().toString())
                && !buttons[0][0].getText().toString().equals("")) {
            highlightWinningCombination(0, 0, 1, 1, 2, 2);
            return true; // Диагональная победа (слева направо)
        }
        if (buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString())
                && buttons[0][2].getText().toString().equals(buttons[2][0].getText().toString())
                && !buttons[0][2].getText().toString().equals("")) {
            highlightWinningCombination(0, 2, 1, 1, 2, 0);
            return true; // Диагональная победа (справа налево)
        }
        return false; // Нет победителя
    }

    private void highlightWinningCombination(int... indices) {
        for (int i = 0; i < indices.length; i += 2) {
            buttons[indices[i]][indices[i + 1]].setBackgroundColor(Color.RED); // Окрашивание победной комбинации
        }
    }

    private void resetGame() {
        roundCount = 0; // Сброс счетчика раундов
        player1Turn = true; // Сброс хода игрока
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Очистка текста кнопок
                buttons[i][j].setBackgroundColor(Color.WHITE); // Сброс цвета кнопок
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); // Показ сообщения о результате
    }
}

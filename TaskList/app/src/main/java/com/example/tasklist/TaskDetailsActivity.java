package com.example.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        // Получаем данные из Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");

        // Находим TextView для отображения данных
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        // Инициализация кнопки "Back to Main"
        Button backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(v -> {
            // Возвращаемся на главный экран
            Intent mainIntent = new Intent(TaskDetailsActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();  // Завершаем текущую активность
        });
    }
}

package com.example.tasklist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTitle, editDescription;
    private TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Убедитесь, что используете правильные ID, которые указаны в XML
        editTitle = findViewById(R.id.editTitle);  // editTitle, а не edit_title
        editDescription = findViewById(R.id.editDescription);  // editDescription, а не edit_description
        taskDatabase = new TaskDatabase(this);
    }

    // Метод для сохранения новой задачи
    public void saveTask(View view) {
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();

        // Проверка на пустые поля
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Title or description cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(0, title, description, 0);
        taskDatabase.addTask(task);  // Добавляем задачу в базу данных

        // Возвращаемся на основной экран
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

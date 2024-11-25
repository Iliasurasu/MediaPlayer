package com.example.a2ddrawing;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливаем CustomView как главное представление
        setContentView(new CustomView(this, null)); // Передаем null для AttributeSet
    }
}

package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private ImageView backgroundImage;
    private int[] backgrounds = {R.drawable.background1, R.drawable.background2, R.drawable.background3};
    private int backgroundIndex = 0;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализируем компоненты
        mediaPlayer = MediaPlayer.create(this, R.raw.song1);
        playButton = findViewById(R.id.playButton);
        backgroundImage = findViewById(R.id.backgroundImage);
        Button changeBackgroundButton = findViewById(R.id.changeBackgroundButton);

        // Проверка на сохранённое состояние
        if (savedInstanceState != null) {
            backgroundIndex = savedInstanceState.getInt("backgroundIndex");
            isPlaying = savedInstanceState.getBoolean("isPlaying");
            backgroundImage.setImageResource(backgrounds[backgroundIndex]);

            // Восстановление состояния плеера
            if (isPlaying) {
                mediaPlayer.start();
                playButton.setText("Pause");
            }
        }

        // Установка слушателя на кнопку воспроизведения
        playButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                playButton.setText("Play");
                isPlaying = false;
            } else {
                mediaPlayer.start();
                playButton.setText("Pause");
                isPlaying = true;
            }
        });

        // Установка слушателя для смены фона
        changeBackgroundButton.setOnClickListener(v -> {
            backgroundIndex = (backgroundIndex + 1) % backgrounds.length;
            backgroundImage.setImageResource(backgrounds[backgroundIndex]);
        });
    }

    // Сохранение состояния при повороте экрана
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("backgroundIndex", backgroundIndex);
        outState.putBoolean("isPlaying", mediaPlayer.isPlaying());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

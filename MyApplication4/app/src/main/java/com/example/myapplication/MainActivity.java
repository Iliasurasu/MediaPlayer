package com.example.myapplication;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView; // Импортируйте TextView
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton, nextButton, prevButton;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private ImageView backgroundImage;
    private TextView currentTime, totalTime; // Объявите переменные для TextView
    private int[] backgrounds = {R.drawable.background_one, R.drawable.background_two, R.drawable.background_three};
    private int[] tracks = {R.raw.track_one, R.raw.track_two, R.raw.track_three};  // Добавьте свои треки
    private int currentTrackIndex = 0;
    private int backgroundIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация компонентов
        mediaPlayer = MediaPlayer.create(this, tracks[currentTrackIndex]);
        playButton = findViewById(R.id.playButton);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        seekBar = findViewById(R.id.seekBar);
        backgroundImage = findViewById(R.id.backgroundImage);
        currentTime = findViewById(R.id.currentTime); // Инициализация TextView для текущего времени
        totalTime = findViewById(R.id.totalTime); // Инициализация TextView для общего времени
        Button changeBackgroundButton = findViewById(R.id.changeBackgroundButton);

        // Установить общее время трека
        totalTime.setText(formatTime(mediaPlayer.getDuration()));

        // Настройка SeekBar
        seekBar.setMax(mediaPlayer.getDuration());
        handler.postDelayed(updateSeekBar, 100);

        // Слушатель для кнопки Play/Pause
        playButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        });

        // Слушатель для кнопки Next
        nextButton.setOnClickListener(v -> playNextTrack());

        // Слушатель для кнопки Prev
        prevButton.setOnClickListener(v -> playPreviousTrack());

        // Слушатель для SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Слушатель для смены фона
        changeBackgroundButton.setOnClickListener(v -> {
            backgroundIndex = (backgroundIndex + 1) % backgrounds.length;
            backgroundImage.setImageResource(backgrounds[backgroundIndex]);
        });
    }

    // Обновление прогресса трека в SeekBar
    private Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mediaPlayer.getCurrentPosition();
            seekBar.setProgress(currentPosition);
            currentTime.setText(formatTime(currentPosition)); // Обновление текста текущего времени
            handler.postDelayed(this, 100);
        }
    };

    // Форматирование времени в строку "mm:ss"
    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    // Воспроизведение следующего трека
    private void playNextTrack() {
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        currentTrackIndex = (currentTrackIndex + 1) % tracks.length;
        mediaPlayer = MediaPlayer.create(this, tracks[currentTrackIndex]);
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());
        totalTime.setText(formatTime(mediaPlayer.getDuration())); // Обновление общего времени
    }

    // Воспроизведение предыдущего трека
    private void playPreviousTrack() {
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        currentTrackIndex = (currentTrackIndex - 1 + tracks.length) % tracks.length;
        mediaPlayer = MediaPlayer.create(this, tracks[currentTrackIndex]);
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());
        totalTime.setText(formatTime(mediaPlayer.getDuration())); // Обновление общего времени
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(updateSeekBar);
    }
}

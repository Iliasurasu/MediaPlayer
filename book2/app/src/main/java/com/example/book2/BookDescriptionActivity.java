package com.example.book2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class BookDescriptionActivity extends AppCompatActivity {

    private TextView titleTextView, authorTextView, genreTextView, postTextView, descriptionTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);

        titleTextView = findViewById(R.id.bookTitle);
        authorTextView = findViewById(R.id.bookAuthor);
        genreTextView = findViewById(R.id.bookGenre);
        postTextView = findViewById(R.id.bookPost);
        descriptionTextView = findViewById(R.id.bookDescription);
        posterImageView = findViewById(R.id.bookPoster);

        Intent intent = getIntent();
        String title = intent.getStringExtra("BOOK_TITLE");
        String author = intent.getStringExtra("BOOK_AUTHOR");
        String genre = intent.getStringExtra("BOOK_GENRE");
        String post = intent.getStringExtra("BOOK_POST");
        String posterUrl = intent.getStringExtra("BOOK_POSTER");
        String description = intent.getStringExtra("BOOK_DESCRIPTION");

        titleTextView.setText(title);
        authorTextView.setText(author);
        genreTextView.setText(genre);
        postTextView.setText(post);
        descriptionTextView.setText(description);

        Glide.with(this)
                .load(posterUrl)
                .into(posterImageView);
    }
}

package com.example.book2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class BookDetailsDialog extends Dialog {

    private Book book;
    private TextView titleTextView, authorTextView, genreTextView, postTextView, posterTextView;

    public BookDetailsDialog(Context context, Book book) {
        super(context);
        this.book = book;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_book_details);

        // Initialize the views
        titleTextView = findViewById(R.id.dialogBookTitle);
        authorTextView = findViewById(R.id.dialogBookAuthor);
        genreTextView = findViewById(R.id.dialogBookGenre);
        postTextView = findViewById(R.id.dialogBookPost);
        posterTextView = findViewById(R.id.dialogBookPoster);

        // Set data to views
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        genreTextView.setText(book.getGenre());
        postTextView.setText(book.getPost());
        posterTextView.setText(book.getPosterUrl());
    }
}

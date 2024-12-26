package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarked_articles")
public class BookmarkedArticle {
    @PrimaryKey
    @NonNull
    public String url;
    public String title;
    public String description;
    public String urlToImage;
    public String publishedAt;
}

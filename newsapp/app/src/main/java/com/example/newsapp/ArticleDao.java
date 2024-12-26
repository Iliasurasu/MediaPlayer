package com.example.newsapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookmarkedArticle article);

    @Query("DELETE FROM bookmarked_articles WHERE url = :url")
    void delete(String url);

    @Query("SELECT * FROM bookmarked_articles")
    LiveData<List<BookmarkedArticle>> getAllBookmarkedArticles();
}

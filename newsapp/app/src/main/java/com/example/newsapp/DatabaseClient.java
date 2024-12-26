package com.example.newsapp;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {
    private static AppDatabase database;

    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, "news_db")
                    .allowMainThreadQueries() // Рекомендуется заменить на асинхронный вызов
                    .build();
        }
        return database;
    }
}

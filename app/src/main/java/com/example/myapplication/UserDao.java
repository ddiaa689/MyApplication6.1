package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private final UserDbHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new UserDbHelper(context);
    }

    // 注册用户
    public boolean register(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = -1;
        try {
            result = db.insertOrThrow("user", null, values);
        } catch (Exception e) {
            // 用户已存在
        }
        db.close();
        return result != -1;
    }

    // 登录校验
    public boolean login(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                "user",
                null,
                "username=? AND password=?",
                new String[]{username, password},
                null,
                null,
                null
        );
        boolean success = cursor.moveToFirst();
        cursor.close();
        db.close();
        return success;
    }
}
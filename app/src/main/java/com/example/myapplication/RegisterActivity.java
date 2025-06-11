package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etPassword2;
    private RadioGroup rgUserType;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);
        rgUserType = findViewById(R.id.rgUserType);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String password2 = etPassword2.getText().toString().trim();

            // 1. 校验输入
            if (username.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(password2)) {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }

            // 2. 获取用户类型
            int checkedId = rgUserType.getCheckedRadioButtonId();
            String userType = "jobseeker";
            if (checkedId == R.id.rbJobSeeker) userType = "jobseeker";
            else if (checkedId == R.id.rbRecruiter) userType = "recruiter";
            else if (checkedId == R.id.rbAdmin) userType = "admin";

            // 3. 检查用户名是否已存在
            UserDbHelper dbHelper = new UserDbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("user", null, "username=?", new String[]{username}, null, null, null);
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues values = new ContentValues();
                values.put("username", username);
                values.put("password", password);
                values.put("usertype", userType); // 你需要在数据库表结构中加usertype字段
                db.insert("user", null, values);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            cursor.close();
            db.close();
        });
    }
}
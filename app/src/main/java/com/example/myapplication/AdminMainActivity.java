package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        TextView tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setText("欢迎，管理员用户！这里可以管理所有用户和职位、审核等功能。");
    }
}
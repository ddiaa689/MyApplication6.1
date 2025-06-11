package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MineActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private TextView tvName, tvEditResume, tvDeliverHistory, tvSettings, tvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        ivAvatar = findViewById(R.id.ivMineAvatar);
        tvName = findViewById(R.id.tvMineName);
        tvEditResume = findViewById(R.id.tvMineEditResume);
        tvDeliverHistory = findViewById(R.id.tvMineDeliverHistory);
        tvSettings = findViewById(R.id.tvMineSettings);
        tvLogout = findViewById(R.id.tvMineLogout);

        // 可根据实际登录用户动态设置
        tvName.setText("Java开发工程师");

        tvEditResume.setOnClickListener(v -> Toast.makeText(this, "编辑简历功能待实现", Toast.LENGTH_SHORT).show());
        tvDeliverHistory.setOnClickListener(v -> Toast.makeText(this, "投递记录功能待实现", Toast.LENGTH_SHORT).show());
        tvSettings.setOnClickListener(v -> Toast.makeText(this, "设置功能待实现", Toast.LENGTH_SHORT).show());
        tvLogout.setOnClickListener(v -> Toast.makeText(this, "退出登录功能待实现", Toast.LENGTH_SHORT).show());
    }
}
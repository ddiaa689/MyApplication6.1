package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MessageDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);



        // 可以根据Intent设置内容
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvSubTitle = findViewById(R.id.tvSubTitle);
        TextView tvTime = findViewById(R.id.tvTime);
        TextView tvJobTitle = findViewById(R.id.tvJobTitle);
        TextView tvJobCompany = findViewById(R.id.tvJobCompany);
        TextView tvChatBubble = findViewById(R.id.tvChatBubble);

        // 这里写死的内容可根据实际传参动态设置
        tvTitle.setText("陈女士");
        tvSubTitle.setText("网易 · 人事经理");
        tvTime.setText("15:46");
        tvJobTitle.setText("java工程师");
        tvJobCompany.setText("网易 未融资");
        tvChatBubble.setText("您好，对您发布的职位很感兴趣，希望您可以看到我的简历[愉快]");

        // 底部输入框可自行实现发送逻辑
        EditText etInput = findViewById(R.id.etInput);
        ImageView btnEmoji = findViewById(R.id.btnEmoji);
        ImageView btnMore = findViewById(R.id.btnMore);

        btnEmoji.setOnClickListener(v -> Toast.makeText(this, "表情面板", Toast.LENGTH_SHORT).show());
        btnMore.setOnClickListener(v -> Toast.makeText(this, "更多功能", Toast.LENGTH_SHORT).show());
    }
}
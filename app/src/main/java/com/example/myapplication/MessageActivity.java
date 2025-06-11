package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView rvMsgList;
    private MessageAdapter msgAdapter;
    private List<MessageItem> msgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        rvMsgList = findViewById(R.id.rvMsgList);
        rvMsgList.setLayoutManager(new LinearLayoutManager(this));

        // 模拟数据
        msgList = new ArrayList<>();
        msgList.add(new MessageItem(R.drawable.bg_avatar, "张经理·字节跳动HR", "你好，可以聊聊你的简历吗？", "09:15", 1));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "王总·美团CTO", "我们对你的背景很感兴趣，欢迎了解岗位详情。", "昨天", 0));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "李女士·腾讯招聘", "请问你近期有换工作的打算吗？", "周一", 2));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "系统消息", "您的简历已被阿里巴巴查看", "08:00", 0));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "BOSS直聘小助手", "每日职位推荐已送达，快来看看吧！", "07:30", 0));

        msgAdapter = new MessageAdapter(this, msgList);
        rvMsgList.setAdapter(msgAdapter);

        // 顶部导航栏点击事件可按需补充
    }
}
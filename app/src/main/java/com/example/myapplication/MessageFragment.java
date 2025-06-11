package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    public MessageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        RecyclerView rvMsgList = view.findViewById(R.id.rvMsgList);
        rvMsgList.setLayoutManager(new LinearLayoutManager(getContext()));

        List<MessageItem> msgList = new ArrayList<>();
        msgList.add(new MessageItem(R.drawable.bg_avatar, "张经理·字节跳动HR", "你好，可以聊聊你的简历吗？", "09:15", 1));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "王总·美团CTO", "我们对你的背景很感兴趣，欢迎了解岗位详情。", "昨天", 0));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "李女士·腾讯招聘", "请问你近期有换工作的打算吗？", "周一", 2));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "系统消息", "您的简历已被阿里巴巴查看", "08:00", 0));
        msgList.add(new MessageItem(R.drawable.bg_avatar, "BOSS直聘小助手", "每日职位推荐已送达，快来看看吧！", "07:30", 0));

        MessageAdapter msgAdapter = new MessageAdapter(getContext(), msgList);

        // 设置 item 点击事件，进入消息详情
        msgAdapter.setOnItemClickListener((position, messageItem) -> {
            Intent intent = new Intent(getContext(), MessageDetailActivity.class);
            intent.putExtra("title", messageItem.title);
            intent.putExtra("avatarResId", messageItem.avatarResId);
            intent.putExtra("content", messageItem.content);
            intent.putExtra("time", messageItem.time);
            // 你可以根据需要添加更多字段
            startActivity(intent);
        });

        rvMsgList.setAdapter(msgAdapter);

        return view;
    }
}
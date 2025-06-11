package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MsgViewHolder> {

    private final Context context;
    private final List<MessageItem> msgList;
    private OnItemClickListener onItemClickListener;

    public MessageAdapter(Context context, List<MessageItem> msgList) {
        this.context = context;
        this.msgList = msgList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, MessageItem messageItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgViewHolder holder, int position) {
        MessageItem msg = msgList.get(position);
        holder.ivAvatar.setImageResource(msg.avatarResId);
        holder.tvTitle.setText(msg.title);
        holder.tvContent.setText(msg.content);
        holder.tvTime.setText(msg.time);

        if (msg.unreadCount > 0) {
            holder.tvUnread.setVisibility(View.VISIBLE);
            holder.tvUnread.setText(String.valueOf(msg.unreadCount));
        } else {
            holder.tvUnread.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, msg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class MsgViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvTitle, tvContent, tvTime, tvUnread;

        MsgViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivMsgAvatar);
            tvTitle = itemView.findViewById(R.id.tvMsgTitle);
            tvContent = itemView.findViewById(R.id.tvMsgContent);
            tvTime = itemView.findViewById(R.id.tvMsgTime);
            tvUnread = itemView.findViewById(R.id.tvMsgUnread);
        }
    }
}
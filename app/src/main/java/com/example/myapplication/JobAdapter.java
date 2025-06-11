package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private final Context context;
    private final List<JobItem> jobList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(JobItem job);
    }

    public JobAdapter(Context context, List<JobItem> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<JobItem> newJobs) {
        this.jobList.clear();
        this.jobList.addAll(newJobs);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobItem job = jobList.get(position);
        holder.bind(job);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView tvJobTitle, tvSalary, tvCompany, tvCity, tvExperience, tvEducation, tvRecruiter;
        LinearLayout llTags;
        ImageView ivAvatar;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJobTitle = itemView.findViewById(R.id.tvJobTitle);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvExperience = itemView.findViewById(R.id.tvExperience);
            tvEducation = itemView.findViewById(R.id.tvEducation);
            llTags = itemView.findViewById(R.id.llTags);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvRecruiter = itemView.findViewById(R.id.tvRecruiter);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(jobList.get(position));
                }
            });
        }

        public void bind(JobItem job) {
            // 使用Glide加载图片
            Glide.with(itemView.getContext())
                    .load(job.avatarResId)
                    .circleCrop()
                    .into(ivAvatar);

            tvJobTitle.setText(job.jobTitle);
            tvSalary.setText(job.salary);
            tvCompany.setText(job.company);
            tvCity.setText("  " + job.city);
            tvExperience.setText("  " + job.experience);
            tvEducation.setText("  " + job.education);

            // 动态添加标签
            llTags.removeAllViews();
            if (job.tags != null) {
                for (String tag : job.tags) {
                    TextView tv = new TextView(context);
                    tv.setText(tag);
                    tv.setTextSize(12);
                    tv.setTextColor(context.getResources().getColor(R.color.teal_700));
                    tv.setBackgroundResource(R.drawable.bg_search_box);
                    tv.setPadding(22, 6, 22, 6);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0, 0, 16, 0);
                    tv.setLayoutParams(lp);
                    llTags.addView(tv);
                }
            }
        }
    }
}
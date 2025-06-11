package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class JobDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        // 获取Intent传递数据
        String jobTitle = getIntent().getStringExtra("jobTitle");
        String salary = getIntent().getStringExtra("salary");
        String company = getIntent().getStringExtra("company");
        String city = getIntent().getStringExtra("city");
        String experience = getIntent().getStringExtra("experience");
        String education = getIntent().getStringExtra("education");
        String recruiter = getIntent().getStringExtra("recruiter");
        int avatarResId = getIntent().getIntExtra("avatarResId", R.drawable.bg_avatar);
        String[] tags = getIntent().getStringArrayExtra("tags");

        ((TextView)findViewById(R.id.tvDetailJobTitle)).setText(jobTitle);
        ((TextView)findViewById(R.id.tvDetailSalary)).setText(salary);
        ((TextView)findViewById(R.id.tvDetailCompany)).setText(company);
        ((TextView)findViewById(R.id.tvDetailCity)).setText(city);
        ((TextView)findViewById(R.id.tvDetailExperience)).setText(experience);
        ((TextView)findViewById(R.id.tvDetailEducation)).setText(education);
        ((TextView)findViewById(R.id.tvDetailRecruiter)).setText(recruiter);
        ((ImageView)findViewById(R.id.ivDetailAvatar)).setImageResource(avatarResId);

        LinearLayout llTags = findViewById(R.id.llDetailTags);
        llTags.removeAllViews();
        if (tags != null) {
            for (String tag : tags) {
                TextView tv = new TextView(this);
                tv.setText(tag);
                tv.setTextSize(13);
                tv.setTextColor(getResources().getColor(R.color.teal_700));
                tv.setBackgroundResource(R.drawable.bg_search_box);
                tv.setPadding(24, 10, 24, 10);
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
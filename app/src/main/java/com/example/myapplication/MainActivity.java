package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private LinearLayout tabJob, tabMsg, tabMine;
    private ImageView ivJob, ivMsg, ivMine;
    private TextView tvJob, tvMsg, tvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 登录态校验
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        if (!sp.getBoolean("isLogin", false)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        tabJob = findViewById(R.id.tabJob);
        tabMsg = findViewById(R.id.tabMsg);
        tabMine = findViewById(R.id.tabMine);
        ivJob = findViewById(R.id.ivJob);
        ivMsg = findViewById(R.id.ivMsg);
        ivMine = findViewById(R.id.ivMine);
        tvJob = findViewById(R.id.tvJob);
        tvMsg = findViewById(R.id.tvMsg);
        tvMine = findViewById(R.id.tvMine);

        // 职位偏好弹窗，若未选择则弹出
        if (!sp.contains("jobPreference")) {
            showJobPreferenceDialog(sp);
        }

        switchFragment(new JobListFragment());
        selectTab(0);

        tabJob.setOnClickListener(v -> {
            switchFragment(new JobListFragment());
            selectTab(0);
        });
        tabMsg.setOnClickListener(v -> {
            switchFragment(new MessageFragment());
            selectTab(1);
        });
        tabMine.setOnClickListener(v -> {
            switchFragment(new MineFragment());
            selectTab(2);
        });
    }

    // 职位偏好多选弹窗（如未设置则弹出）
    private void showJobPreferenceDialog(SharedPreferences sp) {
        String[] jobTypes = {"Java开发", "Android开发", "前端开发", "产品经理", "UI设计", "测试", "运营"};
        boolean[] checkedItems = new boolean[jobTypes.length];

        new AlertDialog.Builder(this)
                .setTitle("请选择您的职位偏好")
                .setMultiChoiceItems(jobTypes, checkedItems, (dialog, which, isChecked) -> {
                    checkedItems[which] = isChecked;
                })
                .setPositiveButton("确定", (dialog, which) -> {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < checkedItems.length; i++) {
                        if (checkedItems[i]) sb.append(jobTypes[i]).append("、");
                    }
                    String preference = sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "未选择";
                    sp.edit().putString("jobPreference", preference).apply();
                    Toast.makeText(this, "已保存: " + preference, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("跳过", (dialog, which) -> {
                    sp.edit().putString("jobPreference", "未选择").apply();
                })
                .setCancelable(false)
                .show();
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.commit();
    }

    private void selectTab(int index) {
        ivJob.setImageResource(R.drawable.ic_tab_job);
        ivMsg.setImageResource(R.drawable.ic_tab_job);
        ivMine.setImageResource(R.drawable.ic_tab_job);
        tvJob.setTextColor(getResources().getColor(R.color.gray));
        tvMsg.setTextColor(getResources().getColor(R.color.gray));
        tvMine.setTextColor(getResources().getColor(R.color.gray));
        if (index == 0) {
            ivJob.setImageResource(R.drawable.img);
            tvJob.setTextColor(getResources().getColor(R.color.teal_700));
        } else if (index == 1) {
            ivMsg.setImageResource(R.drawable.img);
            tvMsg.setTextColor(getResources().getColor(R.color.teal_700));
        } else if (index == 2) {
            ivMine.setImageResource(R.drawable.img);
            tvMine.setTextColor(getResources().getColor(R.color.teal_700));
        }
    }
}
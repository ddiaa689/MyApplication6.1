package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends AppCompatActivity {

    private RecyclerView rvJobList;
    private JobAdapter jobAdapter;
    private List<JobItem> jobList;      // 全部职位
    private List<JobItem> filteredList; // 当前筛选后职位
    private EditText etSearch;
    private Button btnFilter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View loadingLayout;
    private View errorLayout;
    private Handler handler;
    private boolean isLoading = false;

    // 当前筛选条件
    private String filterCity = "";
    private String filterEducation = "";
    private String filterExperience = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        rvJobList = findViewById(R.id.rvJobList);
        etSearch = findViewById(R.id.etSearch);
        btnFilter = findViewById(R.id.btnFilter);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        loadingLayout = findViewById(R.id.loadingLayout);
        errorLayout = findViewById(R.id.errorLayout);

        rvJobList.setLayoutManager(new LinearLayoutManager(this));

        handler = new Handler(Looper.getMainLooper());

        // 初始化职位数据
        jobList = new ArrayList<>();
        jobList.add(new JobItem(
                "Java开发工程师", "15-22K·14薪", "字节跳动", "北京·海淀", "3-5年", "本科",
                Arrays.asList("五险一金", "弹性工作", "年度旅游"), R.drawable.bg_avatar, "HR·张经理"
        ));
        jobList.add(new JobItem(
                "Android高级工程师", "18-28K·13薪", "美团", "上海·浦东", "5-10年", "硕士",
                Arrays.asList("下午茶", "股票期权", "带薪年假"), R.drawable.bg_avatar, "CTO·王总"
        ));
        jobList.add(new JobItem(
                "产品经理", "12-20K·15薪", "腾讯", "深圳·南山", "3-5年", "本科",
                Arrays.asList("免费健身", "成长空间大"), R.drawable.bg_avatar, "经理·李女士"
        ));
        jobList.add(new JobItem(
                "前端开发工程师", "10-18K·13薪", "阿里巴巴", "杭州·西湖", "1-3年", "本科",
                Arrays.asList("餐补", "技术大牛", "双休"), R.drawable.bg_avatar, "主管·赵工"
        ));
        jobList.add(new JobItem(
                "测试工程师", "8-13K·14薪", "网易", "广州·天河", "应届毕业生", "本科",
                Arrays.asList("成长快", "带薪年假"), R.drawable.bg_avatar, "HR·钱女士"
        ));

        filteredList = new ArrayList<>(jobList);
        jobAdapter = new JobAdapter(this, filteredList);
        rvJobList.setAdapter(jobAdapter);

        // 筛选按钮
        btnFilter.setOnClickListener(v -> showFilterDialog());

        // 搜索输入回车简单实现
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            String keyword = etSearch.getText().toString().trim();
            filterJobList(keyword, filterCity, filterEducation, filterExperience);
            return true;
        });

        setupSwipeRefresh();
        loadData();
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this::refreshData);
    }

    private void loadData() {
        if (isLoading) return;
        isLoading = true;
        showLoading();

        // 模拟网络请求
        handler.postDelayed(() -> {
            try {
                List<JobItem> jobs = getMockData();
                jobAdapter.updateData(jobs);
                showContent();
            } catch (Exception e) {
                showError();
            } finally {
                isLoading = false;
            }
        }, 1500);
    }

    private void refreshData() {
        if (isLoading) {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        isLoading = true;

        // 模拟网络请求
        handler.postDelayed(() -> {
            try {
                List<JobItem> jobs = getMockData();
                jobAdapter.updateData(jobs);
                Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "刷新失败", Toast.LENGTH_SHORT).show();
            } finally {
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
            }
        }, 1500);
    }

    private void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        rvJobList.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
    }

    private void showContent() {
        loadingLayout.setVisibility(View.GONE);
        rvJobList.setVisibility(View.VISIBLE);
        errorLayout.setVisibility(View.GONE);
    }

    private void showError() {
        loadingLayout.setVisibility(View.GONE);
        rvJobList.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    private List<JobItem> getMockData() {
        List<JobItem> jobs = new ArrayList<>();
        jobs.add(new JobItem("Android开发工程师", "15k-25k", "腾讯", "深圳", "3-5年", "本科", 
            List.of("Android", "Java", "Kotlin"), R.drawable.ic_launcher, "腾讯HR"));
        jobs.add(new JobItem("iOS开发工程师", "20k-35k", "阿里巴巴", "杭州", "3-5年", "本科", 
            List.of("iOS", "Swift", "Objective-C"), R.drawable.ic_launcher, "阿里HR"));
        return jobs;
    }

    // 显示筛选对话框
    private void showFilterDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_filter, null);

        Spinner spinnerCity = dialogView.findViewById(R.id.spinnerCity);
        Spinner spinnerEducation = dialogView.findViewById(R.id.spinnerEducation);
        Spinner spinnerExperience = dialogView.findViewById(R.id.spinnerExperience);

        // 城市选项
        String[] cities = {"全部", "北京", "上海", "深圳", "杭州", "广州"};
        String[] educations = {"全部", "本科", "硕士"};
        String[] experiences = {"全部", "应届毕业生", "1-3年", "3-5年", "5-10年"};

        spinnerCity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities));
        spinnerEducation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, educations));
        spinnerExperience.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, experiences));

        // 预选项
        if (!filterCity.isEmpty()) {
            for (int i = 0; i < cities.length; ++i)
                if (filterCity.equals(cities[i])) spinnerCity.setSelection(i);
        }
        if (!filterEducation.isEmpty()) {
            for (int i = 0; i < educations.length; ++i)
                if (filterEducation.equals(educations[i])) spinnerEducation.setSelection(i);
        }
        if (!filterExperience.isEmpty()) {
            for (int i = 0; i < experiences.length; ++i)
                if (filterExperience.equals(experiences[i])) spinnerExperience.setSelection(i);
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("筛选职位")
                .setView(dialogView)
                .setPositiveButton("确定", (d, w) -> {
                    // 获取选择
                    filterCity = spinnerCity.getSelectedItemPosition() == 0 ? "" : cities[spinnerCity.getSelectedItemPosition()];
                    filterEducation = spinnerEducation.getSelectedItemPosition() == 0 ? "" : educations[spinnerEducation.getSelectedItemPosition()];
                    filterExperience = spinnerExperience.getSelectedItemPosition() == 0 ? "" : experiences[spinnerExperience.getSelectedItemPosition()];
                    filterJobList(etSearch.getText().toString().trim(), filterCity, filterEducation, filterExperience);
                })
                .setNegativeButton("重置", (d, w) -> {
                    filterCity = "";
                    filterEducation = "";
                    filterExperience = "";
                    filterJobList(etSearch.getText().toString().trim(), "", "", "");
                })
                .create();
        dialog.show();
    }

    // 实现筛选和搜索
    private void filterJobList(String keyword, String city, String education, String experience) {
        filteredList = new ArrayList<>();
        for (JobItem item : jobList) {
            boolean match = true;
            if (!keyword.isEmpty()) {
                match = (item.jobTitle.contains(keyword) || item.company.contains(keyword) ||
                        item.city.contains(keyword) || item.recruiter.contains(keyword));
            }
            if (match && !city.isEmpty()) {
                match = item.city.contains(city);
            }
            if (match && !education.isEmpty()) {
                match = item.education.contains(education);
            }
            if (match && !experience.isEmpty()) {
                match = item.experience.contains(experience);
            }
            if (match) filteredList.add(item);
        }
        jobAdapter = new JobAdapter(this, filteredList);
        rvJobList.setAdapter(jobAdapter);

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "没有符合条件的职位", Toast.LENGTH_SHORT).show();
        }
    }
}
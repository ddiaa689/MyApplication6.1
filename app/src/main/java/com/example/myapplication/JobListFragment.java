package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobListFragment extends Fragment {

    private JobAdapter jobAdapter;
    private List<JobItem> jobList = new ArrayList<>();
    private List<JobItem> allJobs = new ArrayList<>();

    public JobListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);

        RecyclerView rvJobList = view.findViewById(R.id.rvJobList);
        rvJobList.setLayoutManager(new LinearLayoutManager(getContext()));

        // 初始化职位数据
        allJobs.add(new JobItem("Java开发工程师", "15-22K·14薪", "字节跳动", "北京·海淀", "3-5年", "本科", Arrays.asList("五险一金", "弹性工作", "年度旅游"), R.drawable.bg_avatar, "HR·张经理"));
        allJobs.add(new JobItem("Android高级工程师", "18-28K·13薪", "美团", "上海·浦东", "5-10年", "硕士", Arrays.asList("下午茶", "股票期权", "带薪年假"), R.drawable.bg_avatar, "CTO·王总"));
        allJobs.add(new JobItem("产品经理", "12-20K·15薪", "腾讯", "深圳·南山", "3-5年", "本科", Arrays.asList("免费健身", "成长空间大"), R.drawable.bg_avatar, "经理·李女士"));
        // ... 可继续添加职位

        jobList.addAll(allJobs);

        jobAdapter = new JobAdapter(getContext(), jobList);
        rvJobList.setAdapter(jobAdapter);

        // 搜索栏逻辑
        EditText etJobSearch = view.findViewById(R.id.etJobSearch);
        etJobSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                String keyword = s.toString().toLowerCase();
                jobList.clear();
                for (JobItem item : allJobs) {
                    if (item.getTitle().toLowerCase().contains(keyword)
                            || item.getCompany().toLowerCase().contains(keyword)
                            || item.getLocation().toLowerCase().contains(keyword)) {
                        jobList.add(item);
                    }
                }
                jobAdapter.notifyDataSetChanged();
            }
        });

        // 筛选模块逻辑
        ImageView ivJobFilter = view.findViewById(R.id.ivJobFilter);
        ivJobFilter.setOnClickListener(v -> showFilterDialog());

        return view;
    }

    // 筛选弹窗
    private void showFilterDialog() {
        String[] cities = {"全部", "北京", "上海", "深圳", "广州", "杭州"};
        String[] educations = {"全部", "本科", "硕士", "博士"};
        String[] experiences = {"全部", "1-3年", "3-5年", "5-10年"};

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_filter, null);
        Spinner spinnerCity = dialogView.findViewById(R.id.spinnerCity);
        Spinner spinnerEducation = dialogView.findViewById(R.id.spinnerEducation);
        Spinner spinnerExperience = dialogView.findViewById(R.id.spinnerExperience);

        spinnerCity.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, cities));
        spinnerEducation.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, educations));
        spinnerExperience.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, experiences));

        new AlertDialog.Builder(getContext())
                .setTitle("筛选职位")
                .setView(dialogView)
                .setPositiveButton("确定", (dialog, which) -> {
                    String location = cities[spinnerCity.getSelectedItemPosition()];
                    String degree = educations[spinnerEducation.getSelectedItemPosition()];
                    String year = experiences[spinnerExperience.getSelectedItemPosition()];

                    jobList.clear();
                    for (JobItem item : allJobs) {
                        boolean match = true;
                        if (!location.equals("全部") && !item.getLocation().contains(location)) match = false;
                        if (!degree.equals("全部") && !item.getDegree().equals(degree)) match = false;
                        if (!year.equals("全部") && !item.getExp().equals(year)) match = false;
                        if (match) jobList.add(item);
                    }
                    jobAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
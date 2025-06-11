package com.example.myapplication;

import java.util.List;

public class JobItem {


    public String jobTitle;
    public String salary;
    public String company;
    public String city;
    public String experience;
    public String education;
    public List<String> tags;
    public int avatarResId; // drawable 资源id
    public String recruiter;

    public String getTitle() { return jobTitle; }
    public String getCompany() { return company; }
    public String getLocation() { return city; }
    public String getDegree() { return education; }
    public String getExp() { return experience; }
    public JobItem(String jobTitle, String salary, String company, String city, String experience,
                   String education, List<String> tags, int avatarResId, String recruiter) {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.company = company;
        this.city = city;
        this.experience = experience;
        this.education = education;
        this.tags = tags;
        this.avatarResId = avatarResId;
        this.recruiter = recruiter;
    }
}
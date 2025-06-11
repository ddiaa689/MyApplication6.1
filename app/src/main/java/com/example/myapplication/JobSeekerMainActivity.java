package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JobSeekerMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseeker_main);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);

        // 默认显示职位Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new JobListFragment())
                .commit();

        nav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.nav_job) {
                fragment = new JobListFragment();
            } else if (item.getItemId() == R.id.nav_message) {
                fragment = new MessageFragment();
            } else if (item.getItemId() == R.id.nav_mine) {
                fragment = new MineFragment();
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
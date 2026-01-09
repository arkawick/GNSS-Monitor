package com.gnssmonitor;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;

import com.gnssmonitor.databinding.ActivityReadConfigBinding;
import com.gnssmonitor.ui.config.ConfigPagerAdapter;

public class ReadConfigActivity extends AppCompatActivity {

    private ActivityReadConfigBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Keep screen on and disable auto-rotate
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        binding = ActivityReadConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup action bar
        setSupportActionBar(binding.toolbar);

        // Setup tab layout
        var configPagerAdapter = new ConfigPagerAdapter(this);
        binding.viewPager.setAdapter(configPagerAdapter);
        new TabLayoutMediator(binding.tabs, binding.viewPager,
                (tab, position) -> tab.setText(configPagerAdapter.getPageTitle(position))
        ).attach();
    }
}

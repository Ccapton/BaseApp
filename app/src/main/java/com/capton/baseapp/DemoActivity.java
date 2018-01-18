package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by capton on 2018/1/18.
 */

public class DemoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setCheckedItem(R.id.item1);
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    ToastUtils.showShort(item.getTitle());
                return true;
            }
        });

    }
}

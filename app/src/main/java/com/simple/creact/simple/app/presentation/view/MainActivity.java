package com.simple.creact.simple.app.presentation.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.simple.creact.simple.R;
import com.simple.creact.simple.app.data.di.common.component.DaggerProductionComponent;
import com.simple.creact.simple.app.presentation.view.common.BaseActivity;
import com.simple.creact.simple.app.presentation.view.github.SearchRepoActivity;
import com.simple.creact.simple.databinding.ActivityMainBinding;

/**
 * @author:YJJ
 * @date:2016/3/17
 * @email:yangjianjun@117go.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //set binding component
        DataBindingUtil.setDefaultComponent(DaggerProductionComponent.builder().build());
    }

    public void goRepo(View v) {
        Intent intent = new Intent(this, SearchRepoActivity.class);
        startActivity(intent);
    }
}

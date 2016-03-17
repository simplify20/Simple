package com.simple.creact.simple.app.presentation.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.simple.creact.simple.app.data.di.common.component.DaggerProductionComponent;

/**
 * @author:YJJ
 * @date:2016/3/17
 * @email:yangjianjun@117go.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set binding component
        DataBindingUtil.setDefaultComponent(DaggerProductionComponent.builder().build());
    }
}

package com.lqn.dictionary.scenes.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lqn.dictionary.R;
import com.lqn.dictionary.utils.Utils;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Utils.setHeaderPage(getString(R.string.txt_setting),getResources().getColor(R.color.main_setting),findViewById(R.id.txtTitle),
                findViewById(R.id.header_layout),findViewById(R.id.btnBack),this
        );
        Utils.changeStatusBarColor(this,R.color.main_setting);
    }
}
package com.lqn.dictionary.scenes.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.lqn.dictionary.R;
import com.lqn.dictionary.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.setHeaderPage(getString(R.string.txt_account),getResources().getColor(R.color.main_recent),findViewById(R.id.txtTitle),
                findViewById(R.id.header_layout),findViewById(R.id.btnBack),this
        );
        Utils.changeStatusBarColor(this,R.color.main_recent);
    }
}
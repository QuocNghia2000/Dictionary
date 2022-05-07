package com.lqn.dictionary.scenes.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lqn.dictionary.R;

public class HomeActivity extends AppCompatActivity {

    ImageView btnMicro;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        handleEvent();
    }

    private void initView() {
        btnMicro = findViewById(R.id.iv_micro);
        edtSearch = findViewById(R.id.edtSearch);
    }

    private void handleEvent() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtSearch.getText().toString().isEmpty())
                {
                    btnMicro.setVisibility(View.VISIBLE);
                    findViewById(R.id.iv_clear).setVisibility(View.GONE);
                }
                else {
                    btnMicro.setVisibility(View.GONE);
                    findViewById(R.id.iv_clear).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findViewById(R.id.iv_clear).setOnClickListener(v -> {
            edtSearch.setText("");
        });
    }

    public void onClickItemHome(View view)
    {
        Log.d("TAKKK","dsd");
    }
}
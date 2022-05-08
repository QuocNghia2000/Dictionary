package com.lqn.dictionary.scenes.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.lqn.dictionary.R;
import com.lqn.dictionary.scenes.login.LoginActivity;
import com.lqn.dictionary.scenes.recentword.RecentWordActivity;
import com.lqn.dictionary.scenes.setting.SettingActivity;
import com.lqn.dictionary.scenes.translate.text.TranslateTextActivity;
import com.lqn.dictionary.scenes.v_e_dictionary.VEDictionaryActivity;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 1000;
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

        btnMicro.setOnClickListener(v -> {
            openSpeechToTextIntent();
        });
    }

    private void openSpeechToTextIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
//                    getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            showNotice(getString(R.string.speech_not_supported));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtSearch.setText(result.get(0));
                }
                break;
            }

        }
    }

    private void showNotice(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    public void onClickItemHome(View view)
    {
        Log.d("TAKKK","dsd");
        switch (view.getId())
        {
            case R.id.btnVEDictionary:
                startActivity(new Intent(HomeActivity.this, VEDictionaryActivity.class));
                break;
            case R.id.btnTranslateText:
                startActivity(new Intent(HomeActivity.this, TranslateTextActivity.class));
                break;
            case R.id.btnRecentWord:
                startActivity(new Intent(HomeActivity.this, RecentWordActivity.class));
                break;
            case R.id.btnAccount:
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
            case R.id.btnSetting:
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                break;
        }
    }
}
package com.lqn.dictionary.scenes.translate.text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.lqn.dictionary.R;
import com.lqn.dictionary.adapter.translate.HistoryWordAdapter;
import com.lqn.dictionary.callback.translate.OnItemListener;

import java.util.ArrayList;
import java.util.Locale;

public class TranslateTextActivity extends AppCompatActivity {
    RecyclerView rvWord;
    ArrayList<String> mWords;
    TextView btnTranslateEV, btnTranslateVE, edtInputTranslate, edtResult;
    ImageView btnMicro;
    ClipboardManager clipboardManager;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_text);
        initView();
        handleEvent();
    }

    private void handleEvent() {
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

        btnTranslateEV.setOnClickListener(v -> {
            translateText(TranslateLanguage.ENGLISH, TranslateLanguage.VIETNAMESE);
            findViewById(R.id.frmResult).setVisibility(View.VISIBLE);
        });

        btnTranslateVE.setOnClickListener(v -> {
            translateText(TranslateLanguage.VIETNAMESE, TranslateLanguage.ENGLISH);
            findViewById(R.id.frmResult).setVisibility(View.VISIBLE);
        });

        edtInputTranslate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edtInputTranslate.getText().toString().isEmpty())
                {
                    btnMicro.setVisibility(View.VISIBLE);
                    findViewById(R.id.btnClearText).setVisibility(View.GONE);
                }
                else {
                    btnMicro.setVisibility(View.GONE);
                    findViewById(R.id.btnClearText).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void OnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnCopy:
                clipboardManager.setText(edtResult.getText().toString());
                showNotice("Copied text!");
                break;
            case R.id.btnClearText:
                edtInputTranslate.setText("");
                findViewById(R.id.frmResult).setVisibility(View.GONE);
                break;
            case R.id.btnSoundSource:
                textToSpeech.speak(edtInputTranslate.getText().toString().trim(), TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }

    private void showNotice(String message)
    {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    private void translateText(String fromLanguage, String targetlanuage)
    {
        TranslatorOptions options =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(fromLanguage)
                        .setTargetLanguage(targetlanuage)
                        .build();
        final Translator translator =
                Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                translator.translate(edtInputTranslate.getText().toString().trim())
                                        .addOnSuccessListener(
                                                new OnSuccessListener() {
                                                    @Override
                                                    public void onSuccess(Object o) {
                                                        edtResult.setText(o.toString());
                                                    }
                                                })
                                        .addOnFailureListener(
                                                new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Error.
                                                        // ...
                                                    }
                                                });
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be downloaded or other internal error.
                                // ...
                            }
                        });
    }

    private void initView() {
        btnTranslateEV = findViewById(R.id.btnTranslateEV);
        btnTranslateVE = findViewById(R.id.btnTranslateVE);
        edtInputTranslate = findViewById(R.id.editInputTranslate);
        edtResult = findViewById(R.id.txtResultTranslate);
        btnMicro = findViewById(R.id.btnMicro);
        rvWord = findViewById(R.id.rv_history_translate_text);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        mWords = new ArrayList<>();
        mWords.add("ddddd");
        mWords.add("ddddd");
        mWords.add("ddddd");
        rvWord.setLayoutManager(new LinearLayoutManager(this));
        HistoryWordAdapter adapter = new HistoryWordAdapter(this,mWords , new OnItemListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemSound(int position) {

            }

            @Override
            public void onItemStar(int position) {

            }
        });
        rvWord.setAdapter(adapter);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
//                    textToSpeech.setLanguage(new Locale(TranslateLanguage.VIETNAMESE));
                    textToSpeech.setLanguage(Locale.KOREA);
                }
            }
        });
    }
}
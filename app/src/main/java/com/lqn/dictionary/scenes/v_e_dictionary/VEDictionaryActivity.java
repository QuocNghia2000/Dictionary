package com.lqn.dictionary.scenes.v_e_dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.lqn.dictionary.R;
import com.lqn.dictionary.adapter.search.HistoryWordAdapter;
import com.lqn.dictionary.callback.search.OnItemListener;

import java.util.ArrayList;

public class VEDictionaryActivity extends AppCompatActivity {

    RecyclerView rvWord;
    ArrayList<String> mWords;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedictionary);
        initView();
        handleEvent();
    }

    private void handleEvent() {
        ivBack.setOnClickListener(v ->{
            finish();
        });
    }

    private void initView() {
        rvWord = findViewById(R.id.rv_word);
        ivBack = findViewById(R.id.iv_back);

        mWords = new ArrayList<>();
        mWords.add("SSSSSS");
        mWords.add("BBBB");
        mWords.add("NNN");
        rvWord.setLayoutManager(new LinearLayoutManager(this));
        HistoryWordAdapter adapter = new HistoryWordAdapter(this, mWords, new OnItemListener() {
            @Override
            public void onItemPick(int position) {

            }

            @Override
            public void onItemDelete(int position) {

            }

            @Override
            public void onItemClick(int position) {

            }
        });
        rvWord.setAdapter(adapter);
    }
}
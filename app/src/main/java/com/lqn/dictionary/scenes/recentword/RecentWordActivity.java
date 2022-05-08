package com.lqn.dictionary.scenes.recentword;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lqn.dictionary.R;
import com.lqn.dictionary.adapter.RecentWordAdapter;
import com.lqn.dictionary.adapter.translate.HistoryWordAdapter;
import com.lqn.dictionary.callback.recentword.OnItemListener;
import com.lqn.dictionary.utils.Utils;

import java.util.ArrayList;

public class RecentWordActivity extends AppCompatActivity {
    private ArrayList<String> mWords;
    RecyclerView rvWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_word);

        Utils.setHeaderPage(getString(R.string.txt_recent_word),getResources().getColor(R.color.main_recent),findViewById(R.id.txtTitle),
                findViewById(R.id.header_layout),findViewById(R.id.btnBack),this
                );
        Utils.changeStatusBarColor(this,R.color.main_recent);
        initView();
        handleEvent();
    }

    private void handleEvent() {
    }

    private void initView() {
        rvWord = findViewById(R.id.rv_word);
        mWords = new ArrayList<>();
        mWords.add("ddddd");
        mWords.add("ddddd");
        mWords.add("ddddd");
        rvWord.setLayoutManager(new LinearLayoutManager(this));
        RecentWordAdapter adapter = new RecentWordAdapter(this, mWords, new OnItemListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemCheck(int position) {

            }

            @Override
            public void onItemSound(int position) {

            }

            @Override
            public void onItemStar(int position) {

            }
        });
        rvWord.setAdapter(adapter);
    }
}
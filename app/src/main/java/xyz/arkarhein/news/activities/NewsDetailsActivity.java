package xyz.arkarhein.news.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.arkarhein.news.R;

/**
 * Created by Arkar Hein on 12/9/2017.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
    }
}

package xyz.arkarhein.news.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.adapters.ImagesInNewsDetailsAdapter;
import xyz.arkarhein.news.data.model.NewsModel;
import xyz.arkarhein.news.data.vo.NewsVO;

/**
 * Created by Arkar Hein on 12/9/2017.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    @BindView(R.id.iv_eleven_logo)
    ImageView ivElevenLogo;

    @BindView(R.id.tv_eleven_name)
    TextView tvElevenName;

    @BindView(R.id.tv_elven_info)
    TextView tvDate;

    @BindView(R.id.news_details_toolbar)
    Toolbar toolbar;

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    private ImagesInNewsDetailsAdapter mimagesInNewsDetailsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);

        /*setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);*/

        mimagesInNewsDetailsAdapter = new ImagesInNewsDetailsAdapter();

        vpNewsDetailsImages.setAdapter(mimagesInNewsDetailsAdapter);

        String newsId = getIntent().getStringExtra("news_id");

        NewsVO news = NewsModel.getsObjInstance().getNewsById(newsId);
        bindData(news);

    }

    private void bindData(NewsVO news) {
        tvNewsDetails.setText(news.getDetails());
        tvElevenName.setText(news.getPublication().getTitle());
        tvDate.setText(news.getPostedDate());

        Glide.with(ivElevenLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivElevenLogo);


    }

}

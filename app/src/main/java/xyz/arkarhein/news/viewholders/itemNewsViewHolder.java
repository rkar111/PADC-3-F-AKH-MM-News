package xyz.arkarhein.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.data.vo.NewsVO;
import xyz.arkarhein.news.delegates.NewsActionDelegate;

/**
 * Created by Arkar Hein on 12/3/2017.
 */

public class itemNewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_publication_date)
    TextView tvPublicationDate;

    @BindView(R.id.tv_publication_brief_news)
    TextView tvPublicationBriefNews;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.iv_publication_image)
    ImageView ivPublicationImage;

    private NewsActionDelegate mNewsActionDelegate;

    public itemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mNewsActionDelegate = newsActionDelegate;
    }

    @OnClick(R.id.cv_news_items_root)
    public void onTapNewsItem(View view) {
        //Toast.makeText(view.getContext(), "News Item Clicked", Toast.LENGTH_LONG).show();
        mNewsActionDelegate.onTapNewsItem();
    }

    public void setNews(NewsVO news) {
        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvPublicationDate.setText(news.getPostedDate());
        tvPublicationBriefNews.setText(news.getBrief());


        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        if (news.getImages() != null) {
            ivPublicationImage.setVisibility(View.VISIBLE);
            Glide.with(ivPublicationImage.getContext())
                    .load(news.getImages().get(0))
                    .into(ivPublicationImage);
        } else {
            ivPublicationImage.setVisibility(View.GONE);
        }

    }

}

package xyz.arkarhein.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.delegates.NewsActionDelegate;

/**
 * Created by Arkar Hein on 12/3/2017.
 */

public class itemNewsViewHolder extends RecyclerView.ViewHolder {

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
}

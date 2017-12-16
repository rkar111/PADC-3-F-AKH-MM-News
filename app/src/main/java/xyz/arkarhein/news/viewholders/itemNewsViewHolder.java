package xyz.arkarhein.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.arkarhein.news.R;

/**
 * Created by Arkar Hein on 12/3/2017.
 */

public class itemNewsViewHolder extends RecyclerView.ViewHolder {


    public itemNewsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.cv_news_items_root)
    public void onTapNewsItem(View view) {
        Toast.makeText(view.getContext(), "News Item Clicked", Toast.LENGTH_LONG).show();
    }
}

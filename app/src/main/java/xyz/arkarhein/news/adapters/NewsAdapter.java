package xyz.arkarhein.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.arkarhein.news.R;
import xyz.arkarhein.news.data.vo.NewsVO;
import xyz.arkarhein.news.delegates.NewsActionDelegate;
import xyz.arkarhein.news.viewholders.itemNewsViewHolder;

/**
 * Created by Arkar Hein on 12/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<itemNewsViewHolder> {

    private NewsActionDelegate mNewsActionDelegate;

    private List<NewsVO> mNewsList;

    public NewsAdapter(NewsActionDelegate newsActionDelegate) {
        mNewsActionDelegate = newsActionDelegate;
        mNewsList = new ArrayList<>();
    }

    @Override
    public itemNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);
        View newsItemView = inflator.inflate(R.layout.item_news, parent, false);
        itemNewsViewHolder itemNewsViewHolder = new itemNewsViewHolder(newsItemView,mNewsActionDelegate);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(itemNewsViewHolder holder, int position) {
        holder.setNews(mNewsList.get(position));

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNews(List<NewsVO> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }
}

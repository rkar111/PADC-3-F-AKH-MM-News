package xyz.arkarhein.news.adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.arkarhein.news.R;
import xyz.arkarhein.news.viewholders.itemNewsViewHolder;

/**
 * Created by Arkar Hein on 12/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);
        View newsItemView = inflator.inflate(R.layout.item_news, parent, false);
        itemNewsViewHolder itemNewsViewHolder=new itemNewsViewHolder(newsItemView);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}

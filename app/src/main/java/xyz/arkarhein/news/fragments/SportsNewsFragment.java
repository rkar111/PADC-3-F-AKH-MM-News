package xyz.arkarhein.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.adapters.SportsNewsAdapter;

/**
 * Created by Arkar Hein on 1/7/2018.
 */

public class SportsNewsFragment extends Fragment {

    @BindView(R.id.rv_sports_news)
    RecyclerView rvSportsNews;

    SportsNewsAdapter mSportsNewsAdapter = new SportsNewsAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports_news, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvSportsNews.setLayoutManager(linearLayoutManager);
        rvSportsNews.setAdapter(mSportsNewsAdapter);

        return view;
    }
}

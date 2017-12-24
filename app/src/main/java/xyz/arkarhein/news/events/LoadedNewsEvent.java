package xyz.arkarhein.news.events;

import java.util.List;

import xyz.arkarhein.news.data.vo.NewsVO;

/**
 * Created by Arkar Hein on 12/24/2017.
 */

public class LoadedNewsEvent {

    private List<NewsVO> newsList;

    public LoadedNewsEvent(List<NewsVO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}

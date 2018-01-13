package xyz.arkarhein.news.data.model;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import xyz.arkarhein.news.data.vo.NewsVO;
import xyz.arkarhein.news.events.LoadedNewsEvent;
import xyz.arkarhein.news.network.HttpUrlConnectionDataAgent;
import xyz.arkarhein.news.network.NewsDataAgent;
import xyz.arkarhein.news.network.OkHttpDataAgent;
import xyz.arkarhein.news.network.RetrofitDataAgent;

/**
 * Created by Arkar Hein on 12/23/2017.
 */

public class NewsModel {

    private static NewsModel sObjInstance;

    private NewsDataAgent mDataAgent;

    private Map<String, NewsVO> mNews;

    private NewsModel() {

        //mDataAgent = HttpUrlConnectionDataAgent.getsObjInstance();
        //mDataAgent = OkHttpDataAgent.getsObjInstance();
        mDataAgent = RetrofitDataAgent.getsObjInstance();
        mNews = new HashMap<>();

        EventBus.getDefault().register(this);
    }


    public static NewsModel getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new NewsModel();
        }
        return sObjInstance;
    }


    /**
     * Loading News From network API.
     */
    public void loadNews() {
        mDataAgent.loadNews();
    }

    /**
     * Get News Object by Id.
     * @param newsId
     * @return
     */

    public NewsVO getNewsById(String newsId){
        return mNews.get(newsId);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsLoaded(LoadedNewsEvent event) {
        for (NewsVO news : event.getNewsList()) {
            mNews.put(news.getNewsId(), news);
        }

    }
}

package xyz.arkarhein.news.data.model;

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

    private NewsModel() {

        //mDataAgent = HttpUrlConnectionDataAgent.getsObjInstance();
        //mDataAgent = OkHttpDataAgent.getsObjInstance();
        mDataAgent = RetrofitDataAgent.getsObjInstance();

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

}

package xyz.arkarhein.news.network.responses;

import java.util.List;

import xyz.arkarhein.news.data.vo.NewsVO;

/**
 * Created by Arkar Hein on 12/24/2017.
 */

public class GetNewsResponse {
    private int code;
    private String message;
    private String apiVersion;
    private String page;
    private List<NewsVO> mmNews;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewsVO> getMmNews() {
        return mmNews;
    }
}

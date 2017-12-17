package xyz.arkarhein.news.data.vo;

import java.util.List;

/**
 * Created by Arkar Hein on 12/17/2017.
 */

public class NewsVO {

    private String newsId;
    private String brief;
    private String details;
    private List<String> images;
    private String postedDate;

    private PublicationVO publication;

    private List<FavoriteVO> favorite;

    private List<CommentVO> comment;

    private List<SentTosVO> sentTos;

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavoriteVO> getFavorite() {
        return favorite;
    }

    public List<CommentVO> getComment() {
        return comment;
    }

    public List<SentTosVO> getSentTos() {
        return sentTos;
    }
}



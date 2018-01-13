package xyz.arkarhein.news.delegates;

import xyz.arkarhein.news.data.vo.NewsVO;

/**
 * Created by Arkar Hein on 12/17/2017.
 */

public interface NewsActionDelegate {

    void onTapNewsItem(NewsVO tappedNews);

    void onTapCommentButton();

    void onTapSendButton();

    void onTapFavoriteButton();
}

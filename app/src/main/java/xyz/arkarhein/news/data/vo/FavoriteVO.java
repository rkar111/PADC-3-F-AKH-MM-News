package xyz.arkarhein.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arkar Hein on 12/17/2017.
 */

public class FavoriteVO {
    @SerializedName("favorite-id")
    private String favoriteId;

    @SerializedName("favorite-date")
    private String favoriteDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }
}

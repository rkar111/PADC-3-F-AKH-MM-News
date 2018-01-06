package xyz.arkarhein.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arkar Hein on 12/17/2017.
 */

public class CommentVO {

    @SerializedName("comment-id")
    private String commentId;
    private String comment;

    @SerializedName("comment-date")
    private String commentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }
}

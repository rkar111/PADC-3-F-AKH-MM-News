package xyz.arkarhein.news.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arkar Hein on 12/17/2017.
 */

public class SentTosVO {

    @SerializedName("send-to-id")
    private String sendToId;

    @SerializedName("sent-date")
    private String sentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @SerializedName("received-user")
    private ActedUserVO receivedUser;

    public String getSendToId() {
        return sendToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ActedUserVO getReceivedUser() {
        return receivedUser;
    }
}

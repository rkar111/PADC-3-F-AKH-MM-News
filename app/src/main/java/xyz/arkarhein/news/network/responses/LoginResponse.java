package xyz.arkarhein.news.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.arkarhein.news.data.vo.LoginUserVO;

/**
 * Created by Arkar Hein on 1/21/2018.
 */

public class LoginResponse {
    private int code;

    private String message;

    @SerializedName("login_user")
    private LoginUserVO loginUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}

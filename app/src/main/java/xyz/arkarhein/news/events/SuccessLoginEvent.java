package xyz.arkarhein.news.events;

import android.content.Context;

import xyz.arkarhein.news.data.vo.LoginUserVO;

/**
 * Created by Arkar Hein on 1/21/2018.
 */

public class SuccessLoginEvent {

    private LoginUserVO loginUser;

    private Context context;

    public SuccessLoginEvent(LoginUserVO loginUser, Context context) {
        this.loginUser = loginUser;
        this.context = context;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }

    public Context getContext() {
        return context;
    }
}

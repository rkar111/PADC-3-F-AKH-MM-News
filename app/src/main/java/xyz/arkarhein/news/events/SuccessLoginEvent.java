package xyz.arkarhein.news.events;

import xyz.arkarhein.news.data.vo.LoginUserVO;

/**
 * Created by Arkar Hein on 1/21/2018.
 */

public class SuccessLoginEvent {

    private LoginUserVO loginUser;

    public SuccessLoginEvent(LoginUserVO loginUser) {
        this.loginUser = loginUser;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}

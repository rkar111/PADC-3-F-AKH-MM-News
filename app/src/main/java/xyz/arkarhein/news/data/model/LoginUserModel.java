package xyz.arkarhein.news.data.model;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.arkarhein.news.data.vo.LoginUserVO;
import xyz.arkarhein.news.events.SuccessLoginEvent;
import xyz.arkarhein.news.events.UserLogoutEvent;
import xyz.arkarhein.news.network.NewsDataAgent;
import xyz.arkarhein.news.network.RetrofitDataAgent;

/**
 * Created by Arkar Hein on 1/20/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sObjInstance;

    private NewsDataAgent mDataAgent;

    private LoginUserVO mLogInUSer;

    private LoginUserModel() {

        mDataAgent = RetrofitDataAgent.getsObjInstance();

        EventBus.getDefault().register(this);
    }

    public static LoginUserModel getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new LoginUserModel();
        }
        return sObjInstance;
    }

    public void loginUser(String phoneNo, String password) {

        mDataAgent.loginUser(phoneNo, password);

    }

    public boolean isUserLogin() {
        return mLogInUSer != null;
    }

    public void logout() {
        mLogInUSer = null;
        UserLogoutEvent event = new UserLogoutEvent();
        EventBus.getDefault().post(event);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUSerSuccess(SuccessLoginEvent event) {
        mLogInUSer = event.getLoginUser();
    }
}

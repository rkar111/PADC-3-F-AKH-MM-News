package xyz.arkarhein.news.data.model;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.arkarhein.news.data.vo.LoginUserVO;
import xyz.arkarhein.news.events.SuccessLoginEvent;
import xyz.arkarhein.news.events.UserLogoutEvent;
import xyz.arkarhein.news.network.NewsDataAgent;
import xyz.arkarhein.news.network.RetrofitDataAgent;
import xyz.arkarhein.news.utils.AppConstants;

/**
 * Created by Arkar Hein on 1/20/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sObjInstance;

    private NewsDataAgent mDataAgent;

    private LoginUserVO mLogInUSer;

    private LoginUserModel(Context context) {

        mDataAgent = RetrofitDataAgent.getsObjInstance();

        EventBus.getDefault().register(this);

        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,
                Context.MODE_PRIVATE);
        int loginUserID = sharedPreferences.getInt(AppConstants.LOGIN_USER_ID, -1);
        if (loginUserID != -1) {
            //user has already logged in.
            String name = sharedPreferences.getString(AppConstants.LOGIN_NAME, null);
            String email = sharedPreferences.getString(AppConstants.LOGIN_EMAIL, null);
            String phoneNo = sharedPreferences.getString(AppConstants.LOGIN_PHONE_NO, null);
            String profileUrl = sharedPreferences.getString(AppConstants.LOGIN_PROFILE_URL, null);
            String coverUrl = sharedPreferences.getString(AppConstants.LOGIN_COVER_URL, null);
            mLogInUSer = new LoginUserVO(loginUserID, name, email, phoneNo, profileUrl, coverUrl);
        }


    }

    public static LoginUserModel getsObjInstance(Context context) {
        if (sObjInstance == null) {
            sObjInstance = new LoginUserModel(context);
        }
        return sObjInstance;
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
        //Save user data in SharedPreferences.
        SharedPreferences sharedPreferences = event.getContext().getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConstants.LOGIN_USER_ID, event.getLoginUser().getUserId());
        editor.putString(AppConstants.LOGIN_NAME, event.getLoginUser().getName());
        editor.putString(AppConstants.LOGIN_EMAIL, event.getLoginUser().getEmail());
        editor.putString(AppConstants.LOGIN_PHONE_NO, event.getLoginUser().getPhoneNo());
        editor.putString(AppConstants.LOGIN_PROFILE_URL, event.getLoginUser().getProfileUrl());
        editor.putString(AppConstants.LOGIN_COVER_URL, event.getLoginUser().getCoverUrl());
        editor.apply();
    }

    public void loginUser(Context context, String phoneNo, String password) {
        mDataAgent.loginUser(context, phoneNo, password);
    }

    public LoginUserVO getmLogInUSer() {
        return mLogInUSer;
    }

}

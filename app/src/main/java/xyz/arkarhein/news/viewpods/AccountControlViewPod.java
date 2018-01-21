package xyz.arkarhein.news.viewpods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.POST;
import xyz.arkarhein.news.BuildConfig;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.data.model.LoginUserModel;
import xyz.arkarhein.news.delegates.BeforeLoginDelegate;
import xyz.arkarhein.news.delegates.LogInUserDelegate;
import xyz.arkarhein.news.events.SuccessLoginEvent;
import xyz.arkarhein.news.events.UserLogoutEvent;

/**
 * Created by Arkar Hein on 1/21/2018.
 */

public class AccountControlViewPod extends FrameLayout {

    @BindView(R.id.vp_before_login)
    BeforeLoginViewPod vpBeforeLogin;

    @BindView(R.id.vp_login_user)
    LoginUserViewPod vpLoginUser;

    public AccountControlViewPod(@NonNull Context context) {
        super(context);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
        refreshUserSession();
        EventBus.getDefault().register(this);
    }

    public void setDelegate(BeforeLoginDelegate beforeLoginDelegate) {
        vpBeforeLogin.setDelegate(beforeLoginDelegate);
    }

    public void setDelegate(LogInUserDelegate logInUserDelegate) {
        vpLoginUser.setmDelegate(logInUserDelegate);
    }

    private void refreshUserSession() {
        if (LoginUserModel.getsObjInstance().isUserLogin())
            vpBeforeLogin.setVisibility(View.VISIBLE);
        vpLoginUser.setVisibility(View.GONE);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(SuccessLoginEvent event) {
        vpBeforeLogin.setVisibility(View.GONE);
        vpLoginUser.setVisibility(View.VISIBLE);

        vpLoginUser.bindData(event.getLoginUser());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutUser(UserLogoutEvent event) {
        vpBeforeLogin.setVisibility(View.VISIBLE);
        vpLoginUser.setVisibility(View.GONE);
    }
}

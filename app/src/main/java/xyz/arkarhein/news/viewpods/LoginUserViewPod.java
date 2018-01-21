package xyz.arkarhein.news.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.data.vo.LoginUserVO;
import xyz.arkarhein.news.delegates.LogInUserDelegate;

/**
 * Created by Arkar Hein on 1/14/2018.
 */

public class LoginUserViewPod extends RelativeLayout {

    private LogInUserDelegate mDelegate;

    @BindView(R.id.iv_big)
    ImageView ivBig;

    @BindView(R.id.iv_user_image)
    ImageView ivUserImage;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_user_phone_number)
    TextView tvUserPhoneNumber;

    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void bindData(LoginUserVO loginUser) {
        tvName.setText(loginUser.getName());
        tvUserPhoneNumber.setText(loginUser.getPhoneNo());

        Glide.with(ivUserImage.getContext())
                .load(loginUser.getProfileUrl())
                .into(ivUserImage);

        Glide.with(ivBig.getContext())
                .load(loginUser.getCoverUrl())
                .into(ivBig);

    }

    public void setmDelegate(LogInUserDelegate logInUserDelegate) {
        mDelegate = logInUserDelegate;
    }

    @OnClick(R.id.btn_logout)
    public void onTapLogout(View view) {
        mDelegate.onTapLogout();
    }
}

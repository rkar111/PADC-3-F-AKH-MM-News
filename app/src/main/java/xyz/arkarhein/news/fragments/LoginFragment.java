package xyz.arkarhein.news.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.data.model.LoginUserModel;
import xyz.arkarhein.news.delegates.LoginScreenDelegate;
import xyz.arkarhein.news.events.SuccessLoginEvent;

/**
 * Created by Arkar Hein on 1/20/2018.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.et_email_or_phone)
    EditText etEmailOrPhone;

    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginScreenDelegate mLoginScreenDelegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginScreenDelegate = (LoginScreenDelegate) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_register)
    public void onTapRegister(View view) {
        mLoginScreenDelegate.onTapRegister();
    }

    @OnClick(R.id.btn_login)
    public void onTapLogin(View view) {
        String phoneNo = etEmailOrPhone.getText().toString();
        String password = etPassword.getText().toString();
        LoginUserModel.getsObjInstance(getContext()).loginUser(getContext(),phoneNo, password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(SuccessLoginEvent event) {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}

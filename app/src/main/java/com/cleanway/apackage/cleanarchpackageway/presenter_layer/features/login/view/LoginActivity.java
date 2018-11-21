package com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cleanway.apackage.cleanarchpackageway.R;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.Utils;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.cache.Cache;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.Presenter;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.home.view.HomeScreen;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.presenter.LoginPresenter;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.login.presenter.LoginPresenterImp;
import com.cleanway.apackage.cleanarchpackageway.presenter_layer.features.signup.view.SignupActivity;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.EMAIL_ID;
import static com.cleanway.apackage.cleanarchpackageway.presenter_layer.Utils.AppConstant.PASSWORD;

public class LoginActivity extends AppCompatActivity implements LoginView{
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.btn_login_in)
    Button btn_login_in;

    private LoginPresenter prasenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        progress_bar.setVisibility(View.GONE);
        prasenter=new LoginPresenterImp(LoginActivity.this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("presenter_uuid", prasenter.getUuid().toString());
        cachePresenter(prasenter);
        outState.putString(EMAIL_ID, edtEmail.getText().toString());
        outState.putString(PASSWORD, edtPassword.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restorePresenter(UUID.fromString(savedInstanceState.getString("presenter_uuid")));
        edtEmail.setText(savedInstanceState.getString(EMAIL_ID));
        edtPassword.setText(savedInstanceState.getString(PASSWORD));
    }

    @OnClick(R.id.btn_login_in)
    public void onLoginClick(View view) {
        prasenter.onLogin(edtEmail.getText().toString(),edtPassword.getText().toString());

    }

    @OnClick(R.id.btn_sign_up)
    public void onSignUpClick(View view){
        Utils.startNewActivitys(this,SignupActivity.class);
    }

    private void enableUiTask(boolean enable) {
        if (enable) {
            progress_bar.setVisibility(View.INVISIBLE);
        } else {
            progress_bar.setVisibility(View.VISIBLE);
        }
        edtEmail.setEnabled(enable);
        edtPassword.setEnabled(enable);
        btn_login_in.setEnabled(enable);
    }


    @Override
    public void onEmailError() {
        edtEmail.setError(getString(R.string.donotblanks));
    }

    @Override
    public void onPasswordError() {
        edtPassword.setError(getString(R.string.donotblanks));
    }

    @Override
    public void onProgressStart() {
        enableUiTask(false);
    }

    @Override
    public void onProgressEnd() {
        enableUiTask(true);
    }

    @Override
    public void onNavigateActivity() {
        Utils.startNewActivity(this,HomeScreen.class);
        finish();
    }

    @Override
    public void onMessageShow(String message) {
        Utils.ShowToast(this,message);
    }

    @Override
    public void cachePresenter(Presenter presenter) {
        Cache.getInstance().cachePresenterFor(presenter.getUuid(), presenter);
    }

    @Override
    public void restorePresenter(UUID uuid) {
        prasenter = (LoginPresenter) Cache.getInstance().restorePresenterFor(uuid);
    }
}

package ua.com.solodilov.evgen.vjettesttask.login_fb.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import ua.com.solodilov.evgen.vjettesttask.LogUtil;
import ua.com.solodilov.evgen.vjettesttask.R;
import ua.com.solodilov.evgen.vjettesttask.login_fb.presenter.ILoginPresenter;
import ua.com.solodilov.evgen.vjettesttask.login_fb.presenter.LoginPresenter;
import ua.com.solodilov.evgen.vjettesttask.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    public static final String AUTO_START = "auto start";
    private CallbackManager callbackManager;
    private ILoginPresenter mPresenter = new LoginPresenter(this);

    private boolean autoEnter;

    private View.OnClickListener startEnterApp = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.successLogin();
            autoEnter = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton mButtonEnter = findViewById(R.id.btn_next);
        mButtonEnter.setOnClickListener(startEnterApp);
        autoEnter = getIntent().getBooleanExtra(AUTO_START, true);
        initFB();
        initButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoEnter) {
            mPresenter.successLogin();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        mPresenter.stopTracker();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    private void initFB() {
        callbackManager = CallbackManager.Factory.create();
        mPresenter.initTrackers();
    }

    private void initButton() {
        LoginButton loginButton = findViewById(R.id.login_button);
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LogUtil.info(this, "Init Button SUCCESS!!!! " + loginResult.getAccessToken().getToken());
                mPresenter.successLogin();
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions(Arrays.asList("email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, callback);
    }

    @Override
    public void startActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}


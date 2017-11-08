package ua.com.solodilov.evgen.vjettesttask.login_fb.presenter;

import com.facebook.AccessToken;
import com.facebook.Profile;

public interface ILoginPresenter {
    void initTrackers();

    void stopTracker();

    void successLogin();
}

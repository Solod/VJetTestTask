package ua.com.solodilov.evgen.vjettesttask.login_fb.presenter;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;

import org.json.JSONObject;

import ua.com.solodilov.evgen.vjettesttask.LogUtil;
import ua.com.solodilov.evgen.vjettesttask.login_fb.view.LoginActivity;
import ua.com.solodilov.evgen.vjettesttask.login_fb.view.LoginView;
import ua.com.solodilov.evgen.vjettesttask.models.Person;

public class LoginPresenter implements ILoginPresenter {
    private LoginView mLoginView;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private final Person mPerson = new Person();

    public LoginPresenter(LoginActivity loginActivity) {
        mLoginView = loginActivity;
    }

    @Override
    public void initTrackers() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                successLogin();
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public void stopTracker() {
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void successLogin() {
        Profile mProfile = Profile.getCurrentProfile();
        LogUtil.info(this, "PROFILE%%%%" + mProfile);
        if (mProfile != null) {
            mPerson.setAvatar(mProfile.getProfilePictureUri(200, 200));
            mPerson.setId(mProfile.getId());
            mPerson.setFirstName(mProfile.getFirstName());
            mPerson.setLastName(mProfile.getLastName());

            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            if (response.getError() != null) {
                                LogUtil.info(this, "Error profile: " + response.getError().getErrorMessage());
                                return;
                            }
                            LogUtil.info(this, "!!!!!!!!!!!!!!!!!!! " + object.toString());
                            try {
                                mPerson.setEmail(object.getString("email"));
                                mPerson.setBirthday(object.getString("birthday"));
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getCanonicalName(), "Error: getting data profile");
                            } finally {
                                mLoginView.startActivity();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }
}

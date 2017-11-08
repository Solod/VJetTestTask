package ua.com.solodilov.evgen.vjettesttask.main.presenter;

import com.facebook.Profile;

import ua.com.solodilov.evgen.vjettesttask.main.view.ProfileFragment;
import ua.com.solodilov.evgen.vjettesttask.main.view.ProfileView;

public class ProfilePresenter implements IProfilePresenter {
    private final ProfileView mView;
    private Profile mProfile;

    public ProfilePresenter(ProfileFragment profileFragment) {
        mView = profileFragment;
    }

    @Override
    public void fillAllFields() {
        mProfile = Profile.getCurrentProfile();
        mView.setFieldFullName(mProfile.getName());
        mView.setFieldFirstName(mProfile.getFirstName());
        mView.setFieldLastName(mProfile.getLastName());
        mView.setProfileImage(mProfile.getProfilePictureUri(200, 200));
    }

    @Override
    public void saveProfile() {
        Profile saveProfile = new Profile(
                mProfile.getId(),
                mView.getFirstName(),
                mProfile.getMiddleName(),
                mView.getLastName(),
                mView.getFullName(),
                mProfile.getLinkUri()
        );
        Profile.setCurrentProfile(saveProfile);
        mView.notifySave();
        mView.closeFragment();
    }

    @Override
    public void cancelProfile() {
        mView.closeFragment();
    }


}

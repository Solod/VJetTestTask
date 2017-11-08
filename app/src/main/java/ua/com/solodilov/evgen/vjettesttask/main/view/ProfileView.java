package ua.com.solodilov.evgen.vjettesttask.main.view;

import android.net.Uri;

/**
 * Created by jack on 08.11.17.
 */

public interface ProfileView {
    void setFieldFullName(String fullName);
    void setFieldFirstName(String firstNameName);
    void setFieldLastName(String lastName);
    void setProfileImage(Uri link);


    String getFirstName();

    String getLastName();

    String getFullName();

    void closeFragment();

    void notifySave();
}

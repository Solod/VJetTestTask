package ua.com.solodilov.evgen.vjettesttask.main.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ua.com.solodilov.evgen.vjettesttask.GlideApp;
import ua.com.solodilov.evgen.vjettesttask.R;
import ua.com.solodilov.evgen.vjettesttask.main.presenter.IProfilePresenter;
import ua.com.solodilov.evgen.vjettesttask.main.presenter.ProfilePresenter;

public class ProfileFragment extends Fragment implements ProfileView, View.OnClickListener {
    private IProfilePresenter mPresenter = new ProfilePresenter(this);

    private ImageView mImageView;
    private EditText mFullName;
    private EditText mFirstName;
    private EditText mLastName;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = view.findViewById(R.id.iv_profile);
        mFullName = view.findViewById(R.id.tv_name);
        mFirstName = view.findViewById(R.id.tv_fname);
        mLastName = view.findViewById(R.id.tv_lname);
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mPresenter.fillAllFields();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                mPresenter.saveProfile();
                break;
            case R.id.btn_cancel:
                mPresenter.cancelProfile();
                break;
            default:
        }
    }

    @Override
    public void setFieldFullName(String fullName) {
        mFullName.setText(fullName);
    }

    @Override
    public void setFieldFirstName(String firstName) {
        mFirstName.setText(firstName);
    }

    @Override
    public void setFieldLastName(String lastName) {
        mLastName.setText(lastName);
    }

    @Override
    public void setProfileImage(Uri link) {
        GlideApp.with(getContext())
                .load(link)
                .into(mImageView);
    }

    @Override
    public String getFirstName() {
        return String.valueOf(mFirstName.getText());
    }

    @Override
    public String getLastName() {
        return String.valueOf(mLastName.getText());
    }

    @Override
    public String getFullName() {
        return String.valueOf(mFullName.getText());
    }

    @Override
    public void closeFragment() {
        Toast.makeText(getContext(),"Profile UNSAVED!",Toast.LENGTH_LONG).show();
        getActivity().onBackPressed();
    }

    @Override
    public void notifySave() {
        Toast.makeText(getContext(),"Profile SAVED!",Toast.LENGTH_LONG).show();
    }
}

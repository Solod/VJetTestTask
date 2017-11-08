package ua.com.solodilov.evgen.vjettesttask.main.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ua.com.solodilov.evgen.vjettesttask.R;
import ua.com.solodilov.evgen.vjettesttask.main.CustomPagerAdapter;
import ua.com.solodilov.evgen.vjettesttask.main.presenter.GalleryPresenter;
import ua.com.solodilov.evgen.vjettesttask.main.presenter.IGalleryPresenter;
import ua.com.solodilov.evgen.vjettesttask.widgets.ExtendedViewPager;

public class GalleryFragment extends Fragment implements GalleryView {

    private IGalleryPresenter mPresenter = new GalleryPresenter(this);
    private CustomPagerAdapter mCustomPagerAdapter;

    public GalleryFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ExtendedViewPager viewPager = view.findViewById(R.id.vp_img);
        mCustomPagerAdapter = new CustomPagerAdapter(getContext());
        viewPager.setAdapter(mCustomPagerAdapter);
        mPresenter.getImageList();
    }

    @Override
    public void refreshImages(List<String> list) {
        mCustomPagerAdapter.refreshAdapter(list);
    }
}

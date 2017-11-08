package ua.com.solodilov.evgen.vjettesttask.main;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import ua.com.solodilov.evgen.vjettesttask.GlideApp;
import ua.com.solodilov.evgen.vjettesttask.R;
import ua.com.solodilov.evgen.vjettesttask.widgets.TouchImageView;

public class CustomPagerAdapter extends PagerAdapter {

      LayoutInflater mLayoutInflater;
    List<String> mListImg;

    public CustomPagerAdapter(Context context) {
               mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListImg == null ? 0 : mListImg.size();
            }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_image, container, false);
        final TouchImageView imageView = itemView.findViewById(R.id.iv_photo);
        container.addView(itemView);
        GlideApp.with(container.getContext())
                .load(mListImg.get(position))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void refreshAdapter(List<String> list) {
        mListImg = list;
        notifyDataSetChanged();
    }
}
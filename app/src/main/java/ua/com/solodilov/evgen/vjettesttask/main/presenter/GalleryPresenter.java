package ua.com.solodilov.evgen.vjettesttask.main.presenter;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ua.com.solodilov.evgen.vjettesttask.LogUtil;
import ua.com.solodilov.evgen.vjettesttask.main.view.GalleryFragment;
import ua.com.solodilov.evgen.vjettesttask.main.view.GalleryView;

public class GalleryPresenter implements IGalleryPresenter {
    private final GalleryView mView;

    public GalleryPresenter(GalleryFragment fragment) {
        mView = fragment;
    }

    @Override
    public void getImageList() {
        final List<String> list = new ArrayList<>();

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            LogUtil.info(this, "Object get JSON Photo" + object.toString());

                            JSONArray jsonArray = object.getJSONObject("photos").getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONArray subArray = jsonArray.getJSONObject(i).getJSONArray("images");
                                String s = subArray.getJSONObject(0).getString("source");
                                list.add(s);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mView.refreshImages(list);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,photos{images}");
        request.setParameters(parameters);
        request.executeAsync();
    }
}

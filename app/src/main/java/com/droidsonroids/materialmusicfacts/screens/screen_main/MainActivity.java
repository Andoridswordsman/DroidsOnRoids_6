package com.droidsonroids.materialmusicfacts.screens.screen_main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import com.droidsonroids.materialmusicfacts.App;
import com.droidsonroids.materialmusicfacts.R;
import com.droidsonroids.materialmusicfacts.SuperActivity;
import com.droidsonroids.materialmusicfacts.data.MadonnaProvider;
import com.droidsonroids.materialmusicfacts.data.model.Album;
import com.droidsonroids.materialmusicfacts.screens.screen_album_details.AlbumDetailsActivity;
import com.droidsonroids.materialmusicfacts.views.SpacesItemDecoration;
import com.squareup.picasso.Picasso;


public class MainActivity extends SuperActivity implements AppBarLayout.OnOffsetChangedListener, OnAlbumClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.appBarLayout) AppBarLayout appBarLayout;
    @BindView(R.id.headerLinearLayout) LinearLayout linearLayout;
    @BindView(R.id.collapsingToolbarLayout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.firstnameTextView) TextView firstNameTextView;
    @BindView(R.id.lastnameTextView) TextView lastNameTextView;
    @BindView(R.id.birthPlaceTextView) TextView birthplaceNameTextView;
    @BindView(R.id.ageTextView) TextView ageNameTextView;
    @BindView(R.id.albumsRecyclerView) RecyclerView albumsRecyclerView;
    private MainRecyclerViewAdapter mAdapter;

    private boolean madonnaDescriptionVisible = true;

    public static void startActivity(final Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarLayout.addOnOffsetChangedListener(this);

        firstNameTextView.setText(App.getMadonnaProvider().getmMadonna().getName());
        lastNameTextView.setText(App.getMadonnaProvider().getmMadonna().getNickname());
        birthplaceNameTextView.setText(App.getMadonnaProvider().getmMadonna().getPlaceOfBirth());
        ageNameTextView.setText(App.getMadonnaProvider().getmMadonna().getDateOfBirth());
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        mAdapter = new MainRecyclerViewAdapter(Picasso.with(this));
        mAdapter.setOnAlbumClickListener(this);

        albumsRecyclerView.addItemDecoration(new SpacesItemDecoration(4, 5, false));
        albumsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        albumsRecyclerView.setAdapter(mAdapter);

        mAdapter.setAlbumCovers(App.getMadonnaProvider().provideAlbumCovers());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d("OFFSET", verticalOffset + "");

        AlphaAnimation fadeOutAnimation = new AlphaAnimation(1.0f, 0f);
        fadeOutAnimation.setDuration(300);
        fadeOutAnimation.setFillAfter(true);

        AlphaAnimation fadeInAnimation = new AlphaAnimation(0f, 1.0f);
        fadeOutAnimation.setDuration(300);
        fadeOutAnimation.setFillAfter(true);

        if(getAppBarLayoutCollapsePercentage(verticalOffset) > 70 && madonnaDescriptionVisible){
            linearLayout.startAnimation(fadeOutAnimation);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
                }
            }, fadeOutAnimation.getDuration());
            madonnaDescriptionVisible = false;
        }
        else if(getAppBarLayoutCollapsePercentage(verticalOffset) <= 70 && !madonnaDescriptionVisible){

            linearLayout.startAnimation(fadeInAnimation);
            new Handler().post(new Runnable() {
                public void run() {
                    collapsingToolbarLayout.setTitle("");
                }
            });
            madonnaDescriptionVisible = true;
        }
    }

    private float getAppBarLayoutCollapsePercentage(int verticalOffset){
        return Math.abs((float)verticalOffset/appBarLayout.getTotalScrollRange()*100);
    }

    @Override
    public void onAlbumClickedListener(View imageViewAlbum, int position) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(imageViewAlbum, "transitionAlbum"));
        Intent intent = new Intent(this, AlbumDetailsActivity.class);
        intent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM_COVER, position);
        startActivity(intent, options.toBundle());
    }
}

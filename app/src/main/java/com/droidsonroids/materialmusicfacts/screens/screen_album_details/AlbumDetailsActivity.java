package com.droidsonroids.materialmusicfacts.screens.screen_album_details;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidsonroids.materialmusicfacts.App;
import com.droidsonroids.materialmusicfacts.R;
import com.droidsonroids.materialmusicfacts.SuperActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by Radek on 2016-08-01.
 */

public class AlbumDetailsActivity extends SuperActivity {

    public static final String EXTRA_ALBUM_COVER = "EXTRA_ALBUM_COVER";
    @BindView(R.id.biographyTextView) TextView biographyTextView;
    @BindView(R.id.imageview_album) ImageView mImageViewAlbum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Picasso.with(this)
                .load(App.getMadonnaProvider().provideAlbumCoverByPosition(getIntent().getIntExtra(EXTRA_ALBUM_COVER, R.drawable.madonna)))
                .into(mImageViewAlbum);
        biographyTextView.setText(App.getMadonnaProvider().provideMadonnaBiography());
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_details;
    }
}

package com.droidsonroids.materialmusicfacts.screens.screen_main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.droidsonroids.materialmusicfacts.R;

public class MainViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageview_album) ImageView imageViewAlbum;

    public MainViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

package com.droidsonroids.materialmusicfacts.screens.screen_main;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import com.droidsonroids.materialmusicfacts.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private final Picasso picasso;
    private List<Integer> albumCovers;
    private OnAlbumClickListener onAlbumClickListener;

    public MainRecyclerViewAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public MainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        picasso.load(albumCovers.get(position)).into(holder.imageViewAlbum);
        holder.imageViewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                holder.imageViewAlbum.setTransitionName(view.getContext().getResources().getString(R.string.transition_imageview_album));
                onAlbumClickListener.onAlbumClickedListener(view, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumCovers.size();
    }

    public void setAlbumCovers(List<Integer> albumCovers) {
        this.albumCovers = albumCovers;
        notifyDataSetChanged();
    }

    public void setOnAlbumClickListener(final OnAlbumClickListener onAlbumClickListener) {
        this.onAlbumClickListener = onAlbumClickListener;
    }
}

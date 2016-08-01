package com.droidsonroids.materialmusicfacts.data;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.support.v7.graphics.Palette;
import android.view.View;
import com.droidsonroids.materialmusicfacts.App;
import com.droidsonroids.materialmusicfacts.common.Constants;
import com.droidsonroids.materialmusicfacts.data.model.Album;
import com.droidsonroids.materialmusicfacts.data.model.Madonna;
import com.droidsonroids.materialmusicfacts.data.model.Song;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class MadonnaProvider {

    private Madonna mMadonna = null;

    List<Palette> mPaletteList = new ArrayList<>();

    public MadonnaProvider() {
        provideMadonna();
        generatePalettes();
    }

    private void generatePalettes() {
        if(mMadonna != null) {
            for(Album album : mMadonna.getAlbums()) {
                mPaletteList.add(Palette.from(getBitmapFromDrawableRes(album)).generate());
            }
        }
    }

    public Madonna getmMadonna(){
        return mMadonna;
    }

    private void provideMadonna() {
          try {
                AssetManager assetManager = App.getInstance().getAssets();
                InputStream inputStream;

                inputStream = assetManager.open(Constants.MADONNA_JSON_PATH);

                Gson gson = new Gson();
                Reader reader = new InputStreamReader(inputStream);

                mMadonna = gson.fromJson(reader, Madonna.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public List<Integer> provideAlbumCovers() {
        List<Integer> albumCovers = new ArrayList<>();
        for(Album album : mMadonna.getAlbums()) {
                albumCovers.add(prepareDrawableId(album));
            }
        return albumCovers;
    }

    private Integer prepareDrawableId(final Album album) {
        return App.getInstance().getResources().getIdentifier(album.getCoverImg(), "drawable",
                App.getInstance().getPackageName());
    }

    private Bitmap getBitmapFromDrawableRes(final Album album) {
        return BitmapFactory.decodeResource(App.getInstance().getResources(), prepareDrawableId(album));
    }

    public String provideMadonnaBiography() {
        return mMadonna.getBio();
    }

    public @DrawableRes int provideAlbumCoverByPosition(int position) {
        return prepareDrawableId(mMadonna.getAlbums().get(position));
    }

    public String provideAlbumNameByPosition(final int position) {
        return mMadonna.getAlbums().get(position).getTitle();
    }

    public List<Song> provideSongsListByAlbum(final String albumTitle) {
        for(Album album : mMadonna.getAlbums()) {
            if(album.getTitle().equals(albumTitle)) {
                return album.getSongs();
            }
        }
        return null;
    }

    public int getAlphaColorFromPalette(final int position) {
        return adjustAlpha(mPaletteList.get(position).getVibrantSwatch().getRgb(), 0.3f);
    }

    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}

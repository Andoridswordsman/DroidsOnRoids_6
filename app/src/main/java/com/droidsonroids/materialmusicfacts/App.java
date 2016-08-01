package com.droidsonroids.materialmusicfacts;

import android.app.Application;
import com.droidsonroids.materialmusicfacts.common.Constants;
import com.droidsonroids.materialmusicfacts.data.MadonnaProvider;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    private static App sInstance;
    private static MadonnaProvider sMadonnaProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sMadonnaProvider = new MadonnaProvider();
        initCalligraphy();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.DEFAULT_FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static App getInstance() {
        return sInstance;
    }

    public static MadonnaProvider getMadonnaProvider() {
        return sMadonnaProvider;
    }
}

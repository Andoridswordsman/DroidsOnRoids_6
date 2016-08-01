package com.droidsonroids.materialmusicfacts.screens.screen_splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.droidsonroids.materialmusicfacts.R;
import com.droidsonroids.materialmusicfacts.SuperActivity;
import com.droidsonroids.materialmusicfacts.common.Constants;
import com.droidsonroids.materialmusicfacts.screens.screen_main.MainActivity;

public class SplashActivity extends SuperActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, Constants.SPLASH_SCREEN_DURATION);
    }

    private void startMainActivity() {
        MainActivity.startActivity(this);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

}

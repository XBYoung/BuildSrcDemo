package com.l.young.easyApp;

import android.os.Bundle;
import android.view.View;

import com.l.young.mylibrary.activity.BaseActivity;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
            findViewById(R.id.fullscreen_content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
         | View.SYSTEM_UI_FLAG_FULLSCREEN
         | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
         | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
         | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}

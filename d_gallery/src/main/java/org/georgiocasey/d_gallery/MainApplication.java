package org.georgiocasey.d_gallery;

import android.app.Application;
import android.content.Context;

import com.androidquery.callback.BitmapAjaxCallback;

/**
 * Created by Andrey on 26.02.14.
 */
public class MainApplication extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        MainApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MainApplication.context;
    }

    @Override
    public void onLowMemory(){
        BitmapAjaxCallback.clearCache();
    }
}

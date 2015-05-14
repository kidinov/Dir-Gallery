package org.georgiocasey.d_gallery.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import org.georgiocasey.d_gallery.MainApplication;
import org.georgiocasey.d_gallery.R;

/**
 * Created by Andrey on 26.02.14.
 */
public class BitmapHelper {
    private static String TAG = "BitmapHelper";
    private static final Bitmap bitmapVideo = BitmapFactory.decodeResource(MainApplication.getAppContext().getResources(), R.drawable.play_video);

    public static Bitmap getThumbnailOfVideo(String path) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(path);
            Bitmap bmp = retriever.getFrameAtTime(0);
            bmp = Bitmap.createScaledBitmap(bmp, 300, 300 * bmp.getHeight() / bmp.getWidth(), true);
            Canvas canvas = new Canvas(bmp);

            canvas.drawBitmap(BitmapFactory.decodeResource(MainApplication.getAppContext().getResources(), R.drawable.play_video), (bmp.getWidth() - bitmapVideo.getWidth()) / 2,
                    (bmp.getHeight() - bitmapVideo.getHeight()) / 2, null);
            canvas.save();
            return bmp;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
            }
        }
        return null;
    }
}

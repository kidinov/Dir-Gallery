package org.georgiocasey.d_gallery.data;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ProgressBar;

import com.androidquery.AQuery;
import com.androidquery.callback.BitmapAjaxCallback;

import org.georgiocasey.d_gallery.R;
import org.georgiocasey.d_gallery.util.FsHelper;

import java.io.File;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by Andrey on 27.02.14.
 */
public class HorListAdapter extends BaseAdapter {
    private static final String TAG = "HorListAdapter";
    private LayoutInflater inflater;
    private int resource;
    private Gallery gallery;
    private GalleryParamValues paramValues;

    public HorListAdapter(Context context, int resource, GalleryParamValues paramValues, Gallery gallery) {
        super(context, resource);
        this.resource = resource;
        this.gallery = gallery;
        this.paramValues = paramValues;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        fillAdapterWithContent(paramValues);
    }

    private static class ViewHolder {
        ImageViewTouch imageView;
        ProgressBar progressBar;
    }

    public View getView(int pos, View cv, ViewGroup parent) {
        ViewHolder holder;

        if (cv == null) {
            cv = inflater.inflate(resource, null);
            holder = new ViewHolder();
            holder.imageView = (ImageViewTouch) cv.findViewById(R.id.tb);
            holder.imageView.setGallery(gallery);
            holder.progressBar = (ProgressBar) cv.findViewById(R.id.progress);
            cv.setTag(holder);
        } else {
            holder = (ViewHolder) cv.getTag();
        }

        final File f = (File) getItem(pos);
        AQuery aq = new AQuery(cv);
        BitmapAjaxCallback callback = new BitmapAjaxCallback();
        callback.animation(AQuery.FADE_IN_FILE);
        holder.imageView.setBackgroundColor(Color.TRANSPARENT);
        holder.imageView.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        holder.imageView.setScrollEnabled(true);
        aq.id(holder.imageView).progress(holder.progressBar).image(f, true, paramValues.useDownSampling() ? 500 : 0, callback);

        holder.imageView.setClickable(true);
        holder.imageView.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener() {
            @Override
            public void onSingleTapConfirmed() {
                if (FsHelper.VIDEO_FF.accept(f))
                    onVideoClickAction(f);
            }
        });

        return cv;
    }

    public File getSelectedFile() {
        return (File) gallery.getSelectedItem();
    }
}

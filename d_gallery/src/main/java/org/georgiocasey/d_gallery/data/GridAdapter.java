package org.georgiocasey.d_gallery.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;

import org.georgiocasey.d_gallery.R;
import org.georgiocasey.d_gallery.ui.ActivityGalleryHorList;
import org.georgiocasey.d_gallery.util.FsHelper;
import org.georgiocasey.d_gallery.util.IntentParamNames;

import java.io.File;

public class GridAdapter extends BaseAdapter {
    private static final String TAG = "GridAdapter";
    private LayoutInflater inflater;
    private int resource;
    private GalleryParamValues paramValues;

    public GridAdapter(Context context, int resource, GalleryParamValues paramValues) {
        super(context, resource);
        this.resource = resource;
        this.paramValues = paramValues;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        fillAdapterWithContent(paramValues);
    }

    private static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        TextView textView;
    }

    public View getView(final int pos, View cv, ViewGroup parent) {
        ViewHolder holder;

        if (cv == null) {
            cv = inflater.inflate(resource, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) cv.findViewById(R.id.tb);
            holder.progressBar = (ProgressBar) cv.findViewById(R.id.progress);
            holder.textView = (TextView) cv.findViewById(R.id.name);
            cv.setTag(holder);
        } else {
            holder = (ViewHolder) cv.getTag();
        }

        final File f = (File) getItem(pos);
        holder.textView.setText(f.getName());
        AQuery aq = new AQuery(cv);
        aq.id(holder.imageView).progress(holder.progressBar).image(f, true, 300, new BitmapAjaxCallback() {
            @Override
            public void callback(String path, ImageView iv, Bitmap bm, AjaxStatus status) {
                Log.i(TAG, "path = " + path + " bm = " + bm);
                iv.setImageBitmap(bm);
            }

        });

        holder.imageView.setClickable(true);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FsHelper.VIDEO_FF.accept(f)) {
                    onVideoClickAction(f);
                } else {
                    Intent i = new Intent(getContext(), ActivityGalleryHorList.class);
                    i.putExtra(IntentParamNames.PARAM_VALUES, paramValues);
                    i.putExtra(IntentParamNames.SELECTED_POS, pos);
                    getContext().startActivity(i);
                }
            }
        });

        return cv;
    }

}

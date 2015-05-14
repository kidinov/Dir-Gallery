package org.georgiocasey.d_gallery.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import org.georgiocasey.d_gallery.util.FileNameComparator;
import org.georgiocasey.d_gallery.util.FsHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andrey on 27.02.14.
 */
public abstract class BaseAdapter extends ArrayAdapter {
    private Comparator fileNameComparator = new FileNameComparator();

    public BaseAdapter(Context context, int resource) {
        super(context, resource);
    }

    protected void addAll(List<File> files) {
        for (int i = 0; i < files.size(); i++) {
            add(files.get(i));
        }
    }

    protected void fillAdapterWithContent(GalleryParamValues pv) {
        List<File> r = new ArrayList<File>();
        if (pv.isShowImages() && pv.isShowVideo()) {
            r = FsHelper.getMedia(pv.getPath(), pv.isShowTraverse());
        } else if (pv.isShowImages()) {
            r = FsHelper.getImages(pv.getPath(), pv.isShowTraverse());
        } else if (pv.isShowVideo()) {
            r = FsHelper.getVideos(pv.getPath(), pv.isShowTraverse());
        }

        Collections.sort(r, fileNameComparator);
        addAll(r);
    }

    protected int getWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    protected void onVideoClickAction(File f) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(f), "video/*");
        getContext().startActivity(intent);
    }

}

package org.georgiocasey.d_gallery.util;

import android.util.Log;

import org.georgiocasey.d_gallery.data.ImageFileFilter;
import org.georgiocasey.d_gallery.data.VideoImageFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FsHelper {
    public final static FileFilter IMG_FF = new ImageFileFilter();
    public final static FileFilter VIDEO_FF = new VideoImageFilter();
    private static final String TAG = "FsHelper";

    public static List<File> getAll(String path) {
        File file = new File(path);
        if (!file.isDirectory())
            return new ArrayList<File>();
        return Arrays.asList(file.listFiles());
    }

    public static List<File> getImages(String path, boolean traversal) {
        File file = new File(path);
        if (!file.isDirectory())
            return new ArrayList<File>();

        if (!traversal) {
            return Arrays.asList(file.listFiles(IMG_FF));
        } else {
            List<File> res = new ArrayList<File>();
            getFilesRec(file, IMG_FF, res);
            return res;
        }
    }

    public static List<File> getVideos(String path, boolean traversal) {
        File file = new File(path);
        if (!file.isDirectory())
            return new ArrayList<File>();

        if (!traversal) {
            return Arrays.asList(file.listFiles(VIDEO_FF));
        } else {
            List<File> res = new ArrayList<File>();
            getFilesRec(file, VIDEO_FF, res);
            return res;
        }
    }

    public static List<File> getMedia(String path, boolean traversal) {
        File file = new File(path);
        if (!file.isDirectory())
            return new ArrayList<File>();

        if (!traversal) {
            File[] tmp = file.listFiles(IMG_FF);
            if (tmp == null)
                return new ArrayList<File>();

            List<File> images = new ArrayList<File>(Arrays.asList(tmp));
            images.addAll(Arrays.asList(file.listFiles(VIDEO_FF)));
            return images;
        } else {
            List<File> res = new ArrayList<File>();
            getFilesRec(file, IMG_FF, res);
            getFilesRec(file, VIDEO_FF, res);
            return res;
        }
    }

    private static void getFilesRec(File file, FileFilter ff, List<File> result) {
        Log.i(TAG, "getFilesRec file = " + file + "file.isDirectory() = " + file.isDirectory() + " file.listFiles() = " + file.listFiles());

        File[] tmp = file.listFiles(ff);
        if (tmp != null)
            result.addAll(new ArrayList<File>(Arrays.asList(tmp)));
        if (file.isDirectory() && file.listFiles() != null)
            for (File f : file.listFiles()) {
                if (f.isDirectory())
                    getFilesRec(f, ff, result);
            }
    }

}

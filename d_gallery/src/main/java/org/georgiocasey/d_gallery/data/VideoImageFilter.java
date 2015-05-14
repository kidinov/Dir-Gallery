package org.georgiocasey.d_gallery.data;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Andrey on 26.02.14.
 */
public class VideoImageFilter implements FileFilter {
    private final String[] okFileExtensions = new String[]{"3g2", "3gp", "asf", "asx", "avi", "flv",
            "m4v", "mov", "mp4", "mpg", "rm", "srt", "swf", "vob", "wmv"};

    public boolean accept(File file) {
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

}
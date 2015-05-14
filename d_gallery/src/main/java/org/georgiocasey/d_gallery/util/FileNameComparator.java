package org.georgiocasey.d_gallery.util;

import java.io.File;
import java.util.Comparator;

/**
 * Created by Andrey on 27.02.14.
 */
public class FileNameComparator implements Comparator<File> {

    @Override
    public int compare(File f1, File f2) {
        return f1.getName().compareTo(f2.getName());
    }
}

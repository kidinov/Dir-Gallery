package org.georgiocasey.d_gallery.data;

import java.io.Serializable;

/**
 * Created by Andrey on 26.02.14.
 */
public class GalleryParamValues implements Serializable {
    private String path;
    private boolean showImages;
    private boolean showVideo;
    private boolean showTraverse;
    private boolean downSampling;

    public GalleryParamValues(String path, boolean showImages, boolean showVideo, boolean showTraverse, boolean downSampling) {
        this.path = path;
        this.showImages = showImages;
        this.showVideo = showVideo;
        this.showTraverse = showTraverse;
        this.downSampling = downSampling;
    }

    @Override
    public String toString() {
        return "GalleryParamValues{" +
                "path='" + path + '\'' +
                ", showImages=" + showImages +
                ", showVideo=" + showVideo +
                ", showTraverse=" + showTraverse +
                '}';
    }

    public String getPath() {
        return path;
    }

    public boolean isShowImages() {
        return showImages;
    }

    public boolean isShowVideo() {
        return showVideo;
    }

    public boolean isShowTraverse() {
        return showTraverse;
    }

    public boolean useDownSampling() {
        return downSampling;
    }

    public static class Builder {
        private String path;
        private boolean showImages = true;
        private boolean showVideo = true;
        private boolean showTraverse = false;
        private boolean downSampling = true;

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder setShowImages(boolean showImages) {
            this.showImages = showImages;
            return this;
        }

        public Builder setShowVideo(boolean showVideo) {
            this.showVideo = showVideo;
            return this;
        }

        public Builder setShowTraverse(boolean showTraverse) {
            this.showTraverse = showTraverse;
            return this;
        }
        public Builder setDownSampling(boolean downSampling) {
            this.downSampling = downSampling;
            return this;
        }

        public GalleryParamValues createGalleryParamValues() {
            return new GalleryParamValues(path, showImages, showVideo, showTraverse, downSampling);
        }
    }
}

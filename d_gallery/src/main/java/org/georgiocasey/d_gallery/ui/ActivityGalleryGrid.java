package org.georgiocasey.d_gallery.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.androidquery.AQuery;

import org.georgiocasey.d_gallery.R;
import org.georgiocasey.d_gallery.data.GalleryParamValues;
import org.georgiocasey.d_gallery.data.GridAdapter;
import org.georgiocasey.d_gallery.util.IntentParamNames;

public class ActivityGalleryGrid extends ActionBarActivity {
    private GalleryParamValues paramValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_grid);

        initActionBar();

        if (getIntent() == null || TextUtils.isEmpty(getIntent().getStringExtra(IntentParamNames.DIR_PATH))) {
            throw new IllegalArgumentException("Path to dir is illegal");
        }

        paramValues = parseIntent(getIntent());

        AQuery listAq = new AQuery(this);
        listAq.id(R.id.grid).adapter(new GridAdapter(this, R.layout.grid_item, paramValues));

    }

    private GalleryParamValues parseIntent(Intent i) {
        GalleryParamValues.Builder b = new GalleryParamValues.Builder();
        b.setPath(i.getStringExtra(IntentParamNames.DIR_PATH));
        b.setShowImages(i.getBooleanExtra(IntentParamNames.IMAGES, true));
        b.setShowVideo(i.getBooleanExtra(IntentParamNames.VIDEO, true));
        b.setShowTraverse(i.getBooleanExtra(IntentParamNames.TRAVERSE, false));
        b.setDownSampling(i.getBooleanExtra(IntentParamNames.DOWN_SAMPLING, true));
        return b.createGalleryParamValues();
    }

    private void initActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return false;
    }

}

package org.georgiocasey.d_gallery.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;

import com.androidquery.AQuery;

import org.georgiocasey.d_gallery.R;
import org.georgiocasey.d_gallery.data.GalleryParamValues;
import org.georgiocasey.d_gallery.data.HorListAdapter;
import org.georgiocasey.d_gallery.util.IntentParamNames;

public class ActivityGalleryHorList extends ActionBarActivity {
    private final String TAG = "ActivityGalleryHorList";
    private GalleryParamValues paramValues;
    private HorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_hor);
        initActionBar();

        paramValues = (GalleryParamValues) getIntent().getSerializableExtra(IntentParamNames.PARAM_VALUES);

        AQuery listAq = new AQuery(this);
        listAq.clear();
        adapter = new HorListAdapter(this, R.layout.hor_item, paramValues, (Gallery) findViewById(R.id.gallery));
        Log.i(TAG, "selection pos = " + getIntent().getIntExtra(IntentParamNames.SELECTED_POS, 0));
        listAq.id(R.id.gallery).adapter(adapter);
        listAq.setSelection(getIntent().getIntExtra(IntentParamNames.SELECTED_POS, 0));
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
            case R.id.action_share: {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(adapter.getSelectedFile()));
                startActivity(Intent.createChooser(intent, getString(R.string.share_via)));
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hor_main_menu, menu);
        return true;
    }
}

package org.georgiocasey.d_gallery.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.georgiocasey.d_gallery.R;
import org.georgiocasey.d_gallery.ui.ActivityGalleryGrid;
import org.georgiocasey.d_gallery.util.IntentParamNames;

public class TestActivity extends ActionBarActivity {
    private final String TAG = "TestActivity";
    private EditText path;
    private CheckBox showImagesCb;
    private CheckBox showVideoCb;
    private CheckBox showTraverseCb;
    private CheckBox downSamplingCb;
    private Button refresh;
    private Button start;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_test);
        initUi();
    }

    private void initUi() {
        path = (EditText) findViewById(R.id.path);

        showImagesCb = (CheckBox) findViewById(R.id.cb_images);
        showVideoCb = (CheckBox) findViewById(R.id.cb_video);
        showTraverseCb = (CheckBox) findViewById(R.id.cb_traverse);
        downSamplingCb = (CheckBox) findViewById(R.id.cb_down_sampling);

        start = (Button) findViewById(R.id.start);
        refresh = (Button) findViewById(R.id.sdcard);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path.setText(Environment.getExternalStorageDirectory().toString());
                path.requestFocus();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(createTestIntent());
            }
        });

    }

    private Intent createTestIntent() {
        Intent i = new Intent(this, ActivityGalleryGrid.class);
        if (!TextUtils.isEmpty(path.getText().toString()))
            i.putExtra(IntentParamNames.DIR_PATH, path.getText().toString());
        else
            i.putExtra(IntentParamNames.DIR_PATH, "/storage/emulated/0/DCIM/camera");
        i.putExtra(IntentParamNames.IMAGES, showImagesCb.isChecked());
        i.putExtra(IntentParamNames.VIDEO, showVideoCb.isChecked());
        i.putExtra(IntentParamNames.TRAVERSE, showTraverseCb.isChecked());
        i.putExtra(IntentParamNames.DOWN_SAMPLING, downSamplingCb.isChecked());
        return i;
    }
}

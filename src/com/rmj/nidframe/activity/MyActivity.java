package com.rmj.nidframe.activity;

import android.app.Activity;
import android.os.Bundle;
import com.rmj.nidframe.R;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}

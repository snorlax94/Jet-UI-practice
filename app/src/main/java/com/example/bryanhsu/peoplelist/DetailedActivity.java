package com.example.bryanhsu.peoplelist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bryanhsu on 15/01/16.
 */
public class DetailedActivity extends Activity {

    String subtitle;
    TextView mSubtitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            String j = (String) b.get("STRING_I_NEED");
            setSubtitle_Detailed(j);
        }
    }

    public void setSubtitle_Detailed(String subtitle) {
        this.subtitle = subtitle;
        mSubtitleView = (TextView) findViewById(R.id.subtitle);
        mSubtitleView.setVisibility(View.VISIBLE);
        mSubtitleView.setText(subtitle);

    }

    public String getSubtitle() {
        return subtitle;
    }


}



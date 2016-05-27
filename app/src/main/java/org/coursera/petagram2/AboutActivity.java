package org.coursera.petagram2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by rodomualdo on 26/05/2016.
 */
public class AboutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        // ActionBar
        toolbar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        textView = (TextView) findViewById(R.id.tvAbout);
    }

}

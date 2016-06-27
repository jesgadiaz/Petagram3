package org.coursera.petagram2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by rodomualdo on 26/06/2016.
 */
public class SetInstagramUserActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuserinstagram);

        button = (Button) findViewById(R.id.btnSetUser);
        textInputLayout = (TextInputLayout) findViewById(R.id.tilUsuario);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SetInstagramUserActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("InstagramUser",textInputLayout.getEditText().getText().toString());
                editor.apply();
            }
        });


    }
}

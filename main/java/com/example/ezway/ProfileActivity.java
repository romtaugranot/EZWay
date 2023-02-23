package com.example.ezway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends Activity implements View.OnClickListener{

    private TextView tvCancel;
    private TextView tvDone;

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        tvCancel = (TextView) findViewById(R.id.tvCancel);
        tvDone = (TextView) findViewById(R.id.tvDone);

        tvCancel.setOnClickListener(this);
        tvDone.setOnClickListener(this);

    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.profile_layout);
        LinearLayout topLayout =(LinearLayout) findViewById(R.id.topLayout);
        TextView[] textViews = new TextView[4];
        textViews[0] = findViewById(R.id.tvCancel);
        textViews[1] = findViewById(R.id.tvProfile);
        textViews[2] = findViewById(R.id.tvChangeProfilePic);
        textViews[3] = findViewById(R.id.tvChangePassword);
        View[] views = new View[7];
        views[0] = findViewById(R.id.view1);
        views[1] = findViewById(R.id.view2);
        views[2] = findViewById(R.id.view3);
        views[3] = findViewById(R.id.view4);
        views[4] = findViewById(R.id.view5);
        views[5] = findViewById(R.id.view6);
        views[6] = findViewById(R.id.view7);
        EditText[] editTexts = new EditText[5];
        editTexts[0] = findViewById(R.id.etUsername);
        editTexts[1] = findViewById(R.id.etEmail);
        editTexts[2] = findViewById(R.id.etOldPassword);
        editTexts[3] = findViewById(R.id.etNewPassword);
        editTexts[4] = findViewById(R.id.etNewPasswordCon);
        layout.setBackgroundColor(color);
        topLayout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.textColor));
            }
            for (EditText e : editTexts){
                e.setTextColor(getResources().getColor(R.color.hintColor));
                e.setBackgroundColor(color);
            }
            for (View v : views){
                v.setBackgroundColor(getResources().getColor(R.color.viewColor));
            }
        } else{
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.white));
            }
            for (EditText e : editTexts){
                e.setTextColor(getResources().getColor(R.color.white));
                e.setBackgroundColor(color);
            }
            for (View v : views){
                v.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.tvCancel:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.tvDone:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
        }
        startActivity(intent);
    }
}

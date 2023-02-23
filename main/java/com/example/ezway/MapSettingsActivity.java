package com.example.ezway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MapSettingsActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView btnBack;

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String NORTH_PREF = "NORTH_PREF";
    public static final String DIS_PREF = "DIS_PREF";
    public static final String COMPASS_PREF = "COMPASS_PREF";
    public static final String SEARCH_PREF = "SEARCH_PREF";

    public static final String THEME_PREF = "THEME_PREF";

    private Switch northModeSwitch;
    private Switch distanceSwitch;
    private Switch compassSwitch;
    private Switch searchBarSwitch;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_settings);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack = (ImageView) findViewById(R.id.btnBack);
        northModeSwitch = (Switch) findViewById(R.id.northModeSwitch);
        distanceSwitch = (Switch) findViewById(R.id.distanceSwitch);
        compassSwitch = (Switch) findViewById(R.id.compassSwitch);
        searchBarSwitch = (Switch) findViewById(R.id.searchBarSwitch);

        northModeSwitch.setChecked(sharedPreferences.getBoolean(NORTH_PREF, false));
        distanceSwitch.setChecked(sharedPreferences.getBoolean(DIS_PREF, true));
        compassSwitch.setChecked(sharedPreferences.getBoolean(COMPASS_PREF, false));
        searchBarSwitch.setChecked(sharedPreferences.getBoolean(SEARCH_PREF, false));

        btnBack.setOnClickListener(this);
        northModeSwitch.setOnCheckedChangeListener(this);
        distanceSwitch.setOnCheckedChangeListener(this);
        compassSwitch.setOnCheckedChangeListener(this);
        searchBarSwitch.setOnCheckedChangeListener(this);

    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.map_settings_activity);
        View view1 = (View) findViewById(R.id.view1);
        View view2 = (View) findViewById(R.id.view2);
        TextView[] textViews = new TextView[13];
        ImageView applogo = (ImageView) findViewById(R.id.ivLogo);
        ImageView arrow = (ImageView) findViewById(R.id.btnBack);
        textViews[0] = (TextView) findViewById(R.id.tvMapSettings);
        textViews[1] = (TextView) findViewById(R.id.tvNorthMode);
        textViews[2] = (TextView) findViewById(R.id.tvDistance);
        textViews[3] = (TextView) findViewById(R.id.tvCompass);
        textViews[4] = (TextView) findViewById(R.id.tvSearchBar);
        textViews[5] = (TextView) findViewById(R.id.tvYes);
        textViews[6] = (TextView) findViewById(R.id.tvNo);
        textViews[7] = (TextView) findViewById(R.id.tvFeet);
        textViews[8] = (TextView) findViewById(R.id.tvMeters);
        textViews[9] = (TextView) findViewById(R.id.tvNo2);
        textViews[10] = (TextView) findViewById(R.id.tvYes2);
        textViews[11] = (TextView) findViewById(R.id.tvNo3);
        textViews[12] = (TextView) findViewById(R.id.tvYes3);
        layout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            view1.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view2.setBackgroundColor(getResources().getColor(R.color.viewColor));
            applogo.setImageResource(R.drawable.applogo);
            arrow.setImageResource(R.drawable.arrow);
            for(TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.textColor));
            }
        } else{
            view1.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view2.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            applogo.setImageResource(R.drawable.applogo2);
            arrow.setImageResource(R.drawable.arrow2);
            for(TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences.Editor editor = preferences.edit();
        switch(compoundButton.getId()){
            case R.id.northModeSwitch:
                editor.putBoolean(NORTH_PREF, b);
                break;
            case R.id.distanceSwitch:
                editor.putBoolean(DIS_PREF, b);
                break;
            case R.id.compassSwitch:
                editor.putBoolean(COMPASS_PREF, b);
                break;
            case R.id.searchBarSwitch:
                editor.putBoolean(SEARCH_PREF, b);
                break;
        }
        editor.commit();
    }
}


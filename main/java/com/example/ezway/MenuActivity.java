package com.example.ezway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MenuActivity extends Activity implements View.OnClickListener{

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private ImageView btnBack;
    private Button btnEditProfile;
    private TextView tvSettings;
    private TextView tvMapSettings;
    private TextView tvFavoritePlaces;
    private TextView tvLogOut;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);
        tvSettings = (TextView) findViewById(R.id.tvSettings);
        tvMapSettings = (TextView) findViewById(R.id.tvMapSettings);
        tvFavoritePlaces = (TextView) findViewById(R.id.tvFavoritePlaces);
        tvLogOut = (TextView) findViewById(R.id.tvLogOut);

        tvSettings.setOnClickListener(this);
        tvMapSettings.setOnClickListener(this);
        tvFavoritePlaces.setOnClickListener(this);
        tvLogOut.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
    }

    private void changeBackground(int color) {
        LinearLayout menu = (LinearLayout) findViewById(R.id.menu_activity);
        TextView tvNameAndEmail = (TextView) findViewById(R.id.tvNameAndEmail);
        TextView tvSettings = (TextView) findViewById(R.id.tvSettings);
        TextView tvMapSettings = (TextView) findViewById(R.id.tvMapSettings);
        TextView tvFavoritePlaces = (TextView) findViewById(R.id.tvFavoritePlaces);
        TextView tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        ImageView ivArrow = (ImageView) findViewById(R.id.btnBack);
        View view1 = (View) findViewById(R.id.view1);
        View view2 = (View) findViewById(R.id.view2);
        View view3 = (View) findViewById(R.id.view3);
        View view4 = (View) findViewById(R.id.view4);
        View view5 = (View) findViewById(R.id.view5);
        ImageView appLogo = (ImageView) findViewById(R.id.ivLogo);
        TextView btnEditProfile = (TextView) findViewById(R.id.btnEditProfile);
        menu.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            view1.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view2.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view3.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view4.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view5.setBackgroundColor(getResources().getColor(R.color.viewColor));
            ivArrow.setImageResource(R.drawable.arrow);
            appLogo.setImageResource(R.drawable.applogo);
            tvNameAndEmail.setTextColor(getResources().getColor(R.color.textColor));
            tvSettings.setTextColor(getResources().getColor(R.color.textColor));
            tvMapSettings.setTextColor(getResources().getColor(R.color.textColor));
            tvFavoritePlaces.setTextColor(getResources().getColor(R.color.textColor));
            tvLogOut.setTextColor(getResources().getColor(R.color.textColor));
            btnEditProfile.setBackgroundResource(R.drawable.mapbuttondesign);
            btnEditProfile.setTextColor(getResources().getColor(R.color.textColor));
        } else{
            view1.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view2.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view3.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view4.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view5.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            ivArrow.setImageResource(R.drawable.arrow2);
            appLogo.setImageResource(R.drawable.applogo2);
            tvNameAndEmail.setTextColor(getResources().getColor(R.color.white));
            tvSettings.setTextColor(getResources().getColor(R.color.white));
            tvMapSettings.setTextColor(getResources().getColor(R.color.white));
            tvFavoritePlaces.setTextColor(getResources().getColor(R.color.white));
            tvLogOut.setTextColor(getResources().getColor(R.color.white));
            btnEditProfile.setBackgroundResource(R.drawable.mapbuttondesign2);
            btnEditProfile.setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnBack:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.btnEditProfile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSettings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.tvMapSettings:
                intent = new Intent(this, MapSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.tvFavoritePlaces:
                intent = new Intent(this, FavoritePlacesActivity.class);
                startActivity(intent);
                break;
            case R.id.tvLogOut:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}

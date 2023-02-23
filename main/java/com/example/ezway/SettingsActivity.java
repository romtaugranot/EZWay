package com.example.ezway;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class SettingsActivity extends Activity implements View.OnClickListener, Switch.OnCheckedChangeListener{

    private ImageView btnBack;

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String LANG_PREF = "LANG_PREF";
    public static final String THEME_PREF = "THEME_PREF";
    public static final String NOTF_PREF = "NOTF_PREF";
    public static final String REMIND_PREF = "REMIND_PREF";
    public static final String APP_LANG = "MY_LANG";

    private Switch languageSwitch;
    private Switch themeSwitch;
    private Switch notificationsSwitch;
    private Switch remindersSwitch;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack = (ImageView) findViewById(R.id.btnBack);
        languageSwitch = (Switch) findViewById(R.id.languageSwitch);
        themeSwitch = (Switch) findViewById(R.id.themeSwitch);
        notificationsSwitch = (Switch) findViewById(R.id.notificationsSwitch);
        remindersSwitch = (Switch) findViewById(R.id.remindersSwitch);

        languageSwitch.setChecked(sharedPreferences.getBoolean(LANG_PREF, true));
        themeSwitch.setChecked(sharedPreferences.getBoolean(THEME_PREF, true));
        notificationsSwitch.setChecked(sharedPreferences.getBoolean(NOTF_PREF, false));
        remindersSwitch.setChecked(sharedPreferences.getBoolean(REMIND_PREF, false));

        btnBack.setOnClickListener(this);
        languageSwitch.setOnCheckedChangeListener(this);
        themeSwitch.setOnCheckedChangeListener(this);
        notificationsSwitch.setOnCheckedChangeListener(this);
        remindersSwitch.setOnCheckedChangeListener(this);

    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.settings_layout);
        View view1 = (View) findViewById(R.id.view1);
        View view2 = (View) findViewById(R.id.view2);
        TextView[] textViews = new TextView[13];
        ImageView applogo = (ImageView) findViewById(R.id.ivLogo);
        ImageView arrow = (ImageView) findViewById(R.id.btnBack);
        textViews[0] = (TextView) findViewById(R.id.tvSettings);
        textViews[1] = (TextView) findViewById(R.id.tvLanguage);
        textViews[2] = (TextView) findViewById(R.id.tvTheme);
        textViews[3] = (TextView) findViewById(R.id.tvNotifications);
        textViews[4] = (TextView) findViewById(R.id.tvReminders);
        textViews[5] = (TextView) findViewById(R.id.tvHebrew);
        textViews[6] = (TextView) findViewById(R.id.tvEnglish);
        textViews[7] = (TextView) findViewById(R.id.tvDark);
        textViews[8] = (TextView) findViewById(R.id.tvBright);
        textViews[9] = (TextView) findViewById(R.id.tvNo);
        textViews[10] = (TextView) findViewById(R.id.tvNo2);
        textViews[11] = (TextView) findViewById(R.id.tvYes);
        textViews[12] = (TextView) findViewById(R.id.tvYes2);
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

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(APP_LANG, language);
        editor.apply();
        editor.commit();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String language = prefs.getString(APP_LANG, "en");
        setLocale(language);
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences.Editor editor = preferences.edit();
        switch(compoundButton.getId()){
            case R.id.languageSwitch:
                editor.putBoolean(LANG_PREF, b);
                if (b){
                    setLocale("en");
                    recreate();
                } else{
                    setLocale("he");
                    recreate();
                }
                break;
            case R.id.themeSwitch:
                editor.putBoolean(THEME_PREF, b);
                recreate();
                break;
            case R.id.notificationsSwitch:
                editor.putBoolean(NOTF_PREF, b);
                break;
            case R.id.remindersSwitch:
                editor.putBoolean(REMIND_PREF, b);
                break;
        }
        editor.commit();
    }
}


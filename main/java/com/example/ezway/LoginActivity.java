package com.example.ezway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class LoginActivity extends Activity implements View.OnClickListener{

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private Button logIn;
    private TextView signUp;
    private EditText etUsername;
    private EditText etPassword;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        logIn = (Button) findViewById(R.id.btnLogIn);
        signUp = (TextView) findViewById(R.id.tvSignUp);

        signUp.setOnClickListener(this);
        logIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.tvSignUp:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String language = prefs.getString("MY_LANG", "en");
        setLocale(language);
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("MY_LANG", language);
        editor.apply();
        editor.commit();
    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.login_activity);
        View view1 = (View) findViewById(R.id.view1);
        View view2 = (View) findViewById(R.id.view2);
        ImageView applogo = (ImageView) findViewById(R.id.ivLogo);
        TextView tvOr = (TextView) findViewById(R.id.tvOr);
        TextView tvDontHave = (TextView) findViewById(R.id.tvDontHave);
        layout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            view1.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view2.setBackgroundColor(getResources().getColor(R.color.viewColor));
            tvOr.setTextColor(getResources().getColor(R.color.hintColor));
            tvDontHave.setTextColor(getResources().getColor(R.color.hintColor));
            applogo.setImageResource(R.drawable.applogo);
        } else{
            view1.setBackgroundColor(getResources().getColor(R.color.textColor));
            view2.setBackgroundColor(getResources().getColor(R.color.textColor));
            tvOr.setTextColor(getResources().getColor(R.color.white));
            tvDontHave.setTextColor(getResources().getColor(R.color.white));
            applogo.setImageResource(R.drawable.applogo2);
        }
    }
}
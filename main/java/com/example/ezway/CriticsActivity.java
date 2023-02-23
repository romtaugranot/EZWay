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

public class CriticsActivity extends Activity implements View.OnClickListener {

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private SharedPreferences preferences;

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critics);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack = (ImageView) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(this);
    }

    private void changeBackground(int color) {
        LinearLayout layout = findViewById(R.id.critics_layout);
        View view1 = findViewById(R.id.view1);
        View view2 = findViewById(R.id.view2);
        TextView[] textViews = new TextView[3];
        textViews[0] = findViewById(R.id.tvReviews);
        textViews[1] = findViewById(R.id.tvUsername);
        textViews[2] = findViewById(R.id.tvDate);
        ImageView appLogo = findViewById(R.id.ivLogo);
        ImageView ivArrow = findViewById(R.id.btnBack);
        ImageView ivStars = findViewById(R.id.ivStars);
        layout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            view1.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view2.setBackgroundColor(getResources().getColor(R.color.viewColor));
            ivArrow.setImageResource(R.drawable.arrow);
            appLogo.setImageResource(R.drawable.applogo);
            ivStars.setImageResource(R.drawable.rating0);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.textColor));
            }
        } else{
            view1.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view2.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            ivArrow.setImageResource(R.drawable.arrow2);
            appLogo.setImageResource(R.drawable.applogo2);
            ivStars.setImageResource(R.drawable.rating02);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
        }
    }
}

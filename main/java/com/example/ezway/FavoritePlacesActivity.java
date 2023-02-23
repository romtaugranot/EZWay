package com.example.ezway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FavoritePlacesActivity extends Activity implements View.OnClickListener {

    private ImageView btnBack;

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_places);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        btnBack = (ImageView) findViewById(R.id.btnBack);

        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack.setOnClickListener(this);
    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.favorite_places_layout);
        View view1 = (View) findViewById(R.id.view1);
        View view2 = (View) findViewById(R.id.view2);
        ImageView applogo = (ImageView) findViewById(R.id.ivLogo);
        ImageView arrow = (ImageView) findViewById(R.id.btnBack);
        TextView[] textViews = new TextView[6];
        textViews[0] = findViewById(R.id.tvFavoritePlaces);
        textViews[1] = findViewById(R.id.tvPlace1);
        textViews[2] = findViewById(R.id.tvPlace2);
        textViews[3] = findViewById(R.id.tvPlace3);
        textViews[4] = findViewById(R.id.tvPlace4);
        textViews[5] = findViewById(R.id.tvPlace5);
        ImageView[] stars = new ImageView[5];
        stars[0] = findViewById(R.id.ivStar1);
        stars[1] = findViewById(R.id.ivStar2);
        stars[2] = findViewById(R.id.ivStar3);
        stars[3] = findViewById(R.id.ivStar4);
        stars[4] = findViewById(R.id.ivStar5);
        layout.setBackgroundColor(color);
        //ImageView
        if (color == getResources().getColor(R.color.white)){
            view1.setBackgroundColor(getResources().getColor(R.color.viewColor));
            view2.setBackgroundColor(getResources().getColor(R.color.viewColor));
            applogo.setImageResource(R.drawable.applogo);
            arrow.setImageResource(R.drawable.arrow);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.textColor));
            }
            for (ImageView star : stars){
                star.setImageResource(R.drawable.star);
            }
        } else{
            view1.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            view2.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
            applogo.setImageResource(R.drawable.applogo2);
            arrow.setImageResource(R.drawable.arrow2);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.white));
            }
            for (ImageView star : stars){
                star.setImageResource(R.drawable.star2);
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
}

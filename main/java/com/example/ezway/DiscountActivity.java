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

public class DiscountActivity extends Activity implements View.OnClickListener {

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private SharedPreferences preferences;

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
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
        LinearLayout layout =(LinearLayout) findViewById(R.id.discount_layout);
        View view1 = findViewById(R.id.view1);
        View view2 = findViewById(R.id.view2);
        TextView tvDiscount = findViewById(R.id.tvDiscount);
        TextView[] textViews = new TextView[3];
        textViews[0] = findViewById(R.id.tvSale1);
        textViews[1] = findViewById(R.id.tvSale2);
        textViews[2] = findViewById(R.id.tvSale3);
        ImageView appLogo = (ImageView) findViewById(R.id.ivLogo);
        ImageView ivArrow = (ImageView) findViewById(R.id.btnBack);
        layout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            tvDiscount.setTextColor(getResources().getColor(R.color.textColor));
            ivArrow.setImageResource(R.drawable.arrow);
            appLogo.setImageResource(R.drawable.applogo);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.textColor));
                t.setBackgroundResource(R.drawable.mapbuttondesign);
            }
        } else{
            tvDiscount.setTextColor(getResources().getColor(R.color.white));
            ivArrow.setImageResource(R.drawable.arrow2);
            appLogo.setImageResource(R.drawable.applogo2);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.white));
                t.setBackgroundResource(R.drawable.mapbuttondesign2);
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

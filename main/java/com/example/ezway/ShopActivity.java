package com.example.ezway;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ShopActivity extends Activity implements View.OnClickListener {

    private static final int REQUEST_CALL = 1;
    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private SharedPreferences preferences;

    private TextView phoneCallButton;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack = (ImageView) findViewById(R.id.btnBack);
        phoneCallButton = (TextView) findViewById(R.id.phoneCallButton);

        phoneCallButton.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.shop_layout);
        View[] views = new View[5];
        views[0] = findViewById(R.id.view1);
        views[1] = findViewById(R.id.view2);
        views[2] = findViewById(R.id.view3);
        views[3] = findViewById(R.id.view4);
        views[4] = findViewById(R.id.view5);
        TextView[] textViews = new TextView[3];
        textViews[0] = findViewById(R.id.tvDescription);
        textViews[1] = findViewById(R.id.phoneCallButton);
        textViews[2] = findViewById(R.id.tvHours);
        ImageView appLogo = (ImageView) findViewById(R.id.ivLogo);
        ImageView ivArrow = (ImageView) findViewById(R.id.btnBack);
        ImageView ivClock = (ImageView) findViewById(R.id.ivClock);
        layout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            ivArrow.setImageResource(R.drawable.arrow);
            appLogo.setImageResource(R.drawable.applogo);
            ivClock.setImageResource(R.drawable.clock);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.textColor));
            }
            for (View v : views){
                v.setBackgroundColor(getResources().getColor(R.color.viewColor));
            }
        } else{
            ivArrow.setImageResource(R.drawable.arrow2);
            appLogo.setImageResource(R.drawable.applogo2);
            ivClock.setImageResource(R.drawable.clock2);
            for (TextView t : textViews){
                t.setTextColor(getResources().getColor(R.color.white));
            }
            for (View v : views){
                v.setBackgroundColor(getResources().getColor(R.color.darkBackground2));
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
            case R.id.phoneCallButton:
                makePhoneCall();
                break;
        }
    }

    private void makePhoneCall() {
        String number = "0509070662";
        if (ContextCompat.checkSelfPermission(ShopActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(ShopActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else{
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
        }
    }
}

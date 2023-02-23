package com.example.ezway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MapActivity extends Activity implements View.OnClickListener {

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";
    public static final String LANG_PREF = "LANG_PREF";

    private LinearLayout openBar;
    private LinearLayout openMenuActivity;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        openBar = findViewById(R.id.openBar);
        openMenuActivity = findViewById(R.id.menu);

        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        openMenuActivity.setOnClickListener(this);
        openBar.setOnClickListener(this);

    }

    private void changeLanguage(View view){
        TextView tvMeters = view.findViewById(R.id.tvMetersToShop);
        TextView shopButton = view.findViewById(R.id.shopButton);
        TextView discountButton = view.findViewById(R.id.discountButton);
        TextView criticsButton = view.findViewById(R.id.criticsButton);
        if (preferences.getBoolean(LANG_PREF, true)){
            tvMeters.setText(R.string.tvMetersToGo2);
            shopButton.setText(R.string.shopButton2);
            discountButton.setText(R.string.discountButton2);
            criticsButton.setText(R.string.criticsButton2);
        } else{
            tvMeters.setText(R.string.tvMetersToGo);
            shopButton.setText(R.string.shopButton);
            discountButton.setText(R.string.discountButton);
            criticsButton.setText(R.string.criticsButton);
        }
    }

    private void changeBackground(int color) {
        LinearLayout barDown = findViewById(R.id.barDown);
        TextView tvMetersToShop = findViewById(R.id.tvMetersToShop);
        ImageView ivArrow = findViewById(R.id.ivArrow);
        ImageView ivSearch = findViewById(R.id.ivSearch);
        barDown.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            ivArrow.setImageResource(R.drawable.arrow);
            ivSearch.setImageResource(R.drawable.search);
            tvMetersToShop.setTextColor(getResources().getColor(R.color.textColor));
        }else{
            ivArrow.setImageResource(R.drawable.arrow2);
            ivSearch.setImageResource(R.drawable.search2);
            tvMetersToShop.setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.openBar:
                openBottomSheetDialog();
                break;
            case R.id.menu:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.shopButton:
                intent = new Intent(this, ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.discountButton:
                intent = new Intent(this, DiscountActivity.class);
                startActivity(intent);
                break;
            case R.id.criticsButton:
                intent = new Intent(this, CriticsActivity.class);
                startActivity(intent);
                break;

        }
    }


    private void openBottomSheetDialog(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.Theme_Design_BottomSheetDialog);
        View bottomSheetView;
        if (preferences.getBoolean(THEME_PREF, true)){
            bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout,(LinearLayout) findViewById(R.id.bottomSheetContainer));
        } else{
            bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout2,(LinearLayout) findViewById(R.id.bottomSheetContainer));
        }
        bottomSheetDialog.setContentView(bottomSheetView);
        changeLanguage(bottomSheetView);
        bottomSheetDialog.show();
        bottomSheetView.findViewById(R.id.closeBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            }
        });
        bottomSheetView.findViewById(R.id.shopButton).setOnClickListener(MapActivity.this);
        bottomSheetView.findViewById(R.id.discountButton).setOnClickListener(MapActivity.this);
        bottomSheetView.findViewById(R.id.criticsButton).setOnClickListener(MapActivity.this);
    }

}

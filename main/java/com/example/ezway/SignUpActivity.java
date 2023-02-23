package com.example.ezway;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends Activity implements View.OnClickListener{

    public static final String PREFS_NAME = "PREFERENCE";
    public static final String THEME_PREF = "THEME_PREF";

    private ImageView btnBack;
    private Button btnSignUp;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordCon;
    private EditText etEmail;
    private Button btnDatePicker;
    private RadioGroup radioGroup;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordCon = (EditText) findViewById(R.id.etPasswordCon);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        if (preferences.getBoolean(THEME_PREF, true)){
            changeBackground(getResources().getColor(R.color.white));
        } else{
            changeBackground(getResources().getColor(R.color.darkBackground));
        }

        btnBack.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSignUp:
                boolean check = SignedUp();
                if (check) {
                    intent = new Intent(this, MapActivity.class);
                    startActivity(intent);
                }
                else{
                    break;
                }
                break;
            case R.id.btnDatePicker:
                showDatePicker();
                break;
        }
    }

    private boolean SignedUp() {

        if (etPassword.getText().toString().equals(etPasswordCon.getText().toString()) && etPassword.getText().toString().length() > 0){
            // TODO: connect to firebase and authentication
            return true;
        }
        else{
            Toast.makeText(this, "Password incorrect", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void changeBackground(int color) {
        LinearLayout layout =(LinearLayout) findViewById(R.id.signup_activity);
        ImageView applogo = (ImageView) findViewById(R.id.ivLogo);
        ImageView arrow = (ImageView) findViewById(R.id.btnBack);
        layout.setBackgroundColor(color);
        if (color == getResources().getColor(R.color.white)){
            applogo.setImageResource(R.drawable.applogo);
            arrow.setImageResource(R.drawable.arrow);
        } else{
            applogo.setImageResource(R.drawable.applogo2);
            arrow.setImageResource(R.drawable.arrow2);
        }
    }

    private void showDatePicker(){
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Date datePicked = new Date(year,monthOfYear,dayOfMonth);
                        Date dateToday = new Date(mYear,mMonth,mDay);
                        if (datePicked.after(dateToday)){
                            Toast.makeText(SignUpActivity.this, "Incorrect date", Toast.LENGTH_LONG).show();
                        } else {
                            // TODO: send to user class the date
                        }
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void onRadioButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();

    }
}

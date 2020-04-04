package com.neo.notification_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailText;
    Button loginButton;
    CheckBox checkBox;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        loginButton = findViewById(R.id.loginButton);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                if(emailText.getText().toString().equalsIgnoreCase("test@gmail.com")){
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "wrong username", Toast.LENGTH_LONG).show();
                }
            }
        });

        emailText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)&&(emailText.getText().toString().equalsIgnoreCase("test@gmail.com"))){
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_LONG).show();
                    finish();
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}

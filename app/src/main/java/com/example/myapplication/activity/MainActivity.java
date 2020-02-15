package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_new);
    }

    @Override protected void onStart() {
        super.onStart();
        mLoginEditText = findViewById(R.id.et_login);
        mPasswordEditText = findViewById(R.id.et_password);
        Button signInButton = findViewById(R.id.btn_sign_in);

        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                String login = mLoginEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if (TextUtils.isEmpty(login)) {
                    mLoginEditText.setError("Cannot be empty");
                    return;
                } else {
                    mLoginEditText.setError(null);
                }
                if (TextUtils.isEmpty(password)) {
                    mPasswordEditText.setError("Cannot be empty");
                    return;
                } else {
                    mPasswordEditText.setError(null);
                }

                Intent intent = new Intent(MainActivity.this, MainActivityTwo.class);
                startActivity(intent);
            }
        });
    }
}

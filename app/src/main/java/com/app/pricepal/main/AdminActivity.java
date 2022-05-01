package com.app.pricepal.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.pricepal.R;

public class AdminActivity extends AppCompatActivity {
    Button login_btn_admin, loginpage;
    EditText email_et_admin;
    EditText password_et_admin;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
}
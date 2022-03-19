package com.app.pricepal.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.app.pricepal.MainActivity;
import com.app.pricepal.R;

public class Signup extends AppCompatActivity {
    Button signup_btn;
    TextView login_tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getSupportActionBar().hide();

        signup_btn=findViewById(R.id.signup_btn);
        login_tv=findViewById(R.id.login_tv);

        signup_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),MainActivity.class)));
        login_tv.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),Login.class)));
    }
}

package com.app.pricepal.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.app.pricepal.MainActivity;
import com.app.pricepal.R;

public class Login extends AppCompatActivity {
    Button login_btn;
    TextView tvSignup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

         tvSignup=findViewById(R.id.signup_tv);
         login_btn=findViewById(R.id.login_btn);

         login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });
    }
}

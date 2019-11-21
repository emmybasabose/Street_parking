package com.example.street_parking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelp db=new DatabaseHelp(this);
    private EditText name;
    private EditText password;
    private Button Login;
    private TextView register;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.id_name);
        password = findViewById(R.id.id_pwd);
        Login = findViewById(R.id.btn_login);
        register = findViewById(R.id.id_reg);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }

        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Lname = name.getText().toString();
                String Lpass=password.getText().toString();
               if(db.logins(Lname,Lpass)) {
                   Intent intent = new Intent(MainActivity.this,MainActivityss.class);
                   Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                   startActivity(intent);
               }
               else
                   Toast.makeText(getApplicationContext(), "Login failed. You can Register.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

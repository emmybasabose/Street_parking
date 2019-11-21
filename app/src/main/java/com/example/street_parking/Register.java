package com.example.street_parking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    DatabaseHelp db=new DatabaseHelp(this);
    EditText name,tel,pass;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name=findViewById(R.id.staffname);
        tel=findViewById(R.id.tel);
        pass=findViewById(R.id.pass);
        register=findViewById(R.id.signup);

        //listView=findViewById(R.id.listView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Sname = name.getText().toString();
                String Stel = tel.getText().toString();
                String Spass=pass.getText().toString();

                if (Sname.equals("") || Spass.equals("")||Stel.equals("")) {
                    Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    long Staff=db.staff(Sname,Stel);
                    long Login=db.login(Sname,Spass);
                    if (Staff!=-1 && Login!=-1)Toast.makeText(getApplicationContext(), "Registered Successfully.", Toast.LENGTH_SHORT).show();

                    else Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_SHORT).show();

                }
            }});
    }
}

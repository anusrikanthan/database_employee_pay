package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.payapp.EmpClass;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    public EditText phone, email, dob, name, designation, joinDate, achievements,empNo,password,interpersonal;
    public String manager;
    RadioButton yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
    }
    public void signbuttonpressed(View view) {
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        name = findViewById(R.id.name);
        designation = findViewById(R.id.desig);
        joinDate = findViewById(R.id.joindate);
        achievements = findViewById(R.id.achieve);
//        manager = findViewById(R.id.manager);
        password = findViewById(R.id.password);
        interpersonal = findViewById(R.id.inter);
        empNo = findViewById(R.id.empno);
        yes = findViewById(R.id.yesbut); no = findViewById(R.id.nobut);
        if(yes.isChecked()) {
            manager = "Yes";
        }
        else if(no.isChecked()) {
            manager = "No";
        }

        //NEW ENTRY REQUIRED IN DATABASE


    }
    public void goBack(View view) {
        Intent i = new Intent(Register.this,MainActivity.class);
        startActivity(i);
    }
}

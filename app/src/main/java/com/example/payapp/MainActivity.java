package com.example.payapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText empno;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void registerOpen(View view) {
        Intent i = new Intent(MainActivity.this,Register.class);
        startActivity(i);
    }
    public void signin(View view) {
        empno = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        Intent i = new Intent(MainActivity.this,Employees.class);
        startActivity(i);
    }
}

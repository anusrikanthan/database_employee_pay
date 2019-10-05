package com.example.payapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class appraisalRating extends AppCompatActivity {

    SQLiteDatabase db;
    EmpClass employeeDetails;
    //    public String phone, email, dob, name, designation, joinDate, achievements;
//    int empNo;
//    Boolean manager;
    public TextView phone, email, dob, name, designation, joinDate, achievements,empNo,manager,pay,apprx,InterP;
    public EditText result;
    String appr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraisal_rating);
        getSupportActionBar().hide();

        db = openOrCreateDatabase("Employee", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR,designation VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");
        Bundle b = getIntent().getExtras();
        String reg_emp_no = b.getString("regempid");
        phone = findViewById(R.id.phonenumber);
        name = findViewById(R.id.usrname);
        email = findViewById(R.id.usremail);
        dob = findViewById(R.id.dob);
        designation = findViewById(R.id.desig);
        joinDate = findViewById(R.id.joindate);
        achievements = findViewById(R.id.achieve);
        empNo = findViewById(R.id.empno);
        manager = findViewById(R.id.manager);
        pay = findViewById(R.id.payfield);
        apprx = findViewById(R.id.appraisal);
        InterP = findViewById(R.id.inter);

        Cursor c = db.rawQuery("SELECT * FROM employee WHERE emp_id=" + reg_emp_no, null);
        if (c.moveToFirst()) {
            // Displaying record if found 
//            (name VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER)
            phone.setText(c.getString(6));
            name.setText(c.getString(0));
            email.setText(c.getString(5));
            dob.setText(c.getString(8));
            designation.setText(c.getString(1));
            joinDate.setText(c.getString(7));
            achievements.setText(c.getString(10));
            empNo.setText(c.getString(2));

            if((c.getString(4)).equals("-1")) {
                manager.setText("No");
            }
            else {
                manager.setText("Yes");
            }
            pay.setText(c.getString(13));

            apprx.setText(c.getString(12));
            InterP.setText(c.getString(9));
        }
    }
    public void editAppraisal(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
//        result = findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
//                                result.setText(userInput.getText());
                                appr = userInput.getText().toString();
                                if(Integer.parseInt(appr)<11) {
                                    Toast.makeText(appraisalRating.this, appr, Toast.LENGTH_SHORT).show();

                                    Cursor c = db.rawQuery("SELECT * FROM employee WHERE emp_id=" + empNo.getText().toString(), null);
                                    int ran = 11-Integer.parseInt(appr);
                                    int pay = 10000*Integer.parseInt(appr);
                                    db.execSQL("UPDATE employee SET rating='" + appr + "',rank='" + ran +"',pay="+pay);

                                    Intent i = new Intent(appraisalRating.this,appraisalRating.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    i.putExtra("regempid",empNo.getText().toString());
                                    Toast.makeText(appraisalRating.this, "Appraisal rating successfully updated!", Toast.LENGTH_SHORT).show();
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(appraisalRating.this, "Please enter a valid appraisal rating!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
}

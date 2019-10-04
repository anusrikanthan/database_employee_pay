package com.example.payapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.payapp.EmpClass;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    SQLiteDatabase db;
    public EditText phone, email, dob, name, designation, joinDate, achievements,empNo,password,interpersonal;
    public String manager;
    RadioButton yes,no;
    EmpClass e;
    int flag1=0;
    int flag2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        db = openOrCreateDatabase("Employee", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR,emp_id INTEGER,password VARCHAR,employer INTEGER,email VARCHAR,phone VARCHAR,joindate DATE, dob DATE, interpersonal_skills VARCHAR, achievements VARCHAR,rank INTEGER, rating INTEGER,pay INTEGER);");
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
        e = new EmpClass();
        e.phone = phone.getText().toString();
        e.email = email.getText().toString();
        e.dob = dob.getText().toString();
        e.name = name.getText().toString();
        e.designation = designation.getText().toString();
//        e.joinDate = joinDate.getText().toString();
        e.achievements = achievements.getText().toString();
        e.password = password.getText().toString();
        e.interpersonalSkill = interpersonal.getText().toString();
        e.empNo = Integer.parseInt(empNo.getText().toString());
        if(manager.equals("yes"))
            e.employer = 1;
        else
            e.employer = -1;
        e.pay = -1;
        e.rating = 0;       //Rating on a scale of 1 to n, 0 means unrated
        e.rank = -2;
//        new Thread(new Runnable() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void run() {
//                flag1=0;
//                try {
//                    Log.d("ff","blah blah");
//                    Registerr();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        db.execSQL("INSERT INTO employee VALUES('" + e.name + "','" + e.empNo +"','" + e.password + "','" + e.employer + "','" + e.email + "','" + e.phone + "','" + e.joinDate + "','" + e.dob + "','" + e.interpersonalSkill + "','" + e.achievements + "','" + e.rank + "','" + e.rating + "','" + e.pay + "');");
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        //        clearText();
        Intent i = new Intent(Register.this,MainActivity.class);
        startActivity(i);
    }
    public void goBack(View view) {
        Intent i = new Intent(Register.this,MainActivity.class);
        startActivity(i);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}


//    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//
//    OkHttpClient client = new OkHttpClient();
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    String post(String url, String json) throws IOException
//    {
//        RequestBody body = RequestBody.create(JSON,json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute())
//        {
//
//            return response.body().string();
//        }
//    }
//
//    String buildJson(EmpClass e) {
//        return "{\"name\":\""+e.name+ "\",\"emp_id\":\""+e.empNo+"\",\"password\":\""+e.password+"\",\"employer\":\""+e.employer+"\",\"email\":\""+e.email+"\",\"phone\":\""+e.phone+"\",\"joindate\":\""+e.joinDate+"\",\"interpersonal_skill\":\""+e.interpersonalSkill+"\",\"achievements\":\""+e.achievements+"\",\"rank\":\""+e.rank+"\",\"rating\":\""+e.rating+"\",\"pay\":\""+e.pay+"\"}";
//    }
//
//    public void Registerr() throws IOException, JSONException
//    {
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        String url = "http://192.168.43.68:3100/register";
//        String json = buildJson(e);
//        Log.d("hh",json);
//        Log.d("ff","json created");
//        String response = post(url, json);
//        Log.d("ff",response.toString());
//        String[] str=response.split("data");
//        int i=0;
//        for(String a:str)
//        {
//            if(i==0)
//            {
//                Log.d("HelloExcuseme",a.toString());
//                if(response.length()==a.length())
//                {
//
//                    flag1=8;
//                    i=i+1;
//                    break;
//                }
//                else
//                {
//
//                    JSONObject jobj = new JSONObject(response);
//                    Log.d("ff",jobj.toString());
//                    flag1=0;
//                    i=i+1;
//                    Intent i1=new Intent(this,MainActivity.class);
//                    startActivity(i1);
//
//                }
//
//            }
//        }
//
//
//    }
//}

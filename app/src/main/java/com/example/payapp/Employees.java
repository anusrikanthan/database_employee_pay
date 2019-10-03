package com.example.payapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payapp.EmpClass;

import java.util.ArrayList;
import java.util.List;

public class Employees extends AppCompatActivity implements MyItemClickListener {

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.show_profile:
                Toast.makeText(getApplicationContext(),"Here is your profile",Toast.LENGTH_LONG).show();

                //GET EMPLOYER INFO AND SEND IT HERE

                Intent i = new Intent(Employees.this,employee_profile.class);
                startActivity(i);
                return true;
            case R.id.show_logout:
                Toast.makeText(getApplicationContext(),"Logging out...",Toast.LENGTH_LONG).show();
                Intent x = new Intent(Employees.this,MainActivity.class);
                startActivity(x);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public  ArrayList<EmpClass> mData,employees;
    @Override
    public void onItemClick(View view, int postion) {
        EmpClass bean = employees.get(postion);
        if(bean != null){

            //Start Employee Profile Activity Here

            Toast.makeText(this, bean.name, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(Employees.this,appraisalRating.class);
            startActivity(i);
        }
    }
    RecyclerView rv;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        rv  = (RecyclerView) findViewById(R.id.rv);
        llm= new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        employees = new ArrayList<EmpClass>();
        employees.add(new EmpClass("Ajay","CEO",1));
        employees.add(new EmpClass("Rahul","Marketing",2));
        employees.add(new EmpClass("Souza","Design Manager",3));
        RVAdapter adapter = new RVAdapter(employees);
        adapter.setOnItemClickListener(this);
        rv.setAdapter(adapter);
    }

}


class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>  {

    List<EmpClass> employees;
    private MyItemClickListener mItemClickListener;

    RVAdapter(List<EmpClass> employees){
        this.employees = employees;
    }
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v,mItemClickListener);
        return pvh;
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;

    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(employees.get(i).name);
        personViewHolder.personAge.setText(employees.get(i).designation);
//        personViewHolder.personPhoto.setImageResource(employees.get(i).photoId);
    }



    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView personName;
        TextView personAge,empNo;
        ImageView personPhoto;
        private MyItemClickListener mListener;

        PersonViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_designation);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            empNo = (TextView) itemView.findViewById(R.id.empno);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getPosition());
            }
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
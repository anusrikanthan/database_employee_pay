package com.example.payapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Projects extends AppCompatActivity implements MyItemClickListener {

    String emp_nooo;
    Bundle b;
    SQLiteDatabase proj;

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        b = getIntent().getExtras();
        int id = item.getItemId();
        switch (id){
            case R.id.show_profile:
                //GET EMPLOYER INFO AND SEND IT HERE
                Intent i = new Intent(getApplicationContext(),employee_profile.class);
                emp_nooo = b.getString("reg_emp_id");
                Toast.makeText(this, emp_nooo, Toast.LENGTH_SHORT).show();
                i.putExtra("reg_emp_id",emp_nooo);
                startActivity(i);
                return true;
            case R.id.show_logout:
                Toast.makeText(getApplicationContext(),"Logging out...",Toast.LENGTH_LONG).show();
                Intent x = new Intent(getApplicationContext(),MainActivity.class);
                x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(x);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<ProjClass> employees;
    ProjClass e;
    RecyclerView rv;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);


        proj = openOrCreateDatabase("Project", Context.MODE_PRIVATE, null);
        proj.execSQL("CREATE TABLE IF NOT EXISTS project(project_id INTEGER, start_date DATE, status VARCHAR, proj_desc VARCHAR, deadline DATE, completion_date DATE, emp_id INTEGER);");
        rv  = (RecyclerView) findViewById(R.id.rv);
        llm= new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        employees = new ArrayList<ProjClass>();




        Cursor c = db.rawQuery("SELECT * FROM employee", null);
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext())
        {
            Log.i("0",c.getString(0));
            Log.i("1",c.getString(1));
            Log.i("2",c.getString(2));
            Log.i("3",c.getString(3));
            Log.i("4",c.getString(4));
            Log.i("5",c.getString(5));
            Log.i("6",c.getString(6));
//            (project_id INTEGER, start_date DATE, status VARCHAR, proj_desc VARCHAR, deadline DATE, completion_date DATE, emp_id INTEGER);");

            if(Integer.parseInt(c.getString(4))==-1)
                employees.add(new ProjClass(Integer.parseInt(c.getString(0)),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        Integer.parseInt(c.getString(6))));
        }

        RVAdapter2 adapter = new RVAdapter2(employees);
        adapter.setOnItemClickListener(this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int postion) {
        ProjClass bean = employees.get(postion);
        if(bean != null){

            //Start Employee Profile Activity Here

            Intent i = new Intent(getApplicationContext(),appraisalRating.class);
            i.putExtra("regempid",String.valueOf(bean.project_id));
            startActivity(i);
        }
    }
}

class RVAdapter2 extends RecyclerView.Adapter<RVAdapter2.PersonViewHolder>  {

    List<ProjClass> employees;
    private MyItemClickListener mItemClickListener;

    RVAdapter2(List<ProjClass> employees){
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
        personViewHolder.personName.setText(employees.get(i).proj_desc);
        personViewHolder.personAge.setText(employees.get(i).project_id);
        String temp = String.valueOf(employees.get(i).deadline);
        personViewHolder.empNoo.setText(temp);
//        personViewHolder.personPhoto.setImageResource(employees.get(i).photoId);
    }



    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView personName;
        TextView personAge,empNoo;
        ImageView personPhoto;
        private MyItemClickListener mListener;

        PersonViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_designation);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            empNoo = (TextView) itemView.findViewById(R.id.person_number);
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
package com.example.payapp;

//        proj.execSQL("CREATE TABLE IF NOT EXISTS project(project_id INTEGER, start_date DATE, status VARCHAR, proj_desc VARCHAR, deadline DATE, completion_date DATE, emp_id INTEGER);");

public class ProjClass {
    int project_id;
    String start_date,status,proj_desc,deadline,completion_date;
    int emp_id;
    ProjClass(int project_id ,String start_date ,String status ,String proj_desc ,String deadline ,String completion_date ,int emp_id ) {
        this.proj_desc = proj_desc;
        this.project_id = project_id;
        this.start_date = start_date;
        this.status = status;
        this.deadline = deadline;
        this.completion_date = completion_date;
    }
}

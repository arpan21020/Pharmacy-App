package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class Appointments extends AppCompatActivity {
    private String user_id;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Scheduled_Appointments> list=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        user_id=getIntent().getStringExtra("user_id_for_appointments");
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView=findViewById(R.id.recyclerview);


        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM CONSULTATION WHERE USER_ID="+user_id+";",null);
        if(query.moveToFirst()){
            do {
                Scheduled_Appointments doc=new Scheduled_Appointments(query.getString(1),query.getString(2),
                        query.getString(3),query.getString(4),Integer.toString(query.getInt(5)));
                list.add(doc);

            }while(query.moveToNext());
        }
        query.close();

        AppointmentsAdapter c=new AppointmentsAdapter(this,list);
        recyclerView.setAdapter(c);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            Intent intent=new Intent();
            intent.putExtra("user_id_from_appointments", user_id);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        Intent intent=new Intent();
        intent.putExtra("user_id_from_appointments", user_id);
        setResult(RESULT_OK,intent);
        finish();

    }
}

class Scheduled_Appointments{
    private String app_id;
    private String doc;
    private String date;
    private String time;
    private String fee;

    public Scheduled_Appointments(String app_id, String doc, String date, String time, String fee) {
        this.app_id = app_id;
        this.doc = doc;
        this.date = date;
        this.time = time;
        this.fee = fee;
    }

    public String getApp_id() {
        return app_id;
    }

    public String getDoc() {
        return doc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFee() {
        return fee;
    }
}
package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class DoctorsDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<Doctor> list= new ArrayList<>();
    private String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_details);
        user_id=getIntent().getStringExtra("user_id_for_doctor");
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView=findViewById(R.id.recyclerview);
        CustomAdapter c=new CustomAdapter(this,list,sqLiteDatabase,user_id);
        recyclerView.setAdapter(c);

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM Doctors;",null);
        if(query.moveToFirst()){
            do {
                Doctor doc=new Doctor(query.getString(1)+query.getString(2)+query.getString(3),
                        query.getString(9), query.getString(4), query.getString(5),query.getString(6),
                        query.getString(7), query.getString(8) );
                list.add(doc);

            }while(query.moveToNext());
        }
        query.close();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            Intent intent=new Intent();
            intent.putExtra("user_id_from_doctor", user_id);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        Intent intent=new Intent();
        intent.putExtra("user_id_from_doctor", user_id);
        setResult(RESULT_OK,intent);
        finish();

    }
}

class Doctor{


    private String name;
    private String specialization;
    private String exp;
    private String addr;
    private String charges;
    private String email;
    private String mobile;
    public Doctor(String name, String specialization, String exp, String addr, String charges, String email, String mobile) {
        this.name = name;
        this.specialization = specialization;
        this.exp = exp;
        this.addr = addr;
        this.charges = charges;
        this.email = email;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getExp() {
        return exp;
    }

    public String getAddr() {
        return addr;
    }

    public String getCharges() {
        return charges;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}
//    Doctor_ID VARCHAR(255) PRIMARY KEY,
//    FirstName VARCHAR(255),
//    MiddleName VARCHAR(255),
//    LastName VARCHAR(255),
//    Experience VARCHAR(255),
//    Contact_Address VARCHAR(255),
//    Charges INT,
//    Email VARCHAR(255),
//    Mobile_no BIGINT,
//    Specialization VARCHAR(255)
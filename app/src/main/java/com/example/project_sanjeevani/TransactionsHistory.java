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

public class TransactionsHistory extends AppCompatActivity {
    private String user_id;
    SQLiteDatabase sqLiteDatabase;
    Intent intent=new Intent();
    ArrayList<TransactionsRecord> list=new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_history);
        user_id=getIntent().getStringExtra("user_id_for_transactions");
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        recyclerView=findViewById(R.id.recycle_t);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM PAYMENTS WHERE USER_ID="+user_id+";",null);
        if(query.moveToFirst()){
            do {
               TransactionsRecord doc=new TransactionsRecord(query.getString(0),Integer.toString(query.getInt(2)),
                       query.getString(3),query.getString(4));
                list.add(doc);

            }while(query.moveToNext());
        }
        query.close();

        PaymentsAdapter c=new PaymentsAdapter(this,list);
        recyclerView.setAdapter(c);







    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            intent.putExtra("user_id_from_transactions", user_id);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        intent.putExtra("user_id_from_transactions", user_id);
        setResult(RESULT_OK,intent);
        finish();

    }
}

class TransactionsRecord{
    private String t_id;
    private String t_blnc;
    private String t_date;
    private String t_remarks;

    public TransactionsRecord(String t_id, String t_blnc, String t_date, String t_remarks) {
        this.t_id = t_id;
        this.t_blnc = t_blnc;
        this.t_date = t_date;

        this.t_remarks = t_remarks;
    }

    public String getT_id() {
        return t_id;
    }

    public String getT_blnc() {
        return t_blnc;
    }

    public String getT_date() {
        return t_date;
    }



    public String getT_remarks() {
        return t_remarks;
    }
    //    "Transaction_ID varchar(255) PRIMARY KEY,\n" +
//            "User_Id INT,\n" +
//            "Balance BIGINT,\n" +
//            "Date_ DATE NOT NULL,\n" +
//            "Time_ TIME(0),\n" +
//            "Remarks TEXT(65535),\n" +
//            "FOREIGN KEY(User_ID) references Users(User_ID) ON DELETE CASCADE\n" +
//            "\n" +
}
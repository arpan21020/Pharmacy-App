package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class LabCart extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private RecyclerView recyclerView;
    ArrayList<Lab_cart> list=new ArrayList<>();
    private String user_id;
    private TextView l_totaltag;
    private  TextView l_total;
    private TextView l_msg;
    private Button l_checkout;
    private  Button l_clear;

    private int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_cart);
        user_id=getIntent().getStringExtra("user_id_for_labCart");
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        recyclerView=findViewById(R.id.recyclerView);


        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM LAB_TESTS WHERE USER_ID="+user_id+";",null);
        if(query.moveToFirst()){
            do {
                Lab_cart doc=new Lab_cart(query.getString(5),Integer.toString(query.getInt(6)),query.getString(3),query.getString(4));
                list.add(doc);
                total+=query.getInt(6);

            }while(query.moveToNext());
        }
        query.close();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView=findViewById(R.id.recyclerview);
        l_cart_adapter c=new l_cart_adapter(this,list);
        recyclerView.setAdapter(c);
        l_checkout=findViewById(R.id.l_checkout);
        l_clear=findViewById(R.id.l_clear);
        l_msg=findViewById(R.id.l_msg);
        l_totaltag=findViewById(R.id.l_totaltag);
        l_total=findViewById(R.id.l_total);
        if (list.size()==0){
            l_checkout.setVisibility(View.GONE);
            l_clear.setVisibility(View.GONE);
            l_msg.setText("Your Cart is empty");
            l_total.setText("");
            l_totaltag.setText("");
        }
        else{
            l_checkout.setVisibility(View.VISIBLE);
            l_clear.setVisibility(View.VISIBLE);
            l_msg.setText("");
            l_total.setText(Integer.toString(total));
            l_totaltag.setText("Total :");
        }
        l_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String del="DELETE FROM LAB_TESTS WHERE USER_ID="+user_id+"";
                sqLiteDatabase.execSQL(del);
                recreate();
            }
        });
        l_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String update="UPDATE Users SET Acc_Balance=Acc_Balance-"+Integer.toString(total)+" WHERE USER_ID="+user_id+";";
                sqLiteDatabase.execSQL(update);
                String del="DELETE FROM LAB_TESTS WHERE USER_ID="+user_id+"";
                sqLiteDatabase.execSQL(del);

                String blnc=null;
                Cursor query2= sqLiteDatabase.rawQuery("SELECT ACC_BALANCE FROM USERS WHERE USER_ID="+user_id+";",null);
                if (query2.moveToFirst()){
                    blnc=Integer.toString(query2.getInt(0));
                    Toast.makeText(LabCart.this, "Updated Account Balance:"+query2.getInt(0), Toast.LENGTH_SHORT).show();
                }
                query2.close();

                Cursor query3=sqLiteDatabase.rawQuery("SELECT Transaction_ID from PAYMENTS;",null);
                int tid=1;
                if (query3.moveToLast()){
                    tid=Integer.parseInt(query3.getString(0).substring(6));
                    tid+=1;
                }
                query3.close();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String date=String.valueOf(day)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);
                String t_id="TRANS_"+Integer.toString(tid);
                sqLiteDatabase.execSQL("INSERT INTO PAYMENTS VALUES(\""+t_id+"\","+user_id+","+blnc+",\""+ date +"\",\"Booked Lab Test from Sanjeevani Pharmacy App\");");

                recreate();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            Intent intent=new Intent();
            intent.putExtra("user_id_from_labCart", user_id);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        Intent intent=new Intent();
        intent.putExtra("user_id_from_labCart", user_id);
        setResult(RESULT_OK,intent);
        finish();

    }
}


class Lab_cart{
    private String testName;
    private String testPrice;
    private String testDate;
    private String testTime;

    public Lab_cart(String testName, String testPrice, String testDate, String testTime) {
        this.testName = testName;
        this.testPrice = testPrice;
        this.testDate = testDate;
        this.testTime = testTime;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestPrice() {
        return testPrice;
    }

    public String getTestDate() {
        return testDate;
    }

    public String getTestTime() {
        return testTime;
    }
}
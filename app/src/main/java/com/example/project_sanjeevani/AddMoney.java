package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddMoney extends AppCompatActivity {
    private String user_id;
    SQLiteDatabase sqLiteDatabase;
    private EditText amount;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        user_id=getIntent().getStringExtra("user_id_for_addmoney");
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        amount=findViewById(R.id.entered_amount);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase.execSQL("UPDATE USERS SET ACC_BALANCE=ACC_BALANCE+"+amount.getText().toString()+" WHERE USER_ID="+user_id+";");
                String blnc=null;
                Cursor query2= sqLiteDatabase.rawQuery("SELECT ACC_BALANCE FROM USERS WHERE USER_ID="+user_id+";",null);
                if (query2.moveToFirst()){
                    blnc=Integer.toString(query2.getInt(0));
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
                sqLiteDatabase.execSQL("INSERT INTO PAYMENTS VALUES(\""+t_id+"\","+user_id+","+blnc+",\""+ date +"\",\""+amount.getText().toString()+" amount credited to account\");");

                Toast.makeText(AddMoney.this, "Money added to account", Toast.LENGTH_SHORT).show();
                amount.setText("");


            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            Intent intent=new Intent();
            intent.putExtra("user_id_from_addmoney", user_id);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        Intent intent=new Intent();
        intent.putExtra("user_id_from_addmoney", user_id);
        setResult(RESULT_OK,intent);
        finish();

    }
}
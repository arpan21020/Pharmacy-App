package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HomepageSidebar extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    public static final String EXTRA_USER_ID_2="com.example.project_sanjeevani.userid2";
    public static String TAG="tagged";
    private String user_id_send_back;
    Intent intent=new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_sidebar);
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        TextView usid=findViewById(R.id.usid);
        TextView nm=findViewById(R.id.name);
        TextView bday=findViewById(R.id.bday);
        TextView contactno=findViewById(R.id.contactno);
        TextView mail=findViewById(R.id.mail);
        TextView sex=findViewById(R.id.gender);
        TextView addr=findViewById(R.id.addr);
        TextView balance=findViewById(R.id.balance);
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM users where user_id="+getIntent().getStringExtra("finaluser_id")+";",null);
        if(query.moveToFirst()){
            usid.setText(Integer.toString(query.getInt(0)));
            user_id_send_back=Integer.toString(query.getInt(0));

           nm.setText(query.getString(1)+" "+query.getString(2)+" "+query.getString(3));
           bday.setText(query.getString(5));
           contactno.setText(Long.toString(query.getLong(6)));
           mail.setText(query.getString(4));
           sex.setText(query.getString(7));
           addr.setText(query.getString(8)+","+query.getString(9)+","+query.getString(10)+","+query.getString(11)+","+query.getString(12));
           balance.setText(Long.toString(query.getLong(13)));
        }
        query.close();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            intent.putExtra(EXTRA_USER_ID_2,user_id_send_back);

            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        intent.putExtra(EXTRA_USER_ID_2,user_id_send_back);

        setResult(RESULT_OK, intent);//change here
        finish();
    }
    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in Side bar");
        super.onPause();
        Log.d(TAG, "onPause: out in Side bar");
    }
}
package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Cart extends AppCompatActivity {

    private String user_id;
    ArrayList<Cart_items> items=new ArrayList<>();
    private TextView textView;
    private TextView textView2;
    private TextView textView3;


    private RecyclerView recyclerView;
    private Button checkout;
    private Button clear;
    int total=0;
    Intent intent=new Intent();
    HashMap<String,Integer> quant_id=new HashMap<String, Integer>();

    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        user_id=getIntent().getStringExtra("user_id_for_cart");
        Log.d("tagged",(user_id==null)?"user_id is null":"user_id is not null:"+user_id);


        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM CART WHERE USER_ID= "+user_id+";",null);

        if(query.moveToFirst()){
            do {
                Cart_items item= new Cart_items(query.getString(0),query.getString(1),
                        query.getString(2),query.getString(3),
                        query.getInt(4),query.getInt(5));
                total+=item.getC_quantity()*item.getC_price();
                items.add(item);
                quant_id.put(query.getString(0),query.getInt(5));
            }while(query.moveToNext());
        }
        query.close();
        textView=findViewById(R.id.c_total);
        textView2=findViewById(R.id.totaltag);
        textView3=findViewById(R.id.c_msg);

        checkout=findViewById(R.id.checkout);
        clear=findViewById(R.id.clear);
        Log.d("tagged",(textView==null)?"Yes it is":"No its not");
        if(items.size()==0){
            textView2.setText("");
            textView.setText("");

            textView3.setText("Your cart is empty");
            checkout.setVisibility(View.GONE);
            clear.setVisibility(View.GONE);
        }
        else{
            textView2.setText("Total :");
            textView.setText(Integer.toString(total));
            textView3.setText("");
            checkout.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);

        }
        recyclerView=findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView=findViewById(R.id.recyclerview);
        CartAdapter c=new CartAdapter(items);
        recyclerView.setAdapter(c);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String update="UPDATE Users SET Acc_Balance=Acc_Balance-"+Integer.toString(total)+" WHERE USER_ID="+user_id+";";
                sqLiteDatabase.execSQL(update);
                String del="DELETE FROM CART WHERE USER_ID=\""+user_id+"\"";
                sqLiteDatabase.execSQL(del);
                for (Map.Entry<String, Integer> e : quant_id.entrySet()){
                    sqLiteDatabase.execSQL("UPDATE Products SET QUANTITY=QUANTITY-"+Integer.toString(e.getValue())+" WHERE PRODUCT_ID=\""+e.getKey()+"\"");
                }


//                Cursor query= sqLiteDatabase.rawQuery(cmd,null);
//                Log.d("tagged",cmd);
//                query.close();
                String blnc=null;
                Cursor query2= sqLiteDatabase.rawQuery("SELECT ACC_BALANCE FROM USERS WHERE USER_ID="+user_id+";",null);
                if (query2.moveToFirst()){
                    blnc=Integer.toString(query2.getInt(0));
                    Toast.makeText(Cart.this, "Updated Account Balance:"+query2.getInt(0), Toast.LENGTH_SHORT).show();
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
                sqLiteDatabase.execSQL("INSERT INTO PAYMENTS VALUES(\""+t_id+"\","+user_id+","+blnc+",\""+ date +"\",\"Purchased Medicines from Sanjeevani Pharmacy App\");");

                recreate();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String del="DELETE FROM CART WHERE USER_ID=\""+user_id+"\"";
                sqLiteDatabase.execSQL(del);
                recreate();
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            intent.putExtra("user_id_from_cart", user_id);

            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        intent.putExtra("user_id_from_cart", user_id);

        setResult(RESULT_OK,intent);
        finish();

    }
}

class Cart_items{
    private String c_pid;
    private String c_user_id;
    private String c_p_name;
    private String c_p_des;
    private int c_price;
    private int c_quantity;

    public Cart_items(String c_pid, String c_user_id, String c_p_name, String c_p_des, int c_price, int c_quantity) {
        this.c_pid = c_pid;
        this.c_user_id = c_user_id;
        this.c_p_name = c_p_name;
        this.c_p_des = c_p_des;
        this.c_price = c_price;
        this.c_quantity = c_quantity;
    }

    public String getC_pid() {
        return c_pid;
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public String getC_p_name() {
        return c_p_name;
    }

    public String getC_p_des() {
        return c_p_des;
    }

    public int getC_price() {
        return c_price;
    }

    public int getC_quantity() {
        return c_quantity;
    }

}
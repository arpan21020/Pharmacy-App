package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Medicines extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Bitmap> imgDecoded=new ArrayList<>();
    ArrayList<ProductDescription> list=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("Sanjeevani.db", MODE_PRIVATE, null);

        img();
        user_id=getIntent().getStringExtra("user_id_for_medicines");
        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView=findViewById(R.id.recyclerview);
        MedicinesAdapter c=new MedicinesAdapter(this,imgDecoded,list,sqLiteDatabase,user_id);
        recyclerView.setAdapter(c);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Create an intent to send data back to the parent activity
            Intent intent=new Intent();
            intent.putExtra("user_id_from_medicines", user_id);
            setResult(RESULT_OK,intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        // Create an intent to send data back to the parent activity
        Intent intent=new Intent();
        intent.putExtra("user_id_from_medicines", user_id);
        setResult(RESULT_OK,intent);
        finish();

    }
    public void img(){
        ArrayList<Bitmap> images=new ArrayList<>();
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0001));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0002));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0003));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0004));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0005));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0006));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0007));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0008));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0009));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0010));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0011));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0012));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0013));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0014));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0015));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0016));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0017));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0018));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0019));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.p_0020));

        // Convert the Bitmap object to a byte array
        for (int i = 0; i < 20; i++) {
            imgDecoded.add(f(images.get(i)));
        }
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM Products;",null);
        if(query.moveToFirst()){
            do {
                ProductDescription pro=new ProductDescription(query.getString(0),
                        query.getString(1), query.getString(2), query.getString(3),
                        query.getString(4), Integer.toString(query.getInt(5)), Integer.toString(query.getInt(6)) );
                list.add(pro);

            }while(query.moveToNext());
        }
        query.close();



    }
    private Bitmap f(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] imageData = stream.toByteArray();
        Bitmap bi = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

        return bi;
    }

}

class ProductDescription{
    private String p_id;
    private String p_nm;
    private String p_manu_date;
    private String p_ex_date;
    private String p_manuby;
    private String p_price;
    private String p_q;
    private String current_quant;

    public ProductDescription(String p_id, String p_nm, String p_manu_date, String p_ex_date, String p_manuby, String p_price, String p_q) {
        this.p_id = p_id;
        this.p_nm = p_nm;
        this.p_manu_date = p_manu_date;
        this.p_ex_date = p_ex_date;
        this.p_manuby = p_manuby;
        this.p_price = p_price;
        this.p_q = p_q;
        this.current_quant="0";
    }

    public String getP_id() {
        return p_id;
    }

    public String getP_nm() {
        return p_nm;
    }

    public String getP_manu_date() {
        return p_manu_date;
    }

    public String getP_ex_date() {
        return p_ex_date;
    }

    public String getP_manuby() {
        return p_manuby;
    }

    public String getP_price() {
        return p_price;
    }

    public String getP_q() {
        return p_q;
    }

    public String getCurrent_quant() {
        return current_quant;
    }

    public void setCurrent_quant(String current_quant) {
        this.current_quant = current_quant;
    }
}





package com.example.project_sanjeevani;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Homepage extends AppCompatActivity {
    public static String user_id;

    public static String TAG="tagged";
    SQLiteDatabase sqLiteDatabase;

    private ImageView imageView5;
    private ImageButton lb1;
    private ImageButton lb2;
    private ImageButton lb3;
    private ImageButton lb4;


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra(HomepageSidebar.EXTRA_USER_ID_2);

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_doctor");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_medicines");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );

    ActivityResultLauncher<Intent> launcher4 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_cart");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher5 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_labCart");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher6 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_myorders");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher7 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_appointments");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher8 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_transactions");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );
    ActivityResultLauncher<Intent> launcher9 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if (intent!=null){
                            user_id=intent.getStringExtra("user_id_from_addmoney");

                            Log.d(TAG,"onActivityResult: "+user_id);


                        }
                    }
                }
            }

    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Log.d(TAG, "onCreate: in Homepage ");
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);

        Intent intent2=getIntent();
        user_id=intent2.getStringExtra(MainActivity.EXTRA_USER_ID);

        lb1=findViewById(R.id.lb1);
        lb2=findViewById(R.id.lb2);
        lb3=findViewById(R.id.lb3);
        lb4=findViewById(R.id.lb4);

        lb1.setOnClickListener(labTest(1));
        lb2.setOnClickListener(labTest(2));
        lb3.setOnClickListener(labTest(3));
        lb4.setOnClickListener(labTest(4));

//        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM products;",null);
//        byte[] image;
//
//        if(query.moveToFirst()){
//            do {
//                image=query.getBlob(7);
//                Log.d(TAG,Integer.toString(image.length)+new String(image));
//                Log.d(TAG,(image==null)?"Image is null":"Not null");
//                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
//                Log.d(TAG,(bitmap==null)?"Bitmap is null":"Bitmap is Not null");
//
//                imageView5=findViewById(R.id.imageView7);
//                Log.d(TAG, "Imageview:"+imageView5.getWidth() + " x " + imageView5.getHeight());
////                Log.d(TAG, "Bitmap:"+bitmap.getWidth() + " x " + bitmap.getHeight());
//                imageView5.setImageBitmap(bitmap);
//
//            }while(query.moveToNext());
//        }
//        query.close();




    }
    public View.OnClickListener labTest(int buttonChoice){

        View.OnClickListener listener=new View.OnClickListener(){
            Calendar c=Calendar.getInstance();
            int day=c.get(Calendar.DAY_OF_MONTH);
            int month=c.get(Calendar.MONTH);
            int year=c.get(Calendar.YEAR);
            @Override
            public void onClick(View v) {
                DatePickerDialog date=new DatePickerDialog(Homepage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar c=Calendar.getInstance();
                        int hours=c.get(Calendar.HOUR_OF_DAY);
                        int mins=c.get(Calendar.MINUTE);
                        TimePickerDialog time=new TimePickerDialog(Homepage.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar cal=Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.setTimeZone(TimeZone.getDefault());
//                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("k:mm a");
                                String selectedTime=String.valueOf(hourOfDay)+":"+String.valueOf(minute);
//                                String selectedTime=String.valueOf()
                                Cursor query=sqLiteDatabase.rawQuery("SELECT * FROM LAB_TESTS",null);
                                int inc=1;
                                if(query.moveToLast()){
                                    inc=Integer.parseInt(query.getString(0).substring(3));
                                    inc+=1;

                                }
                                query.close();
                                String selectedDate=String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);
                                String sno="LT_"+Integer.toString(inc);
                                String cmd=null;
                                switch (buttonChoice){
                                    case 1:
                                        cmd="INSERT INTO LAB_TESTS VALUES(\""+sno+"\",\""+user_id+"\",\"Hemoglobin,PCV,RBC Count, MCV, MCH,MCHC,R.D.W.*\",\""+selectedDate+"\",\""+selectedTime+"\",\"Complete Blood Count\",300)";
                                        break;
                                    case 2:
                                        cmd="INSERT INTO LAB_TESTS VALUES(\""+sno+"\",\""+user_id+"\",\"Tri Iodothyronine,Thyroxine,Thyroid stimulating hormone\",\""+selectedDate+"\",\""+selectedTime+"\",\"Thyroid Profile\",400)";
                                        break;
                                    case 3:
                                        cmd="INSERT INTO LAB_TESTS VALUES(\""+sno+"\",\""+user_id+"\",\"Physical Examination, BioChemical Examination,Centrifuged Sediment Wet Mount and Microscopy\",\""+selectedDate+"\",\""+selectedTime+"\",\"Complete Urine Examination\",200)";
                                        break;
                                    case 4:
                                        cmd="INSERT INTO LAB_TESTS VALUES(\""+sno+"\",\""+user_id+"\",\"Globuin, Albumin, A/G ratio, Alkaline Phosphatase, Asparatate aminotransferase\",\""+selectedDate+"\",\""+selectedTime+"\",\"Liver Function Test\",500)";
                                        break;



                                }
                                sqLiteDatabase.execSQL(cmd);
//                                "S_no VARCHAR(255) PRIMARY KEY,\n" +
//                                        "User_ID integer,\n" +
//                                        "Test_Included varchar(255),\n" +
//                                        "Date_Of_Booking DATE NOT NULL,\n" +
//                                        "Time_of_booking TIME(0) NOT NULL,\n" +
//                                        "Test_Type varchar(255),\n" +
//                                        "Price integer,\n" +
                                Toast.makeText(Homepage.this, "Lab Test added to Lab test cart,\n please proceed to checkout to confirm your booking ", Toast.LENGTH_SHORT).show();
                            }
                        },hours,mins,false);
                        time.show();
                    }
                },year,month,day);
                date.setTitle("Select a Date");
                date.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                date.show();

            }

        };
        return listener;
    }
    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();

        Log.d(TAG, "onStart: out "+user_id);
    }

    public void doctorsList(View v){
        Intent intent=new Intent(this,DoctorsDetails.class);
        intent.putExtra("user_id_for_doctor",user_id);
        launcher2.launch(intent);
//        startActivity(intent);
    }
    public void productList(View v){
        Intent intent=new Intent(this,Medicines.class);
        intent.putExtra("user_id_for_medicines",user_id);
        launcher3.launch(intent);
//        startActivity(intent);
    }

    public void goToCart(View v){
        Intent intent=new Intent(this,Cart.class);
        intent.putExtra("user_id_for_cart",user_id);
        launcher4.launch(intent);
//        startActivity(intent);
    }
    public void goToLabCart(View v){
        Intent intent=new Intent(this,LabCart.class);
        intent.putExtra("user_id_for_labCart",user_id);
        launcher5.launch(intent);
//        startActivity(intent);
    }

    public void appointments(View v){
        Intent intent=new Intent(this,Appointments.class);
        intent.putExtra("user_id_for_appointments",user_id);
        launcher7.launch(intent);
//        startActivity(intent);
    }
    public void transactions(View v){
        Intent intent=new Intent(this,TransactionsHistory.class);
        intent.putExtra("user_id_for_transactions",user_id);
        launcher8.launch(intent);
//        startActivity(intent);
    }
        public void homepagesidebar(View v){
            Intent intent=new Intent(this,HomepageSidebar.class);
            intent.putExtra("finaluser_id",user_id);
            launcher.launch(intent);
        }

        public void logout(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        }

        public void addAmount(View v){
        Intent intent=new Intent(this,AddMoney.class);
            intent.putExtra("user_id_for_addmoney",user_id);
            launcher9.launch(intent);
        }


//     "Product_ID VARCHAR(255),\n" +
//             "User_ID INT,\n" +
//             "Product_Name VARCHAR(255),\n" +
//             "Product_Description TEXT(65535),\n" +
//             "Price INT,\n" +
//             "Quantity integer,\n"+
}
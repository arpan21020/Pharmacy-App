package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    public static final String EXTRA_USER_ID="com.example.project_sanjeevani.userid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        createTables(sqLiteDatabase);
        insert_products(sqLiteDatabase);
        insert_doctors(sqLiteDatabase);


    }
    private void createTables(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTS;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Doctors;");

//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CONSULTATION;");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PAYMENTS;");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CART;");
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LAB_TESTS;");


        String sql="CREATE TABLE IF NOT EXISTS Users(\n" +
                "User_ID integer,\n" +
                "FirstName varchar(225),\n" +
                "MiddleName varchar(225),\n" +
                "LastName varchar(225),\n" +
                "Email varchar(225) ,\n" +
                "DateOfBirth date not null,\n" +
                "ContactNo BIGINT UNIQUE,\n" +
                "Sex varchar(20),\n" +
                "HouseNo varchar(225),\n" +
                "Street varchar(225),\n" +
                "District varchar(225),\n" +
                "State varchar(225),\n" +
                "Pincode integer,\n" +
                "Acc_Balance BIGINT,\n" +
                "pswd PASSWORD,\n" +
                "PRIMARY KEY(User_ID)\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

        sql="CREATE TABLE IF NOT EXISTS CONSULTATION(\n" +
                "User_ID INT ,\n" +
                "Appointment_ID VARCHAR(255) PRIMARY KEY,\n" +
                "Doctor VARCHAR(255),\n" +
                "DateOfAppointment DATE NOT NULL,\n"+
                "Timeslot TIME(0),\n" +
                "FEES INT,\n" +
                "FOREIGN KEY(User_ID) references Users(User_ID) ON DELETE CASCADE\n" +
                ");";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE IF NOT EXISTS Doctors(\n" +
                "Doctor_ID VARCHAR(255) PRIMARY KEY,\n" +
                "FirstName VARCHAR(255),\n" +
                "MiddleName VARCHAR(255),\n" +
                "LastName VARCHAR(255),\n" +
                "Experience VARCHAR(255),\n" +
                "Contact_Address VARCHAR(255),\n" +
                "Charges INT,\n" +
                "Email VARCHAR(255),\n" +
                "Mobile_no BIGINT,\n" +
                "Specialization VARCHAR(255)\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

        sql="CREATE TABLE IF NOT EXISTS LAB_TESTS(\n" +
                "S_no VARCHAR(255) PRIMARY KEY,\n" +
                "User_ID integer,\n" +
                "Test_Included varchar(255),\n" +
                "Date_Of_Booking DATE NOT NULL,\n" +
                "Time_of_booking TIME(0) NOT NULL,\n" +
                "Test_Type varchar(255),\n" +
                "Price integer,\n" +
                "FOREIGN KEY(User_ID) references Users(User_ID) ON DELETE CASCADE\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

        sql="CREATE TABLE IF NOT EXISTS Products(\n" +
                "Product_ID VARCHAR(255) PRIMARY KEY,\n" +
                "Product_Name VARCHAR(255),\n" +
                "Manufacturing_Date DATE NOT NULL,\n" +
                "Expiry_Date DATE NOT NULL,\n" +
                "Manufacturer TEXT(65535),\n" +
                "Price integer,\n" +
                "Quantity integer\n"+
                ");";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE IF NOT EXISTS CART(\n" +
                "Product_ID VARCHAR(255),\n" +
                "User_ID INT,\n" +
                "Product_Name VARCHAR(255),\n" +
                "Product_Description TEXT(65535),\n" +
                "Price INT,\n" +
                "Quantity integer,\n"+
                "FOREIGN KEY(User_ID) references Users(User_ID) ON DELETE CASCADE,\n" +
                "FOREIGN KEY(Product_ID) references Products(Product_ID) ON DELETE CASCADE\n" +
                ");";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE IF NOT EXISTS PAYMENTS(\n" +
                "Transaction_ID varchar(255) PRIMARY KEY,\n" +
                "User_Id INT,\n" +
                "Balance BIGINT,\n" +
                "Date_ DATE NOT NULL,\n" +
                "Remarks TEXT(65535),\n" +
                "FOREIGN KEY(User_ID) references Users(User_ID) ON DELETE CASCADE\n" +
                "\n" +
                ");";
        sqLiteDatabase.execSQL(sql);



    }
    private void insert_products(SQLiteDatabase sqLiteDatabase){

        String cmd="INSERT INTO Products VALUES"+
        "(\"p_0001\", \"Paracetamol 500mg Tablets\", \"2021-01-01\", \"2023-01-01\", \"SmithKline\", 100,10),"+
        "(\"p_0002\", \"Ibuprofen 200mg Tablets\", \"2021-02-01\", \"2023-02-01\", \"Pfizer\", 150,10),"+
        "(\"p_0003\", \"Aspirin 325mg Tablets\", \"2021-03-01\", \"2023-03-01\", \"Bayer\", 120,10),"+
        "(\"p_0004\", \"Amoxicillin 500mg Capsules\", \"2021-04-01\", \"2023-04-01\", \"GlaxoSmithKline\", 200,10),"+
        "(\"p_0005\", \"Metformin 500mg Tablets\", \"2021-05-01\", \"2023-05-01\", \"Sanofi\", 250,10),"+
        "(\"p_0006\", \"Lisinopril 10mg Tablets\", \"2021-06-01\", \"2023-06-01\", \"AstraZeneca\", 300,10),"+
        "(\"p_0007\", \"Omeprazole 20mg Capsules\", \"2021-07-01\", \"2023-07-01\", \"Pfizer\", 350,10),"+
        "(\"p_0008\", \"Hydrochlorothiazide 25mg Tablets\", \"2021-08-01\", \"2023-08-01\", \"Bristol-Myers Squibb\", 400,10),"+
        "(\"p_0009\", \"Simvastatin 40mg Tablets\", \"2021-09-01\", \"2023-09-01\", \"Merck\", 450,10),"+
        "(\"p_0010\", \"Furosemide 40mg Tablets\", \"2021-10-01\", \"2023-10-01\", \"Sanofi\", 50,10),"+
        "(\"p_0011\", \"Warfarin 5mg Tablets\", \"2021-11-01\", \"2023-11-01\", \"Bristol-Myers Squibb\", 355,10),"+
        "(\"p_0012\", \"Amlodipine 5mg Tablets\", \"2021-12-01\", \"2023-12-01\", \"Pfizer\", 360,10),"+
        "(\"p_0013\", \"Doxycycline 100mg Capsules\", \"2021-01-01\", \"2023-01-01\", \"Pfizer\", 270,10),"+
        "(\"p_0014\", \"Ciprofloxacin 500mg Tablets\", \"2021-02-01\", \"2023-02-01\", \"Bayer\", 375,10),"+
        "(\"p_0015\", \"Clindamycin 300mg Capsules\", \"2021-03-01\", \"2023-03-01\", \"Pfizer\", 280,10),"+
        "(\"p_0016\", \"Azithromycin 250mg Tablets\", \"2021-04-01\", \"2023-04-01\", \"Pfizer\", 250,10),"+
        "(\"p_0017\", \"Tetracycline 500mg Capsules\", \"2021-05-01\", \"2023-05-01\", \"Bristol-Myers Squibb\", 290,10),"+
        "(\"p_0018\", \"Erythromycin 250mg Tablets\", \"2021-06-01\", \"2023-06-01\", \"Pfizer\", 195,10),"+
        "(\"p_0019\", \"Metronidazole 400mg Tablets\", \"2021-07-01\", \"2023-07-01\", \"Sanofi\", 100,10),"+
        "(\"p_0020\", \"Levofloxacin 500mg Tablets\", \"2021-08-01\", \"2023-08-01\", \"Bristol-Myers Squibb\", 150,10);";
        sqLiteDatabase.execSQL(cmd);
    }
    private void insert_doctors(SQLiteDatabase sqLiteDatabase){
        String cmd="INSERT INTO Doctors \n" +
                "VALUES\n" +
                "('DOC_1', 'Jane', 'Michael', 'Doe', '5 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.janedoe@email.com', 1119876543, 'General Physician'),\n" +
                "('DOC_2', 'Sanjay', 'Kumar', 'Singh', '10 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.sanjaysingh@email.com', 1119876542, 'Dentist'),\n" +
                "('DOC_3', 'Abhay', 'Kumar', 'Arora', '15 YEARS', '123, ABC Street, XYZ City, 123456', 800, 'dr.abhayarora@email.com', 1119876541, 'Nurologist'),\n" +
                "('DOC_4', 'John', 'Michael', 'Doe', '5 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.johndoe@email.com', 1119876540, 'ENT'),\n" +
                "('DOC_5', 'Sanjana', 'Kumar', 'Singhal', '10 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.sanjanasinghal@email.com', 1119876504, 'General Physician'),\n" +
                "('DOC_6', 'Abhishek', 'Kumar', 'Arora', '15 YEARS', '123, ABC Street, XYZ City, 123456', 800, 'dr.abhisekhkumar@email.com', 1119876054, 'Dentist'),\n" +
                "('DOC_7', 'Jesika', NULL, 'Jones', '5 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.jesikajones@email.com', 1119870654, 'Nurologist'),\n" +
                "('DOC_8', 'Sandeep', 'Kumar', 'Singh', '10 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.sandeepkumar@email.com', 1119807654, 'ENT'),\n" +
                "('DOC_9', 'Abhishek', 'Kumar', 'Arora', '15 YEARS', '123, ABC Street, XYZ City, 123456', 800, 'dr.abhisekharora@email.com', 1119087654, 'General Physician'),\n" +
                "('DOC_10', 'Sanjay', NULL, 'Singhania', '5 YEARS', '123, ABC Street, XYZ City, 123456', 500, 'dr.sanjaysinghania@email.com', 1119876541, 'Dentist'),\n" +
                "\n" +
                "('DOC_11', 'Abhishek', 'Kumar', 'Arora', '10 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.abhishekarora@email.com', 1119876535, 'Nurologist'),\n" +
                "('DOC_12', 'Sanjana', 'Kumar', 'Singh', '15 YEARS', '987, CBA Street, ZYX City, 123456', 800, 'dr.sanjanasingh@email.com', 1119876539, 'ENT'),\n" +
                "('DOC_13', 'Ashutosh', 'Kumar', 'Ghera', '5 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.ashutoshghera@email.com', 1119876538, 'General Physician'),\n" +
                "('DOC_14', 'Sanju', 'Kumar', 'Singhania', '10 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.sanjusinghania@email.com', 1119876503, 'Dentist'),\n" +
                "('DOC_15', 'Bhavya', NULL, 'Bisht', '15 YEARS', '987, CBA Street, ZYX City, 123456', 800, 'dr.bhavyabisht@email.com', 1119876053, 'Nurologist'),\n" +
                "('DOC_16', 'Cherita', NULL, 'Bisht', '5 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.cheritabisht@email.com', 1119870652, 'ENT'),\n" +
                "('DOC_17', 'Bhumika', NULL, 'Quntan', '10 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.bhumikaquntan@email.com', 1119807653, 'General Physician'),\n" +
                "('DOC_18', 'Ekansh', NULL, 'Kurhana', '15 YEARS', '987, CBA Street, ZYX City, 123456', 800, 'dr.ekanshkurhana@email.com', 1119087653, 'Dentist'),\n" +
                "('DOC_19', 'Fahad', NULL, 'Kurhana', '5 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.fahadkurhana@email.com', 1119876537, 'Nurologist'),\n" +
                "('DOC_20', 'Gaurav', 'Kumar', 'Singh', '10 YEARS', '987, CBA Street, ZYX City, 123456', 500, 'dr.gauravsingh@email.com', 1119876536, 'ENT');";
        sqLiteDatabase.execSQL(cmd);
    }
    public static ArrayList<File> fetchProducts(File file){
        ArrayList arrayList=new ArrayList();
        File[] products=file.listFiles();
        if(products==null){
            for (File myFile : products){
                if(!myFile.isHidden() && myFile.isDirectory()){
                    arrayList.addAll(fetchProducts(myFile));
                }
                else {
                    if (myFile.getName().endsWith(".jpeg")||myFile.getName().endsWith(".jpg")||myFile.getName().endsWith(".png")){
                        arrayList.add(myFile);
                    }
                }
            }
        }
        return arrayList;
    }
    public void addNewUser(View v){
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);

    }
    public void login(View v){
        EditText userid=findViewById(R.id.userid_login);
        EditText paswd=findViewById(R.id.password_login);
        Cursor query = sqLiteDatabase.rawQuery("SELECT User_id,pswd FROM users;",null);
        if(query.moveToFirst()){
            do {
                int uid = query.getInt(0);
                String upwd = query.getString(1);
                if(Integer.parseInt(userid.getText().toString())==uid && paswd.getText().toString().equals(upwd)){
                    Toast.makeText(this, "Logged in successfully", Toast.LENGTH_LONG).show();
                    query.close();
                    Intent intent=new Intent(this,Homepage.class);
                    intent.putExtra(EXTRA_USER_ID,userid.getText().toString());

                    startActivity(intent);
                    finish();
                    return;
                }
            }while(query.moveToNext());
        }
        Toast.makeText(this, "Login Failed:Invalid User ID or Password", Toast.LENGTH_SHORT).show();
        query.close();


        return;

    }
//    sqLiteDatabase.close();


}
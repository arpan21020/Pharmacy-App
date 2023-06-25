package com.example.project_sanjeevani;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase ;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup=findViewById(R.id.signupbtn);
        sqLiteDatabase=getBaseContext().openOrCreateDatabase("Sanjeevani.db",MODE_PRIVATE,null);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fname=findViewById(R.id.fname);
                EditText mname=findViewById(R.id.mname);
                EditText lname=findViewById(R.id.lname);
                EditText email=findViewById(R.id.email);
                EditText dob=findViewById(R.id.dob);
                EditText phone=findViewById(R.id.phone);

                RadioGroup radioGroup=findViewById(R.id.sex);
                RadioButton sex=findViewById(radioGroup.getCheckedRadioButtonId());

                EditText hno=findViewById(R.id.hno);
                EditText street=findViewById(R.id.street);
                EditText district=findViewById(R.id.district);
                EditText state=findViewById(R.id.state);
                EditText pincode=findViewById(R.id.pincode);
                EditText pswd=findViewById(R.id.pswd);
                EditText cpswd=findViewById(R.id.cpswd);

                String fnm=fname.getText().toString();
                String mnm=mname.getText().toString();
                String lnm=lname.getText().toString();
                String em=email.getText().toString();
                String birth=dob.getText().toString();
                String ctcnm=phone.getText().toString();
                String gender=sex.getText().toString();
                String h_no=hno.getText().toString();
                String srt=street.getText().toString();
                String dst=district.getText().toString();
                String sta=state.getText().toString();
                String pcode=pincode.getText().toString();
                String acc_blnc="50000";
                String pwd=pswd.getText().toString();
                String cpwd=cpswd.getText().toString();

                if (pswd.getText().toString().equals(cpswd.getText().toString())){
                    Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM users;",null);
                    int id=(query.moveToLast())?(query.getInt(0)+1):1;
//                    int id=2;
                    System.out.println(id);
                    String sql= "Insert into Users values("+id+",'"+fnm+"','"+mnm+"','"+lnm+"','"+em+"','"+birth+"',"+ctcnm+",'"+gender+"','"+h_no+"','"+srt+"','"+dst+"','"+sta+"',"+pcode+","+acc_blnc+",'"+pwd+"');";
                    sqLiteDatabase.execSQL(sql);
                    query.close();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

                    // Set the title and message for the AlertDialog
                    builder.setTitle("Alert Dialog")
                            .setMessage("Your User ID is: "+id);

                    // Add a button to the AlertDialog and set its click listener
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Do something when the user clicks the OK button
                            Toast.makeText(SignUp.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUp.this,Homepage.class);
                            intent.putExtra(MainActivity.EXTRA_USER_ID,Integer.toString(id));

                            startActivity(intent);
                            finish();
                        }
                    })
                    .show();
//                    sqLiteDatabase.execSQL("Insert into users values(1,'fnm','mnm','lnm','iiitd@m=email.com','20001111',9090128921,'male','402','Phase3','Okhla','Delhi',110040,50000,'1234');");
//                    Toast.makeText(SignUp.this, "Signed in successfully", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
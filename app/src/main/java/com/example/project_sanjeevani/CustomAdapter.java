package com.example.project_sanjeevani;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static ArrayList<Doctor> localDataSet;
    static Context context;
    static String user_id;
    static SQLiteDatabase sqLiteDatabase;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final TextView textView4;
        private final TextView textView5;
        private final TextView textView6;

        private final TextView textView7;
        private final Button book;
        private View.OnClickListener listener;

        //        @SuppressLint("WrongViewCast")
        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View
            textView1=view.findViewById(R.id.doc_nm);
            textView2=view.findViewById(R.id.doc_special);
            textView3=view.findViewById(R.id.doc_exp);
            textView4=view.findViewById(R.id.doc_addr);
            textView5=view.findViewById(R.id.doc_charges);
            textView6=view.findViewById(R.id.doc_email);
            textView7=view.findViewById(R.id.doc_mobile);
            book=view.findViewById(R.id.book);
            listener=new View.OnClickListener() {
                Calendar c=Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                @Override
                public void onClick(View v) {
                    int n=getAdapterPosition();
                    DatePickerDialog date=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            Calendar c=Calendar.getInstance();
                            int hours=c.get(Calendar.HOUR_OF_DAY);
                            int mins=c.get(Calendar.MINUTE);
                            TimePickerDialog time=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    Calendar cal=Calendar.getInstance();
                                    c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                    c.set(Calendar.MINUTE,minute);
                                    c.setTimeZone(TimeZone.getDefault());
//                                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("k:mm a");
                                    String selectedTime=String.valueOf(hourOfDay)+":"+String.valueOf(minute);
                                    Cursor query=sqLiteDatabase.rawQuery("SELECT * FROM CONSULTATION",null);
                                    int inc=1;
                                    if(query.moveToLast()){
                                        inc=Integer.parseInt(query.getString(1).substring(4));
                                        inc+=1;

                                    }
                                    query.close();
                                    String selectedDate=String.valueOf(dayOfMonth)+"-"+String.valueOf(month)+"-"+String.valueOf(year);
                                    String app_id="APP_"+Integer.toString(inc);
                                    Log.d("tagged","Value of n is "+n);
                                    String doc_nm=localDataSet.get(n).getName();
                                    String app_date=String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);
                                    String app_fees=localDataSet.get(n).getCharges();
                                    String cmd="INSERT INTO CONSULTATION VALUES(\""+user_id+"\",\""+app_id+"\",\""+doc_nm+"\",\""+app_date+"\",\""+selectedTime+"\","+app_fees+");";

                                    sqLiteDatabase.execSQL(cmd);
                                    Toast.makeText(context, "Appointment Booked with "+localDataSet.get(n).getName()+"\non "+selectedDate+" at "+selectedTime, Toast.LENGTH_SHORT).show();
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



            //            textView = (TextView) view.findViewById(R.id.textviewer);
        }

        public TextView getTextView1() {
            return textView1;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public TextView getTextView3() {
            return textView3;
        }

        public TextView getTextView4() {
            return textView4;
        }

        public TextView getTextView5() {
            return textView5;
        }

        public TextView getTextView6() {
            return textView6;
        }
        public TextView getTextView7() {
            return textView7;
        }
        public Button getBook(){ return book;}

        public View.OnClickListener getListener() {
            return listener;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CustomAdapter(Context context, ArrayList<Doctor> dataSet,SQLiteDatabase sqLiteDatabase,String user_id) {
        localDataSet = dataSet;
        this.context=context;
        this.sqLiteDatabase=sqLiteDatabase;
        this.user_id=user_id;
    }

    // Create new views (invoked by the layout manager)

    @NonNull

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.getTextView().setText(localDataSet.get(position).getName());
        viewHolder.getBook().setOnClickListener(viewHolder.getListener());
        viewHolder.getTextView1().setText(localDataSet.get(position).getName());
        viewHolder.getTextView2().setText(localDataSet.get(position).getSpecialization());
        viewHolder.getTextView3().setText(localDataSet.get(position).getExp());
        viewHolder.getTextView4().setText(localDataSet.get(position).getAddr());
        viewHolder.getTextView5().setText(localDataSet.get(position).getCharges());
        viewHolder.getTextView6().setText(localDataSet.get(position).getEmail());
        viewHolder.getTextView7().setText(localDataSet.get(position).getMobile());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

package com.example.project_sanjeevani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicinesAdapter extends RecyclerView.Adapter<MedicinesAdapter.ViewHolder> {

    private ArrayList<Bitmap>localDataSet;
    private static ArrayList<ProductDescription> descriptions;
    static Context context;
    private String user_id;
    SQLiteDatabase sqLiteDatabase;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final TextView textView4;
        private final TextView textView5;
        private final TextView textView6;
        private final TextView textView7;
        private final TextView quant;
        private final ImageButton minus;
        private final ImageButton plus;
        private final Button add_to_cart;

        View.OnClickListener minusListener;
        View.OnClickListener plusListener;
        View.OnClickListener add_to_cartListener;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            image=view.findViewById(R.id.imageView17);

            textView1 = view.findViewById(R.id.p_nm);
            textView2 = view.findViewById(R.id.p_id);
            textView3 = view.findViewById(R.id.p_mdt);
            textView4 = view.findViewById(R.id.p_exdt);
            textView5 = view.findViewById(R.id.p_manu);
            textView6 = view.findViewById(R.id.p_q);
            textView7 = view.findViewById(R.id.p_price);
            quant = view.findViewById(R.id.quant);
            minus=view.findViewById(R.id.minus);
            plus=view.findViewById(R.id.plus);
            add_to_cart= view.findViewById(R.id.p_add);

            minusListener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n=getAdapterPosition();
                    int quantity=Integer.parseInt(descriptions.get(n).getCurrent_quant());
                    int max_quantity=Integer.parseInt(textView6.getText().toString());
                    if(quantity>0){
                        descriptions.get(n).setCurrent_quant(Integer.toString(quantity-1));
//                        Toast.makeText(context, n+" index pressed", Toast.LENGTH_SHORT).show();
                        notifyItemChanged(n);
                    }
                }
            };
             plusListener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n=getAdapterPosition();

                    int quantity=Integer.parseInt(descriptions.get(n).getCurrent_quant());
                    int max_quantity=Integer.parseInt(textView6.getText().toString());
                    if (quantity<max_quantity){
                        descriptions.get(n).setCurrent_quant(Integer.toString(quantity+1));
//                        Toast.makeText(context, n+" index pressed", Toast.LENGTH_SHORT).show();
                        notifyItemChanged(n);
                    }
                }
            };
            add_to_cartListener=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n=getAdapterPosition();
                    int quantity=Integer.parseInt(descriptions.get(n).getCurrent_quant());
                    if (quantity==0) Toast.makeText(context, "No item Selected ", Toast.LENGTH_SHORT).show();
                    else{

                        String pro_id=descriptions.get(n).getP_id();
                        String pro_user=user_id;
                        String pro_name=descriptions.get(n).getP_nm();
                        String pro_des=descriptions.get(n).getP_manuby();
                        String pro_price=descriptions.get(n).getP_price();

                        sqLiteDatabase.execSQL("INSERT INTO CART VALUES(\""+pro_id+"\","+pro_user+",\""+pro_name+"\",\""+pro_des+"\","+pro_price+","+Integer.toString(quantity)+");");

                        descriptions.get(n).setCurrent_quant("0");

                        Toast.makeText(context, "Item Added to cart", Toast.LENGTH_SHORT).show();
                        notifyItemChanged(n);
                    }

                }
            };


        }

        public TextView getQuant() {
            return quant;
        }

        public ImageButton getMinus() {
            return minus;
        }

        public ImageButton getPlus() {
            return plus;
        }

        public Button getAdd_to_cart() {
            return add_to_cart;
        }

        public View.OnClickListener getMinusListener() {
            return minusListener;
        }

        public View.OnClickListener getPlusListener() {
            return plusListener;
        }

        public View.OnClickListener getAdd_to_cartListener() {
            return add_to_cartListener;
        }

        public ImageView getImage() {
            return image;
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
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public MedicinesAdapter(Context context,ArrayList<Bitmap> dataSet, ArrayList<ProductDescription> des,SQLiteDatabase sqLiteDatabase,String user_id) {
        localDataSet = dataSet;
        descriptions=des;
        this.context=context;
        this.sqLiteDatabase=sqLiteDatabase;
        this.user_id=user_id;
    }

    public ArrayList<ProductDescription> getDescriptions() {
        return descriptions;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.medicines_products, viewGroup, false);

        return new ViewHolder(view);
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getMinus().setOnClickListener(viewHolder.getMinusListener());
        viewHolder.getPlus().setOnClickListener(viewHolder.getPlusListener());
        viewHolder.getAdd_to_cart().setOnClickListener(viewHolder.add_to_cartListener);
        viewHolder.getImage().setImageBitmap(localDataSet.get(position));
        viewHolder.getQuant().setText(descriptions.get(position).getCurrent_quant());
        viewHolder.getTextView1().setText(descriptions.get(position).getP_nm());
        viewHolder.getTextView2().setText(descriptions.get(position).getP_id());
        viewHolder.getTextView3().setText(descriptions.get(position).getP_manu_date());
        viewHolder.getTextView4().setText(descriptions.get(position).getP_ex_date());
        viewHolder.getTextView5().setText(descriptions.get(position).getP_manuby());
        viewHolder.getTextView6().setText(descriptions.get(position).getP_q());
        viewHolder.getTextView7().setText(descriptions.get(position).getP_price());
//        viewHolder.itemView.setSelected(false);
//        viewHolder.itemView.setEnabled(true);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

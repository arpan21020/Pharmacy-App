package com.example.project_sanjeevani;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<Cart_items> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView1 = (TextView) view.findViewById(R.id.c_item);

            textView2 = (TextView) view.findViewById(R.id.c_price);
            textView3 = (TextView) view.findViewById(R.id.c_quantity);

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
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CartAdapter(ArrayList<Cart_items> dataSet)
    {
        localDataSet = dataSet;

    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cart_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        Log.d("tagged",localDataSet.get(position).getC_price());
        viewHolder.getTextView1().setText(localDataSet.get(position).getC_p_name());
        viewHolder.getTextView2().setText(Integer.toString(localDataSet.get(position).getC_price()));
        viewHolder.getTextView3().setText(Integer.toString(localDataSet.get(position).getC_quantity()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

package com.example.project_sanjeevani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class l_cart_adapter extends RecyclerView.Adapter<l_cart_adapter.ViewHolder> {

    private ArrayList<Lab_cart> localDataSet;
    Context context;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView l_name;
        private final TextView l_price;
        private final TextView l_date;
        private final TextView l_time;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            l_name = view.findViewById(R.id.l_name);
            l_price = view.findViewById(R.id.l_price);
            l_date = view.findViewById(R.id.l_date);
            l_time = view.findViewById(R.id.l_time);
        }

        public TextView getL_name() {
            return l_name;
        }

        public TextView getL_price() {
            return l_price;
        }

        public TextView getL_date() {
            return l_date;
        }

        public TextView getL_time() {
            return l_time;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public l_cart_adapter(Context context,ArrayList<Lab_cart> dataSet) {
        localDataSet = dataSet;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.l_cart_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getL_name().setText(localDataSet.get(position).getTestName());
        viewHolder.getL_price().setText(localDataSet.get(position).getTestPrice());
        viewHolder.getL_date().setText(localDataSet.get(position).getTestDate());
        viewHolder.getL_time().setText(localDataSet.get(position).getTestTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

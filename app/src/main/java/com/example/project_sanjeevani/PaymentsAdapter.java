package com.example.project_sanjeevani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<TransactionsRecord> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView t_date;
        private final TextView t_desc;
        private final TextView t_blnc;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            t_date = view.findViewById(R.id.t_date);
            t_blnc=view.findViewById(R.id.t_blnc);
            t_desc=view.findViewById(R.id.t_desc);
        }

        public TextView getT_date() {
            return t_date;
        }

        public TextView getT_desc() {
            return t_desc;
        }

        public TextView getT_blnc() {
            return t_blnc;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public PaymentsAdapter(Context context,ArrayList<TransactionsRecord> dataSet) {
        localDataSet = dataSet;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.transaction_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getT_date().setText(localDataSet.get(position).getT_date());
        viewHolder.getT_blnc().setText(localDataSet.get(position).getT_blnc());
//        viewHolder.getT_time().setText(localDataSet.get(position).getT_time());
        viewHolder.getT_desc().setText(localDataSet.get(position).getT_remarks());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

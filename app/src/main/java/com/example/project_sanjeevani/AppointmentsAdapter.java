package com.example.project_sanjeevani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<Scheduled_Appointments> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView app_id;
        private final TextView app_doc;
        private final TextView app_date;
        private final TextView app_time;
        private final TextView app_fee;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            app_id =  view.findViewById(R.id.app_id);
            app_doc=view.findViewById(R.id.app_doc);
            app_date=view.findViewById(R.id.app_date);
            app_time=view.findViewById(R.id.app_time);
            app_fee=view.findViewById(R.id.app_fee);
        }

        public TextView getApp_id() {
            return app_id;
        }

        public TextView getApp_doc() {
            return app_doc;
        }

        public TextView getApp_date() {
            return app_date;
        }

        public TextView getApp_time() {
            return app_time;
        }

        public TextView getApp_fee() {
            return app_fee;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public AppointmentsAdapter(Context context, ArrayList<Scheduled_Appointments> dataSet) {
        localDataSet = dataSet;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.appointment_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.getTextView().setText(localDataSet.get(position));
        viewHolder.getApp_id().setText(localDataSet.get(position).getApp_id());
        viewHolder.getApp_doc().setText(localDataSet.get(position).getDoc());
        viewHolder.getApp_date().setText(localDataSet.get(position).getDate());
        viewHolder.getApp_time().setText(localDataSet.get(position).getTime());
        viewHolder.getApp_fee().setText(localDataSet.get(position).getFee());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

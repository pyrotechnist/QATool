package com.netsize.netsizeqa.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.netsize.netsizeqa.R;
import com.netsize.netsizeqa.data.TestCase;

import java.util.List;

/**
 * Created by loxu on 15/06/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataObjectHolder> {

    private List<TestCase> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        //public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            //mTextView = v;
        }
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            /*implements View
            .OnClickListener */
    {
        TextView serviceId;
        TextView title;

        public DataObjectHolder(View itemView) {
            super(itemView);
            serviceId = (TextView) itemView.findViewById(R.id.serviceid);
            title = (TextView) itemView.findViewById(R.id.label);
            //Log.i(LOG_TAG, "Adding Listener");
            //itemView.setOnClickListener(this);
        }

       /* @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }*/
    }

   public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(List<TestCase> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;

    }
    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
       View root= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testcase, parent, false);


        // set the view's size, margins, paddings and layout parameters


        //card view
       /* View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);*/

        DataObjectHolder vh = new DataObjectHolder(root);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position].getmText1());

        final TestCase testCase = mDataset.get(position);

        holder.title.setText(testCase.getTitle());
        holder.serviceId.setText(testCase.getServiceId());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(mContext, testCase.getCommand()+testCase.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addItem(TestCase dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}


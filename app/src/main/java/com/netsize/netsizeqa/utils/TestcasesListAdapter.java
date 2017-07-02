package com.netsize.netsizeqa.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netsize.netsizeqa.R;
import com.netsize.netsizeqa.data.TestCase;

/**
 * Created by loxu on 15/06/2017.
 */

public class TestcasesListAdapter extends ArrayAdapter<TestCase> {

    private final Context context;
    private final TestCase[] values;


    public TestcasesListAdapter(Context context, TestCase[] values) {
        super(context,R.layout.stat_item, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.stat_item, parent, false);

        ImageView countryFlag = (ImageView) rowView.findViewById(R.id.country_flag);

        TextView stat = (TextView) rowView.findViewById(R.id.stat);



        return rowView;
    }
}

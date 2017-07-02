package com.netsize.netsizeqa.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.netsize.netsizeqa.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LONGYUAN on 2017/7/2.
 */

public class MySimpleAdapter extends SimpleAdapter {

    private List<HashMap<String, String>> mMapList;
    private Context mContext;




    public MySimpleAdapter(Context context, List<HashMap<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mMapList = data;
        mContext = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        ImageView imageView = (ImageView) view.findViewById(R.id.country_flag);
       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, arrayList.get(position).get("name"), Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}

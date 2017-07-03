package com.netsize.netsizeqa.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.netsize.netsizeqa.MainContract;
import com.netsize.netsizeqa.R;
import com.netsize.netsizeqa.data.TestCase;
import com.netsize.netsizeqa.utils.MySimpleAdapter;
import com.netsize.netsizeqa.utils.TestcasesListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by LONGYUAN on 2017/7/1.
 */

public class StatisticsFragment extends Fragment implements StatisticsContract.View {

     private StatisticsContract.Presenter mStatisticsPresenter;

    ListView listView;


    public static  StatisticsFragment newInstance(){

        return  new StatisticsFragment();
    }


    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mStatisticsPresenter.start();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.statistics_frag, container, false);


        listView = (ListView) root.findViewById(R.id.stat_list);

        return root;
    }

    @Override
    public void showStatistics(Map<String,List<TestCase>> testCasesMap) {

        //Map statMap = new HashMap<String, Integer>();

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for (Map.Entry<String, List<TestCase>> entry : testCasesMap.entrySet()) {
            //System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
            //statMap.put(entry.getKey().toLowerCase(),entry.getValue().size());
            HashMap<String, String> map = new HashMap<String, String>();

            map.put("Country",entry.getKey().toLowerCase().replace(" mock",""));
            map.put("Testcase",Integer.toString(entry.getValue().size()));
            fillMaps.add(map);
        }

        //Set<String> keys = statMap.keySet();

        //String[] from  = keys.toArray(new String[keys.size()]);

        // create the grid item mapping
        String[] from = new String[] { "Testcase"};

        int[] to = new int[] {  R.id.stat };

        final MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(getContext(),fillMaps,R.layout.stat_item,from,to);


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                listView.setAdapter(mySimpleAdapter);

            }
        });






    }



    @Override
    public void showLoadingStatisticsError() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        mStatisticsPresenter = presenter;

    }
}

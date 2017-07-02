package com.netsize.netsizeqa.statistics;

import android.support.v4.app.Fragment;
import android.widget.ListView;

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



    ListView listView;


    public static  StatisticsFragment newInstance(){

        return  new StatisticsFragment();
    }


    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showStatistics(Map<String,List<TestCase>> testCasesMap) {

        //Map statMap = new HashMap<String, Integer>();

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

        for (Map.Entry<String, List<TestCase>> entry : testCasesMap.entrySet()) {
            //System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
            //statMap.put(entry.getKey().toLowerCase(),entry.getValue().size());
            HashMap<String, String> map = new HashMap<String, String>();

            //map.put("Country",entry.getKey().toUpperCase());
           map.put("Testcase",Integer.toString(entry.getValue().size()));
            fillMaps.add(map);
        }

        //Set<String> keys = statMap.keySet();

        //String[] from  = keys.toArray(new String[keys.size()]);

        // create the grid item mapping
        String[] from = new String[] { "Testcase"};

        int[] to = new int[] {  R.id.stat };

        MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(getContext(),fillMaps,R.id.stat_list,from,to);






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

    }
}

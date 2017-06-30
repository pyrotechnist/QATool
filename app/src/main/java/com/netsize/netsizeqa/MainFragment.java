package com.netsize.netsizeqa;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.netsize.netsizeqa.data.TestCase;
import com.netsize.netsizeqa.utils.QaViewModel;
import com.netsize.netsizeqa.utils.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by loxu on 13/06/2017.
 */

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private Spinner spinnerCountry;

    private Spinner spinnerEnv;

    private TextView textView;

    private String selectedCountry;

   // private ListView testcasesListView;

    private RecyclerView testcasesRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private ProgressDialog mProgressDialog;

    private List<TestCase> mTestList = null;

    Map<String,List<TestCase>> mTestCasesMap = null;


    public MainFragment() {
        // Requires empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mItemListener);
    }

    @Override
    public void showCountries(String[] countries) {

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, countries);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCountry.setAdapter(arrayAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.mainfragment, container, false);

        spinnerCountry = (Spinner) root.findViewById(R.id.spinner_country);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                updateTestCases(spinnerCountry.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        spinnerEnv = (Spinner) root.findViewById(R.id.spinner_env);

        //textView = (TextView) root.findViewById(R.id.test);

        testcasesRecyclerView = (RecyclerView) root.findViewById(R.id.testcases_list) ;
        testcasesRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        testcasesRecyclerView.setLayoutManager(mLayoutManager);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Downloading cache file..");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);


        setHasOptionsMenu(true);

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }



    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void updateDialog(int progress) {
        if (progress == 100) {
            mProgressDialog.dismiss();
        } else
            mProgressDialog.setProgress(progress);

    }

    /**
     *

     */
    @Override
    public void showTestcases(final Map<String,List<TestCase>> testCasesMap) {

         mTestCasesMap = new HashMap<String,List<TestCase>>();

        if(!testCasesMap.isEmpty())
        {
            final String[] countries = testCasesMap.keySet().toArray(new String[testCasesMap.keySet().size()]);
            selectedCountry = countries[0];
            mTestCasesMap = testCasesMap;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    showCountries(countries);

                    updateTestCases(selectedCountry);

                }
            });


        }
    }



    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {

        mPresenter = presenter;

    }

    private void updateTestCases (String country){


        if(mTestCasesMap.get(country) != null)
        {
            mTestList = mTestCasesMap.get(country);

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(mTestList,getContext());

            testcasesRecyclerView.setAdapter(adapter);
        }


      /*  Iterator<TestCase> it = mTestList.iterator();
        final List<TestCase> filteredList = new LinkedList<TestCase>();
        while (it.hasNext()) {
            TestCase local = it.next();

            if (local.getCountry().equalsIgnoreCase(country))
                filteredList.add(local);
        }
*/
        //TestCase[] arr = filteredList.toArray(new TestCase[mTestList.size()]);


    }

    public Context getContext()
    {
        return  getActivity();
    }
}

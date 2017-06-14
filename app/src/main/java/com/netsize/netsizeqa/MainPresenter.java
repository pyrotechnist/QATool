package com.netsize.netsizeqa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 13/06/2017.
 */

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private Spinner spinnerCountry;

    private Spinner spinnerEnv;

    private TextView textView;

    private ProgressDialog mProgressDialog;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.mainfragment, container, false);

        spinnerCountry =  (Spinner) root.findViewById(R.id.spinner_country);

        spinnerEnv =  (Spinner) root.findViewById(R.id.spinner_env);

        textView = (TextView) root.findViewById(R.id.test) ;

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
    public void showCountries() {

    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void updateDialog(int progress) {
        if(progress ==100)
        {
            mProgressDialog.dismiss();
        }
        else
        mProgressDialog.setProgress(progress);

    }

    @Override
    public void showTestcases(String tests) {

        textView.setText(tests);
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {

        mPresenter = presenter;

    }
}

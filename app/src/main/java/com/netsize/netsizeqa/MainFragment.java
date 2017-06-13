package com.netsize.netsizeqa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 13/06/2017.
 */

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;

    private Spinner spinnerCountry;

    private Spinner spinnerEnv;


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
    public void showEnvs(int envArrayResId) {

       /* ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.ENV, android.R.layout.simple_spinner_item);*/

        spinnerEnv.setVisibility(View.VISIBLE);

    }

    @Override
    public void showTestcases() {

    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {

        mPresenter = presenter;

    }
}

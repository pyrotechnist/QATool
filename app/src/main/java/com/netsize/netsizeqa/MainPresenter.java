package com.netsize.netsizeqa;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by loxu on 13/06/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mMainFragment;

    public MainPresenter (MainContract.View mainFragment){

        mMainFragment = mainFragment;

        mMainFragment.setPresenter(this);
    }


    @Override
    public void loadCountries() {


    }

    @Override
    public void loadEnvs() {


        mMainFragment.showEnvs(R.array.ENV);
    }

    @Override
    public void loadTestcases() {

    }

    @Override
    public void start() {

        loadEnvs();
    }
}

package com.netsize.netsizeqa.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.netsize.netsizeqa.MainPresenter;
import com.netsize.netsizeqa.R;
import com.netsize.netsizeqa.data.TestCaseRepository;
import com.netsize.netsizeqa.data.remote.TestCaseRemoteSource;

/**
 * Created by LONGYUAN on 2017/7/1.
 */

public class StatisticsActivity  extends AppCompatActivity {


   // private StatisticsFragment statisticsFragment;

    private StatisticsPresenter statisticsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.statistics_act);

        StatisticsFragment statisticsFragment = (StatisticsFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (statisticsFragment == null) {

            statisticsFragment = StatisticsFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, statisticsFragment);
            transaction.commit();
        }


        //statisticsFragment = StatisticsFragment.newInstance();


        statisticsPresenter =  new StatisticsPresenter(TestCaseRepository.getInstance(TestCaseRemoteSource.getInstance(getApplicationContext())),statisticsFragment);


    }
}

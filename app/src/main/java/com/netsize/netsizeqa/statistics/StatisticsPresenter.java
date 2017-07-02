package com.netsize.netsizeqa.statistics;

import android.support.annotation.NonNull;

import com.netsize.netsizeqa.data.TestCase;
import com.netsize.netsizeqa.data.TestCaseRepository;
import com.netsize.netsizeqa.data.TestCaseSource;

import java.util.List;
import java.util.Map;

/**
 * Created by LONGYUAN on 2017/7/1.
 */

public class StatisticsPresenter implements StatisticsContract.Presenter {

    private final TestCaseRepository mTestCaseRepository;

    private final StatisticsContract.View mStatisticsView;

    public StatisticsPresenter(@NonNull TestCaseRepository testCaseRepository,
                               @NonNull StatisticsContract.View statisticsView) {
        mTestCaseRepository = testCaseRepository;

        mStatisticsView =statisticsView;

        mStatisticsView.setPresenter(this);
    }

    @Override
    public void start() {

        loadStatistics();

    }

    private void loadStatistics() {
       // mStatisticsView.setProgressIndicator(true);

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
       // EspressoIdlingResource.increment(); // App is busy until further notice

        mTestCaseRepository.getTestCases(new TestCaseSource.LoadTaskCasesCallback() {


            @Override
            public void onTasksLoaded(Map<String,List<TestCase>> testCasesMap) {
               /* int activeTasks = 0;
                int completedTasks = 0;

                // This callback may be called twice, once for the cache and once for loading
                // the data from the server API, so we check before decrementing, otherwise
                // it throws "Counter has been corrupted!" exception.
                //if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                //    EspressoIdlingResource.decrement(); // Set app as idle.
               // }

                // We calculate number of active and completed tasks
                for (TestCase task : tasks) {
                    if (task.isCompleted()) {
                        completedTasks += 1;
                    } else {
                        activeTasks += 1;
                    }
                }
                // The view may not be able to handle UI updates anymore
                if (!mStatisticsView.isActive()) {
                    return;
                }
               // mStatisticsView.setProgressIndicator(false);*/

                mStatisticsView.showStatistics(testCasesMap);
            }

            @Override
            public void onDataNotAvailable() {
                // The view may not be able to handle UI updates anymore
                if (!mStatisticsView.isActive()) {
                    return;
                }
                mStatisticsView.showLoadingStatisticsError();
            }
        },false);
    }
}
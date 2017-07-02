package com.netsize.netsizeqa.statistics;

import com.netsize.netsizeqa.BasePresenter;
import com.netsize.netsizeqa.BaseView;
import com.netsize.netsizeqa.data.TestCase;

import java.util.List;
import java.util.Map;

/**
 * Created by LONGYUAN on 2017/7/1.
 */

public interface StatisticsContract {

    interface View extends BaseView<Presenter> {

        void setProgressIndicator(boolean active);

        void showStatistics(Map<String,List<TestCase>> testCasesMap);

        void showLoadingStatisticsError();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

    }
}

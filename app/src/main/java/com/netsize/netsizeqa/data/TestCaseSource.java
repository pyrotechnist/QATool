package com.netsize.netsizeqa.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by loxu on 29/06/2017.
 */

public interface TestCaseSource {


    interface LoadTaskCasesCallback {

        void onTasksLoaded(List<TestCase> tasks);

        void onDataNotAvailable();
    }

    void getTestCases(@NonNull LoadTaskCasesCallback callback) ;


}

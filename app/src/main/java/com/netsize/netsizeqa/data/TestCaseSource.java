package com.netsize.netsizeqa.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

/**
 * Created by loxu on 29/06/2017.
 */

public interface TestCaseSource {


    interface LoadTaskCasesCallback {

        void onTasksLoaded(Map<String,List<TestCase>> testCasesMap);

        void onDataNotAvailable();
    }

    void getTestCases(@NonNull LoadTaskCasesCallback callback, Boolean forceUpdate ) ;


}

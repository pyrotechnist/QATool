package com.netsize.netsizeqa.data;

import android.support.annotation.NonNull;

import com.netsize.netsizeqa.data.remote.TestCaseRemoteSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loxu on 29/06/2017.
 */

public class TestCaseRepository implements TestCaseSource {


    private static TestCaseRepository INSTANCE = null;

    private final TestCaseRemoteSource mTasksRemoteDataSource;


    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, TestCase> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private TestCaseRepository(@NonNull TestCaseRemoteSource testCasesRemoteSource
                           ) {
        mTasksRemoteDataSource = testCasesRemoteSource;

    }


    /**
     *
     * @param testCasesRemoteSource
     * @return
     */
    public static TestCaseRepository getInstance(TestCaseRemoteSource testCasesRemoteSource
                                           ) {
        if (INSTANCE == null) {
            INSTANCE = new TestCaseRepository(testCasesRemoteSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getTestCases(@NonNull final LoadTaskCasesCallback callback) {

        mTasksRemoteDataSource.getTestCases(new LoadTaskCasesCallback() {
                @Override
            public void onTasksLoaded(Map<String,List<TestCase>> testCasesMap) {
                //refreshCache(tasks);
                callback.onTasksLoaded(testCasesMap);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }
}

package com.netsize.netsizeqa.data.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.netsize.netsizeqa.MainPresenter;
import com.netsize.netsizeqa.data.TestCaseSource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by loxu on 29/06/2017.
 */

public class TestCaseRemoteSource implements TestCaseSource{

    private static TestCaseRemoteSource INSTANCE;

    FileOutputStream mOutput ;




    public static TestCaseRemoteSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestCaseRemoteSource();
        }
        return INSTANCE;
    }

    private TestCaseRemoteSource() {}

    @Override
    public void getTestCases(final @NonNull LoadTaskCasesCallback callback) {

        String url= "http://dev.pay.netsize.com/merchantdemo/sdk";

        //OkHttpHandler okHttpHandler= new OkHttpHandler();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG",response.body().string());
            }
        });


        //okHttpHandler.execute(url);


    }


}

package com.netsize.netsizeqa.data.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.netsize.netsizeqa.MainPresenter;
import com.netsize.netsizeqa.data.TestCase;
import com.netsize.netsizeqa.data.TestCaseSource;
import com.netsize.netsizeqa.utils.PullXmlParser;
import com.netsize.netsizeqa.utils.QaViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

/**
 * Created by loxu on 29/06/2017.
 */

public class TestCaseRemoteSource implements TestCaseSource {

    private static TestCaseRemoteSource INSTANCE;

    private Context mContext;

    FileOutputStream mOutput;

    Map<String,List<TestCase>> mTestCasesMap = new HashMap<String,List<TestCase>>();


    /**
     *
     * @param context
     * @return
     */
    public static TestCaseRemoteSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TestCaseRemoteSource(context);
        }
        return INSTANCE;
    }

    private TestCaseRemoteSource(Context context) {

        mContext = context;
    }

    @Override
    public void getTestCases(final @NonNull LoadTaskCasesCallback callback, final Boolean forceUpdate) {

        String url = "http://dev.pay.netsize.com/merchantdemo/sdk";

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
                //Log.d("TAG",response.body().string());
                //callback.onDataNotAvailable();
                Log.d("TAG", "cache xml start");
                File downloadedFile = new File(mContext.getFilesDir(), "cache.xml");

                if((downloadedFile.exists()&& forceUpdate) ||  !downloadedFile.exists())
                {
                    BufferedSink sink = Okio.buffer(Okio.sink(downloadedFile));
                    sink.writeAll(response.body().source());
                    sink.close();
                }

                Log.d("TAG", "cache xml saved");

                callback.onTasksLoaded(parseXML());;
            }
        });


        //okHttpHandler.execute(url);


    }


    /**
     * @return
     */
    private Map<String, List<TestCase>> parseXML() {

        FileInputStream file = null;
        try {

            file = mContext.openFileInput("cache.xml");

            mTestCasesMap = PullXmlParser.getTestCases(file);

            return mTestCasesMap;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { // because close can throw an exception
                if (file != null) file.close();
                return mTestCasesMap;
            } catch (IOException ignored) {

                return mTestCasesMap;
            }
        }
    }

}

package com.netsize.netsizeqa;

import android.content.Context;

import com.netsize.netsizeqa.data.TestCase;
import com.netsize.netsizeqa.utils.QaViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loxu on 13/06/2017.
 */

public interface MainContract {

    interface View extends BaseView<Presenter>{

        void showCountries(String[] countries);

        void showProgressDialog();

        void updateDialog(int progress);

        void showTestcases(Map<String,List<TestCase>> testCasesMap);

        Context getContext();
    }

    interface Presenter extends  BasePresenter{

        void loadCountries();

        void loadTestcases (Boolean forceUpdate);

        void downloadXML();


    }
}
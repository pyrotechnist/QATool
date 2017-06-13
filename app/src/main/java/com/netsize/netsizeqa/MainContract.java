package com.netsize.netsizeqa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loxu on 13/06/2017.
 */

public interface MainContract {

    interface View extends BaseView<Presenter>{

        void showCountries();

        void showEnvs(int textArrayResId);

        void showTestcases();


    }

    interface Presenter extends  BasePresenter{

        void loadCountries();

        void loadEnvs ();

        void loadTestcases ();




    }
}

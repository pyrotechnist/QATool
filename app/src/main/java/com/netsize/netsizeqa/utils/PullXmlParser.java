package com.netsize.netsizeqa.utils;

import android.util.Xml;

import com.netsize.netsizeqa.data.TestCase;

import org.xmlpull.v1.XmlPullParser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by loxu on 15/06/2017.
 */

public class PullXmlParser {
    public static Map<String,List<TestCase>> getTestCases(FileInputStream file) throws Exception{
        Map<String,List<TestCase>> mTestCasesMap = new HashMap<String,List<TestCase>>();


        TestCase testCase = null;
        XmlPullParser parser= Xml.newPullParser();
        //Set<String> mUniqueCountryList = new HashSet<String>();

        //File file = new File( "cache.xml");
        //FileInputStream fileInputStream = new FileInputStream(file);
        InputStream inStream = file;
        parser.setInput(inStream, "UTF-8");

        String country = "";
        int eventType = parser.getEventType();    //触发第一个事件
        while(eventType!=XmlPullParser.END_DOCUMENT){
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:


                    break;

                case XmlPullParser.START_TAG:    //开始元素事件
                    if("testCase".equals(parser.getName())){
                        testCase = new TestCase();
                        testCase.setId(new Integer(parser.getAttributeValue(0)));
                        testCase.setTitle(parser.getAttributeValue(1));
                        testCase.setCountry(parser.getAttributeValue(2));
                        country = parser.getAttributeValue(2);
                       // mUniqueCountryList.add(parser.getAttributeValue(2));
                    }else if(testCase!=null){
                        if("data".equals(parser.getName())){
                            if("Command".equals(parser.getAttributeValue(null,"name")))
                                testCase.setCommand(parser.getAttributeValue(null,"value"));
                            if("ProductName".equals(parser.getAttributeValue(null,"name")))
                                testCase.setProductName(parser.getAttributeValue(null,"value"));
                            if("ServiceId".equals(parser.getAttributeValue(null,"name")))
                                testCase.setServiceId(parser.getAttributeValue(null,"value"));

                        }
                    }
                    break;

                case XmlPullParser.END_TAG:    //结束元素事件
                    if("testCase".equals(parser.getName())){

                        if(mTestCasesMap.containsKey(country)){
                            mTestCasesMap.get(country).add(testCase);
                        }else
                        {
                            List<TestCase> mTestCasesList = new ArrayList<TestCase>();
                            mTestCasesList.add(testCase);
                            mTestCasesMap.put(country,mTestCasesList);
                        }


                        testCase = null;
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }

        return mTestCasesMap;
    }




}


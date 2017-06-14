package com.netsize.netsizeqa.utils;

import android.content.ContextWrapper;
import android.net.Uri;
import android.util.Xml;

import com.netsize.netsizeqa.TestCase;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LONGYUAN on 2017/6/14.
 */

public class PullXmlParser {

    public static List<TestCase> getTestCases(FileInputStream file) throws Exception{
        List<TestCase> persons = null;
        TestCase person = null;
        XmlPullParser parser= Xml.newPullParser();

        //File file = new File( "cache.xml");
        //FileInputStream fileInputStream = new FileInputStream(file);
        InputStream inStream = file;
        parser.setInput(inStream, "UTF-8");
        int eventType = parser.getEventType();    //触发第一个事件
        while(eventType!=XmlPullParser.END_DOCUMENT){
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    persons = new ArrayList<TestCase>();
                    break;

                case XmlPullParser.START_TAG:    //开始元素事件
                    if("testCase".equals(parser.getName())){
                        person = new TestCase();
                        person.setId(new Integer(parser.getAttributeValue(0)));
                        person.setTitle(parser.getAttributeValue(1));
                        person.setCountry(parser.getAttributeValue(2));
                    }else if(person!=null){
                        if("Command".equals(parser.getName())){
                            person.setCommand(parser.nextText());
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:    //结束元素事件
                    if("testCase".equals(parser.getName())){
                        persons.add(person);
                        person = null;
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }
        return persons;
    }




}

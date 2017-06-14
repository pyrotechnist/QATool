package com.netsize.netsizeqa;

/**
 * Created by LONGYUAN on 2017/6/14.
 */

public class TestCase {

    private int  id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String title;
    private String country;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        command = command;
    }

    private String command;


}

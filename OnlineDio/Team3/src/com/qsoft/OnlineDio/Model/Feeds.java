package com.qsoft.OnlineDio.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/1/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Feeds implements Serializable
{
    private int code;
    private String status;
    private List<Feed> data;

    public Feeds()
    {
        data = new ArrayList<Feed>();
    }

    public List<Feed> getFeedList()
    {
        return data;
    }

    public void setFeedList(List<Feed> feedList)
    {
        this.data = feedList;
    }
}

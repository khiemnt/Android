package com.qsoft.OnlineDio.SyncAdapter;

import com.google.gson.Gson;
import com.qsoft.OnlineDio.Model.Feed;
import com.qsoft.OnlineDio.Model.Feeds;
import com.qsoft.OnlineDio.Model.ParseComError;
import com.qsoft.OnlineDio.Model.Profile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/1/13
 * Time: 8:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParseServerAccessor
{
    public ArrayList<Feed> getListFeeds(String token) throws Exception
    {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "http://192.168.1.222/testing/ica467/trunk/public/home-rest?" + "access_token=" +token;
        URL urlReal = new URL(url);
        try
        {
            //make GET request to the given URL
            HttpResponse response = httpClient.execute(new HttpGet(urlReal.toURI()));

            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK)
            {
                ParseComError error = new Gson().fromJson(responseString, ParseComError.class);
                throw new Exception("Error retrieving tv shows [" + error.code + "] - " + error.error);
            }
            if(responseString.equals("cannot access my apis")){
                throw new RuntimeException("cannot access my apis");
            }
            Feeds feeds = new Gson().fromJson(responseString, Feeds.class);
            ArrayList<Feed> feedList = (ArrayList<Feed>) feeds.getFeedList();
            return feedList;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return null;
    }
    public Profile getProfile(int user_id, String token) throws Exception
    {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "http://192.168.1.222/testing/ica467/trunk/public/user-rest/" +user_id+"?access_token="+token;
        URL urlReal = new URL(url);
        try
        {
            //make GET request to the given URL
            HttpResponse response = httpClient.execute(new HttpGet(urlReal.toURI()));

            String responseString = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK)
            {
                ParseComError error = new Gson().fromJson(responseString, ParseComError.class);
                throw new Exception("Error retrieving tv shows [" + error.code + "] - " + error.error);
            }
            if(responseString.equals("cannot access my apis")){
                throw new RuntimeException("cannot access my apis");
            }
            Profile profile = null;
            try{
                JSONObject jo=new JSONObject(responseString);
                 profile= new Gson().fromJson(jo.get("data").toString(), Profile.class);
            }catch (JSONException je){
                je.getMessage();
            }

            return profile;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

}

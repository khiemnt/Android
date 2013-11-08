package com.qsoft.OnlineDio.SyncAdapter;

import android.util.Log;
import com.google.gson.Gson;
import com.qsoft.OnlineDio.DB.DbHelper;
import com.qsoft.OnlineDio.Model.Feed;
import com.qsoft.OnlineDio.Model.Feeds;
import com.qsoft.OnlineDio.Model.ParseComError;
import com.qsoft.OnlineDio.Model.Profile;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
     public void putProfile(String token,String userId,Profile p) throws Exception {
         DefaultHttpClient httpClient=new DefaultHttpClient();

         String url=  "http://192.168.1.222/testing/ica467/trunk/public/user-rest/"+userId+"?access_token="+token;
         HttpPut put=new HttpPut(new URI(url));
         List<NameValuePair> pairs = new ArrayList<NameValuePair>();
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_DISPLAY_NAME,p.getDisplay_name())) ;
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_FULL_NAME,p.getFull_name())) ;
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_PHONE,p.getPhone())) ;
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_BIRTHDAY,p.getBirthday())) ;
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_GENDER,p.getGender()+"")) ;
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_COUNTRY_ID,p.getCountry_id())) ;
         pairs.add(new BasicNameValuePair(DbHelper.PROFILE_COL_DESCRIPTION,p.getDescription())) ;

         put.setEntity(new UrlEncodedFormEntity(pairs));
         HttpResponse response= httpClient.execute(put);
         String responseString = EntityUtils.toString(response.getEntity());
          Log.d("Profile", "Response string = " + responseString);
     }
}

package com.bdcorps.videonews;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sunny on 8/9/2016.
 */
public class Config {

    public static JSONArray initNewsRequest(String topicCode){
    ArrayList<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("api-key", "34aa2b501a284e739996ef1658db42d7"));
    ServerGETRequest sr = new ServerGETRequest();
    JSONObject json = sr.getJSON("https://api.nytimes.com/svc/topstories/v2/" +topicCode+".json", params);

        try {
            JSONArray a = json.getJSONArray("results");
            return a;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

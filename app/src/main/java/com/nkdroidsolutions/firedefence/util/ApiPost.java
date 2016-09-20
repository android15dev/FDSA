package com.nkdroidsolutions.firedefence.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nirav on 09/08/15.
 */
public class ApiPost {



    public static String callPostAPI(String url,JSONObject object){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        String responseBody=null;
        try {
            // Add your data


            List nameValuePairs = new ArrayList(1);
            nameValuePairs.add(new BasicNameValuePair("json", object.toString()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            responseBody = EntityUtils.toString(response.getEntity());
            Log.d("JSON","RESPONSE : " + responseBody);

        } catch (ClientProtocolException e) {
            Log.e("JSON",e.getMessage());
        } catch (IOException e) {
            Log.e("JSON",e.getMessage());
        }
        return responseBody;
    }

    public static Reader callWebservicePost(String SERVER_URL,String jsonString) {

        Reader reader = null;
        InputStream is=null;
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(SERVER_URL);

//            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(jsonString));
            HttpResponse response = client.execute(post);

            StatusLine statusLine = response.getStatusLine();

            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            reader = new InputStreamReader(is,"UTF-8");

        }catch (ClientProtocolException e) {
            Log.e("Error: ",e+"");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            Log.e("Error: ",e+"");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Error: ",e+"");
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("Error: ",e+"");
            e.printStackTrace();
        }
        return reader;
    }


}

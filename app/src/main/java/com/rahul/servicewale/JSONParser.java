package com.rahul.servicewale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JSONParser {
    static InputStream is=null;
    static JSONObject jobj=null;
    static String son="";
    public JSONParser()
    {

    }
    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> parms)
    {
        // TODO Auto-generated method stub
        try
        {
            if(method=="POST")
            {
                DefaultHttpClient httpclient=new DefaultHttpClient();
                HttpPost httpPost=new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(parms));
                HttpResponse httpresponse=httpclient.execute(httpPost);
                HttpEntity httpEntity=httpresponse.getEntity();
                is=httpEntity.getContent();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            is.close();
            son=sb.toString();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            jobj=new JSONObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return jobj;
    }

}



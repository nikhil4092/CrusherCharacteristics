package project.mineralprocessing;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;

public class JSONFunctions {


    public static JSONArray getJSONfromURL(String[] url) throws UnknownHostException {
        String charset = "UTF-8";
        HttpURLConnection conn=null;
        DataOutputStream wr;
        StringBuilder result = new StringBuilder();
        URL urlObj;
        JSONArray jArray = null;
        StringBuilder sbParams;
        String paramsString;
        HashMap<String, String> nameValuePairs = new HashMap<String, String>();
        nameValuePairs.put("dbname", "wavermmi_WINKL");
        nameValuePairs.put("dbuser", "wavermmi_WINKL");
        nameValuePairs.put("dbpass", "#,n){P_Wq{BD");
        if(url!=null) {
            for (int i = 1; i + 1 < url.length; i++)
                nameValuePairs.put(url[i], url[i + 1]);

            sbParams = new StringBuilder();
            int i = 0;
            for (String key : nameValuePairs.keySet()) {
                try {
                    if (i != 0) {
                        sbParams.append("&");
                    }
                    sbParams.append(key).append("=")
                            .append(URLEncoder.encode(nameValuePairs.get(key), charset));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }

            // Download JSON data from URL
            try {
                urlObj = new URL(url[0]);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Accept-Charset", charset);

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                paramsString = sbParams.toString();

                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //convert response to string
            try {
                //Receive the response from the server
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.d("JSON Parser", "result: " + result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();
            //parse json data

            try {
                jArray = new JSONArray(result.toString());
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());

            }
        }
            return jArray;

    }
}
package project.mineralprocessing;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.net.UnknownHostException;

public class JSONObtainer extends AsyncTask<String[], Void, JSONArray> {

    @Override
    protected JSONArray doInBackground(String[]... url) {
        // TODO Auto-generated method stub
        try {
            if(url!=null)
            {return JSONFunctions.getJSONfromURL(url[0]);}
            else{
                return new JSONArray();
            }
        } catch (UnknownHostException e) {
            Log.e("Error in obtainer", e.toString());
            return new JSONArray();
        }
    }
    @Override
    protected void onCancelled() {

        super.onCancelled();
        cancel(true);
    }

}

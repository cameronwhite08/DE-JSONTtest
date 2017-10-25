package com.cameronwhite.dejsontest;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Cameron on 10/25/2017.
 */

public class LoadAdapterFromJSONAsyncTask extends AsyncTask<Void, Void, JSONArray> {

    String url;
    RecyclerView recyclerView;

    public LoadAdapterFromJSONAsyncTask(RecyclerView rv, String url) {
        this.recyclerView = rv;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {
        HttpURLConnection conn = null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line+"\n");
            }
            br.close();

            return new JSONArray(sb.toString());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.disconnect();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray arr) {
        super.onPostExecute(arr);

        if (arr != null)
        {
            ListViewAdapter adapter = new ListViewAdapter(arr);

            recyclerView.setAdapter(adapter);
        }
    }

}

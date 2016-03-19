package com.miguelcr.asynctaskexample;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by miguelcampos on 18/3/16.
 */
public class Util {

    public static String getStringContentFromUrl(String url) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br =  Url2BufferedReader(url);
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();
        return sb.toString();

    }

    public static BufferedReader Url2BufferedReader(String url) throws IOException {
        URL Url = new URL(url);

        InputStream is = Url.openStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        return br;

    }

    public static String getResultadoUrl(String urlConsulta) {
        String responseString=null;

        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urlConsulta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){

                responseString = Util.getStringContentFromUrl(urlConsulta);

                Log.v("GuardarPuntos", responseString);

            }else{

                Log.v("GuardarPuntos", "Response code:"+ responseCode);
            }
        } catch (IOException e) {
            Log.v("GuardarPuntos", "Error conexión");
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return responseString;
    }

}


package openwebinars.navigationdrawer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Fran on 19/11/2015.
 */
public class Util {

    public static final String URL_IMG="http://rest.miguelcr.com/images/aroundme/";
    public static SharedPreferences sp;
    /*
        Método que devuelve el bufferedreader asociado a una URL.
     */

    public static BufferedReader Url2BufferedReader(String url) throws IOException {

        //return new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        URL Url = new URL(url);

        InputStream is = Url.openStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        return br;

    }


    /*
        Método que devuelve el contenido de una URL como una cadena de caracteres
     */

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

    public static boolean existe(Context mContext){
        boolean bandera=false;
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(sp.contains("ID")){
            bandera =true;
        }
        return bandera;
    }

    public static boolean existeAvatar(Context mContext){
        boolean bandera=false;
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        if(sp.contains("avatar")){
            bandera =true;
        }
        return bandera;
    }

    public static boolean guardarN(Context mContext, String n)
    {
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();
        Log.i("Nombre1",n);
        mEdit1.putString("nom" , n);

        return mEdit1.commit();
    }


    public static boolean guardarID(Context mContext, String id)
    {
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();

        mEdit1.putString("ID", id);

        return mEdit1.commit();
    }



    public static boolean guardarAvatar(Context mContext, String ava)
    {
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();
        Log.i("avatar1",ava);
        mEdit1.putString("avatar" , ava);

        return mEdit1.commit();
    }


    public static String obtieneAvatar(Context mContext){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        String ava = sp.getString("avatar", null);
        Log.i("avatar2",ava);
        return ava;
    }


    public static String obtieneId(Context mContext){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        String id = sp.getString("ID", null);
        return id;
    }

    public static String obtieneN(Context mContext){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        String name = sp.getString("nom", null);
        Log.i("Nombre2",name);
        return name;
    }


    public static void limpia(Context mContext){
        sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.clear();
        mEdit1.commit();

    }
}

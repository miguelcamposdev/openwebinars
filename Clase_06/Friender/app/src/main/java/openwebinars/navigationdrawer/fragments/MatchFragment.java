package openwebinars.navigationdrawer.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openwebinars.navigationdrawer.R;
import openwebinars.navigationdrawer.activities.ScrollingActivity;
import openwebinars.navigationdrawer.adapters.AdaptadorPersona;
import openwebinars.navigationdrawer.models.User;
import openwebinars.navigationdrawer.utils.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {


    List<User> listaUsuarios;
    ListView lista;
    private static final String BASE_URL = "http://rest.miguelcr.com/friender/users?regId=";
    AdaptadorPersona adapter;


    public MatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_match, container, false);

        lista = (ListView) v.findViewById(R.id.listView);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ScrollingActivity.class);
                i.putExtra("sexo",listaUsuarios.get(position).getSexo());
                i.putExtra("nick",listaUsuarios.get(position).getNickname());
                i.putExtra("id",listaUsuarios.get(position).getId());
                startActivity(i);
            }
        });

        new GetUsers().execute(Util.obtieneId(v.getContext()));

        return v;
    }

    private class GetUsers extends AsyncTask<String, Void, List<User>> {
        protected List<User> doInBackground(String... params) {
            String url = BASE_URL+params[0];
            List<User> result= new ArrayList<>();
            Log.i("ErrorUrl", url);

            try {

                String jsonText= Util.getStringContentFromUrl(url);

                JSONArray json = new JSONArray(jsonText);
                Log.i("ErrorJson",jsonText);

                for (int i = 0; i< json.length(); i++){
                    JSONObject j = json.getJSONObject(i);
                    result.add(new User(j.getString("nickname"),j.getString("latlon"),j.getString("id"),j.getString("sexo")));
                    Log.i("ErrorJson",j.getString("nickname")+" --------- "+j.getString("sexo"));
                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(List<User> listanom) {
            listaUsuarios = listanom;
            adapter = new AdaptadorPersona(getActivity(),R.layout.list_item_persona,listaUsuarios);

            lista.setAdapter(adapter);
        }


    }

}

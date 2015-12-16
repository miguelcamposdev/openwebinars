package openwebinars.navigationdrawer.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openwebinars.navigationdrawer.R;
import openwebinars.navigationdrawer.models.User;
import openwebinars.navigationdrawer.utils.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment implements CardModel.OnCardDimissedListener {


    List<CardModel> barajaCartas;
    private static final String BASE_URL = "http://rest.miguelcr.com/friender/users?regId=";
    private List<User> listaUsuarios;
    private CardContainer mCardContainer;
    private SimpleCardStackAdapter adapter;
    private CardModel.OnCardDimissedListener listener;
    CardModel card = null;
    String nick, sexo, id;
    int width, height;

    public CardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cards, container, false);

        mCardContainer = (CardContainer) v.findViewById(R.id.layoutview);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);

        adapter = new SimpleCardStackAdapter(getActivity());
        mCardContainer.setAdapter(adapter);

        new GetUsers().execute(Util.obtieneId(v.getContext()));


        // Dimensiones del CardContainer
        RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.layoutCards);
        width = layout.getMeasuredWidth();
        height = layout.getMeasuredHeight();

        return v;
    }

    @Override
    public void onLike() {

    }

    @Override
    public void onDislike() {

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

            adapter = new SimpleCardStackAdapter(getActivity());


            for (int i = 0; i < listaUsuarios.size(); i++) {
                nick = listaUsuarios.get(i).getNickname();
                sexo = listaUsuarios.get(i).getSexo();
                id = listaUsuarios.get(i).getId();

                //Picasso.with(getActivity()).load("http://rest.miguelcr.com/images/friender/" + id + ".png").resize(6000,3000).into();
                card = new CardModel(nick, sexo, ContextCompat.getDrawable(getActivity(),R.drawable.picture1));

                card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
                    @Override
                    public void onLike() {
                        Toast.makeText(getActivity(), "Me gusta", Toast.LENGTH_LONG).show();

                        // Conectar con la función del servidor que nos permite indicar
                        // que nos ha gustado una persona
                    }

                    @Override
                    public void onDislike() {
                        Toast.makeText(getActivity(), "No me gusta", Toast.LENGTH_LONG).show();
                    }
                });



                adapter.add(card);

                mCardContainer.setAdapter(adapter);



            }

            mCardContainer.setAdapter(adapter);
        }


    }


}

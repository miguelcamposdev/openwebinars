package openwebinars.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {


    ArrayList<ItemPersona> personas;
    ListView lista;

    public MatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_match, container, false);

        lista = (ListView) v.findViewById(R.id.listView);

        personas = new ArrayList<>();
        personas.add(new ItemPersona("María",25,2));
        personas.add(new ItemPersona("Rebeca",28,2));
        personas.add(new ItemPersona("Ana",25,1));
        personas.add(new ItemPersona("Mercedes",26,1));

       AdaptadorPersona adaptadorPersonas = new AdaptadorPersona(getActivity(),R.layout.list_item_persona,personas);

        lista.setAdapter(adaptadorPersonas);

        return v;
    }

}

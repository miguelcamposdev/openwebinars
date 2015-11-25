package openwebinars.navegaciontabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FotosFragment extends Fragment implements CardModel.OnCardDimissedListener {
    private CardContainer contenedorCartas;

    public FotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fotos, container, false);

        // Cambios en la vista, antes de devolverla como salida
        contenedorCartas = (CardContainer)v.findViewById(R.id.cartasContainer);

        CardModel cardUno = new CardModel("Susana", "Sevilla", getActivity().getDrawable(R.drawable.picture1));
        CardModel cardDos = new CardModel("María", "Madrid", getActivity().getDrawable(R.drawable.picture2));

        // Asocio las tarjetas al evento que tengo implementado en esta clase
        cardUno.setOnCardDimissedListener(this);
        cardDos.setOnCardDimissedListener(this);


        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(getActivity());
        adapter.add(cardUno);
        adapter.add(cardDos);
        contenedorCartas.setAdapter(adapter);

        return v;
    }

    @Override
    public void onLike() {
        Toast.makeText(getActivity(),"Me gusta",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDislike() {
        Toast.makeText(getActivity(),"No me gusta",Toast.LENGTH_SHORT).show();

    }
}

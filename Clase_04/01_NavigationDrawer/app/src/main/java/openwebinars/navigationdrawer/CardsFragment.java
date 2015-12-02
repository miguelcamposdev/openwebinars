package openwebinars.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment implements CardModel.OnCardDimissedListener {


    List<CardModel> barajaCartas;

    public CardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cards, container, false);

        CardContainer mCardContainer = (CardContainer) v.findViewById(R.id.layoutview);

        mCardContainer.setOrientation(Orientations.Orientation.Ordered);

        CardModel card = new CardModel("Title1", "Description goes here", getActivity().getDrawable(R.drawable.photo_uno));
        CardModel card2 = new CardModel("Title2", "Description goes here", getActivity().getDrawable(R.drawable.picture1));
        CardModel card3 = new CardModel("Title3", "Description goes here", getActivity().getDrawable(R.drawable.picture2));

        card.setOnCardDimissedListener(this);
        card2.setOnCardDimissedListener(this);
        card3.setOnCardDimissedListener(this);

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(getActivity());
        adapter.add(card);
        adapter.add(card2);
        adapter.add(card3);
        mCardContainer.setAdapter(adapter);


        return v;
    }

    @Override
    public void onLike() {
        Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDislike() {
        Toast.makeText(getActivity(),"Dislike",Toast.LENGTH_SHORT).show();
    }
}

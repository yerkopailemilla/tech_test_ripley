package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.yerkode.prueba_tecnica_ripley.R;

public class CartDialogFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.btn_close_dialog) ImageButton btn_close_dialog;

    public static CartDialogFragment newInstance() {
        CartDialogFragment dialogFragment = new CartDialogFragment();
        Bundle args = new Bundle();
        //args.putString(POKE_NAME, name);
        //args.putString(POKE_URL, url);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //namePokemon = getArguments().getString(POKE_NAME);
            //urlPokemon = getArguments().getString(POKE_URL);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        btn_close_dialog.setOnClickListener(this);
        /*pokemon_photo = view.findViewById(R.id.pokemon_photo);
        pokename_detail_tv = view.findViewById(R.id.pokename_detail_tv);

        String url = urlPokemon;
        String[] splitUrl = url.split("https://pokeapi.co/api/v2/pokemon/");
        String[] pokemonNumber = splitUrl[1].split("/");
        String pokemonId = pokemonNumber[0];

        Picasso.get().load(PHOTO_URL_BASE + pokemonId + ".png").into(pokemon_photo);
        pokename_detail_tv.setText(namePokemon);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );
    }

    @Override
    public void onClick(View view) {
        int idClick = view.getId();

        switch (idClick) {
            case R.id.btn_close_dialog:
                dismiss();
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}

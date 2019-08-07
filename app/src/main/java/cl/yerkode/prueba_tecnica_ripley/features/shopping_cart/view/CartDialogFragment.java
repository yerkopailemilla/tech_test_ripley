package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cl.yerkode.prueba_tecnica_ripley.R;
import cl.yerkode.prueba_tecnica_ripley.adapters.shopping_cart.ShoppingCartAdapter;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.Product;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.ShoppingCartMVP;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.presenter.ShoppingCartPresenter;
import cl.yerkode.prueba_tecnica_ripley.utils.FormatNumber;
import cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp.ChangeCartStatusMVP;
import cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp.ChangeStatusCartEntity;
import cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp.ChangeStatusCartPresenter;

public class CartDialogFragment extends DialogFragment implements ShoppingCartMVP.View,
        ChangeCartStatusMVP.View, View.OnClickListener {

    @BindView(R.id.lyt_parent_shopping_cart) LinearLayout lyt_parent_shopping_cart;
    @BindView(R.id.btn_close_dialog) ImageButton btn_close_dialog;
    @BindView(R.id.btn_confirm_purchase) Button btn_confirm_purchase;
    @BindView(R.id.cart_products_recycler_view) RecyclerView cart_products_recycler_view;
    @BindView(R.id.lyt_products) LinearLayout lyt_products;
    @BindView(R.id.lyt_cart_empty) LinearLayout lyt_cart_empty;
    @BindView(R.id.lyt_success) LinearLayout lyt_success;
    @BindView(R.id.sheet_confirm_purchase_quantity) TextView sheet_confirm_purchase_quantity;
    @BindView(R.id.sheet_confirm_purchase_total_card) TextView sheet_confirm_purchase_total_card;
    @BindView(R.id.sheet_confirm_purchase_total_list) TextView sheet_confirm_purchase_total_list;

    private static final String CART_ID = "cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.view.CART_ID";
    private static final String CUSTOMER_ID = "cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.view.CUSTOMER_ID";
    private String cartId;
    private String customerId;

    private Unbinder unbinder;
    private ProgressDialog loader;
    private ShoppingCartPresenter shoppingCartPresenter;
    private ChangeStatusCartPresenter changeStatusCartPresenter;
    private ChangeStatusCartEntity statusCartEntity;

    public static CartDialogFragment newInstance(String cart_id, String customer_id) {
        CartDialogFragment dialogFragment = new CartDialogFragment();
        Bundle args = new Bundle();
        args.putString(CART_ID, cart_id);
        args.putString(CUSTOMER_ID, customer_id);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cartId = getArguments().getString(CART_ID);
            customerId = getArguments().getString(CUSTOMER_ID);
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
        unbinder = ButterKnife.bind(this, view);
        shoppingCartPresenter = new ShoppingCartPresenter(this);
        changeStatusCartPresenter = new ChangeStatusCartPresenter(this);
        statusCartEntity = new ChangeStatusCartEntity();

        statusCartEntity.setCart_id(cartId);
        statusCartEntity.setCustomer_id(customerId);

        loader = new ProgressDialog(getContext());
        cart_products_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        cart_products_recycler_view.setHasFixedSize(true);
        btn_close_dialog.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        shoppingCartPresenter.setCartView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );

        statusCartEntity.setStatus("checkout");

        changeStatusCartPresenter.setCartStatus(statusCartEntity);
        shoppingCartPresenter.getProductsCatalog(cartId, customerId);
        setSuccessPurchaseView();
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

    @Override
    public void showProgress() {
        if (getContext()!= null && !isRemoving()){
            /*getString(R.string.activity_retailers_title)*/
            loader.setTitle("Carrito de compras");
            loader.setMessage("Buscando lista de productos...");
            loader.setIndeterminate(true);
            loader.setCancelable(false);
            loader.show();
        }
    }

    @Override
    public void hideProgress() {
        if (loader != null && loader.isShowing()){
            loader.dismiss();
        }
    }

    @Override
    public void setEmptyCartView() {
        lyt_parent_shopping_cart.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        lyt_cart_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSuccessPurchaseView() {
        btn_confirm_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        statusCartEntity.setStatus("confirmado");
                        changeStatusCartPresenter.setCartStatus(statusCartEntity);

                        lyt_products.setVisibility(View.GONE);
                        lyt_parent_shopping_cart.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        lyt_success.setVisibility(View.VISIBLE);

                    }
                }, 1000);
            }
        });
    }

    @Override
    public void fillTotalList(int listPrice, int cardPrice, int quantity) {
        sheet_confirm_purchase_quantity.setText(String.valueOf(quantity));
        sheet_confirm_purchase_total_card.setText(FormatNumber.toCLP(cardPrice));
        sheet_confirm_purchase_total_list.setText(FormatNumber.toCLP(listPrice));
    }

    @Override
    public void showResult(List<Product> products) {
        lyt_parent_shopping_cart.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        lyt_products.setVisibility(View.VISIBLE);
        ShoppingCartAdapter adapter = new ShoppingCartAdapter(products);
        cart_products_recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        statusCartEntity.setStatus("default");
        changeStatusCartPresenter.setCartStatus(statusCartEntity);
        unbinder.unbind();
    }


}

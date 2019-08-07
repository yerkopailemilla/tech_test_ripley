package cl.yerkode.prueba_tecnica_ripley.adapters.shopping_cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.yerkode.prueba_tecnica_ripley.R;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.Product;
import cl.yerkode.prueba_tecnica_ripley.utils.FormatNumber;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {

    private List<Product> productList;

    public ShoppingCartAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_product_shopping_cart, viewGroup, false);
        return new ShoppingCartViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartViewHolder shoppingCartViewHolder, int position) {
        Picasso.get()
                .load("https:" + productList.get(position).getIsActive())
                .into(shoppingCartViewHolder.shopping_cart_product_thumbnail);
        shoppingCartViewHolder.shopping_cart_product_name.setText(productList.get(position).getDescription());
        shoppingCartViewHolder.shopping_cart_product_sku.setText(productList.get(position).getSku());
        shoppingCartViewHolder.shopping_cart_product_quantity.setText(String.valueOf(productList.get(position).getQuantity()));
        shoppingCartViewHolder.shopping_cart_product_card_price.setText(FormatNumber.toCLP(productList.get(position).getPriceDiscountRipley()));
        shoppingCartViewHolder.shopping_cart_product_list_price.setText(FormatNumber.toCLP(productList.get(position).getPrice()));
    }

    static class ShoppingCartViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shopping_cart_product_thumbnail) ImageView shopping_cart_product_thumbnail;
        @BindView(R.id.shopping_cart_product_name) TextView shopping_cart_product_name;
        @BindView(R.id.shopping_cart_product_sku) TextView shopping_cart_product_sku;
        @BindView(R.id.shopping_cart_product_quantity) TextView shopping_cart_product_quantity;
        @BindView(R.id.shopping_cart_product_card_price) TextView shopping_cart_product_card_price;
        @BindView(R.id.shopping_cart_product_list_price) TextView shopping_cart_product_list_price;

        public ShoppingCartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

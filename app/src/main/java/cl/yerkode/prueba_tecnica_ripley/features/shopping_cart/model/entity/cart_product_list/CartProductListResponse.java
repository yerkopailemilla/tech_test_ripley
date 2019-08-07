package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.cart_product_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.Product;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartResponse;

public class CartProductListResponse {

    @SerializedName("cart")
    @Expose
    private NewCartResponse cart;
    @SerializedName("products")
    @Expose
    private List<Product> products;

    public NewCartResponse getCart() {
        return cart;
    }

    public void setCart(NewCartResponse cart) {
        this.cart = cart;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

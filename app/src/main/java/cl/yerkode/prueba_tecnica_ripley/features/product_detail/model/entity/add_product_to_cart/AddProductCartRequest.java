package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddProductCartRequest {

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

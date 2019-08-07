package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("price_discount_ripley")
    @Expose
    private int priceDiscountRipley;
    @SerializedName("price_discount")
    @Expose
    private int priceDiscount;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("cart_id")
    @Expose
    private String cartId;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceDiscountRipley() {
        return priceDiscountRipley;
    }

    public void setPriceDiscountRipley(int priceDiscountRipley) {
        this.priceDiscountRipley = priceDiscountRipley;
    }

    public int getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}

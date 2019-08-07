package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewCartResponse implements Serializable {

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("customer_rut")
    @Expose
    private String customerRut;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("is_active")
    @Expose
    private String isActive;

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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerRut() {
        return customerRut;
    }

    public void setCustomerRut(String customerRut) {
        this.customerRut = customerRut;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}

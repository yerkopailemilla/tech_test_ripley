package cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeStatusCartEntity {

    @SerializedName("customer_id")
    @Expose
    private String customer_id;
    @SerializedName("cart_id")
    @Expose
    private String cart_id;
    @SerializedName("state")
    @Expose
    private String status;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

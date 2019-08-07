package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Prices implements Serializable {

    @SerializedName("listPrice")
    @Expose
    private int listPrice;
    @SerializedName("cardPrice")
    @Expose
    private int cardPrice;

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public int getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
    }

}

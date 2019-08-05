package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.Prices;

public class ProductDetailEntity {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("prices")
    @Expose
    private Prices prices;
    @SerializedName("images")
    @Expose
    private List<String> images;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    /*public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }*/

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

}

package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attribute {

    @SerializedName("displayable")
    @Expose
    private boolean displayable;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("usage")
    @Expose
    private String usage;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    public boolean isDisplayable() {
        return displayable;
    }

    public void setDisplayable(boolean displayable) {
        this.displayable = displayable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}

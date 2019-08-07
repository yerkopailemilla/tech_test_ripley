package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart;

import java.util.List;

public class AddProductCartResponse {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

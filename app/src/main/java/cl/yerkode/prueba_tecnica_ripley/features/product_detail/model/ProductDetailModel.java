package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.ProductDetailMVP;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.api.ProductDetailInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartRequest;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartResponse;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.api.ShoppingCartInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observable;

public class ProductDetailModel implements ProductDetailMVP.Model {

    private ProductDetailMVP.Presenter presenter;
    private ProductDetailInterceptor productDetailInterceptor;
    private ShoppingCartInterceptor shoppingCartInterceptor;

    public ProductDetailModel(ProductDetailMVP.Presenter presenter) {
        this.presenter = presenter;
        this.productDetailInterceptor = new ProductDetailInterceptor();
        this.shoppingCartInterceptor = new ShoppingCartInterceptor();
    }

    @Override
    public Observable<ProductDetailEntity> getProductDetailData(CatalogEntity product) {
        return productDetailInterceptor.get().getProductDetail(product.getSku());
    }

    @Override
    public Observable<AddProductCartResponse> addProductToCart(AddProductCartRequest addProductCartRequest) {
        return shoppingCartInterceptor.get().addProductToCart(addProductCartRequest);
    }

}

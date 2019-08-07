package cl.yerkode.prueba_tecnica_ripley.features.product_detail;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartRequest;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartResponse;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observable;

public interface ProductDetailMVP {

    interface View {
        void showProgress(String title, String message);
        void hideProgress();

        void productDetailSuccess();
        void productDetailFailed();
        void messageAddProductToCart(String message);

        void clickAddProductToCart();
        void showProduct(ProductDetailEntity productDetailEntity);
    }

    interface Presenter {
        void setProductDetailView(ProductDetailMVP.View view);
        void getProductDetail(CatalogEntity product);
        void getProductToAdd(AddProductCartRequest addProductCartRequest);
    }

    interface Model {
        Observable<ProductDetailEntity> getProductDetailData(CatalogEntity product);
        Observable<AddProductCartResponse> addProductToCart(AddProductCartRequest addProductCartRequest);
    }
}

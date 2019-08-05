package cl.yerkode.prueba_tecnica_ripley.features.product_detail;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import io.reactivex.Observable;

public interface ProductDetailMVP {

    interface View {
        void showProgress();
        void hideProgress();

        void productDetailSuccess();
        void productDetailFailed();

        void showProduct(ProductDetailEntity productDetailEntity);
    }

    interface Presenter {
        void setProductDetailView(ProductDetailMVP.View view);
        void getProductDetail(String skuProduct);
    }

    interface Model {
        Observable<ProductDetailEntity> getProductDetailData(String sku);
    }
}

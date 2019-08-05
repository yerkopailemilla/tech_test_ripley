package cl.yerkode.prueba_tecnica_ripley.features.product_detail.model;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.ProductDetailMVP;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.api.ProductDetailInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import io.reactivex.Observable;

public class ProductDetailModel implements ProductDetailMVP.Model {

    private ProductDetailMVP.Presenter presenter;
    private ProductDetailInterceptor interceptor;

    public ProductDetailModel(ProductDetailMVP.Presenter presenter) {
        this.presenter = presenter;
        this.interceptor = new ProductDetailInterceptor();
    }

    @Override
    public Observable<ProductDetailEntity> getProductDetailData(String sku) {
        return interceptor.get().getProductDetail(sku);
    }
}

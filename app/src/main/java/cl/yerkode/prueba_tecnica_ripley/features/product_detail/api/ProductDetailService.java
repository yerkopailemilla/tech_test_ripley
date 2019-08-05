package cl.yerkode.prueba_tecnica_ripley.features.product_detail.api;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductDetailService {

    @GET("products/{sku}")
    Observable<ProductDetailEntity> getProductDetail(@Path("sku") String sku);
}

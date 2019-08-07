package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.api;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowProductCatalogService {

    @GET("products")
    Observable<List<CatalogEntity>> getProductsCatalogRx(@Query("partNumbers") String sku);

}

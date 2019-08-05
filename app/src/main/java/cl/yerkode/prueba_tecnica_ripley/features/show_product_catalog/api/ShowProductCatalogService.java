package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.api;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ShowProductCatalogService {

    @GET("products?partNumbers=2000371667503P,2000351773811P,2000369109855P,2000366737198P," +
            "2000372411631P,2000359329935P,2000375421729P,2000372107077P,2000371958953P," +
            "2000373857964P,2000371827983P,2000374299572P,2000374310932P,2000368425048P," +
            "2000371915604P,2000369989594P,MPM00002006512,2000335659285P,2000327637482P," +
            "MPM00001075806,2000370840266,2000371979422,2000373199514,2000373423879,2000372471789")
    Observable<List<CatalogEntity>> getProductsCatalogRx();

}

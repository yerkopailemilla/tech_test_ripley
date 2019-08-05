package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.ShowProductCatalogMVP;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.api.ShowProductCatalogInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observable;

public class ShowProductsCatalogModel implements ShowProductCatalogMVP.Model {

    private ShowProductCatalogMVP.Presenter presenter;
    private ShowProductCatalogInterceptor interceptor;

    public ShowProductsCatalogModel(ShowProductCatalogMVP.Presenter presenter) {
        this.presenter = presenter;
        this.interceptor = new ShowProductCatalogInterceptor();
    }

    @Override
    public Observable<List<CatalogEntity>> getCatalogData() {
        return interceptor.get().getProductsCatalogRx();
    }

}

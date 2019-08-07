package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observable;

public interface ShowProductCatalogMVP {

    interface View {

        void showProgress();
        void hideProgress();

        void productsCatalogSuccess();
        void productsCatalogFailed();

        void showCatalog(List<CatalogEntity> catalogEntities);
    }

    interface Presenter {
        void setCatalogView(ShowProductCatalogMVP.View view);
        void getProductsCatalog(String sku);
    }

    interface Model {
        Observable<List<CatalogEntity>> getCatalogData(String sku);
    }
}

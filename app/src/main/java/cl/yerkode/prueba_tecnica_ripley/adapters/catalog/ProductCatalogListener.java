package cl.yerkode.prueba_tecnica_ripley.adapters.catalog;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;

public interface ProductCatalogListener {

    void toProductDetail(CatalogEntity product);
}

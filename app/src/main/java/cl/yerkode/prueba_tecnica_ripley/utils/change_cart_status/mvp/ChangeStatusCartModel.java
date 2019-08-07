package cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp;

import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.api.ShoppingCartInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartResponse;
import io.reactivex.Observable;

public class ChangeStatusCartModel implements ChangeCartStatusMVP.Model {

    private ChangeCartStatusMVP.Presenter presenter;
    private ShoppingCartInterceptor shoppingCartInterceptor;

    public ChangeStatusCartModel(ChangeCartStatusMVP.Presenter presenter) {
        this.presenter = presenter;
        this.shoppingCartInterceptor = new ShoppingCartInterceptor();
    }

    @Override
    public Observable<NewCartResponse> getCatalogData(ChangeStatusCartEntity changeStatusCartEntity) {
        return shoppingCartInterceptor.get().setCartStatus(changeStatusCartEntity);
    }

}

package cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp;

import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartResponse;
import io.reactivex.Observable;

public interface ChangeCartStatusMVP {

    interface View {
    }

    interface Presenter {
        void setCartStatus(ChangeStatusCartEntity cartStatus);
    }

    interface Model {
        Observable<NewCartResponse> getCatalogData(ChangeStatusCartEntity changeStatusCartEntity);
    }
}

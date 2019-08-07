package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model;

import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.ShoppingCartMVP;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.api.ShoppingCartInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.cart_product_list.CartProductListResponse;
import io.reactivex.Observable;

public class ShoppingCartModel implements ShoppingCartMVP.Model{

    private ShoppingCartMVP.Presenter presenter;
    private ShoppingCartInterceptor interceptor;

    public ShoppingCartModel(ShoppingCartMVP.Presenter presenter) {
        this.presenter = presenter;
        this.interceptor = new ShoppingCartInterceptor();
    }

    @Override
    public Observable<CartProductListResponse> getProductsCartData(String cartId, String customerId) {
        return interceptor.get().getProductListCart(cartId, customerId);
    }
}

package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart;

import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.Product;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.cart_product_list.CartProductListResponse;
import io.reactivex.Observable;

public interface ShoppingCartMVP {

    interface View {
        void showProgress();
        void hideProgress();

        void setEmptyCartView();
        void setSuccessPurchaseView();

        void fillTotalList(int listPrice, int cardPrice, int quantity);

        void showResult(List<Product> products);
    }

    interface Presenter {
        void setCartView(ShoppingCartMVP.View view);
        void getProductsCatalog(String cartId, String customerId);
    }

    interface Model {
        Observable<CartProductListResponse> getProductsCartData(String cartId, String customerId);
    }
}

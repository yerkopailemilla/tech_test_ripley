package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.presenter;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.Product;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.ShoppingCartMVP;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.ShoppingCartModel;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.cart_product_list.CartProductListResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCartPresenter implements ShoppingCartMVP.Presenter {

    @Nullable
    private ShoppingCartMVP.View view;
    private ShoppingCartMVP.Model model;

    public ShoppingCartPresenter(ShoppingCartMVP.View view) {
        this.view = view;
        this.model = new ShoppingCartModel(this);
    }

    @Override
    public void setCartView(ShoppingCartMVP.View view) {
        this.view = view;
    }

    @Override
    public void getProductsCatalog(String cartId, String customerId) {
        if (view != null){
            model.getProductsCartData(cartId, customerId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CartProductListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress();
                    }

                    @Override
                    public void onNext(CartProductListResponse cartProductListResponse) {
                        if (cartProductListResponse.getProducts().size() != 0){
                            int listPrice = 0; int cardPrice = 0;
                            view.showResult(cartProductListResponse.getProducts());
                            for (Product product: cartProductListResponse.getProducts()){
                                listPrice += product.getPrice();
                                cardPrice += product.getPriceDiscountRipley();
                            }
                            view.fillTotalList(listPrice, cardPrice, cartProductListResponse.getProducts().size());
                            view.hideProgress();
                        } else {
                            view.setEmptyCartView();
                            view.hideProgress();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();
                    }
                });
        }
    }
}

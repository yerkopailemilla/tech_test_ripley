package cl.yerkode.prueba_tecnica_ripley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.api.ShoppingCartInterceptor;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartRequest;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartResponse;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.view.CatalogActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private NewCartResponse cart;
    private ShoppingCartInterceptor shoppingCartInterceptor;

    public static final String CART_DATA = "cl.yerkode.prueba_tecnica_ripley.KEY.CART_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cart = new NewCartResponse();
        NewCartRequest newCart = new NewCartRequest();
        newCart.setCustomer_id(generateUUID());
        shoppingCartInterceptor = new ShoppingCartInterceptor();
        shoppingCartInterceptor
            .get()
            .createNewShoppingCart(newCart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<NewCartResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(NewCartResponse cartResponse) {
                    cart = cartResponse;
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    Intent toProductDetail = new Intent(SplashActivity.this, CatalogActivity.class);
                    toProductDetail.putExtra(CART_DATA, cart);
                    startActivity(toProductDetail);
                }
            });
    }

    public static String generateUUID() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

package cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.api;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartRequest;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartResponse;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.cart_product_list.CartProductListResponse;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartRequest;
import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartResponse;
import cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp.ChangeStatusCartEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ShoppingCartService {

    @GET("cart")
    Observable<CartProductListResponse> getProductListCart(
            @Query("cart_id") String cartId,
            @Query("customer_id") String customerId);

    @POST("cart")
    Observable<NewCartResponse> createNewShoppingCart(@Body NewCartRequest cartRequest);

    @PUT("cart")
    Observable<NewCartResponse> setCartStatus(@Body ChangeStatusCartEntity changeStatusCartEntity);

    @POST("product")
    Observable<AddProductCartResponse> addProductToCart(@Body AddProductCartRequest addProductCartRequest);

}

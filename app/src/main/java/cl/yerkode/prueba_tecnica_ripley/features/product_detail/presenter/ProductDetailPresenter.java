package cl.yerkode.prueba_tecnica_ripley.features.product_detail.presenter;

import android.support.annotation.Nullable;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.ProductDetailMVP;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.ProductDetailModel;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartRequest;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.add_product_to_cart.AddProductCartResponse;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailPresenter implements ProductDetailMVP.Presenter {

    @Nullable
    private ProductDetailMVP.View view;
    private ProductDetailMVP.Model model;

    public ProductDetailPresenter(@Nullable ProductDetailMVP.View view) {
        this.view = view;
        this.model = new ProductDetailModel(this);
    }

    @Override
    public void setProductDetailView(ProductDetailMVP.View view) {
        this.view = view;
    }

    @Override
    public void getProductDetail(CatalogEntity product) {
        if (view != null){
            model.getProductDetailData(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductDetailEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress("Detalle de producto", "Mostrando datos del producto");
                    }

                    @Override
                    public void onNext(ProductDetailEntity productDetailEntity) {
                        view.showProduct(productDetailEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.productDetailFailed();
                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();
                        view.productDetailSuccess();
                    }
                });
        }
    }

    @Override
    public void getProductToAdd(AddProductCartRequest addProductCartRequest) {
        if (view != null){
            model.addProductToCart(addProductCartRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AddProductCartResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress("Carrito de compras", "Añadiendo producto a tu carrito de compras");
                    }

                    @Override
                    public void onNext(AddProductCartResponse addProductCartResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.messageAddProductToCart("Ha ocurrido un error mientras se agregaba el producto al carrito");
                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();
                        view.messageAddProductToCart("Producto añadido correctamente");
                    }
                });
        }

    }

}

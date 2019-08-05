package cl.yerkode.prueba_tecnica_ripley.features.product_detail.presenter;

import android.support.annotation.Nullable;

import cl.yerkode.prueba_tecnica_ripley.features.product_detail.ProductDetailMVP;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.ProductDetailModel;
import cl.yerkode.prueba_tecnica_ripley.features.product_detail.model.entity.ProductDetailEntity;
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
    public void getProductDetail(String skuProduct) {
        if (view != null){
            model.getProductDetailData(skuProduct)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductDetailEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress();
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

}

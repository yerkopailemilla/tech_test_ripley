package cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.presenter;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.ShowProductCatalogMVP;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.ShowProductsCatalogModel;
import cl.yerkode.prueba_tecnica_ripley.features.show_product_catalog.model.entity.CatalogEntity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShowProductsCatalogPresenter implements ShowProductCatalogMVP.Presenter {

    @Nullable
    private ShowProductCatalogMVP.View view;
    private ShowProductCatalogMVP.Model model;
    private List<CatalogEntity> productList;

    public ShowProductsCatalogPresenter(@Nullable ShowProductCatalogMVP.View view) {
        this.view = view;
        this.model = new ShowProductsCatalogModel(this);
        this.productList = new ArrayList<>();
    }

    @Override
    public void setCatalogView(ShowProductCatalogMVP.View view) {
        this.view = view;
    }

    @Override
    public void getProductsCatalog(String sku) {
        if (view != null){
            model.getCatalogData(sku)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CatalogEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showProgress();
                    }

                    @Override
                    public void onNext(List<CatalogEntity> catalogEntities) {
                        if (catalogEntities != null){
                            productList = catalogEntities;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.productsCatalogFailed();
                    }

                    @Override
                    public void onComplete() {
                        view.showCatalog(productList);
                        view.hideProgress();
                        view.productsCatalogSuccess();
                    }
                });
        }
    }

}

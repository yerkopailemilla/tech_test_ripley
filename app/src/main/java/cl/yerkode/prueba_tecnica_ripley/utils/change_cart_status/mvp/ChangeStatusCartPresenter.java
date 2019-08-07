package cl.yerkode.prueba_tecnica_ripley.utils.change_cart_status.mvp;

import cl.yerkode.prueba_tecnica_ripley.features.shopping_cart.model.entity.new_cart.NewCartResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangeStatusCartPresenter implements ChangeCartStatusMVP.Presenter {

    @Nullable
    private ChangeCartStatusMVP.View view;
    private ChangeCartStatusMVP.Model model;

    public ChangeStatusCartPresenter(ChangeCartStatusMVP.View view) {
        this.view = view;
        this.model = new ChangeStatusCartModel(this);
    }

    @Override
    public void setCartStatus(ChangeStatusCartEntity cartStatus) {
        if (view != null) {
            model.getCatalogData(cartStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewCartResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewCartResponse newCartResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
    }
}

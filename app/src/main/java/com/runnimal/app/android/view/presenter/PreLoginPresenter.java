package com.runnimal.app.android.view.presenter;

import com.runnimal.app.android.service.MediaService;
import com.runnimal.app.android.service.OwnerService;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class PreLoginPresenter extends Presenter<PreLoginPresenter.View> {

    private OwnerService ownerService;


    @Inject
    public PreLoginPresenter(MediaService mediaService, OwnerService ownerService) {
        super(mediaService);
        this.ownerService = ownerService;
    }

    public void login(String email, String password) {
        ownerService.login(email, password, //
                new DisposableObserver<String>() {

                    @Override
                    public void onNext(String id) {
                        getView().loginOk(id);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }

    public interface View extends Presenter.View {

        void loginOk(String token);
    }
}

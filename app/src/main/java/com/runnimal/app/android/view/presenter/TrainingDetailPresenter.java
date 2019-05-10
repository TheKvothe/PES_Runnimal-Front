package com.runnimal.app.android.view.presenter;

import com.runnimal.app.android.domain.Training;
import com.runnimal.app.android.service.TrainingService;
import com.runnimal.app.android.view.viewmodel.TrainingViewModel;
import com.runnimal.app.android.view.viewmodel.converter.TrainingViewModelConverter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import lombok.Setter;

public class TrainingDetailPresenter extends Presenter<TrainingDetailPresenter.View> {

    private TrainingService trainingService;
    @Setter
    private String trainingId;

    @Inject
    public TrainingDetailPresenter(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public void initialize() {
        super.initialize();
        getView().showLoading();
        trainingService.get(trainingId,
                new DisposableObserver<Training>() {

                    @Override
                    public void onNext(Training training) {
                        TrainingViewModel trainingViewModel = TrainingViewModelConverter.convert(training);
                        getView().showTraining(trainingViewModel);
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

        void showTraining(TrainingViewModel training);
    }
}

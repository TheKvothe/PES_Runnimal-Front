package com.runnimal.app.android.view.presenter;

import com.runnimal.app.android.domain.Training;
import com.runnimal.app.android.service.MediaService;
import com.runnimal.app.android.service.TrainingService;
import com.runnimal.app.android.util.ConverterUtils;
import com.runnimal.app.android.view.viewmodel.TrainingViewModel;
import com.runnimal.app.android.view.viewmodel.converter.TrainingViewModelConverter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TrainingsPresenter extends Presenter<TrainingsPresenter.View> {

    private TrainingService trainingService;

    @Inject
    public TrainingsPresenter(MediaService mediaService, TrainingService trainingService) {
        super(mediaService);
        this.trainingService = trainingService;
    }

    @Override
    public void initialize() {
        super.initialize();
        getView().showLoading();
        trainingService.list(new DisposableObserver<List<Training>>() {

            @Override
            public void onNext(List<Training> trainings) {
                List<TrainingViewModel> trainingViewModels = ConverterUtils.convert(trainings, TrainingViewModelConverter::convert);
                getView().showTrainingList(trainingViewModels);
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

    public void onTrainingClicked(TrainingViewModel training) {
        getView().openTrainingScreen(training);
    }

    public interface View extends Presenter.View {

        void showTrainingList(List<TrainingViewModel> trainingList);

        void openTrainingScreen(TrainingViewModel training);
    }
}

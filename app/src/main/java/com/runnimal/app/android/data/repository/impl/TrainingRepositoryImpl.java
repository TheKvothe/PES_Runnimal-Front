package com.runnimal.app.android.data.repository.impl;

import com.runnimal.app.android.data.api.RunnimalApi;
import com.runnimal.app.android.data.repository.TrainingRepository;
import com.runnimal.app.android.domain.Training;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class TrainingRepositoryImpl implements TrainingRepository {

    private final RunnimalApi api;

    @Inject
    public TrainingRepositoryImpl(RunnimalApi api) {
        this.api = api;
    }

    public Observable<List<Training>> list() {
        return Observable.create(emitter -> {
            api.getTrainings(new RunnimalApi.RunnimalApiCallback<List<Training>>() {
                @Override
                public void responseOK(List<Training> trainings) {
                    emitter.onNext(trainings);
                    emitter.onComplete();
                }

                @Override
                public void responseError(Exception e) {
                    emitter.onError(e);
                }
            });
        });
    }
}

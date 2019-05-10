package com.runnimal.app.android.di.modules;

import com.runnimal.app.android.data.api.RunnimalApi;
import com.runnimal.app.android.data.api.impl.RunnimalApiImpl;
import com.runnimal.app.android.data.repository.PetsRepository;
import com.runnimal.app.android.data.repository.TrainingsRepository;
import com.runnimal.app.android.data.repository.impl.PetsRepositoryImpl;
import com.runnimal.app.android.data.repository.impl.TrainingsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;

@Module
@RequiredArgsConstructor
public class RepositoryModule {

    @Provides
    @Singleton
    RunnimalApi runnimalApi(RunnimalApiImpl runnimalApi) {
        return runnimalApi;
    }

    @Provides
    @Singleton
    TrainingsRepository trainingRepository(TrainingsRepositoryImpl trainingsRepository) {
        return trainingsRepository;
    }

    @Provides
    @Singleton
    PetsRepository petsRepository(PetsRepositoryImpl petsRepository) {
        return petsRepository;
    }

}

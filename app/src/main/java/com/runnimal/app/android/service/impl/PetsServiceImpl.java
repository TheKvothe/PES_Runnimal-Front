package com.runnimal.app.android.service.impl;

import com.runnimal.app.android.data.repository.PetsRepository;
import com.runnimal.app.android.domain.Pet;
import com.runnimal.app.android.service.AbstractService;
import com.runnimal.app.android.service.PetsService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class PetsServiceImpl extends AbstractService implements PetsService {

    private final PetsRepository petsRepository;

    @Inject
    public PetsServiceImpl(@Named("executor_thread") Scheduler executorThread, //
                           @Named("ui_thread") Scheduler uiThread, //
                           PetsRepository petsRepository) {
        super(executorThread, uiThread);
        this.petsRepository = petsRepository;
    }

    @Override
    public void list(DisposableObserver<List<Pet>> callback) {
        execute(petsRepository.list(), callback);
    }

    @Override
    public void get(String id, DisposableObserver<Pet> callback) {
        execute(petsRepository.get(id), callback);
    }

    @Override
    public void modify(Pet pet, DisposableObserver<String> callback) {
        execute(petsRepository.modify(pet), callback);
    }

    @Override
    public void create(Pet pet, DisposableObserver<Pet> callback) {
        execute(petsRepository.create(pet), callback);
    }

}

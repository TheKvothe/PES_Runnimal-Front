package com.runnimal.app.android.data.repository;

import com.runnimal.app.android.domain.Training;

import java.util.List;

import io.reactivex.Observable;

public interface TrainingRepository {

    Observable<List<Training>> list();
}

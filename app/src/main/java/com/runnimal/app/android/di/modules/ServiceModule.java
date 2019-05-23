package com.runnimal.app.android.di.modules;

import com.runnimal.app.android.service.FriendsService;
import com.runnimal.app.android.service.FriendshipService;
import com.runnimal.app.android.service.MediaService;
import com.runnimal.app.android.service.OwnerService;
import com.runnimal.app.android.service.PetService;
import com.runnimal.app.android.service.PointService;
import com.runnimal.app.android.service.RankingService;
import com.runnimal.app.android.service.SearchService;
import com.runnimal.app.android.service.TrainingService;
import com.runnimal.app.android.service.WalkService;
import com.runnimal.app.android.service.impl.FriendsServiceImpl;
import com.runnimal.app.android.service.impl.FriendshipServiceImpl;
import com.runnimal.app.android.service.impl.MediaServiceImpl;
import com.runnimal.app.android.service.impl.OwnerServiceImpl;
import com.runnimal.app.android.service.impl.PetServiceImpl;
import com.runnimal.app.android.service.impl.PointServiceImpl;
import com.runnimal.app.android.service.impl.RankingServiceImpl;
import com.runnimal.app.android.service.impl.SearchServiceImpl;
import com.runnimal.app.android.service.impl.TrainingServiceImpl;
import com.runnimal.app.android.service.impl.WalkServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;

@Module
@RequiredArgsConstructor
public class ServiceModule {

    @Provides
    @Singleton
    MediaService mediaService(MediaServiceImpl mediaService) {
        return mediaService;
    }

    @Provides
    @Singleton
    OwnerService ownerService(OwnerServiceImpl ownerService) {
        return ownerService;
    }

    @Provides
    @Singleton
    PetService petService(PetServiceImpl petService) {
        return petService;
    }

    @Provides
    @Singleton
    RankingService rankingService(RankingServiceImpl rankingService) {
        return rankingService;
    }

    @Provides
    @Singleton
    TrainingService trainingService(TrainingServiceImpl trainingService) {
        return trainingService;
    }

    @Provides
    @Singleton
    FriendsService friendsService(FriendsServiceImpl friendsService) {
        return friendsService;
    }

    @Provides
    @Singleton
    SearchService searchService(SearchServiceImpl searchService) {
        return searchService;
    }

    @Provides
    @Singleton
    PointService pointService(PointServiceImpl pointService) {
        return pointService;
    }

    @Provides
    @Singleton
    FriendshipService friendshipService(FriendshipServiceImpl friendshipService) {
        return friendshipService;
    }

    @Provides
    @Singleton
    WalkService walkService(WalkServiceImpl walkService) {
        return walkService;
    }

}

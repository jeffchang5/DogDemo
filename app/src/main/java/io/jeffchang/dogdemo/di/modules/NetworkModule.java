package io.jeffchang.dogdemo.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.jeffchang.dogdemo.network.remote.DogAPIService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Provides dependency injection for networking singletons.
 */

@Module
public class NetworkModule {

    private final String BASE_URL = "https://dog.ceo/api/";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    DogAPIService provideDogAPIService(Retrofit retrofit) {
        return retrofit.create(DogAPIService.class);
    }
}

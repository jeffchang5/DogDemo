package io.jeffchang.dogdemo.ui.doglist;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.jeffchang.dogdemo.network.remote.DogAPIService;
import io.jeffchang.dogdemo.ui.doglist.presenter.DogBreedListPresenter;
import io.jeffchang.dogdemo.ui.doglist.presenter.DogBreedListPresenterImpl;
import io.jeffchang.dogdemo.ui.doglist.view.DogBreedListView;
import io.jeffchang.dogdemo.ui.doglist.view.DogBreedListFragment;

/**
 * Provides dependencies for map fragment module.
 */

@Module
abstract class DogListFragmentModule {

    @Binds
    abstract DogBreedListView provideDogBreedListView(DogBreedListFragment dogBreedListFragment);

    @Provides
    static DogBreedListPresenter provideDogListPresenter(DogBreedListView view,
                                                         DogAPIService dogAPIService) {
        return new DogBreedListPresenterImpl(view, dogAPIService);
    }
}
package io.jeffchang.dogdemo.ui.dogdetail;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.jeffchang.dogdemo.ui.dogdetail.presenter.DogDetailPresenter;
import io.jeffchang.dogdemo.ui.dogdetail.presenter.DogDetailPresenterImpl;
import io.jeffchang.dogdemo.ui.dogdetail.view.DogDetailFragment;
import io.jeffchang.dogdemo.ui.dogdetail.view.DogDetailView;

/**
 * Provides dependencies for map fragment module.
 */

@Module
abstract class DogDetailFragmentModule {

    @Binds
    abstract DogDetailView provideDogDetailFragment(DogDetailFragment dogDetailFragment);

    @Provides
    static DogDetailPresenter provideDogDetailPresenter() {
        return new DogDetailPresenterImpl();
    }
}
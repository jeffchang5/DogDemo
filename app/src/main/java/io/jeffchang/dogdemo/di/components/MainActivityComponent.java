package io.jeffchang.dogdemo.di.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.jeffchang.dogdemo.ui.MainActivity;

/**
 * Component that injects dependencies into [MainActivity] such as
 * its Presenter and View contracts.
 */

@Subcomponent
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}
}
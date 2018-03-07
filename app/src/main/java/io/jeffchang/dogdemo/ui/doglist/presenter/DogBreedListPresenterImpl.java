package io.jeffchang.dogdemo.ui.doglist.presenter;

import io.jeffchang.dogdemo.network.remote.DogAPIService;
import io.jeffchang.dogdemo.ui.doglist.view.DogBreedListView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Implementation of dog list presenter.
 */

public class DogBreedListPresenterImpl implements DogBreedListPresenter {

    private DogAPIService dogAPIService;
    private DogBreedListView dogBreedListView;

    public DogBreedListPresenterImpl(DogBreedListView dogBreedListView,
                                     DogAPIService dogAPIService) {
        this.dogBreedListView = dogBreedListView;
        this.dogAPIService = dogAPIService;
    }

    @Override
    public void getDogBreedList() {
        dogAPIService.getDogBreedResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dogListResponse) -> {
                    dogBreedListView.onDogBreedListSuccess(dogListResponse);
                }, (error -> dogBreedListView.onDogBreedListFailure(error) ));
    }
}

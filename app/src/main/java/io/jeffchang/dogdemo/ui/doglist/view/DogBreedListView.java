package io.jeffchang.dogdemo.ui.doglist.view;

import io.jeffchang.dogdemo.models.DogListResponse;

/**
 * View contract for the list fragment.
 */

public interface DogBreedListView {

    void onDogBreedListSuccess(DogListResponse dogListResponse);

    void onDogBreedListFailure(Throwable error);
}
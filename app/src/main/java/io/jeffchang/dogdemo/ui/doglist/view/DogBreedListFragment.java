package io.jeffchang.dogdemo.ui.doglist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.jeffchang.dogdemo.R;
import io.jeffchang.dogdemo.models.DogListResponse;
import io.jeffchang.dogdemo.network.local.FlatDogListItem;
import io.jeffchang.dogdemo.ui.common.BaseFragment;
import io.jeffchang.dogdemo.ui.common.LineItemDecoration;
import io.jeffchang.dogdemo.ui.doglist.presenter.DogBreedListPresenter;
import timber.log.Timber;

import javax.inject.Inject;

import static io.jeffchang.dogdemo.network.local.FlatDogListItem.DOG_BREED_TYPE;
import static io.jeffchang.dogdemo.network.local.FlatDogListItem.SUB_BREED_TYPE;

/**
 * Fragment that displays a list of dog breeds.
 */

public class DogBreedListFragment extends BaseFragment implements DogBreedListView {

    @Inject DogBreedListPresenter dogBreedListPresenter;
    private RecyclerView dogListRecyclerView;
    private DogBreedListRecyclerViewAdapter dogBreedListRecyclerViewAdapter;

    public static DogBreedListFragment newInstance() {
        Bundle args = new Bundle();
        DogBreedListFragment fragment = new DogBreedListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dogListRecyclerView = findParentViewById(R.id.list_fragment_recycler_view);
        showProgressBar("Loading Dog Breeds...");
        dogBreedListPresenter.getDogBreedList();
    }

    @Override
    public void onDogBreedListSuccess(DogListResponse dogListResponse) {
        dogListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<FlatDogListItem> flatDogListItems
                = convertToFlatMap(dogListResponse.getMessage());
        dogListRecyclerView.addItemDecoration(new LineItemDecoration(getContext(), flatDogListItems));

        dogBreedListRecyclerViewAdapter = new DogBreedListRecyclerViewAdapter(getContext(),
                flatDogListItems);
        dogListRecyclerView.setAdapter(dogBreedListRecyclerViewAdapter);
        showMainContent();
    }

    @Override
    public void onDogBreedListFailure() {

    }

    private ArrayList<FlatDogListItem> convertToFlatMap(Map<String, List<String>> dogBreeds) {
        ArrayList<FlatDogListItem> flatDogListItem = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: dogBreeds.entrySet()) {
            List<String> subcategories = entry.getValue();
            if (subcategories.isEmpty()) {
                flatDogListItem.add(
                        new FlatDogListItem(entry.getKey(), DOG_BREED_TYPE, true));
            } else {
                flatDogListItem.add(
                        new FlatDogListItem(entry.getKey(), DOG_BREED_TYPE, false));
                for (int i = 0; i < subcategories.size(); i++) {
                    if (i == (subcategories.size() - 1)) {
                        flatDogListItem.add(
                                new FlatDogListItem(
                                        subcategories.get(i),
                                        SUB_BREED_TYPE,
                                        true));
                        Timber.e("true %s", flatDogListItem.get(i).getValue());
                    }
                    else {
                        flatDogListItem.add(
                                new FlatDogListItem(
                                        subcategories.get(i),
                                        SUB_BREED_TYPE,
                                        false));
                        Timber.e("false %s", flatDogListItem.get(i).getValue());
                    }

                }
            }
        }
        return flatDogListItem;
    }
}

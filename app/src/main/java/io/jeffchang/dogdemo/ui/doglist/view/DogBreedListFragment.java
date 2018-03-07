package io.jeffchang.dogdemo.ui.doglist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.jeffchang.dogdemo.R;
import io.jeffchang.dogdemo.models.DogListResponse;
import io.jeffchang.dogdemo.network.local.FlatDogListItem;
import io.jeffchang.dogdemo.ui.common.BaseFragment;
import io.jeffchang.dogdemo.ui.common.LineItemDecoration;
import io.jeffchang.dogdemo.ui.doglist.presenter.DogBreedListPresenter;
import retrofit2.HttpException;

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
    public void onDogBreedListFailure(Throwable error) {
        if (error instanceof HttpException) {
            if (((HttpException) error).code() == 401) {
                Toast.makeText(getContext(), "Unauthorized", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Unknown error", Toast.LENGTH_LONG).show();
                }
            }
        else if (error instanceof UnknownHostException) {
            showErrorView(
                    R.drawable.ic_cloud_off_black_24dp,
                    getString(R.string.no_internet_connection), () -> {
                            showProgressBar(getString(R.string.loading_dog_breeds));
                            dogBreedListPresenter.getDogBreedList();
                    });
        }
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
                    }
                    else {
                        flatDogListItem.add(
                                new FlatDogListItem(
                                        subcategories.get(i),
                                        SUB_BREED_TYPE,
                                        false));
                    }

                }
            }
        }
        return flatDogListItem;
    }
}

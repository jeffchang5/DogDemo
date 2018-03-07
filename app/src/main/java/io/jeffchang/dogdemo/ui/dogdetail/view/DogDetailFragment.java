package io.jeffchang.dogdemo.ui.dogdetail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.jeffchang.dogdemo.R;

/**
 * Fragment that displays details about a dog breed.
 */

public class DogDetailFragment extends Fragment implements DogDetailView {

    public static DogDetailFragment newInstance() {
        Bundle args = new Bundle();
        DogDetailFragment fragment = new DogDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}

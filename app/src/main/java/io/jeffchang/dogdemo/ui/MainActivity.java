package io.jeffchang.dogdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;
import io.jeffchang.dogdemo.R;
import io.jeffchang.dogdemo.ui.dogdetail.view.DogDetailFragment;
import io.jeffchang.dogdemo.ui.doglist.view.DogBreedListFragment;

/**
 * Hosts our two main fragments.
 */

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToListFragment();
    }

    void goToDetailFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, DogDetailFragment.newInstance())
                .commit();
    }

    void goToListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, DogBreedListFragment.newInstance())
                .commit();
    }
}

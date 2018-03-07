package io.jeffchang.dogdemo.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import dagger.android.support.DaggerFragment;

/**
 * The base which Fragments in this project will extend.
 */

public abstract class BaseFragment extends DaggerFragment {

    private FrameLayout parentView;

    private ProgressBarView progressBarView;

    private ErrorView errorView;

    private View mainView;

    protected abstract int setLayoutResourceID();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        Context context = getContext();
        if (context != null) {
            parentView = new FrameLayout(context);
            parentView.setLayoutParams(params);

            progressBarView = new ProgressBarView(context);
            progressBarView.setLayoutParams(params);
            progressBarView.setVisibility(View.GONE);
            parentView.addView(progressBarView);

            errorView = new ErrorView(context);
            errorView.setLayoutParams(params);
            errorView.setVisibility(View.GONE);
            parentView.addView(errorView);

            mainView = inflater.inflate(setLayoutResourceID(), container, false);
            parentView.addView(mainView);
        }

        return parentView;
    }

    public <T extends View> T findParentViewById(int resId) {
        return mainView.findViewById(resId);
    }

    public void showProgressBar(String loadingText) {
        mainView.setVisibility(View.GONE);
        progressBarView.setLoadingText(loadingText);
        progressBarView.setVisibility(View.VISIBLE);
    }

    public void showMainContent() {
        progressBarView.setVisibility(View.GONE);
        mainView.setVisibility(View.VISIBLE);
    }

    public void showErrorView(int error, Runnable runnable) {
        runnable.run();
        progressBarView.setVisibility(View.GONE);
        mainView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}

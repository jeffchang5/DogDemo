package io.jeffchang.dogdemo.ui.doglist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.jeffchang.dogdemo.R;
import io.jeffchang.dogdemo.network.local.FlatDogListItem;
import io.jeffchang.dogdemo.utils.ResourceUtil;

import static io.jeffchang.dogdemo.network.local.FlatDogListItem.DOG_BREED_TYPE;
import static io.jeffchang.dogdemo.network.local.FlatDogListItem.SUB_BREED_TYPE;

/**
 * RecyclerView Adapter for dog breed list.
 */

public class DogBreedListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<FlatDogListItem> flatDogListItem;
    private Context context;

    DogBreedListRecyclerViewAdapter(Context context, ArrayList<FlatDogListItem> flatDogListItem) {
        this.context = context;
        this.flatDogListItem = flatDogListItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        switch (viewType) {
            case DOG_BREED_TYPE: {
                TextView dogBreedItem = (TextView) LayoutInflater.from(context)
                        .inflate(R.layout.dog_breed_header_item, parent, false);
                dogBreedItem.setLayoutParams(layoutParams);
                return new DogBreedViewHolder(dogBreedItem);
            }
            case SUB_BREED_TYPE: {
                TextView dogSubBreedItem = (TextView) LayoutInflater.from(context)
                        .inflate(R.layout.dog_subbreed_text_view, parent, false);
                dogSubBreedItem.setLayoutParams(layoutParams);
                return new DogSubBreedViewHolder(dogSubBreedItem);
            }
            default: {
                TextView dogBreedItem = (TextView) LayoutInflater.from(context)
                        .inflate(R.layout.dog_breed_header_item, parent, false);
                dogBreedItem.setLayoutParams(layoutParams);
                return new DogBreedViewHolder(dogBreedItem);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return flatDogListItem.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DogBreedViewHolder) {
            ((DogBreedViewHolder) holder).dogBreedItem.setText(
                    flatDogListItem.get(position).getValue());
        } else {
            DogSubBreedViewHolder dogSubBreedViewHolder = (DogSubBreedViewHolder) holder;
            ViewGroup.LayoutParams params
                    = dogSubBreedViewHolder.dogSubBreedItem.getLayoutParams();
            if (flatDogListItem.get(position).hasLine()) {
                ((ViewGroup.MarginLayoutParams) params).bottomMargin
                        = ResourceUtil.convertDpToPixels(context, 16);
            } else {
                ((ViewGroup.MarginLayoutParams) params).bottomMargin = 0;
            }
            dogSubBreedViewHolder.dogSubBreedItem.setText(
                    flatDogListItem.get(position).getValue());

        }
    }

    @Override
    public int getItemCount() {
        return flatDogListItem.size();
    }

    class DogSubBreedViewHolder extends RecyclerView.ViewHolder {

        final TextView dogSubBreedItem;

        DogSubBreedViewHolder(TextView dogSubBreedItem) {
            super(dogSubBreedItem);
            this.dogSubBreedItem = dogSubBreedItem;
        }
    }

    class DogBreedViewHolder extends RecyclerView.ViewHolder {

        final TextView dogBreedItem;

        DogBreedViewHolder(TextView dogBreedItem) {
            super(dogBreedItem);
            this.dogBreedItem = dogBreedItem;
        }
    }
}

package io.jeffchang.dogdemo.ui.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

import io.jeffchang.dogdemo.R;
import io.jeffchang.dogdemo.network.local.FlatDogListItem;
import io.jeffchang.dogdemo.utils.ResourceUtil;

/**
 * Created by jeffreychang on 3/7/18.
 */

public class LineItemDecoration extends RecyclerView.ItemDecoration {

    private final Drawable divider;
    private final Context context;
    private final ArrayList<FlatDogListItem> flatDogListItems;

    public LineItemDecoration(Context context, ArrayList<FlatDogListItem> flatDogListItems) {
        divider = context.getResources().getDrawable(R.drawable.line_item_decoration);
        this.context = context;
        this.flatDogListItems = flatDogListItems;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            FlatDogListItem flatDogListItem = flatDogListItems.get(
                    parent.getChildAdapterPosition(child));
            if (flatDogListItem.getViewType() == FlatDogListItem.DOG_BREED_TYPE
                    && flatDogListItem.hasLine()) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }

            if ((flatDogListItem.getViewType() == FlatDogListItem.SUB_BREED_TYPE)
                    && flatDogListItem.hasLine()) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
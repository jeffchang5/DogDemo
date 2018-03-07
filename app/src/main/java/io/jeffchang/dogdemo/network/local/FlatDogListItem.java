package io.jeffchang.dogdemo.network.local;

/**
 * Used to store dog categories and subcategories in a flat list.
 */

public class FlatDogListItem {

    public final static int DOG_BREED_TYPE = 0;
    public final static int SUB_BREED_TYPE = 1;

    private String value;

    private int viewType;

    private boolean hasLine;

    public FlatDogListItem(String value, int viewType, boolean hasLine) {
        this.value = value;
        this.viewType = viewType;
        this.hasLine = hasLine;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public boolean hasLine() {
        return hasLine;
    }

    public void setHasLine(boolean hasLine) {
        this.hasLine = hasLine;
    }
}

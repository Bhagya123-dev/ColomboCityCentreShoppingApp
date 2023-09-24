package com.example.colombocitycentreshoppingapp.domain.model;

import android.graphics.drawable.Drawable;

public class TrendingItem {

    Drawable imageName;
    String itemName;
    boolean isFavorite;

    String itemPrice;

    public TrendingItem(Drawable imageName, String itemName, boolean isFavorite, String itemPrice) {
        this.imageName = imageName;
        this.itemName = itemName;
        this.isFavorite = isFavorite;
        this.itemPrice = itemPrice;
    }

    public Drawable getImageName() {
        return imageName;
    }

    public void setImageName(Drawable imageName) {
        this.imageName = imageName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}

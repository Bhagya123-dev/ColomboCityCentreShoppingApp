package com.example.colombocitycentreshoppingapp.domain.model;

import android.graphics.drawable.Drawable;

public class Shops {

    Drawable shopImage;

    public Shops(Drawable shopImage) {
        this.shopImage = shopImage;
    }

    public Drawable getShopImage() {
        return shopImage;
    }

    public void setShopImage(Drawable shopImage) {
        this.shopImage = shopImage;
    }
}

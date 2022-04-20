package com.app.pricepal.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class prices_model {
    public int id;
    public int storeId;
    public int itemId;
    public String date;
    public double price;

    public prices_model(int id, int storeId, int itemId, String date, double price) {
        this.id = id;
        this.storeId = storeId;
        this.itemId = itemId;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}


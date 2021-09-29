package com.example.propertytracker.models;

import org.parceler.Parcel;

@Parcel
public class Property {
    String title;
    String description;
    String imageUri;
    Boolean occupied;
    String price;
    String id;

    public Property() {
        // Default constructor
    }

    public Property(String title, String description, String price) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.occupied = false;
        this.id = "";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public Property(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setId(String id) {
        this.id = id;
    }
}
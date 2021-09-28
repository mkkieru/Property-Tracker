package com.example.propertytracker.models;

public class Property {
    String title;
    String description;
    String imageUri;
    Boolean occupied;
    Double price;
    int pushId;

    public Property() {
        // Default constructor
    }

    public Property(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.occupied = false;
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

    public Double getPrice() {
        return price;
    }

    public int getPushId() {
        return pushId;
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setPushId(int pushId) {
        this.pushId = pushId;
    }
}
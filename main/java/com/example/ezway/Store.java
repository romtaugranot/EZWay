package com.example.ezway;

public class Store {

    private String id;
    private String name;
    private String phone;
    private String openingHours;
    private String closingHours;
    private double rating;
    private int rateCount;
    private String img;
    private String description;
    private Position position;

    public Store(String id, String name, String phone, String openingHours, String closingHours, double rating, int rateCount, String img, String description, Position position) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.rating = rating;
        this.rateCount = rateCount;
        this.img = img;
        this.description = description;
        this.position = position;
    }

    public Store() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

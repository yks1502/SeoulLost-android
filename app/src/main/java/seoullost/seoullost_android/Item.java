package seoullost.seoullost_android;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    private int id;
    @SerializedName("user")
    private User user;
    @SerializedName("created")
    private String created;
    @SerializedName("updated")
    private String updated;
    @SerializedName("title")
    private String title;
    @SerializedName("itemType")
    private String itemType;
    @SerializedName("acquiredDate")
    private String acquiredDate;
    @SerializedName("acquiredPlace")
    private String acquiredPlace;
    @SerializedName("lostPlace")
    private String lostPlace;
    @SerializedName("storagePlace")
    private String storagePlace;
    @SerializedName("color")
    private String color;
    @SerializedName("content")
    private String content;
    @SerializedName("isComplete")
    private boolean isComplete;
    @SerializedName("image")
    private String image;

    public Item(String title, String itemType, String acquiredDate, String lostPlace, String color, String content, String image) {
        this.title = title;
        this.itemType = itemType;
        this.acquiredDate = acquiredDate;
        this.lostPlace = lostPlace;
        this.color = color;
        this.content = content;
        this.image = image;
    }

    public Item(String title, String itemType, String acquiredDate, String acquiredPlace, String storagePlace, String color, String content, String image) {
        this.title = title;
        this.itemType = itemType;
        this.acquiredDate = acquiredDate;
        this.acquiredPlace = acquiredPlace;
        this.storagePlace = storagePlace;
        this.color = color;
        this.content = content;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getTitle() {
        return title;
    }

    public String getItemType() {
        return itemType;
    }

    public String getAcquiredDate() {
        return acquiredDate;
    }

    public String getAcquiredPlace() {
        return acquiredPlace;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public String getColor() {
        return color;
    }

    public String getContent() {
        return content;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getImage() {
        return image;
    }
}

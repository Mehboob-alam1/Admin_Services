package com.example.mazdoorapp;

public class Image {

    private String image;
    private String link;


    public Image(String image, String link) {
        this.image = image;
        this.link = link;
    }

    public Image() {
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

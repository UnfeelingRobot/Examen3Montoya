package com.example.examen3montoya;

public class PictureModel {
    private String pictureName;
    private String picturePhoto;

    public PictureModel(String pictureName, String picturePhoto) {
        this.pictureName = pictureName;
        this.picturePhoto = picturePhoto;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPicturePhoto() {
        return picturePhoto;
    }

    public void setPicturePhoto(String picturePhoto) {
        this.picturePhoto = picturePhoto;
    }
}

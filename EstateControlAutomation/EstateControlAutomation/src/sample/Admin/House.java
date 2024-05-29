package sample.Admin;

import javafx.scene.image.Image;

public class House {
    private String imagePath;
    private String year;
    private String heat;
    private String room;
    private String volume;
    private String type;
    private String village;
    private String city;
    private String price;
    private Image image;

    public House(){
        this.year = null;
        this.heat = null;
        this.room = null;
        this.volume = null;
        this.type = null;
        this.village = null;
        this.city = null;
        this.price = null;
        this.image = null;
    }
    public House(String year, String heat, String room, String volume, String type, String village, String city, String price, Image image) {
        this.year = year;
        this.heat = heat;
        this.room = room;
        this.volume = volume;
        this.type = type;
        this.village = village;
        this.city = city;
        this.price = price;
        this.image = image;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

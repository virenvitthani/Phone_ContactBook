package com.example.phone_contacts;

public class Contact_Model {

    int id;
    String name;
    String number;
    String imagepath;

    public Contact_Model(int id, String name, String number, String imagepath) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.imagepath = imagepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}

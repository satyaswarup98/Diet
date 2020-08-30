package com.example.satya.diet;

public class User {

    String name;
    boolean lunch;
    boolean dinner;


    public User(String name, boolean lunch, boolean dinner) {
        this.name = name;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean getDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }
}

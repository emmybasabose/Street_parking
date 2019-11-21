package com.example.street_parking;

/**
 * Created by User on 1/21/2017.
 */

public class Streets {

    private int id;
    private String name;

    public Streets(int id, String name) {
        this.id = id;
        this.name = name;
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
}

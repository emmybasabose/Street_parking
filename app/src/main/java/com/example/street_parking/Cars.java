package com.example.street_parking;

public class Cars {
    private int id;
    private String nmb_plate;
    private String status;

    public Cars() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmb_plate() {
        return nmb_plate;
    }

    public void setNmb_plate(String nmb_plate) {
        this.nmb_plate = nmb_plate;
    }
}

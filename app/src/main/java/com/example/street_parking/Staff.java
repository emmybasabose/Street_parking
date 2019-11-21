package com.example.street_parking;

public class Staff {
    int id;
    String name,tel;

    public Staff() {
    }

    public Staff(int id, String name, String tel) {
        this.id=id;
        this.name = name;
        this.tel = tel;
        setId(id);
        setName(name);
        setTel(tel);

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

package com.matheus.controlecaminhao.model;

import java.util.Date;

public class Expenses {
    private Integer id;
    private float amount;

    private String date;

    private String name;
    private Integer idTruck;
    public Expenses() {
    }

    public Expenses(Integer id, float amount, String date, String name,Integer idTruck) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.name = name;
        this.idTruck = idTruck;
    }

    public Expenses(float amount, String date, String name,Integer idTruck) {
        this.amount = amount;
        this.date = date;
        this.name = name;
        this.idTruck = idTruck;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdTruck() {
        return idTruck;
    }

    public void setIdTruck(Integer idTruck) {
        this.idTruck = idTruck;
    }
}

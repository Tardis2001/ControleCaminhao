package com.matheus.controlecaminhao.model;

public class Truck {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private int km;
    private String motorista;
    private String placa;

    public Truck() {
    }
    public Truck(String marca, String modelo, int ano, int km, String motorista, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.km = km;
        this.motorista = motorista;
        this.placa = placa;
    }
    public Truck(Integer id,String marca, String modelo, int ano, int km, String motorista, String placa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.km = km;
        this.motorista = motorista;
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", km=" + km +
                ", motorista='" + motorista + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}

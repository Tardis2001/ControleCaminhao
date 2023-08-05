package com.matheus.controlecaminhao.dao;

import com.matheus.controlecaminhao.factory.connection;
import com.matheus.controlecaminhao.model.Truck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class truckdao {
    Connection conn = null;
    public truckdao(){
        conn = connection.getConnection();
        createTable();
    }

    private void createTable() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS frota (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "marca TEXT NOT NULL," +
                    "modelo TEXT NOT NULL," +
                    "ano INTEGER NOT NULL," +
                    "placa TEXT UNIQUE NOT NULL," +
                    "motorista TEXT NOT NULL," +
                    "km TEXT NOT NULL" +
                    ")";
            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarVeiculo(Truck truck) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO frota (marca, modelo, ano, placa,km,motorista) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, truck.getMarca());
            pstmt.setString(2, truck.getModelo());
            pstmt.setInt(3, truck.getAno());
            pstmt.setString(4, truck.getPlaca());
            pstmt.setInt(5, truck.getKm());
            pstmt.setString(6,truck.getMotorista());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Truck> getTruck() {
        List<Truck> trucks = new ArrayList<Truck>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM frota");
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                int km = rs.getInt("km");
                String motorista = rs.getString("motorista");
                String placa = rs.getString("placa");

                Truck veiculo =new Truck(id,
                        marca,
                        modelo,
                        ano,
                        km,
                        motorista,
                        placa);
                trucks.add(veiculo);

                return trucks;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return trucks;
    }
}

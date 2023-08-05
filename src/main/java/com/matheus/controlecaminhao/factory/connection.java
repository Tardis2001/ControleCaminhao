package com.matheus.controlecaminhao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static Connection connection;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:frota.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
    public connection() {
        // Construtor privado para evitar instanciação direta da classe
    }

}

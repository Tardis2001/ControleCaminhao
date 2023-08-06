package com.matheus.controlecaminhao.dao;

import com.matheus.controlecaminhao.factory.connection;
import com.matheus.controlecaminhao.model.Expenses;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class expensedao {

    private Connection conn = null;
    public expensedao(){

        conn = connection.getConnection();
        createTable();
    }

    public void createTable() {

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount REAL,"+
                    "date TEXT NOT NULL," +
                    "name TEXT NOT NULL,id_caminhao INTEGER NOT NULL," +
                    "FOREIGN KEY (id_caminhao) REFERENCES truck (id) " + // Chave estrangeira referenciando a tabela caminhões
                    ")";
            stmt.execute(sql);
            stmt.execute("PRAGMA foreign_keys = ON;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Expenses> getAllExpenses(){
        List<Expenses> expenses = new ArrayList<>();
        try{
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM expenses");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idGasto = rs.getInt("id");
                int idVeiculo = rs.getInt("id_caminhao");
                float valor = rs.getFloat("amount");
                String name = rs.getString("name");
                String data = rs.getString("date");

                Expenses gasto = new Expenses(idGasto,valor,data,name,idVeiculo);
                expenses.add(gasto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
    public List<Expenses> getExpensesForTruck(int truckId) {
        List<Expenses> expenses = new ArrayList<>();
        try{
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM expenses WHERE id_caminhao = ?");
            pstmt.setInt(1, truckId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idGasto = rs.getInt("id");
                int idVeiculo = rs.getInt("id_caminhao");
                float valor = rs.getFloat("amount");
                String name = rs.getString("name");
                String data = rs.getString("date");

                Expenses gasto = new Expenses(idGasto,valor,data,name,idVeiculo);
                expenses.add(gasto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
    public void adicionarExpense(Expenses expense) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO expenses (amount, date, name,id_caminhao) VALUES (?, ?, ?, ?)");
            pstmt.setFloat(1, expense.getAmount());
            pstmt.setString(2, String.valueOf(expense.getDate()));
            pstmt.setString(3, expense.getName());
            pstmt.setInt(4,expense.getIdTruck());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getTotalGastosMesAtual(int idVeiculo) {
        double totalGastos = 0.0;
        try {
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT SUM(amount) FROM expenses WHERE id_caminhao = ? AND strftime('%Y-%m', date) = ?");
            // Obter o primeiro dia do mês atual
            LocalDate hoje = LocalDate.now();
            int iniciodoano = hoje.getYear();
            Month inicioMes = hoje.getMonth();
            String mestmp = "";
            switch (inicioMes) {
                case JANUARY -> mestmp = "01";
                case FEBRUARY -> mestmp = "02";
                case MARCH -> mestmp = "03";
                case APRIL -> mestmp = "04";
                case MAY -> mestmp = "05";
                case JUNE -> mestmp = "06";
                case JULY -> mestmp = "07";
                case AUGUST -> mestmp = "08";
                case SEPTEMBER -> mestmp = "09";
                case OCTOBER -> mestmp = "10";
                case NOVEMBER -> mestmp = "11";
                case DECEMBER -> mestmp = "12";
            }
            String data = Integer.toString(iniciodoano) + "-" + mestmp;
            // Definir o ID do veículo e o primeiro dia do mês para a consulta SQL
            pstmt.setInt(1, idVeiculo);
            pstmt.setString(2, data);
            System.out.println(pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalGastos = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalGastos;
    }
    public double getTotalGastosfromTruck(int idVeiculo){
        double totalGastos = 0.0;
        try{
            PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(amount) FROM expenses WHERE id_caminhao = ?");
            pstmt.setInt(1, idVeiculo);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                totalGastos = rs.getDouble(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return totalGastos;
    }
    public double getTotalGastos() {
        double totalGastos = 0.0;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(amount) FROM expenses");
            if (rs.next()) {
                totalGastos = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalGastos;
    }
    public double getExpensesForWeekTruck(int truckId) {
        List<Expenses> expenses = new ArrayList<>();
        double gastos = 0;
        try {
//             PreparedStatement pstmt = conn.prepareStatement(
//                     "SELECT SUM(amount) FROM expenses WHERE id_caminhao = ? AND date(date) >= date(?, 'weekday 0', '-7 days') AND date(date) <= date(?, 'weekday 6', '-7 days')");
       PreparedStatement pstmt = conn.prepareStatement("  SELECT SUM(amount) FROM expenses WHERE id_caminhao = ? AND date(date) >= date('now', 'weekday 0', '-7 days') AND date(date) <= date('now', 'weekday 6', '-7 days')\n");

                    // Obter as datas de início e fim da semana atual
            LocalDate hoje = LocalDate.now();
            TemporalField firstDayOfWeek = WeekFields.of(Locale.getDefault()).dayOfWeek();
            LocalDate inicioSemana = hoje.with(firstDayOfWeek, 1);
            LocalDate fimSemana = hoje.with(firstDayOfWeek, 7);

            // Definir o ID do veículo e os limites de data para a consulta SQL
            pstmt.setInt(1, truckId);
//            pstmt.setString(2, fimSemana.toString()); // fim da semana atual
//            pstmt.setString(3, inicioSemana.toString()); // início da semana atual

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                gastos = rs.getDouble(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }


}
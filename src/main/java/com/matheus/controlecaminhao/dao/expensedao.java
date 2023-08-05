package com.matheus.controlecaminhao.dao;

import com.matheus.controlecaminhao.factory.connection;
import com.matheus.controlecaminhao.model.Expenses;
import com.matheus.controlecaminhao.model.Truck;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "CREATE TABLE IF NOT EXISTS Gastos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount REAL,"+
                    "date TEXT NOT NULL," +
                    "name TEXT NOT NULL," +
                    "id_caminhao INTEGER REFERENCES frota (id) " + // Chave estrangeira referenciando a tabela caminhões
                    ");";
            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Expenses> getExpensesForTruck(int truckId) {
        List<Expenses> expenses = new ArrayList<>();
        try{
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM gastos WHERE id_caminhao = ?");
            pstmt.setInt(1, truckId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idGasto = rs.getInt("id");
                int idVeiculo = rs.getInt("id_caminhao");
                float valor = rs.getFloat("amount");
                String name = rs.getString("name");
                String data = rs.getString("data");

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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Gastos (amount, date, name,id_caminhao) VALUES (?, ?, ?, ?)");
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
                     "SELECT SUM(valor) FROM gastos WHERE id_veiculo = ? AND strftime('%Y-%m', data) = ?");
            // Obter o primeiro dia do mês atual
            LocalDate hoje = LocalDate.now();
            LocalDate inicioMes = hoje.withDayOfMonth(1);

            // Definir o ID do veículo e o primeiro dia do mês para a consulta SQL
            pstmt.setInt(1, idVeiculo);
            pstmt.setString(2, inicioMes.toString());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalGastos = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalGastos;
    }
}

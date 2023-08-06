package com.matheus.controlecaminhao.controller;

import com.matheus.controlecaminhao.Main;
import com.matheus.controlecaminhao.dao.expensedao;
import com.matheus.controlecaminhao.model.Expenses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterExpenseController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    expensedao expensedao;
    @FXML
    private TextField amountfield;

    @FXML
    private DatePicker datefield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField caminhaofield;
    @FXML
    void sendtoDashboard(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));

        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void ListTrucks(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ListTruck.fxml"));

        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void RegisterTruck(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterTruck.fxml"));

        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void RegisterExpenses(MouseEvent event) {
        expensedao.adicionarExpense(new Expenses(Float.parseFloat(amountfield.getText()),datefield.getValue().toString(),namefield.getText(),Integer.parseInt(caminhaofield.getText())));

    }

    @FXML
    void refresh(ActionEvent event) {

    }

    @FXML
    void register(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expensedao = new expensedao();
    }
}

package com.matheus.controlecaminhao.controller;


import com.matheus.controlecaminhao.Main;
import com.matheus.controlecaminhao.dao.truckdao;
import com.matheus.controlecaminhao.model.Truck;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterTruckController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private truckdao truckdao;

    @FXML
    private MFXTextField kmfield;

    @FXML
    private MFXTextField marcafield;

    @FXML
    private MFXTextField motoristafield;
    @FXML
    private MFXTextField anofield;
    @FXML
    private MFXTextField placafield;
    @FXML
    private MFXTextField modelofield;
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
    private void RegisterExpenses(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("registerExpenses.fxml"));

        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void RegisterTruck(MouseEvent event) {
        truckdao.adicionarVeiculo(new Truck(marcafield.getText(),
                modelofield.getText(),
                Integer.parseInt(anofield.getText()),
                Integer.parseInt(kmfield.getText()),
                motoristafield.getText(),
                placafield.getText()));
    }

    @FXML
    private void sendtoDashboard(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));

        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        truckdao = new truckdao();
    }
}
package com.matheus.controlecaminhao.controller;

import com.matheus.controlecaminhao.Main;
import com.matheus.controlecaminhao.model.Expenses;
import com.matheus.controlecaminhao.model.Truck;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.matheus.controlecaminhao.dao.truckdao;
import com.matheus.controlecaminhao.dao.expensedao;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
public class ListController implements Initializable {

    @FXML
    private Label despesaMes;

    @FXML
    private MFXTextField searchdespesas;

    @FXML
    private TableColumn<Truck, Integer> tableano;

    @FXML
    private TableColumn<Expenses, Integer> tableidCaminhao;

    @FXML
    private MFXLegacyTableView<Truck> tablecaminhoes;

    @FXML
    private TableColumn<Truck, String> tabledata;

    @FXML
    private MFXLegacyTableView<Expenses> tabledespesas;

    @FXML
    private TableColumn<Expenses, Integer> tableid;

    @FXML
    private TableColumn<Truck, Integer> tableidcaminhao;

    @FXML
    private TableColumn<Truck, Integer> tablekm;

    @FXML
    private TableColumn<Truck, String> tablemodelo;

    @FXML
    private TableColumn<Truck, String> tablemotorista;

    @FXML
    private TableColumn<Expenses, String> tablenome;

    @FXML
    private TableColumn<Truck, String> tableplaca;

    @FXML
    private TableColumn<Expenses, Integer> tablevalor;

    @FXML
    private Label total;
    @FXML
    private Label despesaSemana;
    private expensedao expensedao;
    private truckdao truckdao;
    private ObservableList<Expenses> expensesList = FXCollections.observableArrayList();
    private ObservableList<Truck> trucklist = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button voltarbt;
    @FXML
    void voltar(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));

        Parent paneToAdd = fxmlLoader.load();
        Scene scene = voltarbt.getScene();
        paneToAdd.translateXProperty().set(-scene.getWidth());
        parentContainer.getChildren().add(paneToAdd);

        //Create new TimeLine animation
        Timeline timeline = new Timeline();
        //Animate X property
        KeyValue kv = new KeyValue(paneToAdd.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
        timeline.getKeyFrames().add(kf);
        //After completing animation, remove first scene
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    void searchexpense(){
        if(searchdespesas.getText().equals("")) {
            refreshTable();


            total.setText(String.valueOf(expensedao.getTotalGastos()));
        }else {
            expensesList.clear();
            expensesList.addAll(expensedao.getExpensesForTruck(Integer.parseInt(searchdespesas.getText())));
            tabledespesas.setItems(expensesList);
            despesaMes.setText(String.valueOf(expensedao.getTotalGastosMesAtual(Integer.parseInt(searchdespesas.getText()))));

            despesaSemana.setText(String.valueOf(expensedao.getExpensesForWeekTruck(Integer.parseInt(searchdespesas.getText()))));

            total.setText(String.valueOf(expensedao.getTotalGastosfromTruck(Integer.parseInt(searchdespesas.getText()))));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        truckdao = new truckdao();
        expensedao = new expensedao();

        tabledata.setCellValueFactory(new PropertyValueFactory<Truck,String>("date"));
        tableano.setCellValueFactory(new PropertyValueFactory<Truck,Integer>("ano"));
        tablekm.setCellValueFactory(new PropertyValueFactory<Truck,Integer>("km"));
        tablemodelo.setCellValueFactory(new PropertyValueFactory<Truck,String>("modelo"));
        tablemotorista.setCellValueFactory(new PropertyValueFactory<Truck,String>("motorista"));
        tableplaca.setCellValueFactory(new PropertyValueFactory<Truck,String>("placa"));
        tablevalor.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("amount"));
        tableid.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("id"));
        tablenome.setCellValueFactory(new PropertyValueFactory<Expenses,String>("Name"));
        tableidCaminhao.setCellValueFactory(new PropertyValueFactory<Expenses,Integer>("idTruck"));
        tableidcaminhao.setCellValueFactory(new PropertyValueFactory<Truck,Integer>("id"));

//        tablecaminhoes.getColumns().addAll(tableano,tableidcaminhao,tablekm,tablemodelo,tablemodelo,tableplaca);
        refreshTable();


    }
    public void refreshTable(){
        trucklist.clear();
        trucklist.addAll(truckdao.getTruck());
        tablecaminhoes.setItems(trucklist);

        expensesList.clear();
        expensesList.addAll(expensedao.getAllExpenses());
        tabledespesas.setItems(expensesList);
        total.setText(String.valueOf(expensedao.getTotalGastos()));

    }
}
package com.matheus.controlecaminhao.controller;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.matheus.controlecaminhao.Main;
import com.matheus.controlecaminhao.dao.expensedao;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;
    private StackPane stackPane;
    @FXML Button listTrucks;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    expensedao expensedao;
    @FXML
    void ListTrucks(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ListTruck.fxml"));

        Parent paneToAdd = fxmlLoader.load();
        Scene scene = listTrucks.getScene();
        paneToAdd.translateXProperty().set(scene.getWidth());
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
    void RegisterExpenses(MouseEvent event) throws IOException {

    }

    @FXML
    void RegisterTruck(MouseEvent event) throws IOException {

    }

    @FXML
    void sendtoDashboard() {

    }
    @FXML
    void RemoveTruck(){

    }
    @FXML
    void Backup(){

    }
    @FXML
    void RemoverExpense(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackPane = new StackPane();
    }
}

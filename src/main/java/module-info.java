module com.matheus.controlecaminhao {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires MaterialFX;
    requires com.jfoenix;
    requires java.sql;
    opens com.matheus.controlecaminhao to javafx.fxml;
    exports com.matheus.controlecaminhao;
    opens com.matheus.controlecaminhao.controller to javafx.fxml;
    exports com.matheus.controlecaminhao.controller;
    opens com.matheus.controlecaminhao.model to javafx.base;
    exports com.matheus.controlecaminhao.model;
}
module com.matheus.controlecaminhao {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                requires org.kordamp.ikonli.javafx;
                requires eu.hansolo.tilesfx;
        
    opens com.matheus.controlecaminhao to javafx.fxml;
    exports com.matheus.controlecaminhao;
}
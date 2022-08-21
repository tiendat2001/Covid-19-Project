module com.example.code {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires com.jfoenix;

    opens com.example.code to javafx.fxml;
    exports com.example.code;
    exports com.example.code.controllers;
    exports com.example.code.database;
    exports com.example.code.models;
    opens com.example.code.controllers to javafx.fxml;
    exports com.example.code.controllers.addScreens;
    opens com.example.code.controllers.addScreens to javafx.fxml;
    exports com.example.code.controllers.editScreens;
    opens com.example.code.controllers.editScreens to javafx.fxml;
    opens com.example.code.models to javafx.fxml;
}
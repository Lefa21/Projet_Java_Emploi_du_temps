module fr.isep.frattesi.projetjavaedt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens fr.isep.frattesi.projetjavaedt to javafx.fxml;
    exports fr.isep.frattesi.projetjavaedt;
    exports fr.isep.frattesi.projetjavaedt.controller;
    opens fr.isep.frattesi.projetjavaedt.controller to javafx.fxml;
}
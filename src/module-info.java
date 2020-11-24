module medicalEMR {
	requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires javafx.media;
    requires java.sql;

    opens com.dtcc.emr;
    opens com.dtcc.emr.model;
    opens com.dtcc.emr.service;
}
package com.dtcc.emr;

import com.dtcc.emr.service.AddPatient;
import com.dtcc.emr.service.ShowAllPatients;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        TabPane tabPane=new TabPane();
        //All patients tab.
        Tab allPatients=new Tab("All Patients");
        allPatients.setText("All Patients");
        allPatients.setClosable(false);
        ShowAllPatients showAllPatients=new ShowAllPatients(tabPane);
        allPatients.setContent(showAllPatients.getAllPatients());

        //All Procedures Tab
        Tab allProcedures=new Tab();
        allProcedures.setText("All Procedures");
        allProcedures.setClosable(false);
        //ShowAllProcedures showAllProcedures=new ShowAllProcedures(tabPane);
       // allProcedures.setContent(showAllProcedures.getAllProcedures());

        Tab patientVisit=new Tab();
        patientVisit.setText("Patient Visit");
        patientVisit.setClosable(false);
       // PatientAppointment patientAppointment=new PatientAppointment();
       // patientVisit.setContent(patientAppointment.addPatientAppointment());

        Tab patient=new Tab();
        patient.setText("Patient");
        patient.setClosable(false);
         AddPatient addPatient=new AddPatient(tabPane);
         patient.setContent(addPatient.addNewPatient());

        Tab procedure=new Tab();
        procedure.setText("Procedure");
        procedure.setClosable(false);

        //add all tabs to the tabpane.
        tabPane.getTabs().addAll(allPatients,allProcedures,patientVisit,patient,procedure);

        //setting anchor pan as layout.
        AnchorPane pane = new AnchorPane();
        AnchorPane.setTopAnchor(tabPane, 15.0);
        AnchorPane.setRightAnchor(tabPane, 15.0);
        AnchorPane.setBottomAnchor(tabPane, 15.0);
        AnchorPane.setLeftAnchor(tabPane, 15.0);
        pane.getChildren().addAll(tabPane);

        //setting the stage
        Scene scene=new Scene(pane,1100,650);
        stage.setTitle("EMR Patients Data");
        stage.setScene(scene);
        stage.show();

    }
}

package com.dtcc.emr.service;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.sql.SQLException;

public class ShowAllPatients{

    private TabPane tabPane;
    private TableView table;
    private PatientOperations po=new PatientOperations();

    public ShowAllPatients(){}

    public  ShowAllPatients(TabPane tabPane){
        this.tabPane=tabPane;
    }

    public Node getAllPatients() throws SQLException {

        if(this.tabPane!=null) {
            this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
                @Override
                public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                    try{
                        if(tabPane.getSelectionModel().getSelectedIndex()==0){
                            refreshTable();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        Button addPatientBtn=new Button("Add New Patient");
        addPatientBtn.setOnAction((ActionEvent event) -> {
            this.tabPane.getSelectionModel().select(3);;
            try {
                refreshTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        table=po.refreshPatientTable();
        //refreshTable();
        table.setMinWidth(1000);
        table.setMinHeight(550);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //UPDATE BUTTON IN THE TABLE
        updateButtonToTable();

        //DELETE BUTTON IN THE TABLE
        deleteButtonToTable();

        VBox vbox = new VBox();
        vbox.setPrefWidth(1000);
        vbox.setPrefHeight(600);
        vbox.setFillWidth(true);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(addPatientBtn);
        vbox.getChildren().addAll(table);
        return vbox;
    }

    public void updateButtonToTable(){
        TableColumn<PatientsInformation, Void> updateCol = new TableColumn("Update Patient");
        updateCol.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<PatientsInformation, Void>, TableCell<PatientsInformation, Void>> cellFactory = new Callback<TableColumn<PatientsInformation, Void>, TableCell<PatientsInformation, Void>>() {
            public TableCell<PatientsInformation, Void> call(final TableColumn<PatientsInformation, Void> param) {
                final TableCell<PatientsInformation, Void> cell = new TableCell <PatientsInformation, Void>() {
                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            PatientsInformation data = getTableView().getItems().get(getIndex());
                            //System.out.println("ON UPDATE BUTTON CLICK: " + data);
                            PatientOperations updatePatient=new PatientOperations(data);
                            AddPatient addPatient=new AddPatient(tabPane,data);
                            tabPane.getTabs().get(3).setContent(addPatient.addNewPatient());
                            tabPane.getSelectionModel().select(3);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        updateCol.setCellFactory(cellFactory);
        table.getColumns().add(updateCol);
    }

    private void deleteButtonToTable(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        TableColumn<PatientsInformation, Void> deleteCol = new TableColumn("Delete Patient");
        deleteCol.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<PatientsInformation, Void>, TableCell<PatientsInformation, Void>> cellFactory = new Callback<>() {

            public TableCell<PatientsInformation, Void> call(final TableColumn<PatientsInformation, Void> param) {
                final TableCell<PatientsInformation, Void> cell = new TableCell <PatientsInformation, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            PatientsInformation data = getTableView().getItems().get(getIndex());
                            alert.setTitle("DELETE PATIENT RECORD");
                            //CONFIRM WITH THE USER, IF USER WANTS TO DELETE THE RECORD.
                            alert.setContentText("Are you sure you want to delete the patient record?");
                            if (alert.showAndWait().get() == ButtonType.OK){
                                PatientOperations delPatient=new PatientOperations(data);
                                try {
                                    //DELETE THE PATIENT FROM THE DATABASE.
                                    delPatient.deletePatient();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                table.getItems().remove(getIndex());
                            }
                            else {System.out.println("DO NOT DELETE THE RECORD"); }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) { setGraphic(null); }
                        else { setGraphic(deleteButton); }
                    }
                };
                return cell;
            }
        };

        deleteCol.setCellFactory(cellFactory);
        table.getColumns().add(deleteCol);
    }

    //METHOD TO REFRESH THE TABLE
    void refreshTable() throws SQLException {
        final ObservableList<PatientsInformation> items = PatientOperations.getAllPatientsList();
        if( items == null || items.size() == 0) return;
        PatientsInformation item= (PatientsInformation) table.getItems().get(0);
        items.remove(0);

        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                items.add(0, item);
                table.setItems(items);
            }
        });
    }
}
package com.dtcc.emr.service;

import com.dtcc.emr.model.Procedure;
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

public class ShowAllProcedures {


    private TabPane tabPane;
    private TableView table;
    private ProcedureOperations pro=new ProcedureOperations();


    public ShowAllProcedures(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public Node getAllProcedures() throws SQLException {

        if(this.tabPane!=null) {
            this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
                @Override
                public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                    try{
                        if(tabPane.getSelectionModel().getSelectedIndex()==1){
                            refreshTable();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        Button addProcedureBtn=new Button("Add New Procedure");
        addProcedureBtn.setOnAction((ActionEvent event) -> {
            this.tabPane.getSelectionModel().select(4);;
            try {
                refreshTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        table=pro.refreshProcedureTable();
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
        vbox.getChildren().add(addProcedureBtn);
        vbox.getChildren().addAll(table);
        return vbox;
    }

    public void updateButtonToTable(){
        TableColumn<Procedure, Void> updateCol = new TableColumn("Update Procedure");
        updateCol.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<Procedure, Void>, TableCell<Procedure, Void>> cellFactory = new Callback<TableColumn<Procedure, Void>, TableCell<Procedure, Void>>() {
            public TableCell<Procedure, Void> call(final TableColumn<Procedure, Void> param) {
                final TableCell<Procedure, Void> cell = new TableCell <Procedure, Void>() {
                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Procedure data = getTableView().getItems().get(getIndex());
                            System.out.println("ON UPDATE BUTTON CLICK: " + data);
                            ProcedureOperations updateProcedure=new ProcedureOperations(data);
                            AddProcedure addProcedure=new AddProcedure(tabPane,data);
                            tabPane.getTabs().get(4).setContent(addProcedure.addNewProcedure());
                            tabPane.getSelectionModel().select(4);

                            try {
                                refreshTable();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
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
        TableColumn<Procedure, Void> deleteCol = new TableColumn("Delete Procedure");
        deleteCol.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<Procedure, Void>, TableCell<Procedure, Void>> cellFactory = new Callback<>() {

            public TableCell<Procedure, Void> call(final TableColumn<Procedure, Void> param) {
                final TableCell<Procedure, Void> cell = new TableCell <Procedure, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Procedure data = getTableView().getItems().get(getIndex());
                            alert.setTitle("DELETE PROCEDURE");
                            //CONFIRM WITH THE USER, IF USER WANTS TO DELETE THE RECORD.
                            alert.setContentText("Are you sure you want to delete the procedure?");
                            if (alert.showAndWait().get() == ButtonType.OK){
                                ProcedureOperations delProcedure=new ProcedureOperations(data);
                                try {
                                    //DELETE THE PATIENT FROM THE DATABASE.
                                    delProcedure.deleteProcedure();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                table.getItems().remove(getIndex());
                            }
                            else {System.out.println("DO NOT DELETE THE PROCEDURE"); }
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
        final ObservableList<Procedure> items = ProcedureOperations.getAllProcedureList();
        if( items == null || items.size() == 0) return;
        Procedure item= (Procedure) table.getItems().get(0);
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

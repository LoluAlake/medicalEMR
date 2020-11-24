package com.dtcc.emr.service;

import com.dtcc.emr.model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowAllPatients{

   private TabPane tabPane;
    private ObservableList data;
    private TableView table;

    public  ShowAllPatients(TabPane tabPane){
        this.tabPane=tabPane;
    }
    public Node getAllPatients() throws SQLException {
        Button addPatientBtn=new Button("Add New Patient");
       // addPatientBtn.setOnAction();
        addPatientBtn.setOnAction((ActionEvent event) -> {
            this.tabPane.getSelectionModel().select(3);
            System.out.println("Add Patient Tab selected" );
        });

        table=new TableView();
        data = getInitialTableData();
        table.setItems(data);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("first_name"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("last_name"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn accountCol = new TableColumn("Account Number");
        accountCol.setCellValueFactory(new PropertyValueFactory("accountnumber"));

        TableColumn dobCol = new TableColumn("DOB");
        dobCol.setCellValueFactory(new PropertyValueFactory("dob"));

      /*  TableColumn addressCol = new TableColumn("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));
*/
       // table.getColumns().addAll(firstNameCol,lastNameCol,emailCol,accountCol,dobCol,addressCol);
        table.getColumns().addAll(firstNameCol,lastNameCol,emailCol,accountCol,dobCol);
        updateButtonToTable();
        deleteButtonToTable();
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(addPatientBtn);
        vbox.getChildren().addAll(table);
        // vbox.getChildren().addAll(hb, table);
        return vbox;
    }

    private void addNewPatient(){

    }
    private ObservableList getInitialTableData() throws SQLException {
        List<Patient> list = new ArrayList<>();
        //Database conection.
        Connection con=DatabaseConnection.getConnection();
        Statement stmt=con.createStatement();
        String sql="select * from patient where logicalDelete=0";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            int patientId=rs.getInt("patientId");
            String first_name=rs.getString("first_name");
            String last_name=rs.getString("last_name");
            String email=rs.getString("email");
            int accountnumber=rs.getInt("accountnumber");
            Date dob=rs.getDate("dob");
           // String address=rs.getString("address1");
          //  System.out.println("the address "+address);
           // list.add(new Patient(patientId,firstName,lastName,email,account,dob,address));
            list.add(new Patient(patientId,first_name,last_name,email,accountnumber,dob));
        }

        ObservableList data= FXCollections.observableList(list);
        return data;
    }

    private void updateButtonToTable(){
        TableColumn<Patient, Void> updateCol = new TableColumn("Update Patient");
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Patient data = getTableView().getItems().get(getIndex());
                            System.out.println("ON UPDATE BUTTON CLICK: " + data);
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
        TableColumn<Patient, Void> deleteCol = new TableColumn("Delete Patient");
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<>() {

            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Patient data = getTableView().getItems().get(getIndex());
                            System.out.println("On DELETE BUTTON CLICK: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
                return cell;
            }
        };

        deleteCol.setCellFactory(cellFactory);

        table.getColumns().add(deleteCol);
    }
}

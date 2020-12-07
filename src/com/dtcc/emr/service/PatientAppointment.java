package com.dtcc.emr.service;

import com.dtcc.emr.model.PatientHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.sql.*;
import java.util.Date;

public class PatientAppointment {
    private Label lblPatientVisit;
    private Label lblPatientName;
    private Label lblCptDescText;
    private Label lblCptDesc;

    private ComboBox patientComboBox;
    private ComboBox doctorComboBox;
    private ComboBox cptComboBox;

    private TextField purposeField;
    private DatePicker datePicker;
    private Button submitButton;
    private Button resetButton;
    private TableView<PatientHistoryInformation>visitTable;
    ObservableList data= FXCollections.observableArrayList();

    public TableColumn<PatientHistoryInformation,String> firstNameCol;
    public TableColumn <PatientHistoryInformation,String> lastNameCol;
    public TableColumn <PatientHistoryInformation,Integer> accountCol;
    public TableColumn <PatientHistoryInformation,String>  purposeCol;
    public TableColumn <PatientHistoryInformation,String>  cptCol;
    public TableColumn <PatientHistoryInformation,String> descCol;
    public TableColumn<PatientHistoryInformation, Date> appDateCol;
    public TableColumn<PatientHistoryInformation,Date> nextAppCol;


    ObservableList<PatientHistoryInformation> patientData= FXCollections.observableArrayList();
    ObservableList cptData= FXCollections.observableArrayList();
    PatientOperations pi;
    PatientHistoryOperations pho;

    public PatientAppointment(){}
    public Node addPatientAppointment() throws SQLException {
        BorderPane borderPane=new BorderPane();

        GridPane gridPane=new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        lblPatientVisit=new Label("Patient Visit");
        lblPatientVisit.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(lblPatientVisit,  0,0,4,1);
        gridPane.setHalignment(lblPatientVisit, HPos.CENTER);
        gridPane.setMargin(lblPatientVisit, new Insets(10, 0,10,0));

        lblPatientName=new Label("Patient :");
        gridPane.add(lblPatientName, 0,1);

        patientData=pi.getAllPatientsNames();
        patientComboBox=new ComboBox(patientData);
        //patientComboBox.setItems(patientData);
        gridPane.add(patientComboBox, 1,1);

        Label lblCpt = new Label("CPT Code: ");
        gridPane.add(lblCpt,2,1);

        cptData=pi.getAllCPTCodes();
        cptComboBox=new ComboBox();
        cptComboBox.setItems(cptData);
        gridPane.add(cptComboBox,3,1);

        //2nd ROW
        Label lblPurpose = new Label("Purpose of the visit : ");
        gridPane.add(lblPurpose,0,2);

        purposeField = new TextField();
        purposeField.setPrefHeight(40);
        purposeField.setText("");
        gridPane.add(purposeField,1,2);

        lblCptDesc=new Label("CPT Desc : ");
        gridPane.add(lblCptDesc,2,2);

        lblCptDescText=new Label("");
        gridPane.add(lblCptDescText,3,2);

        Label lblNextAppointment = new Label("Next Appointment : ");
        gridPane.add(lblNextAppointment,0,3);

        datePicker= new DatePicker();
        datePicker.getEditor().setPrefWidth(200);
        gridPane.add(datePicker,1,3);

        submitButton = new Button("Submit");
        submitButton.setPrefHeight(30);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 1, 4);
        gridPane.setHalignment(submitButton, HPos.CENTER);
        gridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        resetButton=new Button("Reset");
        resetButton.setPrefHeight(30);
        resetButton.setPrefWidth(100);
        gridPane.add(resetButton, 2, 4);

        HBox hBoxVisitList = new HBox();
        hBoxVisitList.setSpacing(5);
        hBoxVisitList.setPadding(new Insets(10,0,0,10));
        visitTable=new TableView();
        visitTable.setPrefWidth(1000);
        visitTable.setPrefHeight(400);

        var ref = new Object() {
            ObservableList<PatientHistoryInformation> data = pi.getPatientHistory(0);
        };

        firstNameCol=new TableColumn<>("First Name");
        firstNameCol.setMinWidth(75);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        lastNameCol=new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(75);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        accountCol=new TableColumn<>("Account Number");
        accountCol.setMaxWidth(100);
        accountCol.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

        appDateCol=new TableColumn<>("Date Of Visit");
        appDateCol.setMaxWidth(100);
        appDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfVisit"));

        purposeCol=new TableColumn<>("Purpose");
        purposeCol.setMinWidth(150);
        purposeCol.setCellValueFactory(new PropertyValueFactory<>("purpose"));

        cptCol=new TableColumn<>("CPT");
        cptCol.setMinWidth(50);
        cptCol.setCellValueFactory(new PropertyValueFactory<>("cpt"));

        nextAppCol=new TableColumn<>("Next Appointment");
        nextAppCol.setMinWidth(50);
        nextAppCol.setCellValueFactory(new PropertyValueFactory<>("nextAppointment"));

        descCol=new TableColumn<>("Description");
        descCol.setMinWidth(280);
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));

        visitTable.getColumns().addAll(firstNameCol,lastNameCol,accountCol,cptCol,appDateCol,purposeCol,nextAppCol,descCol);
        visitTable.setTableMenuButtonVisible(true);
        visitTable.setItems(ref.data); //set the data into tableview.

        resetButton.setOnAction(e->{
            resetFields();
        });

        //PATIENT COMBO BOX LISTENER
        patientComboBox.valueProperty().addListener((obv,t,t1)->{
            if(patientComboBox.getValue()==null){}
            else{
                PatientHistoryInformation ph= (PatientHistoryInformation) patientComboBox.getSelectionModel().getSelectedItem();
                data.clear();
                ref.data =pi.getPatientHistory(ph.getPatientId());
                visitTable.setItems(ref.data);
            }
        });

        //CPT COMBOBOX LISTENER
        cptComboBox.valueProperty().addListener((obv, t, t1)->{
            if(cptComboBox.getValue()==null){ }
            else{setCPTDescription(cptComboBox.getValue().toString());}
        });

        //SUBMIT BUTTON ACTION
        submitButton.setOnAction(e->{
            boolean isValid= validateAllFields();
            if(isValid){
               addPatientHistoryInDatabase();
            }
        });

        //updateButtonToTable(); //UPDATE BUTTON IN THE TABLE
        deleteButtonToTable(); //DELETE BUTTON IN THE TABLE

        hBoxVisitList.setPadding(new Insets(0,10,10,0));
        hBoxVisitList.setAlignment(Pos.CENTER);
        hBoxVisitList.getChildren().add(visitTable);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(hBoxVisitList);

        return borderPane;
    }

    private void deleteButtonToTable(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        TableColumn<PatientHistoryInformation, Void> deleteCol = new TableColumn("Delete");
        deleteCol.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<PatientHistoryInformation, Void>, TableCell<PatientHistoryInformation, Void>> cellFactory = new Callback<>() {

            public TableCell<PatientHistoryInformation, Void> call(final TableColumn<PatientHistoryInformation, Void> param) {
                final TableCell<PatientHistoryInformation, Void> cell = new TableCell <PatientHistoryInformation, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction((ActionEvent event) -> {
                            PatientHistoryInformation data = getTableView().getItems().get(getIndex());
                            alert.setTitle("DELETE PATIENT HISTORY RECORD");
                            //CONFIRM WITH THE USER, IF USER WANTS TO DELETE THE RECORD.
                            alert.setContentText("Are you sure you want to delete the record?");
                            if (alert.showAndWait().get() == ButtonType.OK){

                                try {
                                    //DELETE THE PATIENT HISTORY RECORD
                                    pho=new PatientHistoryOperations();
                                    pho.deletePatientHistoryData(data);
                                } catch (Exception e) {
                                    e.printStackTrace();
                               }
                                visitTable.getItems().remove(getIndex());
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
        visitTable.getColumns().add(deleteCol);
    }

    public void resetFields(){
        patientComboBox.getSelectionModel().clearSelection();
        patientComboBox.setValue(null);

        cptComboBox.getSelectionModel().clearSelection();
        cptComboBox.setValue(null);

        purposeField.setText("");

        datePicker.getEditor().clear();
        datePicker.setValue(null);
        datePicker.setPromptText("");

        lblCptDescText.setText("");

        data.clear();
        data =pi.getPatientHistory(0);
        visitTable.setItems(data);
    }

    public void setCPTDescription(String cpt){
        String cptDesc=null;
        Connection con=null;
        try {
        con=DatabaseConnection.getConnection();
        String getCptDesc="select description from procedures where cpt='"+cpt+"'";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(getCptDesc);
            while(rs.next()){
                cptDesc=rs.getString("description");
            }
            lblCptDescText.setText(cptDesc);
            rs.close();
            con.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public boolean validateAllFields(){
        boolean valid=true;
        if(patientComboBox.getSelectionModel().isEmpty()){
            System.out.println("no value is selected for the patient combo box.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setWidth(200);
            alert.setContentText("Please select Patient");
            alert.show();
            return false;
        }
        if(cptComboBox.getSelectionModel().isEmpty()){
            System.out.println("no value is selected for the CPT combo box.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setWidth(200);
            alert.setContentText("Please select CPT code.");
            alert.show();
            return false;
        }
        return valid;
    }

    public void refreshTable(){
        data.clear();
        data =pi.getPatientHistory(0);
        visitTable.setItems(data);
    }
    public void addPatientHistoryInDatabase(){
        PatientHistoryInformation ph= (PatientHistoryInformation) patientComboBox.getSelectionModel().getSelectedItem();
        int procedureId=getProcedureId();
        PatientHistory pHistory=new PatientHistory();
        pHistory.setPatientId(ph.getPatientId());
        pHistory.setProcedureId(procedureId);
        pHistory.setPurpose(purposeField.getText()==null? "" :purposeField.getText());
        pHistory.setNextappointment(datePicker.getValue()==null? null : datePicker.getValue());
        pho=new PatientHistoryOperations();
        pho.addPatientHistoryData(pHistory);

        //REFRESH THE TABLE AFTER DATA IS ADDED
        data.clear();
        data=pi.getPatientHistory(ph.getPatientId());
        visitTable.setItems(data);
    }
    private int getProcedureId(){
        int procedureId=0;
        Connection con=DatabaseConnection.getConnection();
        String sql="select procedureId from procedures where cpt=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, cptComboBox.getValue().toString());
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                procedureId=rs.getInt("procedureId");
                System.out.println("the id is "+procedureId);
            }
            rs.close();
            con.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return procedureId;
    }
}

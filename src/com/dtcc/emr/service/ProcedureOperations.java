package com.dtcc.emr.service;

import com.dtcc.emr.model.Procedure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ProcedureOperations {
    private Procedure procedure;

    private int procedureId;
    private String name;
    //private static ResultSet rs;
    //TableColumn<Person, String> column1
    public TableColumn<Procedure,Integer> procedureIdCol;
    public TableColumn <Procedure,Integer> cptCol;
    public TableColumn <Procedure,String>  descriptionCol;
    public TableColumn <Procedure,String> nameCol;
    public TableColumn <Procedure,Double> costCol;
    //private TableView<Person> table = new TableView<Person>();
    public TableView<Procedure> table;
    Connection dbConnection;

    public ProcedureOperations() {

    }

    public ProcedureOperations(Procedure procedure) {
        this.procedure = procedure;
    }

    public void deleteProcedure() throws SQLException {
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "Update Procedures set logicalDelete=1 where procedureId=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, procedure.getProcedureId());
            stmt.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addNewProcedure(Procedure procedure) {

        int procedureID = 0;

        try {
            dbConnection = DatabaseConnection.getConnection();
            String addProcedureSql = "insert into procedures values(?,?,?,?,?,?)";
            PreparedStatement psProcedure = dbConnection.prepareStatement(addProcedureSql);
            psProcedure.setInt(1, 0);
            psProcedure.setString(2, procedure.getCpt());
            psProcedure.setString(3, procedure.getDescription());
            psProcedure.setString(4, procedure.getName());
            psProcedure.setDouble(5, procedure.getCost());
            psProcedure.setInt(6, 0);
            psProcedure.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProcedure(Procedure procedure) throws SQLException {

        dbConnection = DatabaseConnection.getConnection();

        String updateProcedureSql = "update procedures set cpt=?, description=?,name=?, cost=? where procedureId=?";
        PreparedStatement psUpdateProcedure=dbConnection.prepareStatement(updateProcedureSql);
        psUpdateProcedure.setString(1, procedure.getCpt());
        psUpdateProcedure.setString(2, procedure.getDescription());
        psUpdateProcedure.setString(3, procedure.getName());
        psUpdateProcedure.setDouble(4, procedure.getCost());
        psUpdateProcedure.setInt(5, procedure.getProcedureId());


        psUpdateProcedure.executeUpdate();

    }

    public static ObservableList<Procedure> getAllProcedureList() throws SQLException {
        ObservableList<Procedure> list = FXCollections.observableArrayList();
        Connection conObj = DatabaseConnection.getConnection();
        String sql1 = "select * from procedures where logicalDelete=0";
        Statement stmt1 = conObj.createStatement();
        ResultSet rsList = stmt1.executeQuery(sql1);

        while (rsList.next()) {
            String gender = null;

            int procedureId = rsList.getInt("procedureId");
            String cpt = rsList.getString("cpt");
            String description = rsList.getString("description");
            String name = rsList.getString("name");
            double cost = rsList.getInt("cost");

            list.add(new Procedure(procedureId,cpt, description, name, cost));
        }

        rsList.close();
        conObj.close();

        return list;
    }

    public TableView refreshProcedureTable() throws SQLException {
        ObservableList<Procedure> data=getAllProcedureList();
        table=new TableView<Procedure>();

        cptCol = new TableColumn<>("CPT");
        cptCol.setCellValueFactory(new PropertyValueFactory<>("cpt"));

        descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        costCol = new TableColumn<>("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));


        table.getColumns().addAll(cptCol,descriptionCol,nameCol,costCol);
        table.setItems(data);
        return table;

    }
}

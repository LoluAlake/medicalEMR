package com.dtcc.emr.service;

import com.dtcc.emr.model.PatientHistory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PatientHistoryOperations {

    public PatientHistoryOperations(){}

    public void addPatientHistoryData(PatientHistory ph) {
        try{
            Connection dbConnection = DatabaseConnection.getConnection();
            String patientHistorySql = "insert into patienthistory values(?,?,?,?,?,?)";
            PreparedStatement ps = dbConnection.prepareStatement(patientHistorySql);
            ps.setInt(1, ph.getPatientId());
            ps.setInt(2, ph.getProcedureId());
            ps.setString(3, ph.getPurpose());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            if(ph.getNextappointment()==null){ps.setDate(5,null);}
            else{ ps.setDate(5,Date.valueOf(ph.getNextappointment()));}
            ps.setInt(6, 0);
            ps.execute();

            ps.close();
            dbConnection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void deletePatientHistoryData(PatientHistoryInformation ph){
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "Update Patienthistory set logicalDelete=1 where patientId=? and procedureId=? and purpose=? and dateofvisit=? and nextappointment=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ph.getPatientId());
            stmt.setInt(2, ph.getProcedureId());
            stmt.setString(3, ph.getPurpose());
            if(ph.getDateOfVisit()==null){stmt.setDate(4, null);}
            else{stmt.setDate(4, Date.valueOf(ph.getDateOfVisit()));}

            if(ph.getNextAppointment()==null){stmt.setDate(5, null);}
            else{stmt.setDate(5, Date.valueOf(ph.getNextAppointment()));}
            stmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.dtcc.emr.service;

import com.dtcc.emr.model.PatientHistory;

import java.sql.Date;
import java.time.LocalDate;

public class PatientHistoryInformation{
    private int patientId;
    private int procedureId;
    private String firstName;
    private String lastName;
    private int accountNumber;
    private String cpt;
    private String desc;
    private double cost;
    private String purpose;
    private LocalDate dateOfVisit;
    private LocalDate nextAppointment;
    private String comboPatientName;

    public PatientHistoryInformation(){}

    public PatientHistoryInformation(int patientId, int procedureId, int accountnumber, String firstName,
                                     String lastName, String cpt, String desc, double cost, String purpose, LocalDate dateOfVisit, LocalDate nextAppointment) {

    this.patientId=patientId;
    this.procedureId=procedureId;
    this.accountNumber=accountnumber;
    this.firstName=firstName;
    this.lastName=lastName;
    this.cpt=cpt;
    this.desc=desc;
    this.cost=cost;
    this.purpose=purpose;
    this.dateOfVisit=dateOfVisit;
    this.nextAppointment=nextAppointment;
    }

    public PatientHistoryInformation(int patientId, String firstName, String lastName, int accountNumber){
     this.patientId=patientId;
     this.firstName=firstName;
     this.lastName=lastName;
     this.accountNumber=accountNumber;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCpt() {
        return cpt;
    }

    public void setCpt(String cpt) {
        this.cpt = cpt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(LocalDate nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    @Override
    public String toString() {
        return  firstName +" "+ lastName+" -- "+accountNumber;
    }
}

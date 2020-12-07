package com.dtcc.emr.model;

import java.time.LocalDate;
import java.util.Date;

public class PatientHistory {
    private int patientId;
    private int procedureId;
    private String purpose;
    private LocalDate dateofvisit;
    private LocalDate nextappointment;

    public PatientHistory() {
    }

    public PatientHistory(int patientId, int procedureId, String purpose, LocalDate dateofvisit, LocalDate nextappointment) {
        this.patientId = patientId;
        this.procedureId = procedureId;
        this.purpose = purpose;
        this.dateofvisit = dateofvisit;
        this.nextappointment = nextappointment;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDate getDateofvisit() {
        return dateofvisit;
    }

    public void setDateofvisit(LocalDate dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    public LocalDate getNextappointment() {
        return nextappointment;
    }

    public void setNextappointment(LocalDate nextappointment) {
        this.nextappointment = nextappointment;
    }

    @Override
    public String toString() {
        return "PatientHistory{" +
                "patientId=" + patientId +
                ", procedureId=" + procedureId +
                ", purpose='" + purpose + '\'' +
                ", dateofvisit=" + dateofvisit +
                ", nextappointment=" + nextappointment +
                '}';
    }
}

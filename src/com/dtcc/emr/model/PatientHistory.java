package com.dtcc.emr.model;

import java.util.Date;

public class PatientHistory {
    private int patientId;
    private int procedureId;
    private int doctorId;
    private int addressId;
    private String purpose;
    private Date dateofvisit;
    private Date nextappointment;

    public PatientHistory() {
    }

    public PatientHistory(int patientId, int procedureId, int doctorId, int addressId, String purpose, Date dateofvisit, Date nextappointment) {
        this.patientId = patientId;
        this.procedureId = procedureId;
        this.doctorId = doctorId;
        this.addressId = addressId;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDateofvisit() {
        return dateofvisit;
    }

    public void setDateofvisit(Date dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    public Date getNextappointment() {
        return nextappointment;
    }

    public void setNextappointment(Date nextappointment) {
        this.nextappointment = nextappointment;
    }

    @Override
    public String toString() {
        return "PatientHistory{" +
                "patientId=" + patientId +
                ", procedureId=" + procedureId +
                ", doctorId=" + doctorId +
                ", addressId=" + addressId +
                ", purpose='" + purpose + '\'' +
                ", dateofvisit=" + dateofvisit +
                ", nextappointment=" + nextappointment +
                '}';
    }
}

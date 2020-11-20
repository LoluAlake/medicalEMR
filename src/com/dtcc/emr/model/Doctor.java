package com.dtcc.emr.model;

public class Doctor {
    private int doctorId;
    private String first_name;
    private String last_name;
    private int addressId;

    public Doctor() {
    }

    public Doctor(int doctorId, String first_name, String last_name, int addressId) {
        this.doctorId = doctorId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.addressId = addressId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}

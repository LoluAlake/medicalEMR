package com.dtcc.emr.model;

import java.util.Date;

public class Patient {
    private int patientId;
    private int accountnumber;
    private int addressId;
    private String first_name;
    private String last_name;
    private Date dob;
    private char gender;
    private double height;
    private double weight;
    private String email;
    private String phonenumber;
    private String maritalstatus;
    private Long ssn;
    private String emergencyname;
    private String emergencycontact;
    private String employmentstatus;
    private String medicalhistory;
    private String allegies;
    private String medicines;
    private Date createdDate;


    public Patient() {
    }

    public Patient(int patientId, int accountnumber, int addressId1, String first_name, String last_name, Date dob, char gender, double height, double weight, String email, String phonenumber, String maritalstatus, Long ssn, String emergencyname, String emergencycontact, String employmentstatus, String medicalhistory, String allegies, String medicines, Date createdDate) {
        this.patientId = patientId;
        this.accountnumber = accountnumber;
        this.addressId = addressId1;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.phonenumber = phonenumber;
        this.maritalstatus = maritalstatus;
        this.ssn = ssn;
        this.emergencyname = emergencyname;
        this.emergencycontact = emergencycontact;
        this.employmentstatus = employmentstatus;
        this.medicalhistory = medicalhistory;
        this.allegies = allegies;
        this.medicines = medicines;
        this.createdDate = createdDate;
    }

    //list.add(new Patient(patientId,firstName,lastName,email,account,dob,address));
    public Patient(int patientId,String first_name, String last_name, String email, int accountnumber, Date dob) {
        this.patientId=patientId;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.accountnumber=accountnumber;
        this.dob=dob;
        //this.address=address;

    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public String getEmergencyname() {
        return emergencyname;
    }

    public void setEmergencyname(String emergencyname) {
        this.emergencyname = emergencyname;
    }

    public String getEmergencycontact() {
        return emergencycontact;
    }

    public void setEmergencycontact(String emergencycontact) {
        this.emergencycontact = emergencycontact;
    }

    public String getEmploymentstatus() {
        return employmentstatus;
    }

    public void setEmploymentstatus(String employmentstatus) {
        this.employmentstatus = employmentstatus;
    }

    public String getMedicalhistory() {
        return medicalhistory;
    }

    public void setMedicalhistory(String medicalhistory) {
        this.medicalhistory = medicalhistory;
    }

    public String getAllegies() {
        return allegies;
    }

    public void setAllegies(String allegies) {
        this.allegies = allegies;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", accountnumber=" + accountnumber +
                ", addressId=" + addressId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", maritalstatus='" + maritalstatus + '\'' +
                ", ssn=" + ssn +
                ", emergencyname='" + emergencyname + '\'' +
                ", emergencycontact='" + emergencycontact + '\'' +
                ", employmentstatus='" + employmentstatus + '\'' +
                ", medicalhistory='" + medicalhistory + '\'' +
                ", allegies='" + allegies + '\'' +
                ", medicines='" + medicines + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}

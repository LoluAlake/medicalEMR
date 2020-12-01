package com.dtcc.emr.service;

import java.util.Date;

public class PatientsInformation {
    private int patientId;
    private int accountnumber;
    private int addressId;
    private String first_name;
    private String last_name;
    private String doctor_first_name;
    private String doctor_last_name;
    private Date dob;
    private String gender;
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
    private String allergies;
    private String medicines;
    private Date createdDate;
    private String fullAddress;
    private String address1;
    private String address2;
    private String city;
    private String district;
    private String country;
    private String postalCode;


    public PatientsInformation() {
    }

     // ,ssn,height,weight,address1,address2,city,district,country,postalCode,emergencyname,emergencycontact,medicalhistory,allergies,medicines));

    public PatientsInformation(int patientId,int addressId,String first_name, String last_name,String email,int accountnumber, Date dob,String gender,
                               String phonenumber,String maritalstatus, String fullAddress, Long ssn,double height,double weight,String address1,String address2,String city,
                               String district, String country, String postalCode, String emergencyname,String emergencycontact,String medicalhistory,
                               String allergies,String medicines,String employmentstatus){
        this.patientId=patientId;
        this.addressId=addressId;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.phonenumber=phonenumber;
        this.accountnumber=accountnumber;
        this.dob=dob;
        this.gender=gender;
        this.phonenumber=phonenumber;
        this.maritalstatus=maritalstatus;
        this.fullAddress=fullAddress;
        this.ssn=ssn;
        this.height=height;
        this.weight=weight;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.postalCode=postalCode;
        this.country=country;
        this.district=district;
        this.emergencyname=emergencyname;
        this.emergencycontact=emergencycontact;
        this.employmentstatus=employmentstatus;
        this.medicalhistory=medicalhistory;
        this.allergies=allergies;
        this.medicines=medicines;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
        return allergies;
    }

    public void setAllegies(String allegies) {
        this.allergies = allegies;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getDoctor_first_name() {
        return doctor_first_name;
    }

    public void setDoctor_first_name(String doctor_first_name) {
        this.doctor_first_name = doctor_first_name;
    }

    public String getDoctor_last_name() {
        return doctor_last_name;
    }

    public void setDoctor_last_name(String doctor_last_name) {
        this.doctor_last_name = doctor_last_name;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
                ", allegies='" + allergies + '\'' +
                ", medicines='" + medicines + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}


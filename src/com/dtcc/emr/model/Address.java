package com.dtcc.emr.model;

public class Address {
    private int addressId;
    private String address1;
    private String address2;
    private String district;
    private String city;
    private String postalcode;
    private String country;
    private int logicalDelete;

    public Address() {
    }

    public Address(int addressId, String address1, String address2, String district, String city, String postalcode, String country) {
        this.addressId = addressId;
        this.address1 = address1;
        this.address2 = address2;
        this.district = district;
        this.city = city;
        this.postalcode = postalcode;
        this.country = country;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(int logicalDelete) {
        this.logicalDelete = logicalDelete;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

create database medicalEMR;
use medicalEMR;

create table patient(
patientId integer unsigned not null AUTO_INCREMENT,
accountnumber integer,
addressId integer,
first_name varchar(60),
last_name varchar(60),
dob timestamp,
gender char,
ssn integer,
medicalhistory text,
phonenumber integer,
driverslicense varchar(20)
);

create table address(
addressId  integer unsigned AUTO_INCREMENT,
address1 varchar(255),
address2 varchar(255),
district varchar(100),
city varchar(50),
postalCode varchar(20),
country varchar(30)
);

create table procedures(
procedureId  integer unsigned AUTO_INCREMENT,
cpt integer,
description text,
name varchar(250),
cost double
);

create table PatientHistory(
patientId integer unsigned not null,
procedureId integer,
doctorId integer,
addressId integer,
purpose varchar(250),
dateofvisit timestamp
);

create table Doctor(
doctorId integer unsigned AUTO_INCREMENT,
first_name varchar(100),
last_name varchar(100),
addressId integer
);



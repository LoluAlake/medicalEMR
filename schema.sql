create database medicalEMR;
use medicalEMR;

create table patient(
patientId integer unsigned not null AUTO_INCREMENT,
accountnumber integer,
addressId integer,
first_name varchar(60),
last_name varchar(60),
dob date,
gender char,
height double, 
weight double,
email varchar(50),
phonenumber varchar(20),
maritalstatus varchar(10),
ssn bigint,
emergencyname varchar(255),
emergencycontact varchar(20),
employmentstatus varchar(20),
medicalhistory text,
allergies varchar(255),
medicines varchar(255),
createdDate date,
logicalDelete smallint default 0,
primary key(patientId)
);

create table address(
addressId  integer unsigned AUTO_INCREMENT,
address1 varchar(255),
address2 varchar(255),
district varchar(100),
city varchar(50),
postalCode varchar(20),
country varchar(30),
logicalDelete smallint default 0,
primary key(addressId)
);

create table procedures(
procedureId  integer unsigned AUTO_INCREMENT,
cpt varchar(10),
description text,
name varchar(250),
cost double,
logicalDelete smallint default 0,
primary key(procedureId)
);

create table PatientHistory(
patientId integer unsigned not null,
procedureId integer,
doctorId integer,
addressId integer,
purpose varchar(250),
dateofvisit date,
nextappointement date
);

create table Doctor(
doctorId integer unsigned AUTO_INCREMENT,
first_name varchar(100),
last_name varchar(100),
addressId integer,
primary key(doctorId)
);



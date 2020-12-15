package com.dtcc.emr.service;

import com.dtcc.emr.model.Address;
import com.dtcc.emr.model.Patient;
import com.dtcc.emr.model.PatientHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;


public class PatientOperations {
    private PatientsInformation patientInformation;
    // private int patientID;
    private String fullAddress;
    private String gender;
    //private static ResultSet rs;
    //TableColumn<Person, String> column1
    public TableColumn<PatientsInformation, String> firstNameCol;
    public TableColumn<PatientsInformation, String> lastNameCol;
    public TableColumn<PatientsInformation, String> emailCol;
    public TableColumn<PatientsInformation, Integer> accountCol;
    public TableColumn<PatientsInformation, Date> dobCol;
    public TableColumn<PatientsInformation, String> genderCol;
    public TableColumn<PatientsInformation, String> maritalStatusCol;
    public TableColumn<PatientsInformation, String> phoneCol;
    public TableColumn<PatientsInformation, String> addressCol;
    //private TableView<Person> table = new TableView<Person>();
    public TableView<PatientsInformation> table;
    Connection dbConnection;

    public PatientOperations() {

    }

    public PatientOperations(PatientsInformation patientInformation) {
        this.patientInformation = patientInformation;
    }

    public void deletePatient() throws SQLException {
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "Update Patient set logicalDelete=1 where patientId=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, patientInformation.getPatientId());
            stmt.executeUpdate();

            String sqlAddress = "update address set logicalDelete=1 where addressId=?";
            PreparedStatement psAddress = con.prepareStatement(sqlAddress);
            psAddress.setInt(1, patientInformation.getAddressId());
            psAddress.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //public void addNewPatient(Patient patient, Address address){
    public void addNewPatient(Patient patient, Address address) {

        int addressID = 0;

        try {
            fullAddress = address.getAddress1() + ", " + address.getAddress2() + ", " + address.getCity() + ", " + address.getDistrict() + ", " + address.getPostalcode() + ", " + address.getCountry();
            dbConnection = DatabaseConnection.getConnection();

            String addAddressSql = "insert into Address values(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(addAddressSql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, address.getAddress1());
            preparedStatement.setString(3, address.getAddress2());
            preparedStatement.setString(4, address.getDistrict());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getPostalcode());
            preparedStatement.setString(7, address.getCountry());
            preparedStatement.setInt(8, 0);
            preparedStatement.execute();

            String getAddressIdSql = "select max(addressId) as count from address";
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(getAddressIdSql);

            while (rs.next()) {
                addressID = rs.getInt("count");
            }


            String addPatientSql = "insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement psPatient = dbConnection.prepareStatement(addPatientSql);
            psPatient.setInt(1, 0);
            psPatient.setInt(2, patient.getAccountnumber());
            psPatient.setInt(3, addressID);
            psPatient.setString(4, patient.getFirst_name());
            psPatient.setString(5, patient.getLast_name());

            if(patient.getDob()==null){Date date=null; psPatient.setDate(6, date);}
            else{
            Date date = Date.valueOf(patient.getDob());
            psPatient.setDate(6, date);
            }

            psPatient.setString(7, String.valueOf(patient.getGender()));
            psPatient.setDouble(8, patient.getHeight());
            psPatient.setDouble(9, patient.getWeight());
            psPatient.setString(10, patient.getEmail());
            psPatient.setString(11, patient.getPhonenumber());
            psPatient.setString(12, patient.getMaritalstatus());
            psPatient.setLong(13, patient.getSsn());
            psPatient.setString(14, patient.getEmergencyname());
            psPatient.setString(15, patient.getEmergencycontact());
            psPatient.setString(16, patient.getEmploymentstatus());
            psPatient.setString(17, patient.getMedicalhistory());
            psPatient.setString(18, patient.getAllegies());
            psPatient.setString(19, patient.getMedicines());
            psPatient.setDate(20, new java.sql.Date(new java.util.Date().getTime()));
            psPatient.setInt(21, 0);

            psPatient.execute();

           /* String getPatientSql = "select max(patientId) as count from Patient";
            Statement st = dbConnection.createStatement();
            ResultSet rsPatientId = st.executeQuery(getPatientSql);

            while (rsPatientId.next()) {
                int patientID = rsPatientId.getInt("count");
            }
            if (patient.getGender() == 'F') {
                gender = "Female";
            } else {
                gender = "Male";
            }
            rs.close();
            rsPatientId.close();

            java.util.Date dateDOB=Date.from(patient.getDob().atStartOfDay(ZoneId.systemDefault()).toInstant());
           // list1.add(new PatientsInformation(patientID,addressID,patient.getFirst_name(),patient.getLast_name(),patient.getEmail(),patient.getAccountnumber(),dateDOB,gender,patient.getPhonenumber(),patient.getMaritalstatus(),fullAddress, patient.getSsn(), patient.getHeight(),patient.getWeight(),address.getAddress1(), address.getAddress2(),address.getCity(),address.getDistrict(),address.getCountry(),address.getPostalcode(),patient.getEmergencyname(),patient.getEmergencycontact(), patient.getMedicalhistory(),patient.getAllegies(),patient.getMedicines(),patient.getEmploymentstatus()));
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient, Address address, int patientId, int addressId) throws SQLException {

        dbConnection = DatabaseConnection.getConnection();
        String updateSql = "update address set address1=?, address2=?,district=?,city=?,postalcode=?,country=? where addressId=?";
        PreparedStatement addressPStatement = dbConnection.prepareStatement(updateSql);
        addressPStatement.setString(1, address.getAddress1());
        addressPStatement.setString(2, address.getAddress2());
        addressPStatement.setString(3, address.getDistrict());
        addressPStatement.setString(4, address.getCity());
        addressPStatement.setString(5, address.getPostalcode());
        addressPStatement.setString(6, address.getCountry());
        addressPStatement.setInt(7, addressId);
        addressPStatement.executeUpdate();

        String updatePatientSql = "update patient set accountnumber=?, first_name=?,last_name=?, dob=?, gender=?,height=?,weight=?,email=?,phonenumber=?,maritalstatus=?," +
                "ssn=?,emergencyname=?,emergencycontact=?,employmentstatus=?,medicalhistory=?,allergies=?,medicines=? where patientId=?";
        PreparedStatement psUpdatePatient = dbConnection.prepareStatement(updatePatientSql);
        psUpdatePatient.setInt(1, patient.getAccountnumber());
        psUpdatePatient.setString(2, patient.getFirst_name());
        psUpdatePatient.setString(3, patient.getLast_name());
        if (patient.getDob() == null) {
            Date date = null;
            psUpdatePatient.setDate(4, date);
        } else {
            Date date = Date.valueOf(patient.getDob());
            psUpdatePatient.setDate(4, date);
        }
        psUpdatePatient.setString(5, String.valueOf(patient.getGender()));
        psUpdatePatient.setDouble(6, patient.getHeight());
        psUpdatePatient.setDouble(7, patient.getWeight());
        psUpdatePatient.setString(8, patient.getEmail());
        psUpdatePatient.setString(9, patient.getPhonenumber());
        psUpdatePatient.setString(10, patient.getMaritalstatus());
        psUpdatePatient.setLong(11, patient.getSsn());
        psUpdatePatient.setString(12, patient.getEmergencyname());
        psUpdatePatient.setString(13, patient.getEmergencycontact());
        psUpdatePatient.setString(14, patient.getEmploymentstatus());
        psUpdatePatient.setString(15, patient.getMedicalhistory());
        psUpdatePatient.setString(16, patient.getAllegies());
        psUpdatePatient.setString(17, patient.getMedicines());
        psUpdatePatient.setInt(18, patientId);
        psUpdatePatient.executeUpdate();

    }

    public static ObservableList<PatientsInformation> getAllPatientsList() throws SQLException {
        ObservableList<PatientsInformation> list = FXCollections.observableArrayList();
        Connection conObj = DatabaseConnection.getConnection();
        String sql1 = "select p.*,a.* from patient p join address a on p.addressId=a.addressId where p.logicalDelete=0";
        Statement stmt1 = conObj.createStatement();
        ResultSet rsList = stmt1.executeQuery(sql1);

        while (rsList.next()) {
            String gender = null;

            int patientId = rsList.getInt("patientId");
            int addressId = rsList.getInt("addressId");
            String first_name = rsList.getString("first_name");
            String last_name = rsList.getString("last_name");
            String email = rsList.getString("email");
            int accountnumber = rsList.getInt("accountnumber");
            String phonenumber = rsList.getString("phonenumber");
            String maritalstatus = rsList.getString("maritalstatus");
            java.util.Date dob = rsList.getDate("dob");

            if (rsList.getString("gender").equalsIgnoreCase("f")) {
                gender = "Female";
            } else {
                gender = "Male";
            }

            Long ssn = rsList.getLong("ssn");
            double height = rsList.getDouble("height");
            double weight = rsList.getDouble("weight");
            String address1 = rsList.getString("address1");
            String address2 = rsList.getString("address2");
            String city = rsList.getString("city");
            String district = rsList.getString("district");
            String country = rsList.getString("country");
            String postalCode = rsList.getString("postalCode");
            String emergencyname = rsList.getString("emergencyname");
            String emergencycontact = rsList.getString("emergencycontact");
            String medicalhistory = rsList.getString("medicalhistory");
            String allergies = rsList.getString("allergies");
            String medicines = rsList.getString("medicines");
            String fullAddress = address1 + ", " + address2 + ", " + city + ", " + district + ", " + postalCode + ", " + country;
            String employmentstatus = rsList.getString("employmentstatus");
            list.add(new PatientsInformation(patientId, addressId, first_name, last_name, email, accountnumber, dob, gender, phonenumber, maritalstatus, fullAddress, ssn, height, weight, address1, address2, city, district, country, postalCode, emergencyname, emergencycontact, medicalhistory, allergies, medicines, employmentstatus));
        }

        rsList.close();
        conObj.close();
        return list;
    }

    public TableView refreshPatientTable() throws SQLException {
        ObservableList<PatientsInformation> data = getAllPatientsList();
        table = new TableView<PatientsInformation>();

        firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));

        lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        accountCol = new TableColumn<>("Account");
        accountCol.setCellValueFactory(new PropertyValueFactory<>("accountnumber"));

        dobCol = new TableColumn<>("DOB");
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));

        genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        maritalStatusCol = new TableColumn<>("Marital Status");
        maritalStatusCol.setCellValueFactory(new PropertyValueFactory<>("maritalstatus"));

        phoneCol = new TableColumn<>("Phone Number");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, accountCol, dobCol, genderCol, maritalStatusCol, phoneCol, addressCol);
        table.setItems(data);
        return table;

    }

    public static ObservableList<PatientHistoryInformation> getPatientHistory(int patientID) {
        final ObservableList<PatientHistoryInformation> data = FXCollections.observableArrayList();
        Connection conn = DatabaseConnection.getConnection();
        String patientHistoryStr = "select p.patientId, p.accountnumber,p.first_name,p.last_name,pro.procedureId, pro.cpt,pro.description,pro.cost,ph.purpose, ph.dateOfvisit, ph.nextappointment\n" +
                "from patientHistory ph\n" +
                "join patient p on ph.patientId=p.patientId\n" +
                "join procedures pro on ph.procedureId=pro.procedureId\n" +
                "where p.patientId = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(patientHistoryStr);

           /* if (PatientID == 0) {
                ps.setString(1, "%" + "" + "%");
            } else {
                ps.setString(1, "%" + PatientID + "%");
            }*/
            ps.setInt(1,patientID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate localDateVisit;
                LocalDate localDateNextApp;
                if (rs.getDate("dateOfVisit") == null) {
                    localDateVisit = null;
                } else {
                    localDateVisit = rs.getDate("dateOfVisit").toLocalDate();
                }

                if (rs.getDate("nextappointment") == null) {
                    localDateNextApp = null;
                } else {
                    localDateNextApp = rs.getDate("nextappointment").toLocalDate();
                }

                data.add(new PatientHistoryInformation(
                        rs.getInt("patientId"),
                        rs.getInt("procedureId"),
                        rs.getInt("accountnumber"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("cpt"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getString("purpose"),
                        localDateVisit,
                        localDateNextApp
                ));
            }

            conn.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return data;
    }

    public static ObservableList getAllPatientsNames() throws SQLException {

        final ObservableList data = FXCollections.observableArrayList();
        Connection conn = DatabaseConnection.getConnection();
        String patientsName = "select patientId,first_name,last_name,accountnumber from patient where logicalDelete=0";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(patientsName);
            while (rs.next()) {
                String comboPatientData = rs.getString("first_name") + " " + rs.getString("last_name") + " - " + rs.getInt("accountnumber");
                data.add(new PatientHistoryInformation(
                        rs.getInt("patientId"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("accountnumber")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static ObservableList getAllCPTCodes() throws SQLException {

        final ObservableList data = FXCollections.observableArrayList();
        Connection conn = DatabaseConnection.getConnection();
        String patientsName = "select cpt from procedures where logicalDelete=0";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(patientsName);
            while (rs.next()) {
                //String comboPatientData=rs.getString("first_name")+" "+ rs.getString("last_name")+" - "+ rs.getInt("accountnumber");
                data.add(rs.getString("cpt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


}



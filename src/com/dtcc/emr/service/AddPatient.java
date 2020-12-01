package com.dtcc.emr.service;

import com.dtcc.emr.model.Address;
import com.dtcc.emr.model.Patient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddPatient {

    Patient patient=new Patient();
    Address address=new Address();
    PatientOperations po=new PatientOperations();
    PatientsInformation pInformation=new PatientsInformation();
    ShowAllPatients show;
    private TabPane tabPane;

    private Label messageLabel;
    //String message;

    DatePicker datePicker;
    TextField firstNameField;
    TextField lastNameField;
    TextField accountNumberField ;
    TextField phoneField;
    TextField emailField ;
    TextField ssnField;
    TextField heightField;
    TextField weightField;
    TextField address1Field;
    TextField address2Field;
    TextField districtField;
    TextField cityField;
    TextField countryField;
    TextField postalCodeField;
    TextField emergencyNameField;
    TextField emergencyPhoneField;

    ToggleGroup group;
    ToggleGroup groupMaritalStatus;
    ToggleGroup groupEmploymentStatus;

    RadioButton genderFemaleRadioBtn;
    RadioButton genderMaleRadioBtn;

    RadioButton marriedRBtn;
    RadioButton singleRBtn;
    RadioButton divorcedRBtn;
    RadioButton widowedRBtn;

    RadioButton fullTimeRBtn;
    RadioButton partTimeRBtn;
    RadioButton unemployedRBtn;
    RadioButton retiredRBtn;
    RadioButton studentRBtn;
    RadioButton selfEmployedRBtn;

    TextArea medicalHistoryTxtArea;
    TextArea allergiesTxtArea;
    TextArea medicinesTxtArea;

    //public AddPatient(){}

    public AddPatient(TabPane tabPane, PatientsInformation pInformation){
        this.tabPane=tabPane;
        this.pInformation=pInformation;
    }
    public  AddPatient(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public Node addNewPatient() {

        GridPane gridPane=createRegistrationFormPane();
        messageLabel=new Label();
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        messageLabel.setTextFill(Color.RED);

        gridPane.add(messageLabel,0,0,2,1);

        Label headerLabel = new Label("Patient Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(headerLabel,  0,0,6,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(10, 0,10,0));

        //===1st ROW===
        Label firstNameLabel = new Label("First Name : ");
        gridPane.add(firstNameLabel, 0,1);
        // Add Name Text Field
        firstNameField = new TextField();
        firstNameField.setPrefHeight(40);
        firstNameField.setText(pInformation.getFirst_name());
        gridPane.add(firstNameField, 1,1);

        Label lastNameLabel = new Label("Last Name : ");
        gridPane.add(lastNameLabel, 2,1);
        // Add Name Text Field
        lastNameField = new TextField();
        lastNameField.setPrefHeight(40);
        lastNameField.setText(pInformation.getLast_name());
        gridPane.add(lastNameField, 3,1);

        Label accountNumberLabel = new Label("Account Number: ");
        gridPane.add(accountNumberLabel, 4,1);
        // Add Name Text Field
        accountNumberField = new TextField();
        accountNumberField.setPrefHeight(40);
        if(pInformation.getAccountnumber()==0){accountNumberField.setText("");}
        else{accountNumberField.setText(String.valueOf(pInformation.getAccountnumber()));}
        gridPane.add(accountNumberField, 5,1);

        //=====2nd ROW ===
        Label phoneLabel = new Label("Phone Number: ");
        gridPane.add(phoneLabel,0,2);

        phoneField=new TextField();
        gridPane.add(phoneField,1,2);
        phoneField.setPrefHeight(40);
        phoneField.setText(pInformation.getPhonenumber());
        // Add Email Label
        Label emailLabel = new Label("Email ID : ");
        gridPane.add(emailLabel, 2, 2);
        // Add Email Text Field
        emailField = new TextField();
        emailField.setPrefHeight(40);
        emailField.setText(pInformation.getEmail());
        gridPane.add(emailField, 3, 2);

        //ssn
        Label ssnLabel = new Label("Social Security Number: ");
        gridPane.add(ssnLabel,4,2);
        ssnField=new TextField();
        ssnField.setPrefWidth(40);
        if(pInformation.getSsn()==null){ssnField.setText("");}
        else{ ssnField.setText(String.valueOf(pInformation.getSsn()));}
        gridPane.add(ssnField,5,2);

        //dob
        Label dobLabel = new Label("Date of Birth: (mm/dd/yyyy)");
        gridPane.add(dobLabel,0,3);

        datePicker= new DatePicker();
        gridPane.add(datePicker,1,3);

        if(pInformation.getDob()!=null){
            LocalDate lDate= new java.sql.Date(pInformation.getDob().getTime() ).toLocalDate();
            datePicker.setValue(lDate);
        }
        else{ datePicker.setValue( LocalDate.now()); }

        //Gender
        Label genderLabel = new Label("Gender: ");
        gridPane.add(genderLabel,2,3);
        //RadioButton
        group=new ToggleGroup();
        genderFemaleRadioBtn= new RadioButton("Female");
        genderFemaleRadioBtn.setToggleGroup(group);
        if(pInformation.getGender()==null){ genderFemaleRadioBtn.setSelected(true); }

        genderMaleRadioBtn= new RadioButton("Male");
        genderMaleRadioBtn.setToggleGroup(group);

        if(pInformation.getGender()!=null){
            if((pInformation.getGender().equalsIgnoreCase("Female"))){ genderFemaleRadioBtn.setSelected(true);}
            else{genderMaleRadioBtn.setSelected(true);}
        }


        HBox hBox = new HBox(20, genderFemaleRadioBtn,genderMaleRadioBtn);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(10,0,0,10));
        gridPane.add(hBox,3,3);


        //height
        Label heightLabel=new Label("Height in inches");
        gridPane.add(heightLabel,0,4);
        heightField=new TextField();
        heightField.setPrefWidth(40);
        if(pInformation.getHeight()==0){heightField.setText("");}
        else{ heightField.setText(String.valueOf(pInformation.getHeight()));}
        gridPane.add(heightField,1,4);

        //weight
        Label weightLabel=new Label("Weight in Pounds");
        gridPane.add(weightLabel,2,4);
        weightField=new TextField();
        weightField.setPrefWidth(40);
        if(pInformation.getWeight()==0){weightField.setText("");}
        else{ weightField.setText(String.valueOf(pInformation.getWeight()));}
        gridPane.add(weightField,3,4);

        //address1
        Label address1Label = new Label("Address1: ");
        gridPane.add(address1Label,0,5);

        address1Field=new TextField();
        gridPane.add(address1Field,1,5);
        address1Field.setPrefHeight(40);
        address1Field.setText(pInformation.getAddress1());

        //address2
        Label address2Label = new Label("Address2: ");
        gridPane.add(address2Label,2,5);

        address2Field=new TextField();
        gridPane.add(address2Field,3,5);
        address2Field.setPrefHeight(40);
        address2Field.setText(pInformation.getAddress2());

        //district
        Label districtLabel = new Label("District: ");
        gridPane.add(districtLabel,4,5);

        districtField=new TextField();
        gridPane.add(districtField,5,5);
        districtField.setPrefHeight(40);
        districtField.setText(pInformation.getDistrict());

        //city
        Label cityLabel = new Label("City: ");
        gridPane.add(cityLabel,0,6);

        cityField=new TextField();
        gridPane.add(cityField,1,6);
        cityField.setPrefHeight(40);
        cityField.setText(pInformation.getCity());

        //Country
        Label countryLabel = new Label("Country: ");
        gridPane.add(countryLabel,2,6);
        countryField=new TextField();
        gridPane.add(countryField,3,6);
        countryField.setPrefHeight(40);
        countryField.setText(pInformation.getCountry());

        //postal code
        Label postalCodeLabel = new Label("Postal Code: ");
        gridPane.add(postalCodeLabel,4,6);

        postalCodeField=new TextField();
        gridPane.add(postalCodeField,5,6);
        postalCodeField.setPrefHeight(40);
        postalCodeField.setText(pInformation.getPostalCode());

        //Marital status
        Label maritalStatusLabel = new Label("Marital Status: ");
        gridPane.add(maritalStatusLabel,0,7);

        //RadioButtons for Marital Status
        groupMaritalStatus=new ToggleGroup();
        marriedRBtn= new RadioButton("Married");
        marriedRBtn.setToggleGroup(groupMaritalStatus);
        if(pInformation.getMaritalstatus()==null){marriedRBtn.setSelected(true);}

        singleRBtn= new RadioButton("Single");
        singleRBtn.setToggleGroup(groupMaritalStatus);

        divorcedRBtn=new RadioButton("Divorced");
        divorcedRBtn.setToggleGroup(groupMaritalStatus);

        widowedRBtn=new RadioButton("Widowed");
        widowedRBtn.setToggleGroup(groupMaritalStatus);

        if(pInformation.getMaritalstatus()!=null){
            if(pInformation.getMaritalstatus().equalsIgnoreCase("Married")){marriedRBtn.setSelected(true);}
            else if(pInformation.getMaritalstatus().equalsIgnoreCase("Single")){singleRBtn.setSelected(true);}
            else if(pInformation.getMaritalstatus().equalsIgnoreCase("Divorced")){divorcedRBtn.setSelected(true);}
            else {widowedRBtn.setSelected(true);}
        }

        //grid.add(userTextField, 0, 0,3,3);
        HBox hBoxMaritalStatus = new HBox(20, marriedRBtn,singleRBtn,divorcedRBtn,widowedRBtn);
        hBoxMaritalStatus.setSpacing(5);
        hBoxMaritalStatus.setPadding(new Insets(10,0,0,10));
        gridPane.add(hBoxMaritalStatus,1,7,4,1);

        //Employment Status
        Label employmentStatusLabel = new Label("Employment Status: ");
        gridPane.add(employmentStatusLabel,0,8);

        //Radio buttons for Employment Status
        groupEmploymentStatus=new ToggleGroup();
        fullTimeRBtn= new RadioButton("Full time");
        fullTimeRBtn.setToggleGroup(groupEmploymentStatus);
        if(pInformation.getEmploymentstatus()==null){fullTimeRBtn.setSelected(true);}

        partTimeRBtn= new RadioButton("Part time");
        partTimeRBtn.setToggleGroup(groupEmploymentStatus);

        unemployedRBtn= new RadioButton("Unemployed");
        unemployedRBtn.setToggleGroup(groupEmploymentStatus);

        retiredRBtn= new RadioButton("Retired");
        retiredRBtn.setToggleGroup(groupEmploymentStatus);

        studentRBtn= new RadioButton("Student");
        studentRBtn.setToggleGroup(groupEmploymentStatus);

        selfEmployedRBtn= new RadioButton("Self Employed");
        selfEmployedRBtn.setToggleGroup(groupEmploymentStatus);

        if(pInformation.getEmploymentstatus()!=null){
            if(pInformation.getEmploymentstatus().equalsIgnoreCase("Full Time")){fullTimeRBtn.setSelected(true);}
            else if(pInformation.getEmploymentstatus().equalsIgnoreCase("Part Time")){partTimeRBtn.setSelected(true);}
            else if(pInformation.getEmploymentstatus().equalsIgnoreCase("Unemployed")){unemployedRBtn.setSelected(true);}
            else if(pInformation.getEmploymentstatus().equalsIgnoreCase("Retired")){retiredRBtn.setSelected(true);}
            else if(pInformation.getEmploymentstatus().equalsIgnoreCase("Student")){studentRBtn.setSelected(true);}
            else {selfEmployedRBtn.setSelected(true);}
        }

        HBox hBoxEmploymentStatus = new HBox(30, fullTimeRBtn,partTimeRBtn,unemployedRBtn,retiredRBtn,studentRBtn,selfEmployedRBtn);
        hBoxEmploymentStatus.setSpacing(5);
        hBoxEmploymentStatus.setPadding(new Insets(10,0,0,10));
        gridPane.add(hBoxEmploymentStatus,1,8,4,1);

        //Emergency Contact
        Label emergencyNameLabel = new Label("Emergency Contact Name: ");
        gridPane.add(emergencyNameLabel,0,9);

        emergencyNameField=new TextField();
        gridPane.add(emergencyNameField,1,9);
        emergencyNameField.setPrefHeight(40);
        emergencyNameField.setText(pInformation.getEmergencyname());

        Label emergencyPhoneLabel = new Label("Emergency Contact Phone: ");
        gridPane.add(emergencyPhoneLabel,2,9);

        emergencyPhoneField=new TextField();
        gridPane.add(emergencyPhoneField,3,9);
        emergencyPhoneField.setPrefHeight(40);
        emergencyPhoneField.setText(pInformation.getEmergencycontact());

        //Medical History //medical history  //allergies  //medicines
        Label medicalHistoryLabel = new Label("Medical History: ");
        gridPane.add(medicalHistoryLabel,0,10);
        medicalHistoryTxtArea =new TextArea();
        gridPane.add(medicalHistoryTxtArea,1,10,3,1);
        medicalHistoryTxtArea.setText(pInformation.getMedicalhistory());

        Label allergiesLabel = new Label("Allergies: ");
        gridPane.add(allergiesLabel,0,11);
        allergiesTxtArea =new TextArea();
        gridPane.add(allergiesTxtArea,1,11,3,1);
        allergiesTxtArea.setText(pInformation.getAllegies());

        Label medicinesLabel = new Label("Medications currently taking: ");
        gridPane.add(medicinesLabel,0,12);
        medicinesTxtArea =new TextArea();
        gridPane.add(medicinesTxtArea,1,12,3,1);
        medicinesTxtArea.setText(pInformation.getMedicines());
        // Add Submit Button


        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 13, 6, 1);
        gridPane.setHalignment(submitButton, HPos.CENTER);
        gridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        EventHandler<ActionEvent> addEvent = e -> {
            boolean valid=checkValidation();
            if(valid){
                try {
                    addAllDataToPatientAndAddress();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        };

        submitButton.setOnAction(addEvent);

        group.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb1 = (RadioButton)group.getSelectedToggle();
            if (rb1 != null) { //String s = rb1.getText(); }
            }
        });

        groupMaritalStatus.selectedToggleProperty().addListener((ob1, o1, n1) -> {
            RadioButton rb2 = (RadioButton)groupMaritalStatus.getSelectedToggle();
            if (rb2 != null) { //String s = rb2.getText(); }
            }
        });

        groupEmploymentStatus.selectedToggleProperty().addListener((ob2, o2, n3) -> {
            RadioButton rb3 = (RadioButton)groupEmploymentStatus.getSelectedToggle();
            if (rb3 != null) { //String s = rb3.getText(); }
            }
        });

        return gridPane;
    }

    private GridPane createRegistrationFormPane() {

        GridPane gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        return gridPane;
    }

    //SUBMIT BUTTON EVENT HANDLER CALLS THIS METHOD TO ADD PATIENT AND ADDRESS DATA IN THE DATABASE.
    private void addAllDataToPatientAndAddress() throws SQLException {

        address.setAddress1(address1Field.getText()==null? "" :address1Field.getText());
        address.setAddress2(address2Field.getText()==null? "" :address2Field.getText());
        address.setDistrict(districtField.getText()==null? "" :districtField.getText());
        address.setCity(cityField.getText()==null? "" :cityField.getText());
        address.setPostalcode(postalCodeField.getText()==null? "" : postalCodeField.getText());
        address.setCountry(countryField.getText()==null? "" :countryField.getText());

        patient.setFirst_name(firstNameField.getText());
        patient.setLast_name(lastNameField.getText());

        if(accountNumberField.getText().isBlank()||accountNumberField.getText().isEmpty()||accountNumberField.getText()==null){patient.setAccountnumber(0);}
        else{patient.setAccountnumber(Integer.parseInt(accountNumberField.getText()));}

        patient.setPhonenumber(phoneField.getText());
        patient.setEmail(emailField.getText());

        if(ssnField.getText().isBlank()||ssnField.getText().isEmpty()||ssnField.getText()==null){patient.setSsn(0L);}
        else{patient.setSsn(Long.parseLong(ssnField.getText()));}

        patient.setDob(datePicker.getValue());

        if(((RadioButton)group.getSelectedToggle()).getText()!=null){patient.setGender(((RadioButton)group.getSelectedToggle()).getText().charAt(0));}
        else{patient.setGender('F');}

        if(heightField.getText().isBlank()||heightField.getText().isEmpty()||heightField.getText()==null){patient.setHeight(0);}
        else{patient.setHeight(Double.parseDouble(heightField.getText()));}

        if(weightField.getText().isBlank()||weightField.getText().isEmpty()||weightField.getText()==null){patient.setWeight(0);}
        else{patient.setWeight(Double.parseDouble(weightField.getText()));}

        if(((RadioButton)groupMaritalStatus.getSelectedToggle())!=null) {patient.setMaritalstatus(((RadioButton)groupMaritalStatus.getSelectedToggle()).getText()); }
        else{ patient.setMaritalstatus("Married");}

        if(((RadioButton)groupEmploymentStatus.getSelectedToggle())!=null){patient.setEmploymentstatus(((RadioButton)groupEmploymentStatus.getSelectedToggle()).getText());}
        else{patient.setEmploymentstatus("Full Time");}

        patient.setEmergencyname(emergencyNameField.getText());
        patient.setEmergencycontact(emergencyPhoneField.getText());
        patient.setMedicalhistory(medicalHistoryTxtArea.getText());
        patient.setAllegies(allergiesTxtArea.getText());
        patient.setMedicines(medicinesTxtArea.getText());

        //PATIENT ID > 0 MEANS UPDATE PATIENT TAB
        if(pInformation.getPatientId()>0) {
            po.updatePatient(patient, address,pInformation.getPatientId(),pInformation.getAddressId());
        }
        //NO PATIENT ID MEANS ==> ADD NEW PATIENT
        else{
            po.addNewPatient(patient, address);
        }

        show=new ShowAllPatients(this.tabPane);
        this.tabPane.getSelectionModel().select(0);
        clearAllFields();
    }

    public boolean checkValidation(){

        boolean isValid=true;
        messageLabel.setText("");
        if(firstNameField.getText().isBlank() || firstNameField.getText().isEmpty() || firstNameField.getText()==null){
            //message="Please enter first name.";
            messageLabel.setText("Please Enter First Name");
            return false;
        }

        if(lastNameField.getText().isBlank() || lastNameField.getText().isEmpty() || lastNameField.getText()==null){
            messageLabel.setText("Please Enter Last Name");
            return false;
        }

        if(!(accountNumberField.getText().isEmpty() || accountNumberField.getText().isBlank())){
            if(!(accountNumberField.getText().matches("[0-9]+"))){
                messageLabel.setText("Please Enter Numeric value for Account Number");
                return false;
            }
        }

        /*if(!(emailField.getText().isEmpty() || emailField.getText().isBlank() || emailField.getText()==null)){
           // String regex="^(.+)@(.+)$";
            String regex="^.+@.+\\\\..+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(emailField.getText());
            if(matcher.matches()){isValid=true;}
            else{
                messageLabel.setText("Please Enter proper email id.");
                return false;
            }
        }*/

        if(!(ssnField.getText().isEmpty() || ssnField.getText().isBlank())){
            if(!(ssnField.getText().matches("[0-9]+"))){
                messageLabel.setText("Please Enter Numeric value for SSN");
                return false;
            }
        }

        if(!(heightField.getText().isEmpty() || heightField.getText().isBlank())){
           // if(!(heightField.getText().matches("[0-9]+"))){
            if(!(heightField.getText().matches("[0-9]\\d*(\\.\\d+)?$"))){
                messageLabel.setText("Please Enter Numeric value for Height");
                return false;
            }
        }

        //^[1-9]\d*(\.\d+)?$
        if(!(weightField.getText().isEmpty() || weightField.getText().isBlank())){
            if(!(weightField.getText().matches("[0-9]\\d*(\\.\\d+)?$"))){
                messageLabel.setText("Please Enter Numeric value for Weight");
                return false;
            }
        }

        return isValid;
    }

    public void clearAllFields(){
        firstNameField.setText("");
        lastNameField.setText("");
        accountNumberField.setText("");
        phoneField.setText("");
        emailField.setText("");
        ssnField.setText("");
        datePicker.setValue( LocalDate.now());
        genderFemaleRadioBtn.setSelected(true);
        heightField.setText("");
        weightField.setText("");
        address1Field.setText("");
        address2Field.setText("");
        districtField.setText("");
        cityField.setText("");
        countryField.setText("");
        postalCodeField.setText("");
        marriedRBtn.setSelected(true);
        fullTimeRBtn.setSelected(true);
        emergencyNameField.setText("");
        emergencyPhoneField.setText("");
        medicalHistoryTxtArea.setText("");
        allergiesTxtArea.setText("");
        medicinesTxtArea.setText("");
    }

}

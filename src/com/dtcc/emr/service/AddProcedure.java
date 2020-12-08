package com.dtcc.emr.service;


import com.dtcc.emr.model.Procedure;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.SQLException;

public class AddProcedure {
    public TabPane tabPane;
    Procedure procedure;
    TextField cptField;
    TextArea descriptionField;
    TextField nameField ;
    TextField costField;
    Label messageLabel;

    public AddProcedure(TabPane tabPane, Procedure procedure){
       this.tabPane = tabPane;
       this.procedure = procedure;
    }
    public AddProcedure(TabPane tabPane){
        this.tabPane = tabPane;
    }

    public Node addNewProcedure(){

        GridPane gridPane=createRegistrationFormPane();
        messageLabel=new Label();
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        messageLabel.setTextFill(Color.RED);

        gridPane.add(messageLabel,0,0,2,1);

        Label headerLabel = new Label("Procedure Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(headerLabel,  0,0,6,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(10, 0,10,0));

        //===1st ROW===
        Label cptLabel = new Label("CPT : ");
        gridPane.add(cptLabel, 0,1);
        // Add Name Text Field
        cptField = new TextField();
        cptField.setPrefHeight(40);
//        cptField.setText(procedure.getCpt());
        gridPane.add(cptField, 1,1);

            Label descriptionLabel = new Label("Description : ");
        gridPane.add(descriptionLabel, 0,2);
        // Add Name Text Field
        descriptionField = new TextArea();
        //dField.setPrefHeight(40);
//        descriptionField.setText(procedure.getDescription());
        gridPane.add(descriptionField, 1,2);

        Label nameLabel = new Label("Name : ");
        gridPane.add(nameLabel, 0,3);
        // Add Name Text Field
        nameField = new TextField();
        nameField.setPrefHeight(40);
   //     nameField.setText(procedure.getName());
        gridPane.add(nameField, 1,3);

        Label costLabel = new Label("Cost : ");
        gridPane.add(costLabel, 0,4);
        // Add Name Text Field
        costField = new TextField();
        costField.setPrefHeight(40);
        //     nameField.setText(procedure.getName());
        gridPane.add(costField, 1,4);

        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        gridPane.setHalignment(submitButton, HPos.CENTER);
        gridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        EventHandler<ActionEvent> addEvent = e -> {
       //     boolean valid=checkValidation();
            boolean valid = true;
            if(valid){
                try {
      //              addAllDataToPatientAndAddress();
                } catch (Exception p) {
                    p.printStackTrace();
                }
            }
        };

        submitButton.setOnAction(addEvent);


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
}

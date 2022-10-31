package com.example.hopeitworks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientEnrollController implements Initializable {

    @FXML
    public TextField usernameTextField10;
    @FXML
    public TextField usernameTextField11;
    @FXML
    public TextField usernameTextField12;
    @FXML
    private ChoiceBox<String> usernameTextField13;
    @FXML
    public TextField usernameTextField14;
    @FXML
    public TextField usernameTextField15;
    @FXML
    public DatePicker usernameTextField16;
    @FXML
    public TextField usernameTextField17;
    @FXML
    public TextField usernameTextField18;

    @FXML
    private Label gender;
    @FXML
    private ChoiceBox<String> roomcategory;

    private String[] g = {"Male", "Female"};

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
            usernameTextField13.getItems().addAll(g);
        try {
            roomcategory.setItems(DatabaseConnection.getRoomCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void getGender(ActionEvent event){
        String mygender = usernameTextField13.getValue();
        gender.setText(mygender);
    }


    public void Enroll(ActionEvent event) {

        try {
            Connection connect = DatabaseConnection.connectDb();
            //Creating Connection Object
            String Sql = "INSERT INTO `hbms_db`.`patient`(p_name,p_phone,p_age,p_gender,p_relative_name,p_relative_no,p_admission,b_id,p_lastname) VALUES(?,?,?,?,?,?,?,?,?)";

            //Preapared Statement
            PreparedStatement Pstatement = connect.prepareStatement(Sql);
            //Specifying the values of it's parameter

            Pstatement.setString(1, usernameTextField10.getText());
            Pstatement.setString(2, usernameTextField11.getText());
            Pstatement.setString(3, usernameTextField12.getText());
            Pstatement.setString(4, usernameTextField13.getValue());
            Pstatement.setString(5, usernameTextField14.getText());
            Pstatement.setString(6, usernameTextField15.getText());
            Pstatement.setString(7, String.valueOf(usernameTextField16.getValue()));
            Pstatement.setString(8, usernameTextField17.getText());
            Pstatement.setString(9, usernameTextField18.getText());
            int result = Pstatement.executeUpdate();

            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("enrolled succesfully!");
            alert.setContentText("Successfully");

            alert.showAndWait();

            Parent NextParent = FXMLLoader.load(getClass().getResource("patients_infotable.fxml"));
            Scene NextScene = new Scene(NextParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(NextScene);
            window.show();

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void onCancelButtonClick(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_info.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }

    public void onPatientInfoButtonClick(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_infotable.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }

    public void onDashboardButtonClick(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }

    public void onBedInfoButtonClick(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("bed_ui.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }

    public void Logout(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("loginform.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
}

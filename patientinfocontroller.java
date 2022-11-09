package com.example.hopeitworks;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class patientinfocontroller implements Initializable {

    @FXML
    public Label AdmitsNumber;

    @FXML
    public Label Discharge;

    @FXML
    public Label Total;
    @FXML
    private TableView<patientinfomodel> patientTableView;

    @FXML
    private TableColumn<patientinfomodel, Integer> patientIdTableColumn;
    @FXML
    private TableColumn<patientinfomodel, String> patientNameTableColumn;
    @FXML
    private TableColumn<patientinfomodel, String> wardname;
    @FXML
    private TableColumn<patientinfomodel, String> roomAssignedTableColumn;
    @FXML
    private TableColumn<patientinfomodel, String> addmissionDateTableColumn;
    @FXML
    public TextField delete;
    @FXML
            public Label Alertbox;

    ObservableList<patientinfomodel> listM;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            setTotalNumber();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            setAdmitNumber();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            setDischrge();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        patientIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
        patientNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        wardname.setCellValueFactory(new PropertyValueFactory<>("Wardname"));
        roomAssignedTableColumn.setCellValueFactory(new PropertyValueFactory<>("RoomAssigned"));
        addmissionDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("AddmissionDate"));
        // DatabaseConnection connectNow = new DatabaseConnection();
        //Connection connectDB = connectNow.connectDb();

        listM = DatabaseConnection.getData();
        patientTableView.setItems(listM);

    }
    public void setAdmitNumber() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st;
        st = connectdb.createStatement();
        String query = "select count(*) from hbms_new.patient where p_allotment_status=1;";
        ResultSet rs;
        rs = st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        AdmitsNumber.setText(String.valueOf(rs.getInt("count(*)")));
    }
    public void AdmitNumber(){
        try {
            setAdmitNumber();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void onEnrollNewButtonClick(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("Patientenroll.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void onDashboardButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void onPatientInfoButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_info.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void onBedInfoButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("bed_ui.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    public void Logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load((getClass().getResource("loginform.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

//    @FXML
//    public void onDELETE(ActionEvent event) throws IOException{
//        String query = "UPDATE `hbms_new`.`patient` SET 'p_allotment_status' = 0 where `p_id` ";
////        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_info.fxml"));
////        Scene NextScene = new Scene(NextParent);
////        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
////        window.setScene(NextScene);
////        window.show();
//    }
    @FXML
    public void onDelete(ActionEvent event) {

            String id = delete.getText();
            if(id.equals("")) {
                Alertbox.setText("Bed ID Field is Empty!");
                //System.out.println("id Field is empty!");
            } else {
                try {

                    DatabaseConnection.delete(Integer.valueOf(id));

                    //listM = DatabaseConnection.getData(id);
                    //doctorTable.setItems(listM);
                    Alertbox.setText("Patient Deleted Successfully!");
                } catch (SQLException ex) {
                    Alertbox.setText("Patient Deleted Successfully");
                    //System.out.println(ex);
                }
            }

    }
    public void setDischrge() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st;
        st = connectdb.createStatement();
        String query = "select count(*) from hbms_new.patient where p_allotment_status=0;";
        ResultSet rs;
        rs = st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        Discharge.setText(String.valueOf(rs.getInt("count(*)")));
    }
    public void setTotalNumber() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st;
        st = connectdb.createStatement();
        String query = "select count(*) from hbms_new.patient;";
        ResultSet rs;
        rs = st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        Total.setText(String.valueOf(rs.getInt("count(*)")));
    }
    public void Display() throws SQLException {
        setAdmitNumber();
        setDischrge();
        setTotalNumber();
    }

}

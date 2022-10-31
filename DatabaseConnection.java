package com.example.hopeitworks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hbms_db", "root", "11May1996");
            return connect;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Database connection failure!");
        }
        return null;
    }
    public static ObservableList<patientinfomodel> getData() {
        ObservableList<patientinfomodel> list = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.connectDb();
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery("select patient.p_id,patient.p_name,room.rc_name,bed.b_id,patient.p_admission from patient left join bed on bed.b_id=patient.b_id left join room on room.r_id=bed.r_id;");
            while (rs.next()) {
                // number of parameter depends upon columns in table view
                list.add(new patientinfomodel(rs.getInt("p_id"), rs.getString("p_name"), rs.getString("rc_name"), rs.getInt("b_id"), rs.getDate("p_admission")));
            }
        } catch (SQLException e) {
            System.out.println("not connected!");
            throw new RuntimeException(e);
        }

        return list;
    }

    public static ObservableList<PieChartDemo> Data() throws SQLException {
        ObservableList<PieChartDemo> list = FXCollections.observableArrayList();
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.connectDb();
            Statement statement = connectDB.createStatement();
            String query = "select sum(b_occupancy=1)," +
                    "sum(b_occupancy=0)from bed;";
            ResultSet rs = connectDB.createStatement().executeQuery(query);
        } catch (Exception e) {
            System.out.println("error!");
        }
        return list;
    }

    public static int getOccupancy() throws SQLException {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.connectDb();
        Statement statement = connectDB.createStatement();
        int count=0;
        String query="SELECT COUNT(*) FROM bed where b_occupancy=1;";
        ResultSet rs= statement.executeQuery(query);
        while (rs.next()){count=rs.getInt(1);}
        return count;
    }
    public static int getUnoccupancy() throws SQLException {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.connectDb();
        Statement statement = connectDB.createStatement();
        int count=0;
        String query="SELECT COUNT(*) FROM bed where b_occupancy=0;";
        ResultSet rs= statement.executeQuery(query);
        while (rs.next()){count=rs.getInt(1);}
        return count;
    }
    public static int getNUmberOfBeds() throws SQLException{
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(*) from bed;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count= rs.getInt(1);}
        return count;
    }

    public static int getocc_general() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where r_id=1 AND b_occupancy=1;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;}

    public static int getocc_icu() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where (r_id=2 or r_id=3) AND b_occupancy=1;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }

    public static int getocc_emergency() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where r_id=4 AND b_occupancy=1;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }

    public static int getocc_private() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where (r_id=5 or r_id=6 or r_id=7 or r_id=8)  AND b_occupancy=1;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }

    public static int getvac_general() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where r_id=1  AND b_occupancy=0;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }

    public static int getvac_icu() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where (r_id=2 or r_id=3) AND b_occupancy=0;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }

    public static int getvac_emergency() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where r_id=4 AND b_occupancy=0;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }

    public static int getvac_private() throws SQLException {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.connectDb();
        Statement statement=connectDB.createStatement();
        int count=0;
        String query="select count(b_id) from bed where (r_id=5 or r_id=6 or r_id=7 or r_id=8)  AND b_occupancy=0;";
        ResultSet rs= statement.executeQuery(query);
        while(rs.next()){count=rs.getInt(1);}
        return count;
    }
}



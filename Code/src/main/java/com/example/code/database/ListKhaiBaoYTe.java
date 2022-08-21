package com.example.code.database;//package database;

import com.example.code.database.DBConnection;
import com.example.code.models.KhaiBaoYTe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListKhaiBaoYTe extends DBConnection{
//    Connection connection = (Connection) DBConnection.getConnection();
//    ResultSet resultSet;
//    KhaiBaoYTe khaibao;
//    public ObservableList<Admin> listAdmin =  FXCollections.observableArrayList();
//    public ListAdmin(){
//
//    }
//    public ObservableList<Admin> list(String columns, String condition){
//        String sql = " SELECT " + columns + " FROM tbladmin a " + condition;
//        int countColumn = 1;
//        try {
//            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            countColumn = resultSet.getMetaData().getColumnCount();
//            String[] columnName = new String[countColumn+1];
//            while(resultSet.next()){
//                admin = new Admin();
//                for (int i = 1; i <= countColumn; i++) {
//                    columnName[i] = resultSet.getMetaData().getColumnName(i);
//                    switch (columnName[i]) {
//                        case "ad_id":
//                            admin.setAd_id(resultSet.getInt(i));
//                            break;
//                        case "ad_username":
//                            admin.setAd_username(resultSet.getString(i));
//                            break;
//                        case "ad_pass":
//                            admin.setAd_pass(resultSet.getString(i));
//                            break;
//                        case "ad_fullName":
//                            admin.setAd_fullName(resultSet.getString(i));
//                            break;
//                        case "ad_phone":
//                            admin.setAd_phone(resultSet.getString(i));
//                            break;
//                        case "ad_startDate":
//                            admin.setAd_startDate(resultSet.getDate(i));
//                            break;
//                        case "role_id":
//                            admin.setRole_id(resultSet.getInt(i));
//                            break;
//                        case "role_name":
//                            admin.setRole_name(resultSet.getString(i));
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                listAdmin.add(admin);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return listAdmin;
//    }

}
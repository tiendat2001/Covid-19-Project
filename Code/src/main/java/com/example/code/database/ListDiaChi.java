package com.example.code.database;

import com.example.code.models.DiaChi;
import com.example.code.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDiaChi extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    DiaChi diaChi;
    public ObservableList<DiaChi> listDiaChi =  FXCollections.observableArrayList();
    public ListDiaChi(){

    }
    public ObservableList<DiaChi> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM diachi d " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
                diaChi = new DiaChi();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "diachi_id":
                            diaChi.setDiachi_id(resultSet.getInt(i));
                            break;
                        case "quan":
                            diaChi.setQuan(resultSet.getString(i));
                            break;
                        case "phuong":
                            diaChi.setPhuong(resultSet.getString(i));
                            break;
                        case "soNha":
                            diaChi.setSoNha(resultSet.getString(i));
                            break;
                        case "tinh":
                            diaChi.setTinh(resultSet.getString(i));
                            break;
                        default:
                            break;
                    }
                }
                listDiaChi.add(diaChi);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listDiaChi;
    }

}

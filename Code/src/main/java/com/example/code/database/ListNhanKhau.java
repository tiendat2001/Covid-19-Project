package com.example.code.database;

import com.example.code.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListNhanKhau extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    NhanKhau nhanKhau;
    public ObservableList<NhanKhau> listNhanKhau =  FXCollections.observableArrayList();
    public ListNhanKhau(){

    }
    public ObservableList<NhanKhau> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM nhankhau n " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
                nhanKhau = new NhanKhau();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "nhankhau_id":
                            nhanKhau.setNhankhau_id(resultSet.getInt(i));
                            break;
                        case "ten":
                            nhanKhau.setTen(resultSet.getString(i));
                            break;
                        case "ngaysinh":
                            nhanKhau.setNgaysinh(resultSet.getDate(i));
                            break;
                        case "gioitinh":
                            nhanKhau.setGioitinh(resultSet.getString(i));
                            break;
                        case "CMND":
                            nhanKhau.setCMND(resultSet.getString(i));
                            break;
                        case "diachi_id":
                            nhanKhau.setDiachi_id(resultSet.getInt(i));
                            break;
                        case "SDT":
                            nhanKhau.setSDT(resultSet.getString(i));
                            break;
                        case "datest":
                            nhanKhau.setDatest(resultSet.getInt(i));
                            break;
                        case "quan":
                            nhanKhau.setQuan(resultSet.getString(i));
                            break;
                        case "phuong":
                            nhanKhau.setPhuong(resultSet.getString(i));
                            break;
                        case "soNha":
                            nhanKhau.setSoNha(resultSet.getString(i));
                            break;
                        case "tinh":
                            nhanKhau.setTinh(resultSet.getString(i));
                            break;
                        default:
                            break;
                    }
                }
                nhanKhau.setDiachi(nhanKhau.getSoNha() + ", " + nhanKhau.getPhuong() + ", " + nhanKhau.getQuan() + ", " + nhanKhau.getTinh());
                listNhanKhau.add(nhanKhau);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listNhanKhau;
    }

}

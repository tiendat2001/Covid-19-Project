package com.example.code.database;

import com.example.code.models.ThongTinCachLy;

import com.example.code.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListThongTinCachLy extends DBConnection {
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    ThongTinCachLy thongTinCachLy;
    public ObservableList<ThongTinCachLy> listThongTinCachLy = FXCollections.observableArrayList();

    public ListThongTinCachLy() {

    }

    public ObservableList<ThongTinCachLy> list(String columns, String condition) {
        String sql = " SELECT " + columns + " FROM thongtincachli t " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn + 1];
            while (resultSet.next()) {
                thongTinCachLy = new ThongTinCachLy();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "nhankhau_id":
                            thongTinCachLy.setNhankhau_id(resultSet.getInt(i));
                            break;
                        case "ten":
                            thongTinCachLy.setTen(resultSet.getString(i));
                            break;
                        case "ngaysinh":
                            thongTinCachLy.setNgaysinh(resultSet.getDate(i));
                            break;
                        case "gioitinh":
                            thongTinCachLy.setGioitinh(resultSet.getString(i));
                            break;
                        case "CMND":
                            thongTinCachLy.setCMND(resultSet.getString(i));
                            break;
                        case "diachi_id":
                            thongTinCachLy.setDiachi_id(resultSet.getInt(i));
                            break;
                        case "SDT":
                            thongTinCachLy.setSDT(resultSet.getString(i));
                            break;
                        case "datest":
                            thongTinCachLy.setDatest(resultSet.getInt(i));
                            break;
                        case "quan":
                            thongTinCachLy.setQuan(resultSet.getString(i));
                            break;
                        case "phuong":
                            thongTinCachLy.setPhuong(resultSet.getString(i));
                            break;
                        case "soNha":
                            thongTinCachLy.setSoNha(resultSet.getString(i));
                            break;
                        case "tinh":
                            thongTinCachLy.setTinh(resultSet.getString(i));
                            break;
//
                        case "thoigianbatdau":
                            thongTinCachLy.setThoigianbatdau(resultSet.getDate(i));
                            break;
                        case "thoigiancachli":
                            thongTinCachLy.setThoigiancachli(resultSet.getInt(i));
                            break;
                        case "thongtincachli_id":
                            thongTinCachLy.setThongtincachli_id(resultSet.getInt(i));
                            break;
                        case "diadiemcachli_id":
                            thongTinCachLy.setDiadiemcachli_id(resultSet.getInt(i));
                            break;
                        case "diadiem":
                            thongTinCachLy.setDiadiem(resultSet.getString(i));
                            break;
                        default:
                            break;
                    }
                }
                listThongTinCachLy.add(thongTinCachLy);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listThongTinCachLy;
    }
}

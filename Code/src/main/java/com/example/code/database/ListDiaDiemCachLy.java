package com.example.code.database;
import com.example.code.models.DiaDiemCachLy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDiaDiemCachLy extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    DiaDiemCachLy diaChi;
    public ObservableList<DiaDiemCachLy> listDiaChi =  FXCollections.observableArrayList();
    public ListDiaDiemCachLy(){

    }
    public ObservableList<DiaDiemCachLy> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM diadiemcachli d " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
                diaChi = new DiaDiemCachLy();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "diadiemcachli_id":
                            diaChi.setDiadiemcachli_id(resultSet.getInt(i));
                            break;
                        case "diadiem":
                            diaChi.setDiadiem(resultSet.getString(i));
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
package com.example.code.controllers;

import com.example.code.MainApplication;
import com.example.code.database.DBConnection;
import com.example.code.models.DiaChi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DiaChiController implements Initializable {
    public static ObservableList list = FXCollections.observableArrayList();
    public static int id;
    URL image = MainApplication.class.getClassLoader().getResource("delete.png");
    URL image1 = MainApplication.class.getClassLoader().getResource("verified.png");
    Image imageDelete = new Image(String.valueOf(image));
    Image imageVerified = new Image(String.valueOf(image1));
    @FXML
    private TableColumn<DiaChi, Integer> colId;

    @FXML
    private TableColumn<DiaChi, String> colPhuong;

    @FXML
    private TableColumn<DiaChi, String> colQuan;

    @FXML
    private TableColumn<DiaChi, String> colSonha;

    @FXML
    private TableColumn<DiaChi, String> colTinh;

    @FXML
    private TableView<DiaChi> tblAddress;

    @FXML
    private TextField txtPhuong;

    @FXML
    private TextField txtQuan;

    @FXML
    private TextField txtSonha;

    @FXML
    private TextField txtTinh;

    @FXML
    void suaDiachi(ActionEvent event) throws SQLException {
        String soNha = txtSonha.getText();
        String Phuong = txtPhuong.getText();
        String Quan = txtQuan.getText();
        String Tinh = txtTinh.getText();
        if(soNha.equals("") || Tinh.equals("") || Quan.equals("") || Phuong.equals("")){
            errorInform("Không được để trống dòng nào xin mời nhập lại");
        }
        else {
            Connection connection = (Connection) DBConnection.getConnection();

            String sqlInsertRevenue = "update diachi set tinh=?,quan=?,phuong=?,soNha=? where diachi_id = ?";
            PreparedStatement psRevenue = (PreparedStatement) connection.prepareStatement(sqlInsertRevenue);
            psRevenue.setString(1, Tinh);
            psRevenue.setString(2, Quan);
            psRevenue.setString(3, Phuong);
            psRevenue.setString(4, soNha);
            psRevenue.setInt(5, id);

            psRevenue.executeUpdate();
            UpdateTable();
        }
    }

    @FXML
    void themDiachi(ActionEvent event) throws SQLException {

        String soNha = txtSonha.getText();
        String Phuong = txtPhuong.getText();
        String Quan = txtQuan.getText();
        String Tinh = txtTinh.getText();


        if(soNha.equals("") || Tinh.equals("") || Quan.equals("") || Phuong.equals("")){
            errorInform("Không được để trống dòng nào xin mời nhập lại");
        }
        else {
            Connection connection = (Connection) DBConnection.getConnection();
            String sqlInsertRevenue = "INSERT INTO diachi(tinh, quan, phuong, soNha) VALUES(?, ?, ?, ?)";
            PreparedStatement psRevenue = (PreparedStatement) connection.prepareStatement(sqlInsertRevenue);
            psRevenue.setString(1, Tinh);
            psRevenue.setString(2, Quan);
            psRevenue.setString(3, Phuong);
            psRevenue.setString(4, soNha);

            psRevenue.executeUpdate();
            UpdateTable();
            successInform();
        }
    }

    @FXML
    void xoaDiachi(ActionEvent event) throws SQLException {

        Connection connection = (Connection) DBConnection.getConnection();

        String sqlInsertRevenue = "delete from diachi  where diachi_id = ?";
        PreparedStatement psRevenue = (PreparedStatement) connection.prepareStatement(sqlInsertRevenue);

        psRevenue.setInt(1, id);

        psRevenue.executeUpdate();
        UpdateTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }

    public void setLayout() {
        DiaChiController content = new DiaChiController();
    }

    public ObservableList<DiaChi> findAll() {
        ObservableList<DiaChi> listM = FXCollections.observableArrayList();
        String sql = "Select * from diachi ";
        Connection connection = DBConnection.getConnection();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql);
            while (resultSet.next()) {
                DiaChi diaChi = new DiaChi(
                        resultSet.getInt("diachi_id"),
                        resultSet.getString("tinh"),
                        resultSet.getString("quan"),
                        resultSet.getString("phuong"),
                        resultSet.getString("soNha"));
                listM.add(diaChi);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listM;
    }

    public void UpdateTable() {
        list= findAll();

        colId.setCellValueFactory(new PropertyValueFactory<DiaChi,Integer>("diachi_id"));
        colPhuong.setCellValueFactory(new PropertyValueFactory<DiaChi,String>("phuong"));
        colQuan.setCellValueFactory(new PropertyValueFactory<DiaChi,String>("quan"));
        colSonha.setCellValueFactory(new PropertyValueFactory<DiaChi, String>("soNha"));
        colTinh.setCellValueFactory(new PropertyValueFactory<DiaChi, String>("tinh"));

        tblAddress.setItems(list);

    }
    @FXML
    public void getSelected(){
        DiaChi selectedDiaChi = (DiaChi) tblAddress.getSelectionModel().getSelectedItem();
        txtSonha.setText(selectedDiaChi.getSoNha());
        txtPhuong.setText(selectedDiaChi.getPhuong());
        txtQuan.setText(selectedDiaChi.getQuan());
        txtTinh.setText(selectedDiaChi.getTinh());
        id = selectedDiaChi.getDiachi_id();
    }
    public void ClearText(){
        txtPhuong.setText("");
        txtTinh.setText("");
        txtQuan.setText("");
        txtSonha.setText("");
    }
    public void successInform(){
        Notifications notifications = Notifications.create()

                .text("Thêm thông tin thành công")
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_CENTER)
                .graphic(new ImageView(imageVerified));
        notifications.darkStyle();
        notifications.show();
    }
    public void errorInform(String string){
        Notifications notifications = Notifications.create()
                .title("Error")
                .text(string)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_CENTER)
                .graphic(new ImageView(imageDelete));
        notifications.darkStyle();
        notifications.show();
    }
}
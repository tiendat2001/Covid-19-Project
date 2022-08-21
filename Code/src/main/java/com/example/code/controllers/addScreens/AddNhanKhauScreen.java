package com.example.code.controllers.addScreens;

import com.example.code.database.DBConnection;
import com.example.code.database.ListDiaChi;
import com.example.code.models.DiaChi;
import com.example.code.models.NhanKhau;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNhanKhauScreen implements Initializable {
    @FXML
    private JFXTextField txtTen;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private JFXTextField txtCMND;

    @FXML
    private JFXTextField txtSDT;

    @FXML
    private DatePicker dob;

    @FXML
    private ComboBox<DiaChi> diaChi;

    @FXML
    private TableView<NhanKhau> tblList;
    Connection connection = DBConnection.getConnection();
    int diachi_id = 0;

    public void notification(boolean bool){
        URL image = (bool == true)?(getClass().getResource("/image/verified.png")):(getClass().getResource("/image/delete.png"));
        Image img = new Image(String.valueOf(image));
        String title = (bool == true)?("Hoàn thành"):("Lỗi");
        String text = (bool == true)?("Bạn đã thêm thành công!"):("Bạn phải điền đủ thông tin!");
        Notifications notifications = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_CENTER)
                .graphic(new ImageView(img));
        notifications.darkStyle();
        notifications.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Nam", "Nữ");
        gender.setPromptText("Giới tính");

        ObservableList<DiaChi> listAddress = new ListDiaChi().list("*", "WHERE 1=1");
        diaChi.setItems(listAddress);

        diaChi.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            diachi_id = newValue.getDiachi_id();
        });
    }

    public void setLayout() {
        AddNhanKhauScreen content = new AddNhanKhauScreen();
    }


    @FXML
    void addNhanKhau(ActionEvent event) throws SQLException {//check null
        if (txtTen.getText() == "" || dob.getValue() == null || txtCMND.getText() == "" || txtSDT.getText() == "") {
            notification(false);
        } else {
            //update
            try {
                String sqlInsertRevenue = "INSERT INTO nhankhau(ten, ngaysinh, gioitinh, CMND, diachi_id, SDT, datest) VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement psRevenue = (PreparedStatement) connection.prepareStatement(sqlInsertRevenue);
                psRevenue.setString(1, txtTen.getText());
                psRevenue.setDate(2, Date.valueOf(dob.getValue()));
                psRevenue.setString(3, gender.getValue());
                psRevenue.setString(4, txtCMND.getText());
                psRevenue.setInt(5, diachi_id);
                psRevenue.setString(6, txtSDT.getText());
                psRevenue.setInt(7, 0);
                psRevenue.executeUpdate();
                notification(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

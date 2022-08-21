package com.example.code.controllers.editScreens;

import com.example.code.database.DBConnection;
import com.example.code.database.ListDiaChi;
import com.example.code.database.ListNhanKhau;
import com.example.code.models.DiaChi;
import com.example.code.models.EditNhanKhauSession;
import com.example.code.models.NhanKhau;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditNhanKhauScreen implements Initializable {
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

    int nhankhau_id = 0;
    int diachi_id = 0;
    Connection connection = DBConnection.getConnection();

    public void notification(boolean bool){
        URL image = (bool == true)?(getClass().getResource("/image/verified.png")):(getClass().getResource("/image/delete.png"));
        Image img = new Image(String.valueOf(image));
        String title = (bool == true)?("Hoàn thành"):("Lỗi");
        String text = (bool == true)?("Bạn đã cập nhật thành công!"):("Bạn phải điền đủ thông tin!");
        Notifications notifications = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_CENTER)
                .graphic(new ImageView(img));
        notifications.darkStyle();
        notifications.show();
    }

    public void updateNhanKhau(ActionEvent actionEvent) throws SQLException {

        //check null
        if(txtTen.getText() == "" || dob.getValue() == null || txtCMND.getText() == "" || txtSDT.getText() == ""){
            notification(false);
        }
        else{
            //update
            try {
                String sqlUpdateRevenue = "UPDATE nhankhau SET ten = ?, ngaysinh = ?, gioitinh = ?, CMND = ?, diachi_id = ?, SDT = ? WHERE nhankhau_id = ?";
                PreparedStatement psRevenue = (PreparedStatement) connection.prepareStatement(sqlUpdateRevenue);
                psRevenue.setString(1, txtTen.getText());
                psRevenue.setDate(2, Date.valueOf(dob.getValue()));
                psRevenue.setString(3, gender.getValue());
                psRevenue.setString(4, txtCMND.getText());
                psRevenue.setInt(5, diachi_id);
                psRevenue.setString(6, txtSDT.getText());
                psRevenue.setInt(7, nhankhau_id);
                psRevenue.executeUpdate();
                notification(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> genderList = FXCollections.observableArrayList("Nam", "Nữ");
        ObservableList<DiaChi> diaChiList = new ListDiaChi().list("*", "WHERE 1=1");
        DiaChi selectedDiaChi;

        nhankhau_id = EditNhanKhauSession.getInstance();
        NhanKhau nhanKhau = new ListNhanKhau().list("*", "WHERE nhankhau_id = " + nhankhau_id).get(0);
        selectedDiaChi = new ListDiaChi().list("*", "WHERE diachi_id = " + nhanKhau.getDiachi_id()).get(0);
        diachi_id = selectedDiaChi.getDiachi_id();
        txtTen.setText(nhanKhau.getTen());
        txtCMND.setText(nhanKhau.getCMND());
        txtSDT.setText(nhanKhau.getSDT());
        gender.setItems(genderList);
        gender.setValue(nhanKhau.getGioitinh());
        diaChi.setItems(diaChiList);
        diaChi.setValue(selectedDiaChi);
        dob.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(nhanKhau.getNgaysinh())));

        diaChi.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            diachi_id = newValue.getDiachi_id();
        });
    }
}

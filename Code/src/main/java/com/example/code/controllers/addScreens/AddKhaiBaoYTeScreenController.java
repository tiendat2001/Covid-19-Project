package com.example.code.controllers.addScreens;

import com.example.code.database.DBConnection;
import com.example.code.database.ListNhanKhau;
import com.example.code.models.NhanKhau;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddKhaiBaoYTeScreenController implements Initializable {

    @FXML
    private Button btn_them;

    @FXML
    private CheckBox cb_cotrieuchung;

    @FXML
    private CheckBox cb_diquavungdich;

    @FXML
    private ComboBox<NhanKhau> cb_hovaten;

    @FXML
    private CheckBox cb_tiepxucnguoicobieuhien;

    @FXML
    private CheckBox cb_tiepxucnguoimaccovid;

    @FXML
    private CheckBox cb_tiepxucnguoivungdich;

    @FXML
    private Text lb_hovaten;

    @FXML
    private Text lbl_kbyt;

    int nhankhau_id = -1;


    @FXML
    private Button btn_return;

    @FXML
    private Button btn_update;


    //


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // khởi tạo comboBox cb_hovaten chứa list các nhân khẩu -> lấy được nhankhau_id với mỗi object NhanKhau
        ObservableList<NhanKhau> listNhanKhau = new ListNhanKhau().list("*","INNER JOIN diachi d ON d.diachi_id = n.diachi_id WHERE nhankhau_id not in (SELECT nhankhau_id FROM khaibaoyte)");
        cb_hovaten.setItems(listNhanKhau);
        cb_hovaten.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            nhankhau_id= newValue.getNhankhau_id();
        });


    }

    // NÚT THÊM
    @FXML
    void AddAction(ActionEvent event) throws SQLException {
       // Sử dụng nhân khẩu id để liên kết các bảng thêm vào bảng

        String sql = "insert into khaibaoyte(nhankhau_id,codiquavungdich,cotrieuchung,tiepxucnguoimaccovid,tiepxucnguoituvungdich, " +
                "tiepxucnguoicobieuhien) values(?, ?, ?, ?,?,?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

        // Nếu nhân khẩu id =-1 -> báo lỗi người dùng chưa nhập tên trong combobox
        if(nhankhau_id!=-1) {
            preparedStatement.setInt(1, nhankhau_id);


            // check xem box được tích chưa set String phù hợp
            if (cb_diquavungdich.isSelected())
                preparedStatement.setString(2, "Có");
            else preparedStatement.setString(2, "Không");

            if (cb_cotrieuchung.isSelected())
                preparedStatement.setString(3, "Có");
            else preparedStatement.setString(3, "Không");

            if (cb_tiepxucnguoimaccovid.isSelected())
                preparedStatement.setString(4, "Có");
            else preparedStatement.setString(4, "Không");

            if (cb_tiepxucnguoivungdich.isSelected())
                preparedStatement.setString(5, "Có");
            else preparedStatement.setString(5, "Không");

            if (cb_tiepxucnguoicobieuhien.isSelected())
                preparedStatement.setString(6, "Có");
            else preparedStatement.setString(6, "Không");

// Thông báo trạng thái sau khi thêm thành công
            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");

            alert.setHeaderText("Trạng thái:");
            alert.setContentText("Thêm thành công! Tải lại bảng để xem kết quả");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");

            alert.setHeaderText("Trạng thái:");
            alert.setContentText("Không thành công! Bạn chưa chọn người muốn thêm");
            alert.showAndWait();
        }

        // Reset lại các checkbox
        cb_tiepxucnguoicobieuhien.setSelected(false);
        cb_tiepxucnguoivungdich.setSelected(false);
        cb_tiepxucnguoimaccovid.setSelected(false);
        cb_diquavungdich.setSelected(false);
        cb_cotrieuchung.setSelected(false);

    }

    // NÚT QUAY LẠI
    @FXML
    void returnAction(ActionEvent event) {
        // Đóng màn hình hiện tại
        Stage stage = (Stage) btn_return.getScene().getWindow();
        stage.close();
    }


    public void updateAction(ActionEvent actionEvent) {
    }
}


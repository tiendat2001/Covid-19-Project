package com.example.code.controllers.editScreens;

import com.example.code.database.DBConnection;
import com.example.code.database.ListNhanKhau;
import com.example.code.models.EditKhaiBaoYTeSession;
import com.example.code.models.EditNhanKhauSession;
import com.example.code.models.KhaiBaoYTe;
import com.example.code.models.NhanKhau;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EditKhaiBaoYTeScreen implements Initializable {



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
    int khaibaoyte_idd=0;


    @FXML
    private Button btn_return;

    @FXML
    private Button btn_update;

    @FXML
    private Label lbl_hovaten;

    @FXML
    private Label ten;

    //


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // khởi tạo comboBox cb_hovaten chứa list các nhân khẩu -> lấy được nhankhau_id với mỗi object NhanKhau
//        ObservableList<NhanKhau> listNhanKhau = new ListNhanKhau().list("*","INNER JOIN diachi d ON d.diachi_id = n.diachi_id WHERE 1=1");
//        cb_hovaten.setItems(listNhanKhau);
//        cb_hovaten.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
//            nhankhau_id= newValue.getNhankhau_id();
//        });
        khaibaoyte_idd = EditKhaiBaoYTeSession.getInstance();
        System.out.println(khaibaoyte_idd);

        // Khởi tạo thông tin các checkbox
        String sql = "Select * from khaibaoyte k " +
                " INNER JOIN nhankhau n ON n.nhankhau_id = k.nhankhau_id " +
                " INNER JOIN diachi d ON n.diachi_id = d.diachi_id "+
                "WHERE k.khaibaoyte_id=" +khaibaoyte_idd ;
        Connection connection = DBConnection.getConnection();
        try {
            Statement Statement = connection.createStatement();
            ResultSet rs = Statement.executeQuery(sql);
            while (rs.next()) {

                ten.setText(rs.getString("ten"));

                if(rs.getString("codiquavungdich").equals("Có"))
                    cb_diquavungdich.setSelected(true);
                else  cb_diquavungdich.setSelected(false);

                if(rs.getString("cotrieuchung").equals("Có"))
                    cb_cotrieuchung.setSelected(true);
                else  cb_cotrieuchung.setSelected(false);

                if(rs.getString("tiepxucnguoimaccovid").equals("Có"))
                    cb_tiepxucnguoimaccovid.setSelected(true);
                else  cb_tiepxucnguoimaccovid.setSelected(false);

                if(rs.getString("tiepxucnguoituvungdich").equals("Có"))
                    cb_tiepxucnguoivungdich.setSelected(true);
                else  cb_tiepxucnguoivungdich.setSelected(false);

                if(rs.getString("tiepxucnguoicobieuhien").equals("Có"))
                    cb_tiepxucnguoicobieuhien.setSelected(true);
                else  cb_tiepxucnguoicobieuhien.setSelected(false);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    // NÚT UPDATE
    @FXML
    void updateAction(ActionEvent event) throws SQLException {
        String sql = "UPDATE `khaibaoyte` SET codiquavungdich=?,`cotrieuchung`=?,`tiepxucnguoimaccovid`=?,tiepxucnguoituvungdich=?," +
                "tiepxucnguoicobieuhien=?   WHERE khaibaoyte_id=? ";
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setInt(6, khaibaoyte_idd);


        // check xem box được tích chưa set String phù hợp
        if(cb_diquavungdich.isSelected())
            preparedStatement.setString(1,"Có" );
        else preparedStatement.setString(1,"Không" );

        if(cb_cotrieuchung.isSelected())
            preparedStatement.setString(2,"Có" );
        else preparedStatement.setString(2,"Không" );

        if(cb_tiepxucnguoimaccovid.isSelected())
            preparedStatement.setString(3,"Có" );
        else preparedStatement.setString(3,"Không" );

        if(cb_tiepxucnguoivungdich.isSelected())
            preparedStatement.setString(4,"Có" );
        else preparedStatement.setString(4,"Không" );

        if(cb_tiepxucnguoicobieuhien.isSelected())
            preparedStatement.setString(5,"Có" );
        else preparedStatement.setString(5,"Không" );

// Thông báo trạng thái sau khi update thành công
        preparedStatement.execute();
        // Kiểm tra xem update thành công chưa
        int i = preparedStatement.executeUpdate();
        if(i<=0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");

            alert.setHeaderText("Trạng thái:");
            alert.setContentText("Không thành công hoặc người này chưa có thông tin trên bảng!");
            alert.showAndWait();}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");

            alert.setHeaderText("Trạng thái:");
            alert.setContentText("Chỉnh sửa thành công! Tải lại bảng để xem kết quả");
            alert.showAndWait();

            // Reset lại các checkbox


        }
    }

    // NÚT QUAY LẠI
    @FXML
    void returnAction(ActionEvent event) {
        // Đóng màn hình hiện tại
        Stage stage = (Stage) btn_return.getScene().getWindow();
        stage.close();
    }


}


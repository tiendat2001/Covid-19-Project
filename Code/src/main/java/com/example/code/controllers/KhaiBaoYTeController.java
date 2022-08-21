package com.example.code.controllers;

import com.example.code.MainApplication;
import com.example.code.database.DBConnection;
import com.example.code.models.EditKhaiBaoYTeSession;
import com.example.code.models.EditNhanKhauSession;
import com.example.code.models.KhaiBaoYTe;
import com.example.code.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class KhaiBaoYTeController implements Initializable {
    public static ObservableList<KhaiBaoYTe> list = FXCollections.observableArrayList();


    @FXML
    private Button btn_them;

    @FXML
    private ChoiceBox<?> choice_boxKhaiBao;

    @FXML
    private TableColumn<?, ?> col_bieuhien;

    @FXML
    private TableColumn<?, ?> col_diqua;

    @FXML
    private TableColumn<?, ?> col_hoten;

    @FXML
    private TableColumn<?, ?> col_maccovid;

    @FXML
    private TableColumn<?, ?> col_phuong;

    @FXML
    private TableColumn<?, ?> col_quan;

    @FXML
    private TableColumn<?, ?> col_tiepxuc;

    @FXML
    private TableColumn<?, ?> col_soNha;

    @FXML
    private TableColumn<?, ?> col_trieuchung;

    @FXML
    private TableColumn<?, ?> col_vungdich;

    @FXML
    private TableView<KhaiBaoYTe> tbl_khaibaoyte;

    @FXML
    private AnchorPane khaibaoyte;

    @FXML
    private Button btn_refresh;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    int khaibaoyte_id=-1;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EditKhaiBaoYTeSession.cleanSession();
        // Tạo 1 list Object KhaiBaoYTe rồi lấy dữ liệu từ database thêm vào list
        list.clear();
        String sql = "Select * from khaibaoyte k " +
                " INNER JOIN nhankhau n ON n.nhankhau_id = k.nhankhau_id " +
                " INNER JOIN diachi d ON n.diachi_id = d.diachi_id ";
        Connection connection = DBConnection.getConnection();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new KhaiBaoYTe(
                        resultSet.getInt("khaibaoyte_id"),
                        resultSet.getString("ten"),
                        resultSet.getString("soNha"),
                        resultSet.getString("quan"),
                        resultSet.getString("phuong"),
                        resultSet.getString("codiquavungdich"),
                        resultSet.getString("cotrieuchung"),
                        resultSet.getString("tiepxucnguoimaccovid"),
                        resultSet.getString("tiepxucnguoituvungdich"),
                        resultSet.getString("tiepxucnguoicobieuhien"))
                );

            }

            // Hiện ra thông tin từng cột dựa theo từng thuộc tính của đối tượng KhaiBaoYTe
            col_hoten.setCellValueFactory(new PropertyValueFactory<>("ten"));
            col_soNha.setCellValueFactory(new PropertyValueFactory<>("soNha"));
            col_quan.setCellValueFactory(new PropertyValueFactory<>("quan"));
            col_phuong.setCellValueFactory(new PropertyValueFactory<>("phuong"));
            col_diqua.setCellValueFactory(new PropertyValueFactory<>("codiquavungdich"));
            col_trieuchung.setCellValueFactory(new PropertyValueFactory<>("cotrieuchung"));
            col_maccovid.setCellValueFactory(new PropertyValueFactory<>("tiepxucnguoimaccovid"));
            col_vungdich.setCellValueFactory(new PropertyValueFactory<>("tiepxucnguoituvungdich"));
            col_bieuhien.setCellValueFactory(new PropertyValueFactory<>("tiepxucnguoicobieuhien"));


            tbl_khaibaoyte.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    @FXML
    void handleMouseAction(MouseEvent event) {
        KhaiBaoYTe khaibaoyte = tbl_khaibaoyte.getSelectionModel().getSelectedItem();
        if(khaibaoyte != null) khaibaoyte_id = khaibaoyte.getKhaibaoyte_id();
        EditKhaiBaoYTeSession instance = new EditKhaiBaoYTeSession(khaibaoyte.getKhaibaoyte_id());

    }
    // NÚT THÊM ĐỂ HIỆN RA BẢNG THÊM
    @FXML
    void AddKhaiBaoYTe (ActionEvent event){
        Stage addScreen = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(MainApplication.class.getResource("addScreens/AddKhaiBaoYTeScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = 664;
        int height = 469;
        Stage current = (Stage) khaibaoyte.getScene().getWindow();
        Scene scene = new Scene(root, width, height);
        addScreen.setScene(scene);
        addScreen.show();
    }

    // NÚT EDIT ĐỂ HIỆN BẢNG CHỈNH SỬA
    @FXML
    void editAction(ActionEvent event) {
        if(khaibaoyte_id!=-1) {
            Stage editScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(MainApplication.class.getResource("editScreens/EditKhaiBaoYTeScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int width = 664;
            int height = 469;
            Stage current = (Stage) khaibaoyte.getScene().getWindow();
            Scene scene = new Scene(root, width, height);
            editScreen.setScene(scene);
            editScreen.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");

            alert.setHeaderText("Trạng thái:");
            alert.setContentText("Bạn chưa chọn người muốn sửa");
            alert.showAndWait();
        }
    }

    // Nút refresh lại bảng sau khi thêm xóa sửa (lấy lại từ cơ sở dữ liệu để show ra bảng mới )
    @FXML
    void refreshAction(ActionEvent event) {
        tbl_khaibaoyte.getItems().clear();
        String sql = "Select * from khaibaoyte k " +
                " INNER JOIN nhankhau n ON n.nhankhau_id = k.nhankhau_id " +
                    " INNER JOIN diachi d ON n.diachi_id = d.diachi_id ";
        Connection connection = DBConnection.getConnection();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new KhaiBaoYTe(
                        resultSet.getInt("khaibaoyte_id"),
                        resultSet.getString("ten"),
                        resultSet.getString("soNha"),
                        resultSet.getString("quan"),
                        resultSet.getString("phuong"),
                        resultSet.getString("codiquavungdich"),
                        resultSet.getString("cotrieuchung"),
                        resultSet.getString("tiepxucnguoimaccovid"),
                        resultSet.getString("tiepxucnguoituvungdich"),
                        resultSet.getString("tiepxucnguoicobieuhien"))
                );

            }
            col_hoten.setCellValueFactory(new PropertyValueFactory<>("ten"));
            col_soNha.setCellValueFactory(new PropertyValueFactory<>("soNha"));
            col_quan.setCellValueFactory(new PropertyValueFactory<>("quan"));
            col_phuong.setCellValueFactory(new PropertyValueFactory<>("phuong"));
            col_diqua.setCellValueFactory(new PropertyValueFactory<>("codiquavungdich"));
            col_trieuchung.setCellValueFactory(new PropertyValueFactory<>("cotrieuchung"));
            col_maccovid.setCellValueFactory(new PropertyValueFactory<>("tiepxucnguoimaccovid"));
            col_vungdich.setCellValueFactory(new PropertyValueFactory<>("tiepxucnguoituvungdich"));
            col_bieuhien.setCellValueFactory(new PropertyValueFactory<>("tiepxucnguoicobieuhien"));


            tbl_khaibaoyte.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // NÚT XÓA
    @FXML
    void deleteAction(ActionEvent event) {

        // tạo 1 đối tượng selectedKhaiBaoYTe nhận biết đối tượng đang được click trong bảng
        KhaiBaoYTe selectedKhaiBaoYTe = (KhaiBaoYTe) tbl_khaibaoyte.getSelectionModel().getSelectedItem();

        // Bảng thông báo muốn xóa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (selectedKhaiBaoYTe != null) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xóa ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();

            // xóa
            if (alert1.getResult() == ButtonType.YES) {
                int selectedId = selectedKhaiBaoYTe.getKhaibaoyte_id();

                String sql = "delete from khaibaoyte where khaibaoyte_id = ?";
                Connection connection = DBConnection.getConnection();
                try {
                    PreparedStatement preparedStatement =  connection.prepareStatement(sql);
                    preparedStatement.setInt(1, selectedId);

                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Xóa đối tượng khỏi list và refresh lại bảng
                list.remove(selectedKhaiBaoYTe);
                tbl_khaibaoyte.refresh();


                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Xóa thành công!");
                alert.show();
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
            alert.show();
        }
    }
    public void setLayout() {
        KhaiBaoYTeController content = new KhaiBaoYTeController();
    }

}






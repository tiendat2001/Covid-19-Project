package com.example.code.controllers;

import com.example.code.MainApplication;
import com.example.code.database.DBConnection;
import com.example.code.database.ListNhanKhau;
import com.example.code.models.NhanKhau;
import com.example.code.models.EditNhanKhauSession;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {
    @FXML
    private AnchorPane nhankhauscreen;

    @FXML
    private TableView<NhanKhau> tblList;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colCMND;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colDiaChi;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton deleteBtn;

    int nhankhau_id = 0;


    public void notification(boolean bool){
        URL image = (bool == true)?(getClass().getResource("/image/mooo.png")):(getClass().getResource("/image/delete.png"));
        Image img = new Image(String.valueOf(image));
        String title = (bool == true)?("Thành công"):("Thất bại");
        String text = (bool == true)?("Bạn đã xóa thành công!"):("Bạn không xóa được bản ghi này!");
        Notifications notifications = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_CENTER)
                .graphic(new ImageView(img));
        notifications.darkStyle();
        notifications.show();
    }

    @FXML
    void delRecord(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác Nhận");
        alert.setHeaderText("Bạn đang xóa 1 bản ghi!");
        alert.setContentText("Bạn có chắc muốn xóa không?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String sql = "DELETE FROM nhankhau WHERE nhankhau_id = " + nhankhau_id;
            Connection connection = DBConnection.getConnection();
            Statement con = null;
            try {
                con = connection.createStatement();
                con.execute(sql);
                notification(true);
                reloadTable();
            } catch (SQLException throwables) {
                notification(false);
            }
        }
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        NhanKhau nhanKhau = tblList.getSelectionModel().getSelectedItem();
        if(nhanKhau != null) nhankhau_id = nhanKhau.getNhankhau_id();
        EditNhanKhauSession instance = new EditNhanKhauSession(nhanKhau.getNhankhau_id());

    }

    @FXML
    void openAddScreen(ActionEvent event) {
        Stage addScreen = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(MainApplication.class.getResource("addScreens/AddNhanKhauScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = 678;
        int height = 505;
        Stage current = (Stage) nhankhauscreen.getScene().getWindow();
        Scene scene = new Scene(root, width, height);
        addScreen.setScene(scene);
        addScreen.show();
    }

    @FXML
    void openEditScreen(ActionEvent event) {
        if(EditNhanKhauSession.getInstance() != 0){
            Stage editScreen = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(MainApplication.class.getResource("editScreens/EditNhanKhauScreen.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int width = 678;
            int height = 505;
            Stage current = (Stage) nhankhauscreen.getScene().getWindow();
            Scene scene = new Scene(root, width, height);
            editScreen.setScene(scene);
            editScreen.show();
        }
        else{
            URL image = MainApplication.class.getClassLoader().getResource("delete.png");
            Image imageDelete = new Image(String.valueOf(image));
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Mời chọn hàng muốn sửa trước")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .graphic(new ImageView(imageDelete));
            notifications.darkStyle();
            notifications.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reloadTable();
        EditNhanKhauSession.cleanSession();
    }

    public void setLayout() {
        NhanKhauController content = new NhanKhauController();
    }

    public void reloadTable(){
        ObservableList<NhanKhau> nhanKhauList = new ListNhanKhau().list("*", " INNER JOIN diachi d ON d.diachi_id = n.diachi_id WHERE 1=1");
        setTable(nhanKhauList);
    }

    private void setTable(ObservableList<NhanKhau> list) {
        colId.setCellValueFactory(new PropertyValueFactory<>("nhankhau_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ten"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gioitinh"));
        colCMND.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        tblList.setItems(list);
    }

    public void refreshTable(ActionEvent actionEvent) {
        reloadTable();
    }
}

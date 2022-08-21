package com.example.code.controllers;

import com.example.code.database.DBConnection;
import com.example.code.database.ListDiaDiemCachLy;
import com.example.code.database.ListNhanKhau;
import com.example.code.database.ListThongTinCachLy;
import com.example.code.models.DiaDiemCachLy;
import com.example.code.models.NhanKhau;
import com.example.code.models.ThongTinCachLy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.lang.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static com.example.code.database.DBConnection.getConnection;

public class ThongTinCachLyController implements Initializable {

    @FXML
    private TableColumn<ThongTinCachLy,String> diadiem;

    @FXML
    private TableColumn<ThongTinCachLy,String> ten;

    @FXML
    private TableView<ThongTinCachLy> table;

    @FXML
    private TableColumn thoigianbatdau;

    @FXML
    private TableColumn <ThongTinCachLy,Integer>thoigiancachli;

    @FXML
    private TableColumn<ThongTinCachLy,Integer> thongtincachli_id;

    @FXML
    private JFXButton them;

    @FXML
    private JFXButton update;
    @FXML
    private DatePicker txt_thoigianbatdau;

    @FXML
    private JFXTextField txt_thoigiancachli;

    @FXML
    private ComboBox<DiaDiemCachLy> box_diadiem;

    @FXML
    private ComboBox<NhanKhau> box_hoten;

    @FXML
    private JFXButton xoathongtin;



    int selectedNhanKhauId = 0;
    int selectedDiaDiemCachLyId = 0;

    @FXML
    public void selectMouseClicked(MouseEvent mouseEvent) {
        //click vào đối tượng trong bảng trả về các thông tin
        ThongTinCachLy selectedThongTinCachLy = table.getSelectionModel().getSelectedItem();
        if(selectedThongTinCachLy != null){

            int selectedId = selectedThongTinCachLy.getThongtincachli_id();
            ObservableList<ThongTinCachLy> thongTinCachLy = new ListThongTinCachLy().list("*", "WHERE t.thongtincachli_id = " + selectedId);

            ObservableList<NhanKhau> listNhanKhau = new ListNhanKhau().list("*",
                    " INNER JOIN thongtincachli t ON t.nhankhau_id = n.nhankhau_id WHERE n.nhankhau_id = " + thongTinCachLy.get(0).getNhankhau_id());
            box_hoten.setItems(listNhanKhau);
            box_hoten.setValue(listNhanKhau.get(0));
            ObservableList<DiaDiemCachLy> listDiaDiemCachLy = new ListDiaDiemCachLy().list("*", "WHERE 1=1");
            ObservableList<DiaDiemCachLy> selectedDiaDiemCachLy = new ListDiaDiemCachLy().list("*",
                    " INNER JOIN thongtincachli t ON t.diadiemcachli_id = d.diadiemcachli_id WHERE t.diadiemcachli_id = " + thongTinCachLy.get(0).getDiadiemcachli_id());

            box_diadiem.setItems(listDiaDiemCachLy);
            box_diadiem.setValue(selectedDiaDiemCachLy.get(0));

            txt_thoigianbatdau.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(selectedThongTinCachLy.getThoigianbatdau())));
            txt_thoigiancachli.setText(String.valueOf(selectedThongTinCachLy.getThoigiancachli()));
            System.out.println(selectedThongTinCachLy.getDiadiemcachli_id());
        }
    }

    public void reLoadTable() {
        table.getItems().clear();
        ObservableList<ThongTinCachLy> listThongTinCachLy = new ListThongTinCachLy().list("*",
                " INNER JOIN nhankhau n ON t.nhankhau_id = n.nhankhau_id" +
                        " INNER JOIN diadiemcachli d ON t.diadiemcachli_id = d.diadiemcachli_id ");
        setTable(listThongTinCachLy);
        table.refresh();
        box_hoten.setValue(null);
        box_diadiem.setValue(null);
        txt_thoigianbatdau.setValue(null);
        txt_thoigiancachli.setText(null);
    }
    public void xoa_thongtin(ActionEvent actionEvent) {
        ThongTinCachLy selectedThongTinCachLy = table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (selectedThongTinCachLy != null) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xóa ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert1.showAndWait();

            if (alert1.getResult() == ButtonType.YES) {
                int selectedId = selectedThongTinCachLy.getThongtincachli_id();
                String sql = "delete from thongtincachli where thongtincachli_id = ?";
                Connection connection = DBConnection.getConnection();
                try {
                    PreparedStatement preparedStatement =  connection.prepareStatement(sql);
                    preparedStatement.setInt(1, selectedId);

                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                list.remove(selectedThongTinCachLy);
                table.refresh();

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Xóa thành công!!");

                alert.show();
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
            alert.show();
        }

    }

    ObservableList<ThongTinCachLy> list = FXCollections.observableArrayList();
    public void them_thongtin(ActionEvent event){
       if(box_hoten.getValue()==null || txt_thoigianbatdau.getValue()==null || txt_thoigiancachli.getText()==null || box_diadiem.getValue()==null){
           Alert thongbao = new Alert(Alert.AlertType.ERROR);
           thongbao.setContentText("Xin hãy điền đầy đủ thông tin");
           thongbao.show();
           return;
       }
       Connection conn= DBConnection.getConnection();
       try {
           PreparedStatement pst = conn.prepareStatement("INSERT INTO thongtincachli(nhankhau_id, thoigianbatdau, thoigiancachli, diadiemcachli_id) VALUES (?,?,?,?)");
                pst.setInt(1, selectedNhanKhauId);
                pst.setDate(2, Date.valueOf(txt_thoigianbatdau.getValue()));
                pst.setString(3, txt_thoigiancachli.getText());
                pst.setInt(4, selectedDiaDiemCachLyId);
                pst.execute();
            table.getItems().clear();
            String sql ="SELECT * FROM thongtincachli t " +
                    "              INNER JOIN nhankhau n ON t.nhankhau_id = n.nhankhau_id " +
                    "              INNER JOIN diadiemcachli d ON t.diadiemcachli_id = d.diadiemcachli_id ";

            try{
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()){
                    list.add(new ThongTinCachLy(rs.getInt("thongtincachli_id"),
                            rs.getString("ten"),
                            rs.getDate("thoigianbatdau"),
                            rs.getInt("thoigiancachli"),
                            rs.getString("diadiem")));

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
           reLoadTable();
       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<NhanKhau> listNhanKhau = new ListNhanKhau().list("*", "WHERE nhankhau_id NOT IN (SELECT nhankhau_id from thongtincachli )");
        box_hoten.setItems(listNhanKhau);
        box_hoten.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            selectedNhanKhauId = newValue.getNhankhau_id();
        });

        ObservableList<DiaDiemCachLy> listDiaDiemCachLy = new ListDiaDiemCachLy().list("*", "WHERE 1=1");
        box_diadiem.setItems(listDiaDiemCachLy);
        box_diadiem.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            selectedDiaDiemCachLyId = newValue.getDiadiemcachli_id();
        });
        table.getItems().clear();
        ObservableList<ThongTinCachLy> listThongTinCachLy = new ListThongTinCachLy().list("*",
                " INNER JOIN nhankhau n ON t.nhankhau_id = n.nhankhau_id" +
                        " INNER JOIN diadiemcachli d ON t.diadiemcachli_id = d.diadiemcachli_id ");
        setTable(listThongTinCachLy);
    }

    @FXML
    public void sua_thongtin(ActionEvent event) {
        ThongTinCachLy selectedThongTinCachLy = (ThongTinCachLy) table.getSelectionModel().getSelectedItem();
        if (selectedThongTinCachLy != null) {
            String sql = "update thongtincachli set thoigianbatdau= ?, thoigiancachli=?, diadiemcachli_id=? where thongtincachli_id = ?";
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setDate(1,Date.valueOf(txt_thoigianbatdau.getValue()));
                pst.setString(2,txt_thoigiancachli.getText());
                pst.setInt(3,selectedDiaDiemCachLyId);
                pst.setInt(4,selectedThongTinCachLy.getThongtincachli_id());
                pst.execute();
                reLoadTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sửa thành công!!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("bạn hãy click chọn vào đối tượng cần update ở bảng bên!");
            alert.show();
        }
    }
    private void setTable(ObservableList<ThongTinCachLy> list) {
        thongtincachli_id.setCellValueFactory(new PropertyValueFactory<>("thongtincachli_id"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        thoigianbatdau.setCellValueFactory(new PropertyValueFactory<>("thoigianbatdau"));
        thoigiancachli.setCellValueFactory(new PropertyValueFactory<>("thoigiancachli"));
        diadiem.setCellValueFactory(new PropertyValueFactory<>("diadiem"));
        thongtincachli_id.setStyle("-fx-alignment: CENTER");
        ten.setStyle("-fx-alignment: CENTER");
        thoigianbatdau.setStyle("-fx-alignment: CENTER");
        thoigiancachli.setStyle("-fx-alignment: CENTER");
        diadiem.setStyle("-fx-alignment: CENTER");

        table.setItems(list);
    }

    public void setLayout(){
        ThongTinCachLyController content = new ThongTinCachLyController();
    }
}
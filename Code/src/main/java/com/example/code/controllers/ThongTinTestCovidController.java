package com.example.code.controllers;

import com.example.code.database.DBConnection;
import com.example.code.models.thongTinTest;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;

public class ThongTinTestCovidController implements Initializable {
    public static ObservableList list = FXCollections.observableArrayList();

    @FXML
    private Label IdLable;

    @FXML
    private ComboBox <String> HTTest_txt;

    @FXML
    private ComboBox<thongTinTest> HoTen_txt;

    @FXML
    private ComboBox <String> KQTest_txt;


    @FXML
    private DatePicker TGTest_txt;

    @FXML
    private TableColumn<?, ?> NhanKhauId;

    @FXML
    private TableColumn<thongTinTest, String> HoTen;

    @FXML
    private TableColumn<thongTinTest, Integer> HTTest;

    @FXML
    private TableColumn<thongTinTest, Integer> TGTest;

    @FXML
    private TableColumn<thongTinTest, String> KQTest;
    

    @FXML
    private JFXButton bntAdd;

    @FXML
    private JFXButton bntDelete1;

    @FXML
    private JFXButton bntUpdate1;

    @FXML
    private JFXTextField findTextField;

    @FXML
    private TableView<?> table;

    @FXML
    void addOnClick(ActionEvent event) {
        Alert alert = new Alert((Alert.AlertType.ERROR));
        if (HoTen_txt.getValue() == null || HTTest_txt.getValue() == null || TGTest_txt.getValue() == null || KQTest_txt.getValue() == null) {
            alert.setContentText("Bạn phải điền đầy đủ thông tin!");
            alert.show();
            return;
        }
        String sql = "insert into thongtintest(nhankhau_id, thoigiantest, hinhthuctest, ketquatest) values(?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, getcbNhanKhauId());
            preparedStatement.setDate(2, Date.valueOf(TGTest_txt.getValue()));
            preparedStatement.setString(3, HTTest_txt.getValue());
            preparedStatement.setString(4, KQTest_txt.getValue());
            preparedStatement.execute();

            reLoadTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm thành công!!");
        alert.show();
    }

    @FXML
    void deleteOnClick(ActionEvent event) {
        thongTinTest selectedThongTinTest = (thongTinTest) table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (selectedThongTinTest != null) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xóa ?", ButtonType.YES, ButtonType.CANCEL);
            alert1.showAndWait();

            if (alert1.getResult() == ButtonType.YES) {
                int selectedId = selectedThongTinTest.getThongTinTestId();
                String sql = "delete from thongtintest where thongtintest_id = ?";
                Connection connection = DBConnection.getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, selectedId);

                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                reLoadTable();

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

    @FXML
    void updateOnClick(ActionEvent event) {
        thongTinTest selectedThongTinTest = (thongTinTest) table.getSelectionModel().getSelectedItem();
        if (selectedThongTinTest != null) {
            String sql = "update thongtintest set nhankhau_id =?, thoigiantest=?, hinhthuctest=?, ketquatest=? where thongtintest_id = ?";
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
                preparedStatement.setInt(1, getcbNhanKhauId());
                preparedStatement.setDate(2, Date.valueOf(TGTest_txt.getValue()));
                preparedStatement.setString(3, HTTest_txt.getValue());
                preparedStatement.setString(4, KQTest_txt.getValue());
                preparedStatement.setInt(5, selectedThongTinTest.getThongTinTestId());
                preparedStatement.execute();

                reLoadTable();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Sửa thành công!!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
            alert.show();
        }
    }

    @FXML
    public void selectMouseClicked(MouseEvent mouseEvent) {
        //click vào đối tượng trong bảng trả về các thông tin
        thongTinTest selectedThongTinTest = (thongTinTest) table.getSelectionModel().getSelectedItem();
        if (selectedThongTinTest != null) {
            HoTen_txt.setValue(selectedThongTinTest);
            KQTest_txt.setValue(selectedThongTinTest.getKetQuaTest());
            HTTest_txt.setValue(selectedThongTinTest.getHinhThucTest());
            TGTest_txt.setValue(selectedThongTinTest.getThoiGianTest().toLocalDate());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //không cho thay đổi kích thước cột
        NhanKhauId.setResizable(false);
        HoTen.setResizable(false);
        HTTest.setResizable(false);
        TGTest.setResizable(false);
        KQTest.setResizable(false);

        //lấy đối tượng có trong bảng nhân khẩu và cho vào combobox HoTen_txt
        ObservableList<thongTinTest> NameList = getNameList();
        HoTen_txt.setItems(NameList);
        HoTen_txt.setConverter(new StringConverter<thongTinTest>() {
            @Override
            public String toString(thongTinTest object) {
                if (object != null) {
                    return object.getTen();
                }
                return null;
            }

            @Override
            public thongTinTest fromString(String s) {
                return HoTen_txt.getValue();
            }
        });
        //hiển thị nhân khẩu ID lên lable và để test hàm là chình
        HoTen_txt.valueProperty().addListener((obs, oldval, newval) -> {
            if (newval != null) {
                IdLable.setText(String.valueOf(newval.getNhanKhauId()));
                IdLable.setVisible(true);
//                System.out.println(" thông tin test: " + newval.getTen()
//                        + ". ID: " + newval.getNhanKhauId());
            }
        });

        HTTest_txt.getItems().addAll("Test nhanh", "RT-PCR");
        KQTest_txt.getItems().addAll("Âm tính", "Dương tính");

        //hiển thị các thông tin lên bảng
        list = FXCollections.observableArrayList();
        reLoadTable();
        NhanKhauId.setCellValueFactory(new PropertyValueFactory<>("nhanKhauId"));
        HoTen.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        HTTest.setCellValueFactory(new PropertyValueFactory<>("hinhThucTest"));
        TGTest.setCellValueFactory(new PropertyValueFactory<>("thoiGianTest"));
        KQTest.setCellValueFactory(new PropertyValueFactory<>("ketQuaTest"));
        
        //set text center in table view
        NhanKhauId.setStyle("-fx-alignment: CENTER;");
        HoTen.setStyle("-fx-alignment: CENTER;");
        HTTest.setStyle("-fx-alignment: CENTER;");
        TGTest.setStyle("-fx-alignment: CENTER;");
        KQTest.setStyle("-fx-alignment: CENTER;");

    }

    public void reLoadTable() {
        list.removeAll();
        table.getItems().clear();
        String sql2 = "Select * from nhankhau, thongtintest where nhankhau.nhankhau_id = thongtintest.nhankhau_id ";
        Connection connection = DBConnection.getConnection();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql2);
            while (resultSet.next()) {
                list.add(new thongTinTest(resultSet.getString("ten"),
                        resultSet.getString("hinhthuctest"),
                        resultSet.getString("ketquatest"),
                        resultSet.getDate("thoigiantest"),
                        resultSet.getInt("thongtintest_id"),
                        resultSet.getInt("nhankhau_id")));

            }
            table.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table.refresh();

        //clear các trường combobox
        HoTen_txt.setValue(null);
        HTTest_txt.setValue(null);
        TGTest_txt.setValue(null);
        KQTest_txt.setValue(null);
    }

    public static ObservableList<thongTinTest> getNameList() {
        //danh sách nhankhau_id và tên trong bảng nhân khẩu
        //hàm này dùng để lấy thông tin vào đưa vào combobox HoTen_txt
        ObservableList<thongTinTest> list = FXCollections.observableArrayList();
        String sql = "select ten, nhankhau_id from nhankhau ";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new thongTinTest(resultSet.getInt("nhankhau_id"), resultSet.getString("ten")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public int getcbNhanKhauId() {
        //lấy nhankhau_id từ combobox HoTen_txt
        thongTinTest t1 = HoTen_txt.getSelectionModel().getSelectedItem();
        if (t1 != null)
            return t1.getNhanKhauId();
        else return 0;
    }

    public void setLayout() {
        ThongTinTestCovidController content = new ThongTinTestCovidController();
    }
}

package com.example.code.controllers.editScreens;

import com.example.code.MainApplication;
import com.example.code.controllers.ThongKeTiemChungController;
import com.example.code.database.DBConnection;
import com.example.code.database.ListNhanKhau;
import com.example.code.models.NhanKhau;
import com.example.code.models.thongKeTiemChung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.ResourceBundle;

public class EditThongKeTiemChungScreen implements Initializable {
    private Stage stage;
    private Double x,y;

    @FXML
    private CheckBox CheckInject;
    @FXML
    private ChoiceBox<String> typeOfFirstVaccine,typeOfSecondVaccine,numOfInject;
    @FXML
    private DatePicker dateOfFirstVaccine,dateOfSecondVaccine;
    @FXML
    private ComboBox<NhanKhau> nhanKhau;
    int nhanKhauID;

    String nameProp = ThongKeTiemChungController.transferDataName();
    int idProp = ThongKeTiemChungController.transferDataID();

    URL image = MainApplication.class.getClassLoader().getResource("delete.png");
    URL image1 = MainApplication.class.getClassLoader().getResource("verified.png");
    Image imageDelete = new Image(String.valueOf(image));
    Image imageVerified = new Image(String.valueOf(image1));
    ObservableList<String> numInject = FXCollections.observableArrayList("0","1","2");

    ObservableList<String> typeOfVaccine = FXCollections.observableArrayList("Chưa tiêm","AstraZeneca","SPUTNIK V","Vero Cell","Pfizer","Moderna");
    @FXML
    private void Close(MouseEvent event){
        ThongKeTiemChungController.restartNameProp();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.hide();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<NhanKhau> listNhanKhau = new ListNhanKhau().list("*"," WHERE 1=1");

        nhanKhau.setItems(listNhanKhau);


        numOfInject.setItems(numInject);
        typeOfFirstVaccine.setItems(typeOfVaccine);
        typeOfSecondVaccine.setItems(typeOfVaccine);

        // get data from ThongKeConTroller
        getData();
    }
    @FXML
    public void DisableText(){
        if(numOfInject.getValue().equals("0")){
            ClearText();
            dateOfFirstVaccine.setValue(null);
            dateOfSecondVaccine.setValue(null);
            CheckInject.setSelected(false);
            CheckInject.setDisable(true);
            typeOfFirstVaccine.setValue("Chưa tiêm");
            typeOfFirstVaccine.setDisable(true);
            typeOfSecondVaccine.setValue("Chưa tiêm");
            typeOfSecondVaccine.setDisable(true);
            dateOfFirstVaccine.setDisable(true);
            dateOfSecondVaccine.setDisable(true);

        }
        if(numOfInject.getValue().equals("1")){
            ClearText();
            ObservableList<NhanKhau> nhanKhaus = new ListNhanKhau().list("*"," WHERE ten Like'%"+nameProp+"%'");
            nhanKhau.setValue(nhanKhaus.get(0));
            thongKeTiemChung thongKeTiemChung = new thongKeTiemChung();
            try {
                thongKeTiemChung = findThongKeTiemChung();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            typeOfFirstVaccine.setValue(thongKeTiemChung.getLoaivacxinlan1());
            typeOfFirstVaccine.setDisable(false);

            dateOfFirstVaccine.setDisable(false);
            if(thongKeTiemChung.getThoigiantiemlan1() !=null){
                LocalDate date1 = ((java.sql.Date) thongKeTiemChung.getThoigiantiemlan1()).toLocalDate();
                dateOfFirstVaccine.setValue(date1);
            }
            CheckInject.setSelected(true);

            typeOfSecondVaccine.setValue("Chưa tiêm");
            typeOfSecondVaccine.setDisable(true);
            dateOfSecondVaccine.setValue(null);
            dateOfSecondVaccine.setDisable(true);

        }
        if(numOfInject.getValue().equals("2")){
            ClearText();
            ObservableList<NhanKhau> nhanKhaus = new ListNhanKhau().list("*"," WHERE ten Like'%"+nameProp+"%'");
            nhanKhau.setValue(nhanKhaus.get(0));
            thongKeTiemChung thongKeTiemChung = new thongKeTiemChung();
            try {
                thongKeTiemChung = findThongKeTiemChung();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            typeOfFirstVaccine.setValue(thongKeTiemChung.getLoaivacxinlan1());
            typeOfSecondVaccine.setValue(thongKeTiemChung.getLoaivacxinlan2());
            typeOfFirstVaccine.setDisable(false);
            typeOfSecondVaccine.setDisable(false);
            dateOfFirstVaccine.setDisable(false);
            dateOfSecondVaccine.setDisable(false);
            CheckInject.setDisable(false);
            CheckInject.setSelected(true);

        }
    }

    public void editOnClick(){
        int numberOfInject;

        String typeOf1Vaccine;
        String typeOf2Vaccine;
        Date day1;
        Date day2;


        int check =0;


        if(CheckInject.isSelected()){
            check = 1;
        }
        if(nhanKhau.getValue()==null && !CheckInject.isSelected() && typeOfFirstVaccine.getValue()== null && typeOfSecondVaccine.getValue() ==null && numOfInject.getValue() == null && dateOfFirstVaccine.getValue()==null && dateOfSecondVaccine.getValue() == null){
            errorInform("Không được để trống dòng nào xin mời nhập lại");
        }


        else{
            Connection connection = (Connection) DBConnection.getConnection();
            String sql = "update thongketiemchung set  datiem=? , somuitiem=?, loaivacxinlan1=?,thoigiantiemlan1=?,loaivacxinlan2=?,thoigiantiemlan2=? where thongke_id=?";

            try{

                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

                numberOfInject = Integer.parseInt(numOfInject.getValue());
                typeOf1Vaccine = typeOfFirstVaccine.getValue();
                typeOf2Vaccine = typeOfSecondVaccine.getValue();


                    if(numOfInject.getValue().equals("2")){
                        String sql1 ="update nhankhau set datest=1 where nhankhau_id = ?";
                        PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql1);
                        if(typeOfFirstVaccine.getValue().equals("") ||typeOfSecondVaccine.getValue().equals("")|| dateOfSecondVaccine.getValue() == null || dateOfFirstVaccine.getValue() == null ||nhanKhau.getValue() == null){
                            errorInform("Không được để trống dòng nhân khẩu");
                        }
                       else{

                            day1 = java.sql.Date.valueOf(dateOfFirstVaccine.getValue());
                            day2 = java.sql.Date.valueOf(dateOfSecondVaccine.getValue());

                            preparedStatement.setInt(1, check);
                            preparedStatement.setInt(2, numberOfInject);
                            preparedStatement.setString(3, typeOf1Vaccine);
                            preparedStatement.setDate(4, day1);
                            preparedStatement.setString(5, typeOf2Vaccine);
                            preparedStatement.setDate(6, day2);
                            preparedStatement.setInt(7, idProp);
                            preparedStatement.execute();

                            preparedStatement1.setInt(1,nhanKhauID);
                            preparedStatement1.execute();


                            successInform();
                        }
                    }
                    if(numOfInject.getValue().equals("1")){
                        String sql1 ="update nhankhau set datest=1 where nhankhau_id = ?";
                        PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql1);
                        if(typeOfFirstVaccine.getValue().equals("") || dateOfFirstVaccine.getValue() == null ||nhanKhau.getValue() == null){
                            errorInform("Không được để trống dòng");
                        }
                        else{
                            day1 = java.sql.Date.valueOf(dateOfFirstVaccine.getValue());
                            preparedStatement.setInt(1, check);
                            preparedStatement.setInt(2, numberOfInject);
                            preparedStatement.setString(3, typeOf1Vaccine);
                            preparedStatement.setDate(4, day1);
                            preparedStatement.setString(5, typeOf2Vaccine);
                            preparedStatement.setNull(6,java.sql.Types.DATE);
                            preparedStatement.setInt(7, idProp);
                            preparedStatement.execute();

                            preparedStatement1.setInt(1,nhanKhauID);
                            preparedStatement1.execute();

                            successInform();
                        }
                    }

                if(check ==0){
                    String sql1 ="update nhankhau set datest=0 where nhankhau_id = ?";
                    PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql1);
                    if( nhanKhau.getValue() == null){
                        errorInform("Không được để trống dòng nhân khẩu");
                    }
                    else{
                        preparedStatement.setInt(1, check);
                        preparedStatement.setInt(2, 0);
                        preparedStatement.setString(3, typeOf1Vaccine);
                        preparedStatement.setNull(4,java.sql.Types.DATE);
                        preparedStatement.setString(5, typeOf2Vaccine);
                        preparedStatement.setNull(6,java.sql.Types.DATE);
                        preparedStatement.setInt(7, idProp);
                        preparedStatement.execute();

                        preparedStatement1.setInt(1,nhanKhauID);
                        preparedStatement1.execute();
                        successInform();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
    }
    public void successInform(){
        Notifications notifications = Notifications.create()
                .title("Error")
                .text("Sửa thông tin thành công")
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
    public thongKeTiemChung findThongKeTiemChung() throws SQLException {
        int idProp = ThongKeTiemChungController.transferDataID();

        thongKeTiemChung thongKeTiemChung = new thongKeTiemChung();

        String sql = "Select * from  thongketiemchung where thongke_id ="+idProp;

        Connection connection = DBConnection.getConnection();
        try {
            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql);
            while (resultSet.next()) {
                thongKeTiemChung = new thongKeTiemChung(
                        resultSet.getInt("nhankhau_id"),
                        resultSet.getInt("thongke_id"),
                        resultSet.getInt("datiem"),
                        resultSet.getInt("somuitiem"),
                        resultSet.getString("loaivacxinlan1"),
                        resultSet.getString("loaivacxinlan2"),
                        resultSet.getDate("thoigiantiemlan1"),
                        resultSet.getDate("thoigiantiemlan2"));

            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return thongKeTiemChung;
    }
    public void ClearText(){
        typeOfFirstVaccine.setValue("");
        typeOfSecondVaccine.setValue("");

    }
    public void getData(){
        ObservableList<NhanKhau> nhanKhaus = new ListNhanKhau().list("*"," WHERE ten Like'%"+nameProp+"%'");
        nhanKhau.setValue(nhanKhaus.get(0));
        thongKeTiemChung thongKeTiemChung = new thongKeTiemChung();
        try {
            thongKeTiemChung = findThongKeTiemChung();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        typeOfFirstVaccine.setValue(thongKeTiemChung.getLoaivacxinlan1());
        typeOfSecondVaccine.setValue(thongKeTiemChung.getLoaivacxinlan2());
        if(thongKeTiemChung.getDatiem() == 1){
            CheckInject.setSelected(true);
        }
        numOfInject.setValue(String.valueOf(thongKeTiemChung.getSomuitiem()));

        if(thongKeTiemChung.getThoigiantiemlan1() !=null){
            LocalDate date1 = ((java.sql.Date) thongKeTiemChung.getThoigiantiemlan1()).toLocalDate();
            dateOfFirstVaccine.setValue(date1);
        }


        if(thongKeTiemChung.getThoigiantiemlan2() != null){
            LocalDate date2 = ((java.sql.Date) thongKeTiemChung.getThoigiantiemlan2()).toLocalDate();
            dateOfSecondVaccine.setValue(date2);
        }
        nhanKhauID = thongKeTiemChung.getNhankhau_id();
    }
    @FXML
    public void deleteDate1(){
        dateOfFirstVaccine.setValue(null);
    }
    @FXML
    public void deleteDate2(){
        dateOfSecondVaccine.setValue(null);
    }

}
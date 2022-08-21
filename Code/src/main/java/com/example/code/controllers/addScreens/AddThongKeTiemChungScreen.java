package com.example.code.controllers.addScreens;

import com.example.code.MainApplication;
import com.example.code.database.DBConnection;
import com.example.code.database.ListNhanKhau;

import com.example.code.models.NhanKhau;
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
import java.util.ResourceBundle;

public class AddThongKeTiemChungScreen implements Initializable {
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

    URL image = MainApplication.class.getClassLoader().getResource("delete.png");
    URL image1 = MainApplication.class.getClassLoader().getResource("verified.png");
    Image imageDelete = new Image(String.valueOf(image));
    Image imageVerified = new Image(String.valueOf(image1));
    ObservableList<String> numInject = FXCollections.observableArrayList("0","1","2");

    ObservableList<String> typeOfVaccine = FXCollections.observableArrayList("Chưa tiêm","AstraZeneca","SPUTNIK V","Vero Cell","Pfizer","Moderna");
    @FXML
    private void Close(MouseEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.hide();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<NhanKhau> listNhanKhau = new ListNhanKhau().list("*"," WHERE n.nhankhau_id NOT IN (SELECT nhankhau_id FROM thongketiemchung)");
        nhanKhau.setItems(listNhanKhau);
        nhanKhau.getSelectionModel().selectedItemProperty().addListener((option, oldValue, newValue) -> {
            nhanKhauID= newValue.getNhankhau_id();
        });

        numOfInject.setItems(numInject);
        numOfInject.setValue("0");
        typeOfFirstVaccine.setItems(typeOfVaccine);
        typeOfSecondVaccine.setItems(typeOfVaccine);
    }

    @FXML
    public void DisableText(){
        if(numOfInject.getValue().equals("0")){
            ClearText();
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
            typeOfFirstVaccine.setDisable(false);

            dateOfFirstVaccine.setDisable(false);
            CheckInject.setSelected(true);

            typeOfSecondVaccine.setValue("Chưa tiêm");
            typeOfSecondVaccine.setDisable(true);
            dateOfSecondVaccine.setDisable(true);

        }
        if(numOfInject.getValue().equals("2")){
            ClearText();
            typeOfFirstVaccine.setDisable(false);
            typeOfSecondVaccine.setDisable(false);
            dateOfFirstVaccine.setDisable(false);
            dateOfSecondVaccine.setDisable(false);

            CheckInject.setSelected(true);

        }
    }

    public void addOnClick(){
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
            String sql = "insert into thongketiemchung(nhankhau_id, datiem, somuitiem,loaivacxinlan1,thoigiantiemlan1,loaivacxinlan2,thoigiantiemlan2) values( ?, ?, ?,?,?,?,?)";
            String sql1 ="update nhankhau set datest=1 where nhankhau_id = ?";
            try{
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
                PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql1);
                numberOfInject = Integer.parseInt(numOfInject.getValue());
                typeOf1Vaccine = typeOfFirstVaccine.getValue();
                typeOf2Vaccine = typeOfSecondVaccine.getValue();


                    if(numberOfInject == 2){
                        if(typeOfFirstVaccine.getValue().equals("") ||typeOfSecondVaccine.getValue().equals("")|| dateOfSecondVaccine.getValue() == null || dateOfFirstVaccine.getValue() == null ||nhanKhau.getValue() == null){
                            errorInform("Không được để trống dòng nhân khẩu");
                        }
                        else{
                            day1 = java.sql.Date.valueOf(dateOfFirstVaccine.getValue());
                            day2 = java.sql.Date.valueOf(dateOfSecondVaccine.getValue());
                            preparedStatement.setInt(1, nhanKhauID);
                            preparedStatement.setInt(2, check);
                            preparedStatement.setInt(3, numberOfInject);
                            preparedStatement.setString(4, typeOf1Vaccine);
                            preparedStatement.setDate(5, day1);
                            preparedStatement.setString(6, typeOf2Vaccine);
                            preparedStatement.setDate(7, day2);
                            preparedStatement.execute();

                            preparedStatement1.setInt(1,nhanKhauID);
                            preparedStatement1.execute();
                            successInform();
                        }
                    }
                    if(numberOfInject == 1){
                        if(typeOfFirstVaccine.getValue().equals("") || dateOfFirstVaccine.getValue() == null ||nhanKhau.getValue() == null){
                            errorInform("Không được để trống dòng");
                        }
                        else{
                            day1 = java.sql.Date.valueOf(dateOfFirstVaccine.getValue());
                            preparedStatement.setInt(1, nhanKhauID);
                            preparedStatement.setInt(2, check);
                            preparedStatement.setInt(3, numberOfInject);
                            preparedStatement.setString(4, typeOf1Vaccine);
                            preparedStatement.setDate(5, day1);
                            preparedStatement.setString(6, typeOf2Vaccine);
                            preparedStatement.setNull(7,java.sql.Types.DATE);
                            preparedStatement.execute();


                            preparedStatement1.setInt(1,nhanKhauID);
                            preparedStatement1.execute();

                            successInform();
                        }
                    }

                if(check ==0){
                    if( nhanKhau.getValue() == null){
                        errorInform("Không được để trống dòng nhân khẩu");
                    }
                    else{
                        preparedStatement.setInt(1, nhanKhauID);
                        preparedStatement.setInt(2, check);
                        preparedStatement.setInt(3, 0);
                        preparedStatement.setString(4, typeOf1Vaccine);
                        preparedStatement.setNull(5,java.sql.Types.DATE);
                        preparedStatement.setString(6, typeOf2Vaccine);
                        preparedStatement.setNull(7,java.sql.Types.DATE);
                        preparedStatement.execute();
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
    public void ClearText(){
        typeOfFirstVaccine.setValue("");
        typeOfSecondVaccine.setValue("");

    }

}

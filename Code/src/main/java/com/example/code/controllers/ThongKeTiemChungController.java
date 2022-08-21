package com.example.code.controllers;

import com.example.code.MainApplication;
import com.example.code.database.DBConnection;
import com.example.code.models.thongKeTiemChung;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class ThongKeTiemChungController implements Initializable {
   private Stage stage;
   private Double x,y;

   public static ObservableList list = FXCollections.observableArrayList();
   static String nameProp ="";
   static int idProp = 0;
   @FXML
   private TableColumn<thongKeTiemChung, Integer> nhanKhauID;
   @FXML
   private TableColumn<thongKeTiemChung, Integer> thongKeID;
   @FXML
   private TableColumn<thongKeTiemChung, String> daTiem;
   @FXML
   private TableColumn<thongKeTiemChung, Integer> soMuiTiem;
   @FXML
   private TableColumn<thongKeTiemChung, String> loaiVaccine1;
   @FXML
   private TableColumn<thongKeTiemChung, String> loaiVaccine2;
   @FXML
   private TableColumn<thongKeTiemChung, Date> thoiGian1;
   @FXML
   private TableColumn<thongKeTiemChung, Date> thoiGian2;
   @FXML
   private TableView table;
   public int index =0;
   URL image = MainApplication.class.getClassLoader().getResource("delete.png");
   Image imageDelete = new Image(String.valueOf(image));

   public static void restartNameProp(){
       nameProp = "";
   }
   public void editScene(){
      if(nameProp.equals("")){
         Notifications notifications = Notifications.create()
                 .title("Error")
                 .text("Mời chọn hàng muốn sửa trước")
                 .hideAfter(Duration.seconds(3))
                 .position(Pos.TOP_CENTER)
                 .graphic(new ImageView(imageDelete));
         notifications.darkStyle();
         notifications.show();
      }
      else {
         Stage addScreen = new Stage();
         Parent root = null;
         try {
            root = FXMLLoader.load(MainApplication.class.getResource("editScreens/EditThongKeTiemChungScreen.fxml"));
         } catch (IOException e) {
            e.printStackTrace();
         }
         int width = 600;
         int height = 400;
         root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
         });
         root.setOnMouseDragged(event -> {

            addScreen.setX(event.getScreenX() - x);
            addScreen.setY(event.getScreenY() - y);

         });
         Scene scene = new Scene(root, width, height);
         addScreen.setScene(scene);
         addScreen.show();
      }
   }

   public void addScene(){
      Stage addScreen = new Stage();
      Parent root = null;
      try {
         root = FXMLLoader.load(MainApplication.class.getResource("addScreens/AddThongKeTiemChungScreen.fxml"));
      } catch (IOException e) {
         e.printStackTrace();
      }
      int width = 600;
      int height = 400;
      root.setOnMousePressed(event -> {
         x = event.getSceneX();
         y = event.getSceneY();
      });
      root.setOnMouseDragged(event -> {

         addScreen.setX(event.getScreenX() - x);
         addScreen.setY(event.getScreenY() - y);

      });
      Scene scene = new Scene(root, width, height);
      addScreen.setScene(scene);
      addScreen.show();
   }

   public  ObservableList<thongKeTiemChung> findAll(){
      ObservableList<thongKeTiemChung> listM = FXCollections.observableArrayList();
      String sql = "Select * from nhankhau, thongketiemchung where nhankhau.nhankhau_id = thongketiemchung.nhankhau_id ";
      Connection connection = DBConnection.getConnection();
      try {
         Statement Statement = connection.createStatement();
         ResultSet resultSet = Statement.executeQuery(sql);
         while (resultSet.next()) {
            thongKeTiemChung thongKeTiemChung = new thongKeTiemChung(
                    resultSet.getInt("nhankhau_id"),
                    resultSet.getInt("thongke_id"),
                    resultSet.getString("ten"),
                    resultSet.getInt("datiem"),
                    resultSet.getInt("somuitiem"),
                    resultSet.getString("loaivacxinlan1"),
                    resultSet.getString("loaivacxinlan2"),
                    resultSet.getDate("thoigiantiemlan1"),
                    resultSet.getDate("thoigiantiemlan2"));

            listM.add(thongKeTiemChung);
         }
      }
      catch (SQLException throwables) {
         throwables.printStackTrace();
      }
      return listM;
   }
   public void UpdateTable() {
      list= findAll();

      thongKeID.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,Integer>("thongke_id"));
      nhanKhauID.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,Integer>("ten"));
      daTiem.setCellValueFactory(new Callback<>() {
         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<thongKeTiemChung, String> p) {
            return p.getValue().getCheckTiem();
         }
      });
      soMuiTiem.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,Integer>("somuitiem"));
      loaiVaccine1.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,String>("loaivacxinlan1"));
      loaiVaccine2.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,String>("loaivacxinlan2"));
      thoiGian1.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,Date>("thoigiantiemlan1"));
      thoiGian2.setCellValueFactory(new PropertyValueFactory<thongKeTiemChung,Date>("thoigiantiemlan2"));

      table.setItems(list);

   }

   public void initialize(URL url, ResourceBundle resourceBundle)  {
      UpdateTable();
   }


   @FXML
   public void getSelected(){
      // Item here is the table view type:
      TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
      int row = pos.getRow();

// Item here is the table view type:
      thongKeTiemChung item =(thongKeTiemChung) table.getItems().get(row);

      TableColumn colName = (TableColumn) table.getColumns().get(1);
      TableColumn colID = (TableColumn) table.getColumns().get(0);

      // this gives the value in the selected cell:
       nameProp = (String) colName.getCellObservableValue(item).getValue();
       idProp = (int) colID.getCellObservableValue(item).getValue();

   }
   public static String transferDataName(){
      return nameProp;
   }
   public static int transferDataID(){
      return idProp;
   }

   @FXML
   public void onDelete(){
      thongKeTiemChung selectedThongKeTiemChung = (thongKeTiemChung) table.getSelectionModel().getSelectedItem();
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      if (selectedThongKeTiemChung != null) {
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xóa?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
         alert1.showAndWait();

         if (alert1.getResult() == ButtonType.YES) {
            int selectedId = selectedThongKeTiemChung.getThongke_id();
            String sql = "delete from thongketiemchung where thongke_id = ?";
            Connection connection = DBConnection.getConnection();
            try {
               PreparedStatement preparedStatement = connection.prepareStatement(sql);
               preparedStatement.setInt(1, selectedId);

               preparedStatement.execute();

            } catch (SQLException e) {
               e.printStackTrace();
            }
            UpdateTable();
         }

      } else {
         alert.setAlertType(Alert.AlertType.ERROR);
         alert.setContentText("Bạn hãy click chọn vào đối tượng cần xóa ở bảng bên!");
         alert.show();
      }
   }
   public void reload(){
         UpdateTable();
       }


    public void setLayout() {
       ThongKeTiemChungController content = new ThongKeTiemChungController();
    }
}

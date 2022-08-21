package com.example.code.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable {
    @FXML
    private Button dashboardBtn;

    @FXML
    private Button diachiBtn;

    @FXML
    private GridPane gridContent;

    @FXML
    private Button khaibaoyteBtn;

    @FXML
    private Button nhankhauBtn;

    @FXML
    private AnchorPane mainpane;

    @FXML
    private VBox sidebarID;

    @FXML
    private Button thongketiemchungBtn;

    @FXML
    private Button thongtincachliBtn;

    @FXML
    private Button thongtintestcovidBtn;

    @FXML
    private Text lblInstance;

    @FXML
    private ImageView iconInstance;

    public void getInstance(int controller) throws IOException {
        switch (controller) {
            case 1:
                openDashboardScreen();
                break;
            case 2:
                openNhanKhauScreen();
                break;
            case 3:
                openDiaChiScreen();
                break;
            case 4:
                openKhaiBaoYTeScreen();
                break;
            case 5:
                openThongKeTiemChungScreen();
                break;
            case 6:
                openThongTinCachLyScreen();
                break;
            case 7:
                openThongTinTestScreen();
                break;
            default: break;
        }
    }

    private void openThongTinTestScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/ThongTinTestCovidScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        ThongTinTestCovidController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    private void openThongTinCachLyScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/ThongTinCachLyScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        ThongTinCachLyController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    private void openThongKeTiemChungScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/ThongKeTiemChungScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        ThongKeTiemChungController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    private void openKhaiBaoYTeScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/KhaiBaoYTeScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        KhaiBaoYTeController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    private void openDiaChiScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/DiaChiScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        DiaChiController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    @FXML
    void opennhankhau(ActionEvent event) throws IOException {
        lblInstance.setText("Thông Tin Nhân Khẩu");
        URL image = getClass().getResource("/image/man.png");
        Image img = new Image(String.valueOf(image));
        iconInstance.setImage(img);
        getInstance(2);
    }

    @FXML
    void openaddress(ActionEvent event) throws IOException {
        lblInstance.setText("Địa Chỉ");
        URL image = getClass().getResource("/image/address.png");
        Image img = new Image(String.valueOf(image));
        iconInstance.setImage(img);
        getInstance(3);
    }

    public void openNhanKhauScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/NhanKhauScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        NhanKhauController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    public void openDashboardScreen() throws IOException {
        gridContent.getChildren().clear();
        int column = 0;
        int row = 0;
        URL url = LayoutController.class.getResource("/com/example/code/DashBoardScreen.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        AnchorPane anchorPane = fxmlLoader.load();
        DashBoardController itemController = fxmlLoader.getController();

        itemController.setLayout();
        gridContent.add(anchorPane, column++, row); //(child,column,row)
        int width = (int) Screen.getPrimary().getBounds().getWidth() - 264;
        int height = (int) Screen.getPrimary().getBounds().getHeight() - 70;

        gridContent.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefWidth(width);
        gridContent.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        gridContent.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridContent.setPrefHeight(height);
        gridContent.setMaxHeight(Region.USE_PREF_SIZE);
    }

    @FXML
    void openDashBoard(ActionEvent event) throws IOException {
        getInstance(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getInstance(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opentestcovid(ActionEvent actionEvent) throws IOException {
        lblInstance.setText("Test Covid");
        URL image = getClass().getResource("/image/pcr-test.png");
        Image img = new Image(String.valueOf(image));
        iconInstance.setImage(img);
        getInstance(7);
    }

    public void openkhaibaoyte(ActionEvent actionEvent) throws IOException {
        lblInstance.setText("Khai Báo Y Tế");
        URL image = getClass().getResource("/image/healthdeclaration.png");
        Image img = new Image(String.valueOf(image));
        iconInstance.setImage(img);
        getInstance(4);
    }

    public void openthongke(ActionEvent actionEvent) throws IOException {
        lblInstance.setText("Thống Kê Tiêm Chủng");
        URL image = getClass().getResource("/image/statistics.png");
        Image img = new Image(String.valueOf(image));
        iconInstance.setImage(img);
        getInstance(5);

    }

    public void openthongtincachly(ActionEvent actionEvent) throws IOException {
        lblInstance.setText("Thông Tin Cách Ly");
        URL image = getClass().getResource("/image/quarantine.png");
        Image img = new Image(String.valueOf(image));
        iconInstance.setImage(img);
        getInstance(6);
    }
}

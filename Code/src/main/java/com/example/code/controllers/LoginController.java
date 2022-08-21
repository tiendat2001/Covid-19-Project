package com.example.code.controllers;

import com.example.code.MainApplication;
import com.example.code.database.DBConnection;
import com.example.code.models.AdminSession;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane loginscreen;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    String saltDefault = "CHANG";
    @FXML
    void moveToMainScreen(ActionEvent event) {
        if(txtEmail.getText().toString().equals("")){
            URL image = getClass().getResource("/image/delete.png");
            Image img = new Image(String.valueOf(image));
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Username is empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .graphic(new ImageView(img));
            notifications.darkStyle();
            notifications.show();
        }else if(txtPassword.getText().toString().equals("")) {
            URL image = getClass().getResource("/image/delete.png");
            Image img = new Image(String.valueOf(image));
            Notifications notifications = Notifications.create()
                    .title("Error")
                    .text("Password is empty")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.TOP_CENTER)
                    .graphic(new ImageView(img));
            notifications.darkStyle();
            notifications.show();
        }else{
            boolean isExist = false;
            String adminPass = "";
//            int role_id = 0;
            int ad_id = 0;
            String sql = "Select * from admin where ad_email = '" + txtEmail.getText().toString().trim()+"'";
            Connection connection = DBConnection.getConnection();
            try {
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    isExist = true;
                    adminPass = resultSet.getString(3); // position column of Password column in database
//                    role_id = resultSet.getInt(7);
                    ad_id = resultSet.getInt(1);
                }
                String encryptPass = get_SHA_512_SecurePassword(txtPassword.getText().toString().trim(),saltDefault);
                if(isExist && encryptPass.equals(adminPass)){
                    //if user admin -> admin screen
                    Stage adminScreen = new Stage();
                    Parent root = null;
                    try {
                        //Khai tao gia tri session (co 2 truong: 1 truong la username và 2 là role_id)
                        AdminSession.getInstance(txtEmail.getText(), ad_id);
//                        System.out.println(AdminSession.getUserName() + "," + AdminSession.getCurrentRole());
                        root = FXMLLoader.load(MainApplication.class.getResource("LayoutScreen.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int width = (int) Screen.getPrimary().getBounds().getWidth();
                    int height = (int) Screen.getPrimary().getBounds().getHeight()-70;
                    Stage current = (Stage) loginscreen.getScene().getWindow();
                    Scene scene = new Scene(root, width, height);
                    adminScreen.setScene(scene);
                    current.close();
                    adminScreen.show();

                }else{
                    URL image = getClass().getResource("/image/delete.png");
                    Image img = new Image(String.valueOf(image));
                    Notifications notifications = Notifications.create()
                            .title("Error")
                            .text("Your Username or password is incorrect!")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.TOP_CENTER)
                            .graphic(new ImageView(img));
                    notifications.darkStyle();
                    notifications.show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

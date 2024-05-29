package sample.Auth;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Admin.UserController;

import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class AuthController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public TextField usernameText;
    public TextField passwordText;
    public Button registerButton;
    public Button enterButton;
    public ArrayList<String> usernameDB = new ArrayList<String>(){};
    public ArrayList<String> passwordDB = new ArrayList<String>(){};
    public boolean authControl=false;
    public Statement myStat;

    @FXML
    public void enter() throws IOException {
        int usernameIndex = -1;
         for(String username:usernameDB){
            if(username.equals(usernameText.getText())) {
                usernameIndex = usernameDB.indexOf(username);
                if (passwordDB.get(usernameIndex).equals(passwordText.getText())) {

                    authControl = true;
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample/Admin/user.fxml")));
                    scene = new Scene(root,800,600);
                    stage = (Stage) enterButton.getScene().getWindow();
                    stage.setTitle("Estate Automation System");
                    stage.setScene(scene);
                    stage.show();

                }

                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ŞİFRENİZ YANLIŞ!!!");
                    usernameText.setText("");
                    passwordText.setText("");
                    alert.showAndWait();
                    authControl=true;
                }}
            }
            if (!authControl){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("BÖYLE BİR KULLANICI YOK!!!");
                usernameText.setText("");
                passwordText.setText("");
                alert.showAndWait();
            }


    }
    @FXML
    public void register() throws IOException, SQLException {
        String insertQuery = "INSERT INTO users (username, password) VALUES ('" + usernameText.getText() + "', '" + passwordText.getText() + "')";
        myStat.executeUpdate(insertQuery);
    }
    @FXML
    public void initialize() throws SQLException {
        Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","estateadmin","admin123+");
        myStat = (Statement)myConn.createStatement();
        ResultSet myRs = myStat.executeQuery("SELECT * FROM users");
        while (myRs.next()) {
            // ResultSet üzerinde işlemleri burada gerçekleştirin
            usernameDB.add(myRs.getString("username"));
            passwordDB.add(myRs.getString("password"));
        }


    }
}

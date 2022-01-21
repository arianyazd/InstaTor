package com.example.projectfx;

import com.example.projectfx.UserManagement.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginTitle;

    private Stage stage;
    private Scene scene;

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
            String username = usernameField.getText();
            String password = passwordField.getText();
            User loggedIn = User.login(username, password);
            if(loggedIn==null){
                loginTitle.setText("wrong user info");
            }
            else{

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));


            Parent root = fxmlLoader.load();
            HomeController homeController = fxmlLoader.getController();
            homeController.setLoggedIn(loggedIn);

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
    }
}

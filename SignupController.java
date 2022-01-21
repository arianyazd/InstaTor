package com.example.projectfx;

import com.example.projectfx.UserManagement.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField biographyField;
    @FXML
    private TextField phonenumberField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label warningLabel;


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
        String username, nickname, email, bio, phonenumber, password;
        int age = 1;
        username = usernameField.getText();
        nickname = nicknameField.getText();
        email = emailField.getText();
        bio = biographyField.getText();
        phonenumber = phonenumberField.getText();
        password = passwordField.getText();
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (Exception e) {
            warningLabel.setText("age is not a number try again");
        }
        if (age == 1) {
            warningLabel.setText("enter the age again");
        } else {
            User signedUp = User.signup(username, password, email, bio, phonenumber, nickname, age);
            if (signedUp == null) {
                warningLabel.setText("some of your properties are not completely entered please try again");
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}

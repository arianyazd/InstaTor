package com.example.projectfx;

import com.example.projectfx.UserManagement.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    @FXML
    private CheckBox privateCheckBox;
    @FXML
    private TextField postNumberField;
    @FXML
    private Label nickname;
    @FXML
    private Label age;
    @FXML
    private Label bio;
    @FXML
    private Label followingsCount;
    @FXML
    private Label followersCount;
    @FXML
    private Label postCount;
    @FXML
    private Label username;

    private Scene scene;
    private Stage stage;

    User owner;

    public void setOwner(User user){
        this.owner=user;
        username.setText(user.getUsername());
        Integer number=(user.getUserPosts().size());
        postCount.setText(number.toString());
        bio.setText(user.getBiography());
        number = user.getAge();
        age.setText(number.toString());
        nickname.setText(user.getNickName());
        privateCheckBox.setSelected(user.getPrivate());
    }

    public void onSubmit(ActionEvent event) throws IOException {
        int postChoice = 0;
        try{
            postChoice=Integer.parseInt(postNumberField.getText());
        }
        catch (Exception e){
            postNumberField.setText("enter a number");
        }
        if(postChoice<owner.getUserPosts().size()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PostPage.fxml"));


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

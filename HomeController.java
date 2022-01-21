package com.example.projectfx;

import com.example.projectfx.PostManagement.Post;
import com.example.projectfx.PostManagement.*;
import com.example.projectfx.UserManagement.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private User loggedIn;
    private List<Post> posts;
    private Post showPost;
    private int postIndex;
    @FXML
    private Label warningLable;
    @FXML
    private MediaView mediaView;
    @FXML
    public TextField userSearched;
    @FXML
    public Label myUsername;
    @FXML
    public Label postUser;
    private Stage stage;
    private Scene scene;
    private Image image;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{myUsername.setText(loggedIn.getUsername());
            posts= loggedIn.getFollowingPosts();
            showPost=posts.get(0);
            showHomePost();
        }catch (Exception e){
            warningLable.setText("there is no such post within your followings post");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            warningLable.setText("");

        }


    }

    private void showHomePost() {
            if(showPost!=null) {
                file = new File(showPost.getUrl());
                if (showPost.getPostType() == PostType.photo) {
                    image = new Image(file.toURI().toString());
                    imageView = new ImageView(image);
                    mediaView.setDisable(true);
                    imageView.setDisable(false);
                } else if (showPost.getPostType() == PostType.video) {
                    media = new Media(file.toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                    mediaView = new MediaView(mediaPlayer);
                    mediaView.setDisable(false);
                    imageView.setDisable(true);
                }
            }
    }

    public void setLoggedIn(User user){
        this.loggedIn=user;
        myUsername.setText(user.getUsername());
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void sendMessage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MessageView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void myProfile(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProfileView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void searchUsers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProfileView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void previousPost(ActionEvent event) throws InterruptedException {
        postIndex--;
        try {
            if (posts.size() == 0) {
                postIndex = 0;
            } else if (postIndex == -1) {
                warningLable.setText("there is no more post before this one");
                Thread.sleep(2000);
                warningLable.setText("");
                postIndex++;

            } else {
                showPost = posts.get(postIndex);
                postUser.setText(showPost.getPostOwner().getUsername());
                showHomePost();
            }
        }catch (Exception e){
            warningLable.setText("no post exist");
        }
    }

    public void nextPost(ActionEvent event) throws InterruptedException {
        postIndex++;
        try {
            if (posts.size() == 0) {
                postIndex = 0;
            } else if (posts.get(postIndex) == null) {
                warningLable.setText("there is no more post after this one");
                Thread.sleep(2000);
                warningLable.setText("");
                postIndex--;
            } else {
                showPost = posts.get(postIndex);
                postUser.setText(showPost.getPostOwner().getUsername());
                showHomePost();
            }
        }catch (Exception e){
            warningLable.setText("no post exist");
        }
    }

    public void goTo(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PostView.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}

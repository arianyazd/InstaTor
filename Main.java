package com.example.projectfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        List<User> users = new ArrayList();
//        User user1 = new User("a", "a", "a", false);
//        User user2 = new User("b", "b", "b", true);
//        User user3 = new User("c", "c", "c", false);
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//        User.setUsers(users);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("InstaTor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
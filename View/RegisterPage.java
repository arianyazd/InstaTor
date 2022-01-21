package com.example.projectfx.View;

import com.example.projectfx.UserManagement.User;

import java.io.IOException;
import java.util.Scanner;

public class RegisterPage {

    private static Scanner scanner = new Scanner(System.in);

    private void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("1. Login");
        System.out.println("2. Sign-up");
        System.out.println("0. Exit");
    }

    public void run() {
        User user;
        System.out.println("Welcome to instagram!");
        while (true) {
        	try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			Thread.currentThread().interrupt();
    		}
        	System.out.println("_______________________________________");
            System.out.println("--REGISTER--");
            System.out.println("Number of all users:  "+User.getUsers().size());
            showMenu();
            System.out.println("choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                System.out.println("Good Bye!");
                return;
            }
            if (choice == 1) {
                user = login();
                if(user == null){
                    System.out.println("User not found!");
                    continue;
                }
                HomePage homePage = new HomePage(user);
                homePage.run();
            } else if (choice == 2) {
                signUp();
            }else {
            	System.out.println("Option is not valid! Please try again");
            }
        }
    }

    private User login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        return User.login(username, password);
    }

    private void signUp() {
//        System.out.println("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.println("Enter password: ");
//        String password = scanner.nextLine();
//        System.out.println("Enter email: ");
//        String email = scanner.nextLine();
//        User.signup(username, password, email);
    }
}
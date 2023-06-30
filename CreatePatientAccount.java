package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class CreatePatientAccount {

    private GridPane root;
    private Stage primaryStage;

    public CreatePatientAccount(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createPatientAccount();
    }

    private void createPatientAccount() {
        
        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("dark-button");
        homeButton.setOnAction(e -> {
            // Handle home button click
            // Logic to navigate to home page
        	SignInPage signInPage = new SignInPage(primaryStage);
            primaryStage.setScene(new Scene(signInPage.getRoot(), 900, 600));
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(false);
        });
        
        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(50, 0, 0, 50));
        topBox.getChildren().addAll(homeButton);
    	
        Button createAccountButton = new Button("Create Account");
        createAccountButton.getStyleClass().add("create-account-button");
        
        Label titleLabel = new Label("Create Patient \n     Account");
        titleLabel.setWrapText(true);
        titleLabel.getStyleClass().add("create-patient-label");

        Label usernameLabel = new Label("Enter username: ");
        TextField usernameField = new TextField();

        GridPane inputBox = new GridPane();
        inputBox.setHgap(10); 
        inputBox.setVgap(10); 
        inputBox.setPadding(new Insets(10));

        inputBox.add(usernameLabel, 0, 0); // Column 0, Row 0
        inputBox.add(usernameField, 1, 0); // Column 1, Row 0
        inputBox.add(createAccountButton, 1, 3);
        GridPane.setMargin(createAccountButton, new Insets(20, 0, 0, -20));
        
        // Add event handlers for the buttons
        createAccountButton.setOnAction(e -> {
            if (!usernameField.equals("")) {
            	boolean bool;
          		File patientFile = new File("C:\\Users\\jlbdv\\eclipse-workspace\\javafxtest\\src\\application\\PatientInfoFiles\\" + usernameField.getText() +  "_patientInfo.txt");
          		File patientMessageFile = new File("C:\\Users\\jlbdv\\eclipse-workspace\\javafxtest\\src\\application\\PatientInfoFiles\\" + usernameField.getText() +  "_patientMessage.txt");
          		try {
    				bool = patientFile.createNewFile();
    				bool = patientMessageFile.createNewFile();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
                PatientPortal patientPortal = new PatientPortal(primaryStage, usernameField.getText());
                primaryStage.setScene(new Scene(patientPortal.getRoot(), 900, 600));
                primaryStage.setResizable(false);
                primaryStage.setFullScreen(false);
            }
        });
        
        root = new GridPane();
        root.setHgap(50);
        root.setVgap(100);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        root.add(topBox, 0, 0);
       
        root.add(titleLabel, 1, 0);
        root.add(inputBox, 1, 1);
        GridPane.setMargin(titleLabel, new Insets(30, 0, 0, 60));
        GridPane.setMargin(inputBox, new Insets(-50, 0, 0, 60));

        
      

        
    }

    public GridPane getRoot() {
        return root;
    }
}

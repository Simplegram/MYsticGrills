package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RegisView extends VBox {
    private TextField usernameInput = new TextField();
    private TextField emailInput = new TextField();
    private PasswordField passwordInput = new PasswordField();
    private PasswordField confirmPasswordInput = new PasswordField();
    private Button regisButton, backButton;
    private Stage primaryStage;


	public RegisView(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        Label usernameLabel = new Label("Username:");
        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");
        Label confirmPasswordLabel = new Label("Confirm Password:");

        regisButton = new Button("Register");
        backButton = new Button("Back");
        
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameInput, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailInput, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordInput, 1, 2);
        grid.add(confirmPasswordLabel, 0, 3);
        grid.add(confirmPasswordInput, 1, 3);
        grid.add(regisButton, 0, 4);
        grid.add(backButton, 0, 5);
        this.getChildren().add(grid);

        
		backButton.setOnAction(e->{
			MainView mainView = new MainView(primaryStage);
			MainController mainControler = new MainController(mainView);
		});
		
        Scene scene = new Scene(this, 300, 250);
        Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



	public TextField getUsernameInput() {
		return usernameInput;
	}



	public void setUsernameInput(TextField usernameInput) {
		this.usernameInput = usernameInput;
	}



	public TextField getEmailInput() {
		return emailInput;
	}



	public void setEmailInput(TextField emailInput) {
		this.emailInput = emailInput;
	}



	public PasswordField getPasswordInput() {
		return passwordInput;
	}



	public void setPasswordInput(PasswordField passwordInput) {
		this.passwordInput = passwordInput;
	}



	public PasswordField getConfirmPasswordInput() {
		return confirmPasswordInput;
	}



	public void setConfirmPasswordInput(PasswordField confirmPasswordInput) {
		this.confirmPasswordInput = confirmPasswordInput;
	}



	public Button getRegisButton() {
		return regisButton;
	}



	public void setRegisButton(Button regisButton) {
		this.regisButton = regisButton;
	}



	public Button getBackButton() {
		return backButton;
	}



	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}



	public Stage getPrimaryStage() {
		return primaryStage;
	}



	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	
	
}

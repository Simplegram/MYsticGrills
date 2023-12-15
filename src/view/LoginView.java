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

public class LoginView extends VBox {
    private TextField emailInput = new TextField();
    private PasswordField passwordInput = new PasswordField();
    private Button loginButton, backButton;
    private Stage primaryStage;
    
    public LoginView(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        loginButton = new Button("Login");
        backButton = new Button("Back");
        
        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");

        grid.add(emailLabel, 0, 0);
        grid.add(emailInput, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordInput, 1, 1);
        grid.add(loginButton, 0, 2);
        grid.add(backButton, 0, 3);

        this.getChildren().add(grid);
       
        backButton.setOnAction(e->{
			MainView mainView = new MainView(primaryStage);
			MainController mainControler = new MainController(mainView);
		});
		
        Scene scene = new Scene(this, 300, 200);
        Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
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

	public Button getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

}





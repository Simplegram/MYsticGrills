package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView extends VBox{	
	private Button regisButton, loginButton;
	private LoginView loginView;
	private RegisView regisView;
	private ImageView imageView;
	private Stage primaryStage;

	
	public MainView(Stage primaryStage) {
			this.primaryStage = primaryStage;
		 	VBox vbox = new VBox();
	        vbox.setPadding(new Insets(20, 20, 20, 20));
	        vbox.setSpacing(10);
	        Image iconImage = new Image("/image/icons/logo.png");
	        
	        imageView = new ImageView(iconImage);
	        imageView.setFitWidth(100);
	        imageView.setFitHeight(100);
	        imageView.setPreserveRatio(true);
	        vbox.getChildren().add(imageView);

	        //ke RegisView
	        regisButton = new Button("Register");
	        regisButton.setOnAction(e -> openRegisView());
	        vbox.getChildren().add(regisButton);
	        
	        //ke LoginView
	        loginButton = new Button("Login");
	        loginButton.setOnAction(event -> openLoginView());
	        vbox.getChildren().add(loginButton);

	        Scene scene = new Scene(vbox, 300, 220);
	        primaryStage.getIcons().add(iconImage);
	        primaryStage.setTitle("Mystic Grills: Home");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	
	private void openRegisView() {
		RegisView regisView = new RegisView(primaryStage);
		UserController userController = new UserController(regisView);
	}
	
	private void openLoginView() {
		LoginView loginView = new LoginView(primaryStage);
		UserController userController = new UserController(loginView);
	}
	
}

package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminView extends VBox{	
	private Label greetings;
	private Button userMgrButton, menuItemButton, backButton;
	private MenuItemView menuItemView;
	private Stage primaryStage;

	public AdminView(Stage primaryStage) {
		
			this.primaryStage = primaryStage;
		 	VBox vbox = new VBox();
	        vbox.setPadding(new Insets(20, 20, 20, 20));
	        vbox.setSpacing(10);
	        
	        //ke item page
	        userMgrButton = new Button("User Management");
	        
	        //ke item page
	        menuItemButton = new Button("View Menu Item");
	        
	        backButton = new Button("Logout");	        
	        
	        greetings = new Label();

	        vbox.getChildren().addAll(greetings, userMgrButton, menuItemButton, backButton);
	        
	        Scene scene = new Scene(vbox, 300, 220);
	        Image image = new Image("/image/icons/logo.png");
	        primaryStage.getIcons().add(image);
	        primaryStage.setTitle("Admin View");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		
	}

	public Label getGreetings() {
		return greetings;
	}

	public Button getUserMgrButton() {
		return userMgrButton;
	}

	public Button getMenuItemButton() {
		return menuItemButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public MenuItemView getMenuItemView() {
		return menuItemView;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}

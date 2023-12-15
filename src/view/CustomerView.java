package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import controller.MainController;
import controller.MenuItemController;
import controller.OrderController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerView extends VBox {
	private Button menuViewButton, orderViewButton, logoutButton, receiptButton;
	private Label greetings, role;
	private Stage primaryStage;
	private VBox vbox;
	
	public CustomerView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	 	vbox = new VBox();
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(10);

        menuViewButton = new Button("View Menu");
        orderViewButton = new Button("View Order");
        receiptButton = new Button("View Receipt");
        logoutButton = new Button("Logout");       
        logoutButton.setOnAction(e->{
			MainView mainView = new MainView(primaryStage);
			MainController mainControler = new MainController(mainView);
		});
        
        greetings = new Label();
        role = new Label();

        Scene scene = new Scene(vbox, 300, 200);
        Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public VBox getVbox() {
		return vbox;
	}

	public Button getLogoutButton() {
		return logoutButton;
	}

	public Label getRole() {
		return role;
	}

	public Label getGreetings() {
		return greetings;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Button getMenuViewButton() {
		return menuViewButton;
	}

	public Button getOrderViewButton() {
		return orderViewButton;
	}

	public Button getReceiptButton() {
		return receiptButton;
	}

	public void setReceiptButton(Button receiptButton) {
		this.receiptButton = receiptButton;
	}
	
}

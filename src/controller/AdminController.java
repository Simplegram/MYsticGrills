package controller;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.AdminView;
import view.MainView;
import view.MenuItemView;
import view.UserManagementView;

public class AdminController {
	private Stage primaryStage;
	private AdminView adminView;

	public AdminController(AdminView adminView) {
		this.adminView = adminView;
		initHandler();
		greetings();
	}
	
	void initHandler() {
		adminView.getUserMgrButton().setOnAction(e -> {
			primaryStage = adminView.getPrimaryStage();
			UserManagementView userManagementView = new UserManagementView(primaryStage);
			UserManagementController userManagementController = new UserManagementController(userManagementView);
		});
		
		adminView.getMenuItemButton().setOnAction(e -> {
			primaryStage = adminView.getPrimaryStage();
			MenuItemView menuItemView = new MenuItemView(primaryStage);
			MenuItemController MenuItemControler = new MenuItemController(menuItemView);
		});
		
		adminView.getBackButton().setOnAction(e -> {
			primaryStage = adminView.getPrimaryStage();
			MainView mainView = new MainView(primaryStage);
			MainController mainControler = new MainController(mainView);
		});
	}
	
	private void greetings() {
		Label greetings = adminView.getGreetings();
		
		greetings.setText("Welcome, Admin");
		greetings.setFont(new Font("Cambria", 20));
		greetings.setStyle("--fx-font-weight: bold");
	}

}

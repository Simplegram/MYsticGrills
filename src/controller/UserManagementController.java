package controller;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.MenuItem;
import model.User;
import view.AdminView;
import view.UserManagementView;

public class UserManagementController extends Controller{
	
	private UserManagementView userManagementView;
	private AdminView adminView;
	
	public UserManagementController(UserManagementView userManagementView) {
		this.userManagementView = userManagementView;
		initializeHandler();
		setupTableSelectionListener();
		loadTableData();
	}
	
	private void initializeHandler() {		
		userManagementView.getUpdateButton().setOnAction(e -> {
			String id = userManagementView.getIdInput().getText();
			String role = userManagementView.getRoleInput().getText();
			String username = userManagementView.getNameInput().getText();
            String email = userManagementView.getEmailInput().getText();
            String password = userManagementView.getPasswordInput().getText();
			
			if(role.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Role field cannot be empty");
				return;
			}
			
			if(!role.equals("Admin") && !role.equals("Customer") && !role.equals("Chef") && !role.equals("Waiter") && !role.equals("Cashire")) {
			    showAlert(Alert.AlertType.ERROR, "Error", null, "Role must be either Admin, Chef, Waiter, Cashier, or Customer" );
			    return;
			}
			
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Update Data Confirmation");
		    alert.setHeaderText("Confirmation Update");
		    alert.setContentText("Are you sure to Update the data?");
		    
		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() != ButtonType.OK) {
		        return;
		    }
		    
		    User.updateRole(Integer.parseInt(id), role, username, email, password);
		    

		    loadTableData();
		});
		
		userManagementView.getDeleteButton().setOnAction(e -> {
			String id = userManagementView.getIdInput().getText();
			
			if(id.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Please choose the data you want to delete");
				return;
			}

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Delete Data Confirmation");
		    alert.setHeaderText("Confirmation Delete");
		    alert.setContentText("Are you sure to Delete the data?");

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() != ButtonType.OK) {
		        return;
		    }
		    
			User.delete(Integer.parseInt(id));
			
			loadTableData();
		});
	}

	void loadTableData() {
		ArrayList<User> users = User.getAllUser();
		userManagementView.getTable().getItems().setAll(users);
	}
	
	private void setupTableSelectionListener() {
        userManagementView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	userManagementView.getIdInput().setText(String.valueOf(newSelection.getUserId()));
            	userManagementView.getRoleInput().setText(newSelection.getUserRole());
            	userManagementView.getNameInput().setText(newSelection.getUserName());
            	userManagementView.getEmailInput().setText(newSelection.getUserEmail());
            	userManagementView.getPasswordInput().setText(newSelection.getUserPassword());
            }
        });
    }
	
}

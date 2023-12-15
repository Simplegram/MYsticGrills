package controller;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.MenuItem;
import view.AdminView;
import view.CustomerMenuView;
import view.CustomerView;
import view.MenuItemView;

public class MenuItemController extends Controller{
	
	private MenuItemView menuItemView;
	private AdminView adminView;
	
	public MenuItemController(MenuItemView menuItemView) {
		this.menuItemView = menuItemView;
		initializeHandler();
		setupTableSelectionListener();
		loadTableData();
	}

	private void initializeHandler() {
		menuItemView.getAddButton().setOnAction(e ->{
			String name = menuItemView.getNameInput().getText();
			String desc = menuItemView.getDescInput().getText();
			String price = menuItemView.getPriceInput().getText();
			
			if(name.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Name field cannot be empty");
				return;
			}
			
			ArrayList<MenuItem> menuitems = new ArrayList<>();
			menuitems = MenuItem.getAllMenuItems();
			
			for (MenuItem menuitem : menuitems) {
				if (name.equals(menuitem.getMenuItemName())) {
					showAlert(Alert.AlertType.ERROR, "Error", null, "Name is not unique");
				    return;
				}
			}
			
			if(desc.length() <= 10) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Description must be more than 10 characters");
			    return;
			}
			
			double prices;
			try {
			    prices = Double.parseDouble(price);
			    // Perform any additional calculations or validations here
			} catch (NumberFormatException e1) {
			    showAlert(Alert.AlertType.ERROR, "Error", null, "Price must be a number");
			    return;
			}
			
			if(prices < 2.5) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Price must be greater than or equals to 2.5");
			    return;
			}
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Add Data Confirmation");
		    alert.setHeaderText("Confirmation Add");
		    alert.setContentText("Are you sure to Add the data?");
		    
		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() != ButtonType.OK) {
		        return;
		    }
		    
			MenuItem.createMenuItem(name, desc, prices);
			loadTableData();
			showAlert(Alert.AlertType.INFORMATION, "Success", null, "Successfully created");
		});
		
		menuItemView.getUpdateButton().setOnAction(e -> {
			String id = menuItemView.getIdInput().getText();
			String name = menuItemView.getNameInput().getText();
			String desc = menuItemView.getDescInput().getText();
			String price = menuItemView.getPriceInput().getText();
			
			if(name.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Name field cannot be empty");
				return;
			}
			
			ArrayList<MenuItem> menuitems = new ArrayList<>();
			menuitems = MenuItem.getAllMenuItems();
			
			for (MenuItem menuitem : menuitems) {
				if (name.equals(menuitem.getMenuItemName())) {
					showAlert(Alert.AlertType.ERROR, "Error", null, "Name is not unique");
				    return;
				}
			}
			
			if(desc.length() <= 10) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Description must be more than 10 characters");
			    return;
			}
			
			double prices;
			try {
			    prices = Double.parseDouble(price);
			    // Perform any additional calculations or validations here
			} catch (NumberFormatException e1) {
			    showAlert(Alert.AlertType.ERROR, "Error", null, "Price must be a number");
			    return;
			}
			
			if(prices < 2.5) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Price must be greater than or equals to 2.5");
			    return;
			}
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Add Data Confirmation");
		    alert.setHeaderText("Confirmation Add");
		    alert.setContentText("Are you sure to Add the data?");
		    
		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() != ButtonType.OK) {
		        return;
		    }
		    
		    MenuItem.updateMenuItem(Integer.parseInt(id), name, desc, prices);
		    loadTableData();
		});
		
		menuItemView.getDeleteButton().setOnAction(e -> {
			String id = menuItemView.getIdInput().getText();
			
			if(id.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Please choose the data you want to delete");
				return;
			}

			int ids = Integer.parseInt(id);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Delete Data Confirmation");
		    alert.setHeaderText("Confirmation Delete");
		    alert.setContentText("Are you sure to Delete the data?");

		    Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() != ButtonType.OK) {
		        return;
		    }
		    
			MenuItem.deleteMenuItem(ids);
			loadTableData();
		});
	}

	void loadTableData() {
		ArrayList<MenuItem> menuItems = MenuItem.getAllMenuItems();
		menuItemView.getTable().getItems().setAll(menuItems);
	}
	
	private void setupTableSelectionListener() {
        menuItemView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	menuItemView.getIdInput().setText(String.valueOf(newSelection.getMenuItemId()));
            	menuItemView.getNameInput().setText(newSelection.getMenuItemName());
            	menuItemView.getDescInput().setText(newSelection.getMenuItemDescription());
            	menuItemView.getPriceInput().setText(String.valueOf(newSelection.getMenuItemPrice()));
            }
        });
    }
	
}

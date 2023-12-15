package view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.CustomerController;
import controller.OrderController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import model.MenuItem;

public class CustomerMenuView extends VBox {
	private Stage primaryStage;
	private TextField idInput = new TextField();
	private TextField nameInput = new TextField();
	private TextField descInput = new TextField();
	private TextField priceInput = new TextField();
	private TextField quantityInput = new TextField();
	private TableView<MenuItem> table;
	private Button addButton, backButton;
	
	public TableView<MenuItem> getTable(){
		return table;
	}
	
	public void setTable(TableView<MenuItem> table) {
		this.table = table;
	}

	public CustomerMenuView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		table = createMenuItemTable();
		
		VBox root = new VBox();
		table = createMenuItemTable();
		GridPane form = createOrderForm(table);
		VBox.setMargin(form, new Insets(20));
		root.getChildren().addAll(table, form);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		Scene scene = new Scene(root, 800, 600);
		Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Order Menu");
		primaryStage.show();
	}
	
	private TableView<MenuItem> createMenuItemTable(){
		TableView<MenuItem> table = new TableView<>();
		TableColumn<MenuItem, Number> idColumn = new TableColumn<>("Menu Item ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemId"));

		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemName"));

		TableColumn<MenuItem, String> descColumn = new TableColumn<>("Description");
		descColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemDescription"));

		TableColumn<MenuItem, Number> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("MenuItemPrice"));

		table.getColumns().add(idColumn);
		table.getColumns().add(nameColumn);
		table.getColumns().add(descColumn);
		table.getColumns().add(priceColumn);

		return table;
	}
	
	private GridPane createOrderForm(TableView<MenuItem> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		addButton = new Button("Add");
		backButton = new Button("Back");
		
		form.add(new Label("Menu Item ID:"), 0, 0);
		form.add(idInput, 1, 0);
		idInput.setDisable(true);
		form.add(new Label("Name:"), 0, 1);
		form.add(nameInput, 1, 1);
		nameInput.setDisable(true);
		form.add(new Label("Desc:"), 0, 2);
		form.add(descInput, 1, 2);
		descInput.setDisable(true);
		form.add(new Label("Price:"), 0, 3);
		form.add(priceInput, 1, 3);
		priceInput.setDisable(true);
		form.add(new Label("Quantity:"), 0, 4);
		form.add(quantityInput, 1, 4);
		form.add(addButton, 1, 5);
		form.add(backButton, 0, 5);
		
		return form;
	}
	
	public TextField getIdInput() {
		return idInput;
	}

	public TextField getNameInput() {
		return nameInput;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public TextField getDescInput() {
		return descInput;
	}
	
	public TextField getPriceInput() {
		return priceInput;
	}
	
	public TextField getQuantityInput() {
		return quantityInput;
	}

	public Button getAddButton() {
		return addButton;
	}

	public Button getBackButton() {
		return backButton;
	}

}

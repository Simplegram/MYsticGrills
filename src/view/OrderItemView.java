package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.OrderItem;

public class OrderItemView {
	private Stage primaryStage;
	private int orderId;
	private TextField orderIdInput = new TextField();
	private TextField menuItemInput = new TextField();
	private TextField quantityInput = new TextField();
	private TextField paymentTypeInput = new TextField();
	private TextField paymentAmountInput = new TextField();
	private TableView<OrderItem> table;
	private Button backButton, updateButton;
	private GridPane form;
	
	public TableView<OrderItem> getTable(){
		return table;
	}
	
	public void setTable(TableView<OrderItem> table) {
		this.table = table;
	}

	public OrderItemView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		table = createOrderTable();
		
		VBox root = new VBox();
		table = createOrderTable();
		GridPane form = createOrderForm(table);
		VBox.setMargin(form, new Insets(20));
		root.getChildren().addAll(table, form);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		Scene scene = new Scene(root, 800, 600);
		Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private TableView<OrderItem> createOrderTable(){
		TableView<OrderItem> table = new TableView<>();
		TableColumn<OrderItem, Number> idColumn = new TableColumn<>("Order ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

		TableColumn<OrderItem, Number> menuItemColumn = new TableColumn<>("Menu Item ID");
		menuItemColumn.setCellValueFactory(new PropertyValueFactory<>("menuItem"));

		TableColumn<OrderItem, Number> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		table.getColumns().add(idColumn);
		table.getColumns().add(menuItemColumn);
		table.getColumns().add(quantityColumn);

		return table;
	}
	
	private GridPane createOrderForm(TableView<OrderItem> table) {
		form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		backButton = new Button("Back");
		updateButton = new Button();
		
		form.add(new Label("Order ID:"), 0, 0);
		form.add(orderIdInput, 1, 0);
		orderIdInput.setDisable(true);
		
		form.add(menuItemInput, 1, 1);
		menuItemInput.setDisable(true);
		
		form.add(quantityInput, 1, 2);
		
		form.add(paymentTypeInput, 1, 3);
		
		form.add(paymentAmountInput, 1, 4);
		
		form.add(backButton, 0, 6);
		form.add(updateButton, 1, 6);
		
		return form;
	}

	public TextField getPaymentTypeInput() {
		return paymentTypeInput;
	}

	public TextField getPaymentAmountInput() {
		return paymentAmountInput;
	}

	public GridPane getForm() {
		return form;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public int getOrderId() {
		return orderId;
	}

	public TextField getOrderIdInput() {
		return orderIdInput;
	}

	public TextField getMenuItemInput() {
		return menuItemInput;
	}

	public TextField getQuantityInput() {
		return quantityInput;
	}

	public Button getBackButton() {
		return backButton;
	}

	public Button getUpdateButton() {
		return updateButton;
	}

}

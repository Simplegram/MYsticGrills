package view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import controller.CustomerController;
import database.Connect;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import model.Order;

public class OrderView extends VBox {
	private Stage primaryStage;
	private TextField idInput = new TextField();
	private TextField orderStatusInput = new TextField();
	private TextField orderDateInput = new TextField();
	private TextField orderTotalInput = new TextField();
	private TableView<Order> table;
	private Button backButton, viewButton;
	
	public TableView<Order> getTable(){
		return table;
	}
	
	public void setTable(TableView<Order> table) {
		this.table = table;
	}

	public OrderView(Stage primaryStage) {
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
		primaryStage.setTitle("Order List");
		primaryStage.show();
	}
	
	private TableView<Order> createOrderTable(){
		TableView<Order> table = new TableView<>();
		TableColumn<Order, Number> idColumn = new TableColumn<>("Order ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

		TableColumn<Order, String> orderStatusColumn = new TableColumn<>("Order Status");
		orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		TableColumn<Order, Date> orderDateColumn = new TableColumn<>("Order Date");
		orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		
		TableColumn<Order, Number> orderTotalColumn = new TableColumn<>("Order Total");
		orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));

		table.getColumns().add(idColumn);
		table.getColumns().add(orderStatusColumn);
		table.getColumns().add(orderDateColumn);
		table.getColumns().add(orderTotalColumn);

		return table;
	}
	
	private GridPane createOrderForm(TableView<Order> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		backButton = new Button("Back");
		viewButton = new Button("View Detail");
		
		form.add(new Label("Order ID:"), 0, 0);
		form.add(idInput, 1, 0);
		idInput.setDisable(true);
		
		form.add(new Label("Order Status:"), 0, 1);
		form.add(orderStatusInput, 1, 1);
		orderStatusInput.setDisable(true);
		
		form.add(new Label("Order Date:"), 0, 2);
		form.add(orderDateInput, 1, 2);
		orderDateInput.setDisable(true);
		
		form.add(new Label("Order Total:"), 0, 3);
		form.add(orderTotalInput, 1, 3);
		orderTotalInput.setDisable(true);
		
		form.add(backButton, 0, 4);
		form.add(viewButton, 1, 4);
		
		return form;
	}

	public TextField getIdInput() {
		return idInput;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Button getViewButton() {
		return viewButton;
	}

	public TextField getOrderStatusInput() {
		return orderStatusInput;
	}

	public TextField getOrderDateInput() {
		return orderDateInput;
	}

	public TextField getOrderTotalInput() {
		return orderTotalInput;
	}

	public Button getBackButton() {
		return backButton;
	}

}

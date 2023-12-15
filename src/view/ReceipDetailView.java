package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.OrderItem;
import model.ReceiptDetail;

public class ReceipDetailView {
	
	private Stage primaryStage;
	private Button backButton;
	private GridPane form;
	
	private TableView<ReceiptDetail> table;
	private TableView<ReceiptDetail> tableTemp;
	
//	private TableColumn<OrderItem, String> menuItemName;
//	private TableColumn<OrderItem, Number> menuItemPrice;
//	private TableColumn<OrderItem, Number> quantity;
	
	private TableColumn<ReceiptDetail, String> menuItemName;
	private TableColumn<ReceiptDetail, Number> menuItemPrice;
	private TableColumn<ReceiptDetail, Number> quantity;
	public ReceipDetailView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		table = tableDetailReceipt();
		VBox root = new VBox();
		GridPane form = createReceiptForm(table);
		VBox.setMargin(form, new Insets(20));
		root.getChildren().addAll(table, form);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Scene scene = new Scene(root, 800, 600);
		Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Receipt List");
		primaryStage.show();
	}
	
	private GridPane createReceiptForm(TableView<ReceiptDetail> table) {
		form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);
		backButton = new Button("Back");

		form.add(backButton, 0, 6);
		
		return form;
	}
	
	private TableView<ReceiptDetail> tableDetailReceipt(){
		tableTemp = new TableView<>();

		menuItemName = new TableColumn<>("Menu Item Name");
		menuItemName.setCellValueFactory(new PropertyValueFactory<>("menuItemName")); 

		menuItemPrice = new TableColumn<>("Menu Item Price");
		menuItemPrice.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
		
		quantity = new TableColumn<>("Quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		
		tableTemp.getColumns().add(menuItemName);
		tableTemp.getColumns().add(menuItemPrice);
		tableTemp.getColumns().add(quantity);

		return tableTemp;
	}

	public TableView<ReceiptDetail> getTableTemp() {
		return tableTemp;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public TableView<ReceiptDetail> getTable() {
		return table;
	}

	public Button getBackButton() {
		return backButton;
	}

//	public Button getReceiptDetailsButton() {
//		return receiptDetailsButton;
//	}

	public GridPane getForm() {
		return form;
	}

	public TableColumn<ReceiptDetail, String> getMenuItemName() {
		return menuItemName;
	}

	public TableColumn<ReceiptDetail, Number> getMenuItemPrice() {
		return menuItemPrice;
	}

	public TableColumn<ReceiptDetail, Number> getQuantity() {
		return quantity;
	}
}

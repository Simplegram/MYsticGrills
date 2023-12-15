package controller;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.MenuItem;
import model.OrderItem;
import model.Receipt;
import model.ReceiptDetail;
import model.User;
import view.CustomerView;
import view.MenuItemView;
import view.OrderItemView;
import view.OrderView;
import view.ReceiptView;
import view.ReceipDetailView;

public class ReceiptController {
	
	private ReceiptView receiptView;
	private Stage primaryStage;
	private User user;
	private ReceipDetailView receipDetailView;
	private String orderId;
	
	private TableView<ReceiptDetail> tableTemp;
	
	private TableColumn<ReceiptDetail, String> menuItemName;
	private TableColumn<ReceiptDetail, Number> menuItemPrice;
	private TableColumn<ReceiptDetail, Number> quantity;
	

	public ReceiptController(ReceiptView receiptView, User user) {
		this.receiptView = receiptView;
		this.user = user;
		initializeHandler();
		setupCustTableSelectionListener();
		loadTableData();
	}
	
	public ReceiptController(ReceipDetailView receipDetailView, User user, String orderId) {
		this.receipDetailView = receipDetailView;
		this.user = user;
		this.orderId = orderId;
		initReceiptDetailHandler();
		loadReceiptDetailData();
	}
	
	private void loadTableData() {
		ArrayList<Receipt> receipt = Receipt.getAllReceipts();
		receiptView.getTable().getItems().setAll(receipt);
	}
	
	private void loadReceiptDetailData() {
		ArrayList<ReceiptDetail> receipt = Receipt.getReceiptById(Integer.parseInt(orderId));
		receipDetailView.getTable().getItems().setAll(receipt);

	}
	
	private void setupCustTableSelectionListener() {
        receiptView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	receiptView.getOrderIdInput().setText(String.valueOf(newSelection.getOrderId()));
            }
        });
    }
	
	private void initializeHandler() {
		receiptView.getBackButton().setOnAction(e -> {
			primaryStage = receiptView.getPrimaryStage();
			CustomerView customerView = new CustomerView(primaryStage);
			CustomerController customerController = new CustomerController(customerView, user);
		});
		
		receiptView.getReceiptDetailsButton().setOnAction(e->{
			primaryStage = receiptView.getPrimaryStage();
			String orderId = receiptView.getOrderIdInput().getText();
			ReceipDetailView receiptDetailView = new ReceipDetailView(primaryStage);
			ReceiptController receiptController = new ReceiptController(receiptDetailView, user, orderId);
		});
		
		TableColumn<MenuItem, String> menuItemName = new TableColumn<>("Menu Item Name");
		menuItemName.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

		TableColumn<MenuItem, Number> menuItemPrice = new TableColumn<>("Menu Item Price");
		menuItemPrice.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));
		
		TableView<OrderItem> table = new TableView<>();
		TableColumn<OrderItem, Number> quantity = new TableColumn<>("Quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		
	}	
	
	private void initReceiptDetailHandler() {
		receipDetailView.getBackButton().setOnAction(e -> {
			primaryStage = receipDetailView.getPrimaryStage();
			ReceiptView receiptView = new ReceiptView(primaryStage);
			ReceiptController receiptController = new ReceiptController(receiptView, user);
		});
	}

	
}

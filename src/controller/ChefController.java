package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;
import model.User;
import view.ChefView;
import view.CustomerMenuView;
import view.CustomerView;
import view.OrderItemView;
import view.OrderView;
import view.ReceiptView;

public class ChefController {
	private Stage primaryStage;
	private CustomerView customerView;
	private ReceiptView receiptView;
	private OrderItemView orderItemView;
	private User user;
	private Button menuViewButton, orderViewButton, receiptButton, logoutButton, receiptDetailsButton;
	private Label greetings, role;
	private VBox vbox;
	private ChefView chefView;
	private ChefController chefController;
	
	public ChefController(ChefView chefView, User user) {
		this.chefView = chefView;
		this.user = user;
		initHandler();
		loadTableData();
	}

	
	private void initHandler() {
		chefView.getBackButton().setOnAction(e->{
			primaryStage = chefView.getPrimaryStage();
			CustomerView customerView = new CustomerView(primaryStage);
			CustomerController customerController = new CustomerController(customerView, user);
		});
		
//		chefView.getUpdateButton().setOnAction(e->{
//			ArrayList<Order> order;
//			Order order = null;
//			ArrayList<Order> order;
//			String orderId_t = chefView.getIdInput().getText();
//			String orderStatus = chefView.getOrderStatusInput().getText();
//			int orderId = Integer.parseInt(orderId_t);
////			
//			orderStatus = "PREAPARE";
//			Order.updateOrder(orderId, orderStatus);
//			Order orders.updateOrder(orderId, "PREAPARE");
//			
//			loadTableData();
//		});
	}
	private void loadTableData() {
		ArrayList<Order> order = Order.getAllChefOrders();
		chefView.getTable().getItems().setAll(order);
	}
	
}

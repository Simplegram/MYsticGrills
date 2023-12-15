package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import javafx.scene.control.Alert;
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

public class ChefController extends Controller{
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
		setupChefTableSelectionListener();
		loadTableData();
	}

	
	private void initHandler() {
        chefView.getBackButton().setOnAction(e->{
            primaryStage = chefView.getPrimaryStage();
            CustomerView customerView = new CustomerView(primaryStage);
            CustomerController customerController = new CustomerController(customerView, user);
        });
        
        chefView.getPrepareButton().setOnAction(e->{
            String orderId_t = chefView.getIdInput().getText();
            
            if(orderId_t.isEmpty()){
                showAlert(Alert.AlertType.ERROR, "Error", null, "You must choose a PENDING order!");
                return;
            }
            
            int orderId = Integer.parseInt(orderId_t);
            
            Order.updateOrder(orderId, "PREPARED");
            
            loadTableData();
        });
        
        chefView.getDeleteButton().setOnAction(e->{
            
            Order.deleteOrder(Integer.parseInt(chefView.getIdInput().getText()));
            
            
            loadTableData();
        });
       
        chefView.getViewDetailButton().setOnAction(e->{
        	primaryStage = chefView.getPrimaryStage();
            OrderItemView orderItemView = new OrderItemView(primaryStage);
            OrderController orderController = new OrderController(orderItemView, user, Integer.parseInt(chefView.getIdInput().getText()));
        });
        
    }
	
	private void setupChefTableSelectionListener() {
        chefView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	chefView.getIdInput().setText(String.valueOf(newSelection.getOrderId()));
            	chefView.getOrderStatusInput().setText(newSelection.getOrderStatus());
            	chefView.getOrderDateInput().setText(String.valueOf(newSelection.getOrderDate()));
            	chefView.getOrderTotalInput().setText(String.valueOf(newSelection.getOrderTotal()));
            }
        });
    }
	
	private void loadTableData() {
		ArrayList<Order> order = Order.getAllChefOrders();
		chefView.getTable().getItems().setAll(order);
	}
	

	
}

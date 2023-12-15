package controller;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;
import model.User;
import view.ChefView;
import view.CustomerView;
import view.OrderItemView;
import view.ReceiptView;
import view.WaiterView;

public class WaiterController extends Controller{
	private Stage primaryStage;
	private CustomerView customerView;
	private ReceiptView receiptView;
	private OrderItemView orderItemView;
	private User user;
	private Button menuViewButton, orderViewButton, receiptButton, logoutButton, receiptDetailsButton;
	private Label greetings, role;
	private VBox vbox;
	private WaiterView waiterView;
	private ChefController chefController;
	
	public WaiterController(WaiterView waiterView, User user) {
		this.waiterView = waiterView;
		this.user = user;
		initHandler();
		setupChefTableSelectionListener();
		loadTableData();
	}

	
	private void initHandler() {
        waiterView.getBackButton().setOnAction(e->{
            primaryStage = waiterView.getPrimaryStage();
            CustomerView customerView = new CustomerView(primaryStage);
            CustomerController customerController = new CustomerController(customerView, user);
        });
        
        waiterView.getPrepareButton().setOnAction(e->{
            String orderId_t = waiterView.getIdInput().getText();
            
            if(orderId_t.isEmpty()){
                showAlert(Alert.AlertType.ERROR, "Error", null, "You must choose a PENDING order!");
                return;
            }
            
            int orderId = Integer.parseInt(orderId_t);
            
            Order.updateOrder(orderId, "SERVED");
            
            loadTableData();
        });
        
        waiterView.getDeleteButton().setOnAction(e->{
            
            Order.deleteOrder(Integer.parseInt(waiterView.getIdInput().getText()));
            
            loadTableData();
        });
       
        waiterView.getViewDetailButton().setOnAction(e->{
        	primaryStage = waiterView.getPrimaryStage();
            OrderItemView orderItemView = new OrderItemView(primaryStage);
            OrderController orderController = new OrderController(orderItemView, user, Integer.parseInt(waiterView.getIdInput().getText()));
        });
        
    }
	
	private void setupChefTableSelectionListener() {
        waiterView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	waiterView.getIdInput().setText(String.valueOf(newSelection.getOrderId()));
            	waiterView.getOrderStatusInput().setText(newSelection.getOrderStatus());
            	waiterView.getOrderDateInput().setText(String.valueOf(newSelection.getOrderDate()));
            	waiterView.getOrderTotalInput().setText(String.valueOf(newSelection.getOrderTotal()));
            }
        });
    }
	
	private void loadTableData() {
		ArrayList<Order> order = Order.getAllWaiterOrders();
		waiterView.getTable().getItems().setAll(order);
	}
	

	
}

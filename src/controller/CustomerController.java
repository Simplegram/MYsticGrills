package controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.User;
import view.ChefView;
import view.CustomerMenuView;
import view.CustomerView;
import view.OrderItemView;
import view.OrderView;
import view.ReceiptView;
import view.WaiterView;

public class CustomerController {
	private Stage primaryStage;
	private CustomerView customerView;
	private ReceiptView receiptView;
	private OrderItemView orderItemView;
	private User user;
	private Button menuViewButton, orderViewButton, receiptButton, logoutButton, receiptDetailsButton;
	private Label greetings, role;
	private VBox vbox;
	private ChefView chefView;
	private Button cheforderViewButton;
	public CustomerController(CustomerView customerView, User user) {
		this.customerView = customerView;
		this.user = user;
		initHandler();
		greetings();
		senseRole();
	}
	
	private void initHandler() {
		customerView.getMenuViewButton().setOnAction(e -> {
			primaryStage = customerView.getPrimaryStage();
			CustomerMenuView orderView = new CustomerMenuView(primaryStage);
			OrderController OrderController = new OrderController(orderView, user);
		});
		
		customerView.getOrderViewButton().setOnAction(e -> {
			primaryStage = customerView.getPrimaryStage();
			OrderView orderView = new OrderView(primaryStage);
			OrderController orderController = new OrderController(orderView, user);
		});

//		chefView.getCheforderViewButton().setOnAction(e->{
//			primaryStage = chefView.getPrimaryStage();
//			ChefView chefView = new ChefView(primaryStage);
//			Chef
//		});
		
	}
	
	private void greetings() {
		Label greetings = customerView.getGreetings();
		Label role = customerView.getRole();
		
		greetings.setText("Welcome, " + user.getUserName());
		greetings.setFont(new Font("Cambria", 20));
		greetings.setStyle("--fx-font-weight: bold");
		
		role.setText("You are a " + user.getUserRole());
	}
	
	private void senseRole() {
		String userRole = user.getUserRole();
		primaryStage = customerView.getPrimaryStage();
		vbox = customerView.getVbox();
		menuViewButton = customerView.getMenuViewButton();
		orderViewButton = customerView.getOrderViewButton();
		receiptButton = customerView.getReceiptButton();
//		receiptDetailsButton = receiptView.getReceiptDetails();
		logoutButton = customerView.getLogoutButton();
		greetings = customerView.getGreetings();
		role = customerView.getRole();
		
		if(userRole.equals("Customer")) {
			vbox.getChildren().addAll(greetings, role, menuViewButton, orderViewButton, logoutButton);
			primaryStage.setTitle("Customer Page");
			
		} else if(userRole.equals("Cashier")) {
			vbox.getChildren().addAll(greetings, role, orderViewButton, receiptButton, logoutButton);
			primaryStage.setTitle("Cashier Page");
			
			receiptButton.setOnAction(e ->{
				primaryStage = customerView.getPrimaryStage();
				ReceiptView receiptView = new ReceiptView(primaryStage);
				ReceiptController receiptController = new ReceiptController(receiptView, user);
				
			});
			
//			receiptView.getReceiptDetailsButton().setOnAction(e->{
//				primaryStage = orderItemView.getPrimaryStage();
//				OrderItemView orderItemView = new OrderItemView(primaryStage);
//				OrderItemController orderItemController = new OrderItemController();
//			});
			
//			receiptDetailsButton.setOnAction(e-> {
//				primaryStage = orderItemView.getPrimaryStage();
//				OrderItemView orderItemView = new OrderItemView(primaryStage);
//				OrderItemController orderController = new OrderItemController();
//			});
			
		} else if(userRole.equals("Chef")) {
			customerView.getOrderViewButton().setOnAction(e-> {
				primaryStage = customerView.getPrimaryStage();
				ChefView chefView = new ChefView(primaryStage);
				ChefController chefController = new ChefController(chefView, user);
			});

			vbox.getChildren().addAll(greetings, role, orderViewButton, logoutButton);
			primaryStage.setTitle("Chef Page");
			
			
		}
		

	    else if(userRole.equals("Waiter")) {
	            customerView.getOrderViewButton().setOnAction(e-> {
	                primaryStage = customerView.getPrimaryStage();
	                WaiterView waiterView = new WaiterView(primaryStage);
	                WaiterController waiterController = new WaiterController(waiterView, user);
	            });
	            
	            vbox.getChildren().addAll(greetings, role, orderViewButton, logoutButton);
	            primaryStage.setTitle("Waiter Page");

	        

		}
	}

}
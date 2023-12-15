package controller;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.Receipt;
import model.User;
import view.ChefView;
import view.CustomerMenuView;
import view.CustomerView;
import view.OrderView;
import view.OrderItemView;
import java.sql.Date;

public class OrderController extends Controller {
	private Stage primaryStage;
	private int orderId;
	private User user;
	private OrderView orderView;
	private CustomerMenuView customerMenuView;
	private OrderItemView orderItemView;
	private ChefView chefView;
	
	private TextField orderIdInput;
	private TextField menuItemInput;
	private TextField quantityInput;
	private TextField paymentTypeInput;
	private TextField paymentAmountInput;
	private Button updateButton;
	private GridPane form;

	public OrderController(OrderView orderView, User user) {
		this.user = user;
		this.orderView = orderView;
		
		setupTableSelectionListener();
		initHandler();
		
		String userRole = user.getUserRole();
		if(userRole.equals("Customer")) {
			loadTableData();
		} else if(userRole.equals("Cashier")) {
			loadCashierData();
		} else if(userRole.equals("Chef")){
			loadChefData();
		} else if(userRole.equals("Waiters")) {
			loadWaitersTableData();
		}
	}
	
	public OrderController(CustomerMenuView customerMenuView, User user) {
		this.user = user;
		this.customerMenuView = customerMenuView;
		loadCustTableData();
		setupCustTableSelectionListener();
		initCustMenuHandler();
	}
	
	public OrderController(OrderItemView orderItemView, User user, int orderId) {
		this.user = user;
		this.orderItemView = orderItemView;
		this.orderId = orderId;
		
		loadOrderItemTableData();
		
		String userRole = user.getUserRole();
		if(userRole.equals("Customer")) {
			setupOrderItemSelectionListener();
			initOrderItemHandler();
			initCustUIHandler();
		} else if(userRole.equals("Cashier")) {
			initCashierHandler();
			initCashierUIHandler();
		} else if(userRole.equals("Chef")) {
			initChefHandler();
			initChefUIHandler();
		}
//		}else if(userRole.equals("Chef")) {
//			initChefHandler();
//			initChefUIHandler();
//		}
		
	}

	void loadTableData() {
		ArrayList<Order> orders = Order.getOrdersByCustomerId(user.getUserId());
		orderView.getTable().getItems().setAll(orders);
	}
	
	void loadCashierData() {
		ArrayList<Order> orders = Order.getAllOrders();
		orderView.getTable().getItems().setAll(orders);
	}
	
	void loadChefData() {
		ArrayList<Order> orders = Order.getAllChefOrders();
		orderView.getTable().getItems().setAll(orders);
	}
	
	void loadCustTableData() {
		ArrayList<MenuItem> menuItems = MenuItem.getAllMenuItems();
		customerMenuView.getTable().getItems().setAll(menuItems);
	}
	
	void loadOrderItemTableData() {
		ArrayList<OrderItem> orderItems = OrderItem.getAllOrderItemsByOrderId(orderId);
		orderItemView.getTable().getItems().setAll(orderItems);
	}
	
	void loadWaitersTableData() {
		ArrayList<Order> orders = Order.getAllWaiterOrders();
		orderView.getTable().getItems().setAll(orders);
	}
	
	private void setupTableSelectionListener() {
		orderView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				orderView.getIdInput().setText(String.valueOf(newSelection.getOrderId()));
				orderView.getOrderStatusInput().setText(newSelection.getOrderStatus());
				orderView.getOrderDateInput().setText(String.valueOf(newSelection.getOrderDate()));
				orderView.getOrderTotalInput().setText(String.valueOf(newSelection.getOrderTotal()));
			}
		});
	}
	
	private void setupCustTableSelectionListener() {
        customerMenuView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	customerMenuView.getIdInput().setText(String.valueOf(newSelection.getMenuItemId()));
            	customerMenuView.getNameInput().setText(newSelection.getMenuItemName());
            	customerMenuView.getDescInput().setText(newSelection.getMenuItemDescription());
            	customerMenuView.getPriceInput().setText(String.valueOf(newSelection.getMenuItemPrice()));
            }
        });
    }
	
	private void setupOrderItemSelectionListener() {
		orderItemView.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				orderItemView.getOrderIdInput().setText(String.valueOf(newSelection.getOrderId()));
				orderItemView.getMenuItemInput().setText(String.valueOf(newSelection.getMenuItem()));
				orderItemView.getQuantityInput().setText(String.valueOf(newSelection.getQuantity()));
			}
		});
	}
	
	private void initCustUIHandler() {
		paymentTypeInput = orderItemView.getPaymentTypeInput();
		paymentAmountInput = orderItemView.getPaymentAmountInput();
		updateButton = orderItemView.getUpdateButton();
		form = orderItemView.getForm();
		updateButton.setText("Update");
		paymentTypeInput.setVisible(false);
		paymentAmountInput.setVisible(false);
		form.add(new Label("Quantity:"), 0, 2);
		form.add(new Label("Menu Item ID:"), 0, 1);
	}
	
	private void initCashierUIHandler() {
		Order order = Order.getOrderByOrderId(orderId);
		
		updateButton = orderItemView.getUpdateButton();
		form = orderItemView.getForm();
		paymentTypeInput = orderItemView.getPaymentTypeInput();
		paymentAmountInput = orderItemView.getPaymentAmountInput();
		quantityInput = orderItemView.getQuantityInput();
		menuItemInput = orderItemView.getMenuItemInput();
		
		quantityInput.setVisible(false);
		updateButton.setText("Process");
		form.add(new Label("Total Price:"), 0, 1);
		form.add(new Label("Payment Type:"), 0, 3);
		form.add(new Label("Payment Amount:"), 0, 4);
		
		orderIdInput = orderItemView.getOrderIdInput();
			
		orderIdInput.setText(String.valueOf(orderId));
		menuItemInput.setText(String.valueOf(order.getOrderTotal()));
	}
	
	private void initChefUIHandler() {
		
		updateButton = chefView.getUpdateButton();
		form = chefView.getForm();
	
		paymentTypeInput = orderItemView.getPaymentTypeInput();
		paymentAmountInput = orderItemView.getPaymentAmountInput();
		quantityInput = orderItemView.getQuantityInput();
		menuItemInput = orderItemView.getMenuItemInput();
		
		quantityInput.setVisible(false);
		updateButton.setText("U");
		form.add(new Label("Total Price:"), 0, 1);
		form.add(new Label("Payment Type:"), 0, 3);
		form.add(new Label("Payment Amount:"), 0, 4);
	
		orderIdInput = orderItemView.getOrderIdInput();
		orderIdInput.setText(String.valueOf(orderId));
//		menuItemInput.setText(String.valueOf(order.getOrderTotal()));
	}
	
	private void initChefHandler() {
		
		orderView.getBackButton().setOnAction(e -> {
		primaryStage = orderView.getPrimaryStage();
		CustomerView customerView = new CustomerView(primaryStage);
		CustomerController customerController = new CustomerController(customerView, user);
		});
		
		chefView.getUpdateButton().setOnAction(e -> {
			primaryStage = chefView.getPrimaryStage();
			ArrayList<Order> orders = Order.getAllChefOrders();
			
			String updates_t;
			Boolean pendingExist = false;
			updates_t = "SERVE";
			
			showAlert(Alert.AlertType.INFORMATION, "Success", null, "Order Status Has been Updated");
		});
	}
	
	private void initHandler() {
			orderView.getBackButton().setOnAction(e -> {
			primaryStage = orderView.getPrimaryStage();
			CustomerView customerView = new CustomerView(primaryStage);
			CustomerController customerController = new CustomerController(customerView, user);
		});
		
		orderView.getViewButton().setOnAction(e -> {
			primaryStage = orderView.getPrimaryStage();
			
			String orderId_t = orderView.getIdInput().getText();
			if (orderId_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Please select an item.");
				return;
			}
			int orderId = Integer.parseInt(orderId_t);

			OrderItemView orderItemView = new OrderItemView(primaryStage);
			OrderController orderController = new OrderController(orderItemView, user, orderId);
		});
	}
	

	private void initCashierHandler() {
		orderItemView.getBackButton().setOnAction(e -> {
			primaryStage = orderItemView.getPrimaryStage();
			OrderView orderView = new OrderView(primaryStage);
			OrderController orderController = new OrderController(orderView, user);
		});
		
		orderItemView.getUpdateButton().setOnAction(e -> {
			String orderId_t = orderItemView.getOrderIdInput().getText();
			String payment_t = orderItemView.getPaymentTypeInput().getText();
			String paymentAmount_t = orderItemView.getPaymentAmountInput().getText();
			
			Order order = Order.getOrderByOrderId(orderId);
			
			if(!order.getOrderStatus().equals("PENDING")) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Order already paid!");
				return;
			}
			
			int totalPrice = Integer.parseInt(orderItemView.getMenuItemInput().getText());
			if (payment_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "You must choose a payment type, either \'Cash\', \'Debit\', or \'Credit\'.");
				return;
			} else if (!(payment_t.equals("Cash")) && !(payment_t.equals("Debit")) && !(payment_t.equals("Credit"))) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "You must choose either \'Cash\', \'Debit\', or \'Credit\'.");
				return;
			} else if(paymentAmount_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "You must input an amount to be paid.");
				return;
			}
			
			int orderId = Integer.parseInt(orderId_t);
			int paymentAmount = Integer.parseInt(paymentAmount_t);
			
			if (paymentAmount < totalPrice) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "You must input an amount equal or greater than the total price.");
				return;
			}
			
			Order.updateOrder(orderId, "PAID");
			long ms = System.currentTimeMillis();
			Date date = new Date(ms);
			Date orderDate = date;
			Receipt.createReceipt(orderId, payment_t, totalPrice, orderDate);
			
			primaryStage = orderItemView.getPrimaryStage();
			OrderView orderView = new OrderView(primaryStage);
			OrderController orderController = new OrderController(orderView, user);
		});
	}
	
	private void initCustMenuHandler() {
		customerMenuView.getBackButton().setOnAction(e -> {
			primaryStage = customerMenuView.getPrimaryStage();
			CustomerView customerView = new CustomerView(primaryStage);
			CustomerController customerController = new CustomerController(customerView, user);
		});


		customerMenuView.getAddButton().setOnAction(e -> {
			primaryStage = customerMenuView.getPrimaryStage();
			ArrayList<Order> orders = Order.getOrdersByCustomerId(user.getUserId());
			ArrayList<OrderItem> orderItems;
			
			String menuItems_t = customerMenuView.getIdInput().getText();
			String quantity_t = customerMenuView.getQuantityInput().getText();
			Boolean pendingExist = false;
			int orderTotal;
			
			if (menuItems_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Please select an item.");
				return;
			} else if (quantity_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Quantity can't be null!");
				return;
			} 
			
			int userId;
			
			try {
				userId = user.getUserId();
			} catch (Exception e1) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Who in the name of BINUS UNIVERSITY are you???");
				return;
			}
			
			int menuItem = Integer.parseInt(menuItems_t);
			int quantity = Integer.parseInt(quantity_t);
			int itemPrice = Math.round(Float.parseFloat(customerMenuView.getPriceInput().getText()));
			
			int orderId;
			for (Order order : orders) {
				if(order.getOrderStatus().equals("PENDING")) {
					orderId = order.getOrderId();
					pendingExist = true;
				}
			}
			
			if(orders.isEmpty() || pendingExist.equals(false)) {
				if (quantity < 1) {
					showAlert(Alert.AlertType.ERROR, "Error", null, "Quantity can't go below 1!");
					return;
				}
				
				long ms = System.currentTimeMillis();
				Date date = new Date(ms);
				Date orderDate = date;
				
				Order.createOrder(userId, orderDate);
			}
			
			orders = Order.getOrdersByCustomerId(user.getUserId());
			for (Order order : orders) {
				if(order.getOrderStatus().equals("PENDING")) {					
					orderId = order.getOrderId();
					orderItems = OrderItem.getAllOrderItemsByOrderId(orderId);
					orderTotal = order.getOrderTotal();
					
					int itemPriceTotal = orderTotal + (quantity * itemPrice);
					
					for (OrderItem orderItem : orderItems) {
						if(orderItem.getMenuItem() == menuItem) {
							int currQuantity = orderItem.getQuantity();
							OrderItem.updateOrderItem(orderId, menuItem, currQuantity + quantity);
							Order.updateOrderTotal(orderId, itemPriceTotal);
							showAlert(Alert.AlertType.INFORMATION, "Success", null, "Order item successfully added!");
							return;
						}
					}
					
					OrderItem.createOrderItem(orderId, menuItem, quantity);
					Order.updateOrderTotal(orderId, itemPriceTotal);
					
					showAlert(Alert.AlertType.INFORMATION, "Success", null, "Order item successfully created! You can add more if you wish.");
				}
			}
		});
	}
	
	private void initOrderItemHandler() {
		orderItemView.getBackButton().setOnAction(e -> {
			primaryStage = orderItemView.getPrimaryStage();
			OrderView orderView = new OrderView(primaryStage);
			OrderController orderController = new OrderController(orderView, user);
		});
		
		orderItemView.getUpdateButton().setOnAction(e -> {
			ArrayList<OrderItem> orderItems;
			MenuItem menuItems;
			Order order;
			
			String orderId_t = orderItemView.getOrderIdInput().getText();
			String menuItem_t = orderItemView.getMenuItemInput().getText();
			String quantity_t = orderItemView.getQuantityInput().getText();
			
			if (orderId_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Please select an item.");
				return;
			}
			
			if (quantity_t.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Quantity can't be null!");
				return;
			}
			
			int orderId = Integer.parseInt(orderId_t);
			int menuItem = Integer.parseInt(menuItem_t);
			int quantity = Integer.parseInt(quantity_t);
			
			int currMenuQuantity, orderTotal, menuPrice;
			orderItems = OrderItem.getAllOrderItemsByOrderId(orderId);
			menuItems = MenuItem.getMenuItemById(menuItem);
			order = Order.getOrderByOrderId(orderId);
			
			if(!order.getOrderStatus().equals("PENDING")) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "You can only update PENDING orders.");
				return;
			}
			
			if(quantity < 0) {
				showAlert(Alert.AlertType.ERROR, "Error", null, "Quantity can only be 0 or more (0 means that the item will be removed from the order)");
				return;
			} else if(quantity >= 0){
				for (OrderItem orderItem : orderItems) {
					if (orderItem.getMenuItem() == menuItem) {
						currMenuQuantity = orderItem.getQuantity();
						menuPrice = (int) (menuItems.getMenuItemPrice());
						orderTotal = order.getOrderTotal();
						
						if(quantity < currMenuQuantity) {
							orderTotal = orderTotal - ((currMenuQuantity - quantity) * menuPrice);
						} else if(quantity > currMenuQuantity) {
							orderTotal = orderTotal + ((quantity - currMenuQuantity) * menuPrice);
						} else {
							orderTotal = orderTotal + (quantity * menuPrice);
						}
						
						if(quantity == 0) {
							OrderItem.deleteOrderItem(orderId, menuItem);
							Order.updateOrderTotal(orderId, orderTotal);
							showAlert(Alert.AlertType.INFORMATION, "Success", null, "Order item successfully deleted.");
						} else {
							OrderItem.updateOrderItem(orderId, menuItem, quantity);
							Order.updateOrderTotal(orderId, orderTotal);
							showAlert(Alert.AlertType.INFORMATION, "Success", null, "Order item successfully updated.");
						}
					}
				}
			}
				
			loadOrderItemTableData();
		});
	}
}

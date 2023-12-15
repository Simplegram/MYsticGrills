package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;

import database.Connect;

public class Order {
	private enum status{
		PENDING,
		PAID,
		PREPARED,
		SERVED
	}
	
	private int orderId;
	private int orderUser;
	private String orderStatus;
	private Date orderDate;
	private int orderTotal;

	public Order(int orderId, int orderUser, String orderStatus, Date orderDate, int orderTotal) {
		this.orderId = orderId;
		this.orderUser = orderUser;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
	}
	
	public static void createOrder(int orderUser, Date orderDate) {
		String query = String.format("INSERT INTO orders (orderUser, orderStatus, orderDate, orderTotal) VALUES (?, ?, ?, ?)");
		try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setInt(1, orderUser);
			ps.setString(2, "PENDING");
			ps.setDate(3, orderDate);
			ps.setDouble(4, 0);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateOrder(int orderId, String orderStatus) {
		String query = String.format("UPDATE orders SET orderStatus = ? WHERE orderId = ?");
		try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setString(1, orderStatus);
			ps.setInt(2, orderId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateOrderTotal(int orderId, int orderPrice) {
		String query = String.format("UPDATE orders SET orderTotal = ? WHERE orderId = ?");
		try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setInt(1, orderPrice);
			ps.setInt(2, orderId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteOrder(int orderId) {
		String query = String.format("DELETE FROM order WHERE orderId = ?");
		try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setInt(1, orderId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Order> getOrdersByCustomerId(int customerId) {
		ArrayList<Order> orders = new ArrayList<>();
		String query = String.format("SELECT * FROM orders WHERE orderUser = %d", customerId);
		ResultSet rs = Connect.getConnection().executeQuery(query);
		
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderUser = rs.getInt(2);
				String orderStatus = rs.getString(3);
				Date orderDate = rs.getDate(4);
				int orderTotal = rs.getInt(5);
				orders.add(new Order(id, orderUser, orderStatus, orderDate, orderTotal));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static Order getOrderByOrderId(int orderId) {
		String query = String.format("SELECT * FROM orders WHERE orderId = ?");
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		ResultSet rs = null;
		
		try {
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderUser = rs.getInt(2);
				String orderStatus = rs.getString(3);
				Date orderDate = rs.getDate(4);
				int orderTotal = rs.getInt(5);
				return new Order(id, orderUser, orderStatus, orderDate, orderTotal);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Order> getAllOrders(){
		ArrayList<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM orders";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderUser = rs.getInt(2);
				String orderStatus = rs.getString(3);
				Date orderDate = rs.getDate(4);
				int orderTotal = rs.getInt(5);
				orders.add(new Order(id, orderUser, orderStatus, orderDate, orderTotal));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static ArrayList<Order> getAllChefOrders(){
		ArrayList<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM orders WHERE orderStatus = 'PENDING'";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderUser = rs.getInt(2);
				String orderStatus = rs.getString(3);
				Date orderDate = rs.getDate(4);
				int orderTotal = rs.getInt(5);
				orders.add(new Order(id, orderUser, orderStatus, orderDate, orderTotal));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public static ArrayList<Order> getAllWaiterOrders(){
		ArrayList<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM orders WHERE orderStatus = 'PREPARED' OR orderStatus = 'SERVE'";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderUser = rs.getInt(2);
				String orderStatus = rs.getString(3);
				Date orderDate = rs.getDate(4);
				int orderTotal = rs.getInt(5);
				orders.add(new Order(id, orderUser, orderStatus, orderDate, orderTotal));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(int orderUser) {
		this.orderUser = orderUser;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
}

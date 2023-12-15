package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class OrderItem {
	private int orderId;
	private int menuItem;
	private int quantity;

	public OrderItem(int orderId, int menuItem, int quantity) {
		this.orderId = orderId;
		this.menuItem = menuItem;
		this.quantity = quantity;
	}

	public static void createOrderItem(int orderId, int menuItem, int quantity) {
		String query = String.format("INSERT INTO orderitem (orderID, menuItem, quantity) VALUES (%d, %d, %d)", orderId, menuItem, quantity);
		Connect.getConnection().executeUpdate(query);
	}
	
	public static void updateOrderItem(int orderId, int menuItem, int quantity) {
		String query = String.format("UPDATE orderitem SET orderID = ?, menuItem = ?, quantity = ? WHERE orderID = ? AND menuItem = ?");
		try(PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setInt(1, orderId);
			ps.setInt(2, menuItem);
			ps.setInt(3, quantity);
			ps.setInt(4, orderId);
			ps.setInt(5, menuItem);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<OrderItem> getAllOrderItemsByOrderId(int orderId) {
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		String query = "SELECT * FROM orderitem WHERE orderId = ?";
		try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setInt(1, orderId);
			try (ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					int menuItem = rs.getInt("menuItem");
					int quantity = rs.getInt("quantity");
					orderItems.add(new OrderItem(orderId, menuItem, quantity));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItems;
	}
	
	public static void deleteOrderItem(int orderId, int menuItemId) {
		String query = String.format("DELETE FROM orderitem WHERE orderId = ? AND menuItem = ?");
		try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
			ps.setInt(1, orderId);
			ps.setInt(2, menuItemId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public int getMenuItem() {
		return menuItem;
	}

	public int getQuantity() {
		return quantity;
	}
}

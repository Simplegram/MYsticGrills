package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class ReceiptDetail {
	private final String menuItemName;
	private final double menuItemPrice;
	private final int quantity;
	
	public ReceiptDetail(String menuItemName, double menuItemPrice, int quantity) {
		this.menuItemName = menuItemName;
		this.menuItemPrice = menuItemPrice;
		this.quantity = quantity;
	}
	public static ArrayList<ReceiptDetail> getReceiptById(int receiptId) {
	    ArrayList<ReceiptDetail> receipts = new ArrayList<>();
	    String query = "SELECT menu.menuItemName, menu.menuItemPrice, `order`.quantity " +
	                   "FROM menu JOIN `order` ON menu.menuID = `order`.menuID";
	    ResultSet rs = Connect.getConnection().executeQuery(query);
	    try {
	        while (rs.next()) {
	            String menuItemName = rs.getString("menuItemName");
	            double menuItemPrice = rs.getDouble("menuItemPrice");
	            int quantity = rs.getInt("quantity");
	            
	            receipts.add(new ReceiptDetail(menuItemName, menuItemPrice, quantity));
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return receipts;
	}
	public String getMenuItemName() {
		return menuItemName;
	}
	public double getMenuItemPrice() {
		return menuItemPrice;
	}
	public int getQuantity() {
		return quantity;
	}

	
}
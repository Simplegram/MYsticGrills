package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Receipt {
		
		private int receiptId;
		private int receiptOrder;
		private int receiptPaymentAmount;
		private Date receiptPaymentDate;
		private String receiptPaymentType;
		private MenuItem menuItem;

//		private final String menuItemName;
//		private final double menuItemPrice;
//		private final int quantity;
		
		public Receipt(int receiptId, int receiptOrder, int receiptPaymentAmount, Date receiptPaymentDate, String receiptPaymentType) {
			super();
			this.receiptId = receiptId;
			this.receiptOrder = receiptOrder;
			this.receiptPaymentAmount = receiptPaymentAmount;
			this.receiptPaymentDate = receiptPaymentDate;
			this.receiptPaymentType = receiptPaymentType;
		}

//		public Receipt(String menuItemName, double menuItemPrice, int quantity) {
//			this.menuItemName = menuItemName;
//			this.menuItemPrice = menuItemPrice;
//			this.quantity = quantity;
//		}


		public static void createReceipt(int receiptOrder, String receiptPaymentType, int receiptPaymentAmount, Date receiptPaymentDate) {
			String query = String.format("INSERT INTO receipt (receiptOrder, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType) VALUES ( ?, ?, ?, ?)");
			try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
				ps.setInt(1, receiptOrder);
				ps.setInt(2, receiptPaymentAmount);
				ps.setDate(3, receiptPaymentDate);
				ps.setString(4, receiptPaymentType);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static ArrayList<Receipt> getAllReceipts(){
			ArrayList<Receipt> receipts = new ArrayList<>();
			String query = "SELECT * FROM receipt";
			ResultSet rs = Connect.getConnection().executeQuery(query);
			try {
				while (rs.next()) {
					int receiptId1 = rs.getInt(1);
					int receiptOrder = rs.getInt(2);
					int receiptPaymentAmount = rs.getInt(3);
					Date receiptPaymentDate = rs.getDate(4);
					String receiptPaymentType = rs.getString(5);
					receipts.add(new Receipt(receiptId1, receiptOrder, receiptPaymentAmount, receiptPaymentDate,receiptPaymentType));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return receipts;
		}

		public static ArrayList<ReceiptDetail> getReceiptById(int receiptId) {
		    ArrayList<ReceiptDetail> receipts = new ArrayList<>();
		    String query = "SELECT m.menuItemName AS menuItemName, m.menuItemPrice AS menuItemPrice, oi.quantity AS quantity FROM orders o JOIN orderitem oi ON oi.orderID = o.orderId JOIN menuitem m ON m.menuItemId = oi.menuItem JOIN receipt r ON r.receiptOrder = o.orderId WHERE r.receiptId = ?";
		    PreparedStatement ps = Connect.getConnection().prepareStatement(query);
	        ResultSet rs = null;
	        
	        try {
	            ps.setInt(1, receiptId);
	            rs = ps.executeQuery();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
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
		
		public int getReceiptId() {
			return receiptId;
		}

		public void setReceiptId(int receiptId) {
			this.receiptId = receiptId;
		}

		public int getReceiptOrder() {
			return receiptOrder;
		}

		public int getReceiptPaymentAmount() {
			return receiptPaymentAmount;
		}

		public Date getReceiptPaymentDate() {
			return receiptPaymentDate;
		}

		public String getReceiptPaymentType() {
			return receiptPaymentType;
		}

		public MenuItem getMenuItem() {
			return menuItem;
		}

		public void setMenuItem(MenuItem menuItem) {
			this.menuItem = menuItem;
		}

//		public String getMenuItemName() {
//			return menuItemName;
//		}
//
//		public double getMenuItemPrice() {
//			return menuItemPrice;
//		}
//
//		public int getQuantity() {
//			return quantity;
//		}

}

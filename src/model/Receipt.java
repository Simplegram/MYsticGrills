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
		private int orderId;
		private String receiptPaymentAmount;
		private int receiptPaymentDate;
		private Date receiptPaymentType;
		private MenuItem menuItem;

//		private final String menuItemName;
//		private final double menuItemPrice;
//		private final int quantity;
		
		public Receipt(int receiptId, int orderId, String receiptPaymentAmount, int receiptPatmentAmount,Date receiptPaymentDate) {
			super();
			this.receiptId = receiptId;
			this.orderId = orderId;
			this.receiptPaymentAmount = receiptPaymentAmount;
			this.receiptPaymentDate = receiptPatmentAmount;
			this.receiptPaymentType = receiptPaymentDate;
		}

//		public Receipt(String menuItemName, double menuItemPrice, int quantity) {
//			this.menuItemName = menuItemName;
//			this.menuItemPrice = menuItemPrice;
//			this.quantity = quantity;
//		}


		public static void createReceipt(int receiptOrder, String receiptPaymentType, int receiptPaymentAmount, Date receiptPaymentDate) {
			String query = String.format("INSERT INTO receipt (receiptOrder, receiptPaymentType, receiptPaymentAmount, receiptPaymentDate) VALUES ( ?, ?, ?, ?)");
			try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)){
				ps.setInt(1, receiptOrder);
				ps.setString(2, receiptPaymentType);
				ps.setInt(3, receiptPaymentAmount);
				ps.setDate(4, receiptPaymentDate);
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
					int orderId = rs.getInt(2);
					String receiptPaymentType = rs.getString(5);
					int receiptPatmentAmount = rs.getInt(3);
					Date receiptPaymentDate = rs.getDate(4);
					receipts.add(new Receipt(receiptId1, orderId, receiptPaymentType,receiptPatmentAmount,receiptPaymentDate));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return receipts;
		}

		public static ArrayList<ReceiptDetail> getReceiptById(int receiptId) {
		    ArrayList<ReceiptDetail> receipts = new ArrayList<>();
		    String query = "SELECT m.menuItemName, m.menuItemPrice, oi.quantity FROM orders o JOIN orderitem oi ON oi.orderID = o.orderId JOIN menuitem m ON m.menuItemId = oi.menuItem JOIN receipt r ON r.receiptOrder = o.orderId WHERE r.receiptOrder = ?";
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
		            String menuItemName = rs.getString("m.menuItemName");
		            double menuItemPrice = rs.getDouble("m.menuItemPrice");
		            int quantity = rs.getInt("oi.quantity");
		            
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

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public String getReceiptPaymentAmount() {
			return receiptPaymentAmount;
		}

		public void setReceiptPaymentAmount(String receiptPaymentAmount) {
			this.receiptPaymentAmount = receiptPaymentAmount;
		}

		public int getReceiptPaymentDate() {
			return receiptPaymentDate;
		}
		public void setReceiptPaymentDate(int receiptPaymentDate) {
			this.receiptPaymentDate = receiptPaymentDate;
		}

		public Date getReceiptPaymentType() {
			return receiptPaymentType;
		}

		public void setReceiptPaymentType(Date receiptPaymentType) {
			this.receiptPaymentType = receiptPaymentType;
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

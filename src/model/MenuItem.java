package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class MenuItem {
	private int MenuItemId;
	private String MenuItemName;
	private String MenuItemDescription;
	private double MenuItemPrice;

	public MenuItem(int menuItemId, String menuItemName, String menuItemDescription, double menuItemPrice) {
		MenuItemId = menuItemId;
		MenuItemName = menuItemName;
		MenuItemDescription = menuItemDescription;
		MenuItemPrice = menuItemPrice;
	}
	
	public static ArrayList<MenuItem> getAllMenuItems() {
		ArrayList<MenuItem> menuitems = new ArrayList<>();
		String query = "SELECT * FROM menuitem";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String desc = rs.getString(3);
				double price = rs.getDouble(4);

				menuitems.add(new MenuItem(id, name, desc, price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuitems;
	}
	
	public static void createMenuItem(String menuItemName, String menuItemDescription, double menuItemPrice) {
		String query = String.format(
				"INSERT INTO menuitem (menuItemName, menuItemDescription, menuItemPrice) VALUES ('%s', '%s', %f)", menuItemName, menuItemDescription,
				menuItemPrice);
		Connect.getConnection().executeUpdate(query);
	}
	
	public static void updateMenuItem(int menuItemIds, String menuItemName, String menuItemDescription, double menuItemPrice) {
		
		String query = String.format(
				"UPDATE menuitem SET menuItemName = ?, menuItemDescription = ?, menuItemPrice = ? WHERE menuItemId = ?"
				);
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setString(1, menuItemName);
			ps.setString(2, menuItemDescription);
			ps.setDouble(3, menuItemPrice);
			ps.setInt(4, menuItemIds);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteMenuItem(int id) {
		
		String query = "DELETE FROM menuitem WHERE menuItemId = ?";
		
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1,  id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static MenuItem getMenuItemById(int menuItemId) {
	    String query = "SELECT * FROM menuitem WHERE menuItemId = ?";
	    
	    PreparedStatement ps = Connect.getConnection().prepareStatement(query);
	    ResultSet rs = null;
	    try {
			ps.setInt(1, menuItemId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    MenuItem items;
	    try {
	        if (rs.next()) {
	            int id = rs.getInt(1);
	            String name = rs.getString(2);
	            String desc = rs.getString(3);
	            int price = rs.getInt(4);
	            items = new MenuItem(id, name, desc, price);
	        } else {
	            items = null;
	        }
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        items = null;
	    }
	    return items;
	}
	
	public int getMenuItemId() {
		return MenuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		MenuItemId = menuItemId;
	}

	public String getMenuItemName() {
		return MenuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		MenuItemName = menuItemName;
	}

	public String getMenuItemDescription() {
		return MenuItemDescription;
	}

	public void setMenuItemDescription(String menuItemDescription) {
		MenuItemDescription = menuItemDescription;
	}

	public double getMenuItemPrice() {
		return MenuItemPrice;
	}

	public void setMenuItemPrice(int menuItemPrice) {
		MenuItemPrice = menuItemPrice;
	}

}

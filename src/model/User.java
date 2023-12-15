package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class User {
	private int userId;
	private String userRole;
	private String userName;
	private String userEmail;
	private String userPassword;
	
	public User(int userId, String userRole, String userName, String userEmail, String userPassword) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public static void insert(String userRole, String userName, String userEmail, String userPassword) {
	    String query = "INSERT INTO user (userRole, userName, userEmail, userPassword) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)) {
	        ps.setString(1, userRole);
	        ps.setString(2, userName);
	        ps.setString(3, userEmail);
	        ps.setString(4, userPassword);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void delete(int id) {	
		String query = "DELETE FROM user WHERE userId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
		try {
			ps.setInt(1,  id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateRole(int id, String userRole, String userName, String userEmail, String userPassword) {
	    String query = "UPDATE user SET userRole = ? WHERE userId = ?";
	    try {
	        PreparedStatement ps = Connect.getConnection().prepareStatement(query);
	        ps.setString(1, userRole);
	        ps.setInt(2, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ArrayList<User> getAllUser() {
		ArrayList<User> users = new ArrayList<>();
		String query = "SELECT * FROM user";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String role = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);				
				String password = rs.getString(5);	
				
				users.add(new User(id, role, name, email, password));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}
	
	public static User getUserById(int userId) {
	    String query = "SELECT * FROM user WHERE userId = ?";
	    try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)) {
	        ps.setInt(1, userId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("userId");
	                String role = rs.getString("userRole");
	                String name = rs.getString("userName");
	                String email = rs.getString("userEmail");
	                String password = rs.getString("userPassword");
	                return new User(id, role, name, email, password);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static User getUserByEmail(String email) {
	    String query = "SELECT * FROM user WHERE userEmail = ?";
	    PreparedStatement ps = Connect.getConnection().prepareStatement(query);
	    try {
			ps.setString(1, email);
			 ResultSet rs = ps.executeQuery();

			    if (rs.next()) {
			        int id = rs.getInt("userId");
			        String role = rs.getString("userRole");
			        String name = rs.getString("userName");
			        String userEmail = rs.getString("userEmail");
			        String password = rs.getString("userPassword");

			        return new User(id, role, name, userEmail, password);
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return null; // Jika tidak ditemukan user dengan email tersebut
	}
	
	public static boolean isValidEmail(String email) {
	    String query = "SELECT * FROM user WHERE userEmail = ?";
	    try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)) {
	        ps.setString(1, email);
	        try (ResultSet rs = ps.executeQuery()) {
	            return !rs.next(); // Return true if no email found
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public static boolean loginChecking(String email, String password) {
        try {
            String query = "SELECT * FROM user WHERE userEmail = ? AND userPassword = ?";
            PreparedStatement statement = Connect.getConnection().prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}

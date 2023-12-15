//package controller;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//
//
//public class ReceiptControllerss {
////	private final SimpleStringProperty menuItemNames = new SimpleStringProperty();
////	private final SimpleIntegerProperty menuItemPrices = new SimpleIntegerProperty();
////	private final SimpleIntegerProperty quantitys = new SimpleIntegerProperty();
//	
//	private void fetchDataFromDatabase() {
//        try {
//            Connection connection = DriverManager.getConnection();s
//            Statement statement = connection.createStatement();
//            
//            // Menggunakan JOIN untuk menggabungkan data dari tabel menu dan order
//            ResultSet resultSet = statement.executeQuery("SELECT menu.menuName, menu.PriceMenu, order.quantity " +
//                    "FROM menu JOIN order ON menu.menuID = order.menuID");
//
//            while (resultSet.next()) {
//                String menuName = resultSet.getString("menuName");
//                double priceMenu = resultSet.getDouble("PriceMenu");
//                int quantity = resultSet.getInt("quantity");
//
//                orderItems.add(new OrderItem(menuName, priceMenu, quantity));
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
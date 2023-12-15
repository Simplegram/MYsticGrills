package controller;

import javafx.stage.Stage;
import model.User;
import view.AdminView;
import view.CustomerView;
import view.LoginView;
import view.MainView;
import view.RegisView;
import javafx.scene.control.Alert;

public class UserController extends Controller {
    private Stage primaryStage;
    private LoginView loginView;
    private RegisView regisView;

    public UserController(LoginView loginView) {
        this.loginView = loginView;
        initializeLoginHandler();
    }
    
    public UserController(RegisView regisView) {
        this.regisView = regisView;
        initializeRegisHandler();
    }


    private void initializeRegisHandler() {
        regisView.getRegisButton().setOnAction(e -> {
            String username = regisView.getUsernameInput().getText();
            String email = regisView.getEmailInput().getText();
            String password = regisView.getPasswordInput().getText();
            String confirmPassword = regisView.getConfirmPasswordInput().getText();
            
            
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Please fill in all fields.");
                return;
            } else if (password.length() < 6) {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Password should be at least 6 characters long.");
                return;
            } 
            
            if(!password.equals(confirmPassword)) {
            	showAlert(Alert.AlertType.ERROR, "Error", null, "Password and comfirm password must be same");
            	return;
            }
            
           if(User.isValidEmail(email)) {
        	   User.insert("Customer", username, email, confirmPassword);
        	   showAlert(Alert.AlertType.INFORMATION, "Success", null, "Account registered");
        	   primaryStage = regisView.getPrimaryStage();
        	   MainView mainView = new MainView(primaryStage);
   				MainController mainControler = new MainController(mainView);
        	   
           }
           else {
        	   showAlert(Alert.AlertType.ERROR, "Error", null, "Email is already registered.");
        	   return;
           }
           
        });
    }
    
    private void initializeLoginHandler() {
        loginView.getLoginButton().setOnAction(e -> {
            String email = loginView.getEmailInput().getText();
            String password = loginView.getPasswordInput().getText();

            if(User.loginChecking(email, password)) {
//                System.out.println("berhasil login");

                User user = User.getUserByEmail(email);
                String userRole = user.getUserRole();

                if(userRole.equals("Admin")) {
                	primaryStage = loginView.getPrimaryStage();
                    AdminView adminView = new AdminView(primaryStage);
                    AdminController adminController = new AdminController(adminView);
                } else if(userRole.equals("Customer") || userRole.equals("Cashier") || userRole.equals("Chef") || userRole.equals("Waiter")) {
                	primaryStage = loginView.getPrimaryStage();
                	CustomerView customerView = new CustomerView(primaryStage);
                	CustomerController customerController = new CustomerController(customerView, user);
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Login failed.");
            }
        });
    }

}

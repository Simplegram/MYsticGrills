package view;

import controller.AdminController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class UserManagementView extends VBox{
	private Stage primaryStage;
	private TextField idInput = new TextField();
	private TextField roleInput = new TextField();
	private TextField nameInput = new TextField();
	private TextField emailInput = new TextField();
	private TextField passwordInput = new TextField();
	private TableView<User> table;
	private Button updateButton, deleteButton, backButton;

	
	public UserManagementView(Stage primaryStage) {
		this.primaryStage = primaryStage;
		//table = createMenuItemTable();
		
		VBox root = new VBox();
		table = createUserTable();
		GridPane form = createUserForm(table);
		VBox.setMargin(form, new Insets(20));
		root.getChildren().addAll(table, form);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		Scene scene = new Scene(root, 800, 600);
		Image image = new Image("/image/icons/logo.png");
        primaryStage.getIcons().add(image);
		primaryStage.setTitle("User Management");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private TableView<User> createUserTable() {
		TableView<User> table = new TableView<>();
		TableColumn<User, Number> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		
		TableColumn<User, String> roleColumn = new TableColumn<>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		
		TableColumn<User, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

		TableColumn<User, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));

		TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("userPassword"));


		table.getColumns().add(idColumn);
		table.getColumns().add(roleColumn);
		table.getColumns().add(nameColumn);
		table.getColumns().add(emailColumn);
		table.getColumns().add(passwordColumn);
		
		return table;
	}
	
	private GridPane createUserForm(TableView<User> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		deleteButton = new Button("Delete");
		updateButton = new Button("Update");
		backButton = new Button("Back");

		form.add(new Label("ID:"), 0, 0);
		idInput.setDisable(true);
		form.add(idInput, 1, 0);
		
		form.add(new Label("Role:"), 0, 1);
		form.add(roleInput, 1, 1);
		
		form.add(new Label("Name:"), 0, 2);
		nameInput.setDisable(true);
		form.add(nameInput, 1, 2);
		
		form.add(new Label("Email:"), 0, 3);
		emailInput.setDisable(true);
		form.add(emailInput, 1, 3);
		
		form.add(new Label("Password:"), 0, 4);
		passwordInput.setDisable(true);
		form.add(passwordInput, 1, 4);
		

		form.add(deleteButton, 0, 5);
		form.add(updateButton, 1, 5);
		form.add(backButton, 5, 5);
		
		backButton.setOnAction(e->{
			AdminView adminView = new AdminView(primaryStage);
			AdminController adminControler = new AdminController(adminView);
		});
		
		return form;
	}

	public TableView<User> getTable() {
		return table;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public TextField getIdInput() {
		return idInput;
	}

	public void setIdInput(TextField idInput) {
		this.idInput = idInput;
	}

	public TextField getRoleInput() {
		return roleInput;
	}

	public void setRoleInput(TextField roleInput) {
		this.roleInput = roleInput;
	}

	public TextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(TextField nameInput) {
		this.nameInput = nameInput;
	}

	public TextField getEmailInput() {
		return emailInput;
	}

	public void setEmailInput(TextField emailInput) {
		this.emailInput = emailInput;
	}

	public TextField getPasswordInput() {
		return passwordInput;
	}

	public void setPasswordInput(TextField passwordInput) {
		this.passwordInput = passwordInput;
	}

	public Button getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public void setTable(TableView<User> table) {
		this.table = table;
	}


	
}
	
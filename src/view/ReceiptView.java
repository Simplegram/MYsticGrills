	package view;

	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.Date;

	import controller.CustomerController;
	import database.Connect;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextField;
	import javafx.scene.control.cell.PropertyValueFactory;
	import javafx.scene.image.Image;
	import model.Order;
import model.Receipt;

public class ReceiptView extends VBox{

		private Stage primaryStage;
		private TextField orderIdInput = new TextField();
		private TextField receiptPaymentTypeInput = new TextField();
		private TextField receiptPaymentAmountInput = new TextField();
		private TextField receiptPaymentDateInput = new TextField();
		private TableView<Receipt> table;
		private Button backButton, receiptDetailsButton;

		public ReceiptView(Stage primaryStage) {
			this.primaryStage = primaryStage;
			table = tableReceipt();
			VBox root = new VBox();
//			root.getChildren().addAll(table);
			GridPane form = createReceiptForm(table);
			VBox.setMargin(form, new Insets(20));
			root.getChildren().addAll(table, form);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			Scene scene = new Scene(root, 800, 600);
			Image image = new Image("/image/icons/logo.png");
	        primaryStage.getIcons().add(image);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Receipt List");
			primaryStage.show();
		}


		private TableView<Receipt> tableReceipt(){
			TableView<Receipt> table = new TableView<>();
			TableColumn<Receipt, Number> receiptID = new TableColumn<>("Receipt ID");
			receiptID.setCellValueFactory(new PropertyValueFactory<>("receiptId"));

			TableColumn<Receipt, Number> orderID = new TableColumn<>("Order ID");
			orderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));

			TableColumn<Receipt, Number> receiptPaymentAmount = new TableColumn<>("Receipt Payment Amount");
			receiptPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentAmount"));
			
			TableColumn<Receipt, Date> receiptPaymentDate = new TableColumn<>("Receipt Payment Date");
			receiptPaymentDate.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentDate"));

			TableColumn<Receipt, String> receiptPaymentType = new TableColumn<>("Receipt Payment Type");
			receiptPaymentType.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentType"));
			table.getColumns().add(receiptID);
			table.getColumns().add(orderID);
			table.getColumns().add(receiptPaymentAmount);
			table.getColumns().add(receiptPaymentDate);
			table.getColumns().add(receiptPaymentType);

			return table;
		}
		private GridPane createReceiptForm(TableView<Receipt> table) {
			GridPane form = new GridPane();
			form.setVgap(20);
			form.setHgap(10);
			
			backButton = new Button("Back");
			receiptDetailsButton = new Button("View Receipts Details");
			
			form.add(new Label("Order ID:"), 0, 0);
			form.add(orderIdInput, 1, 0);
			orderIdInput.setDisable(true);
			
			form.add(backButton, 0, 6);
			form.add(receiptDetailsButton, 1, 6);
			
			return form;
		}

		public Stage getPrimaryStage() {
			return primaryStage;
		}

		public void setPrimaryStage(Stage primaryStage) {
			this.primaryStage = primaryStage;
		}

		public TextField getOrderIdInput() {
			return orderIdInput;
		}

		public void setOrderIdInput(TextField orderIdInput) {
			this.orderIdInput = orderIdInput;
		}

		public TextField getReceiptPaymentTypeInput() {
			return receiptPaymentTypeInput;
		}

		public void setReceiptPaymentTypeInput(TextField receiptPaymentTypeInput) {
			this.receiptPaymentTypeInput = receiptPaymentTypeInput;
		}

		public TextField getReceiptPaymentAmountInput() {
			return receiptPaymentAmountInput;
		}

		public void setReceiptPaymentAmountInput(TextField receiptPaymentAmountInput) {
			this.receiptPaymentAmountInput = receiptPaymentAmountInput;
		}

		public TextField getReceiptPaymentDateInput() {
			return receiptPaymentDateInput;
		}

		public void setReceiptPaymentDateInput(TextField receiptPaymentDateInput) {
			this.receiptPaymentDateInput = receiptPaymentDateInput;
		}

		public TableView<Receipt> getTable() {
			return table;
		}

		public void setTable(TableView<Receipt> table) {
			this.table = table;
		}

		public Button getBackButton() {
			return backButton;
		}

		public void setBackButton(Button backButton) {
			this.backButton = backButton;
		}

		public Button getReceiptDetailsButton() {
			return receiptDetailsButton;
		}

		public void setReceiptDetailsButton(Button receiptDetailsButton) {
			this.receiptDetailsButton = receiptDetailsButton;
		}

	}

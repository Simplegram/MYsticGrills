//package view;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.ReceiptDetail;
//public class TesReceiptDetailView extends VBox {
//
//    private ObservableList<ReceiptDetail> receiptDetails = FXCollections.observableArrayList();
//
//    public void start(Stage primaryStage) {
//        TableView<ReceiptDetail> tableView = new TableView<>();
//        TableColumn<ReceiptDetail, String> menuNameColumn = new TableColumn<>("Menu Name");
//        TableColumn<ReceiptDetail, Double> priceMenuColumn = new TableColumn<>("Price Menu");
//        TableColumn<ReceiptDetail, Integer> quantityColumn = new TableColumn<>("Quantity");
//
//        menuNameColumn.setCellValueFactory(data -> data.getValue().menuNameProperty());
//        priceMenuColumn.setCellValueFactory(data -> data.getValue().menuItemPriceProperty().asObject());
//        quantityColumn.setCellValueFactory(data -> data.getValue().quantityProperty().asObject());
//
//        tableView.getColumns().addAll(menuNameColumn, priceMenuColumn, quantityColumn);
//        tableView.setItems(receiptDetails);
//
//        VBox vbox = new VBox(tableView);
//
//        Scene scene = new Scene(vbox, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Multi-Table Display");
//        primaryStage.show();
//
//        // Assume ReceiptDetail contains getReceiptById method (not ReceiptApp)
//        receiptDetails.addAll(ReceiptDetail.getReceiptById(1)); // Replace 1 with the actual receiptID
//    }
//}

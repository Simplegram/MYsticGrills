import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainView;

public class Main extends Application {

    public void start(Stage primaryStage) {
    	MainView mainView = new MainView(primaryStage);
    	MainController mainController = new MainController(mainView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
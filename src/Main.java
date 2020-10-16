import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlURL = getClass().getResource("views/javafx/MainView.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Scene scene = new Scene((GridPane) fxmlLoader.load());
            scene.getStylesheets().add("views/javafx/style.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("TITRE");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // views.cli.MainView.openMainMenu();
        launch(args);
    }
}

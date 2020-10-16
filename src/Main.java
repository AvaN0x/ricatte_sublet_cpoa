import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlURL = getClass().getResource("views/javafx/main.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Scene scene = new Scene((GridPane) root, 600, 400);
            scene.getStylesheets().add("views/javafx/style.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("Création de produit");
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

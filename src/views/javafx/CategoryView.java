package views.javafx;

import java.net.URL;

import controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoryView extends Stage {
    public CategoryView() {
        try {
            URL fxmlURL = getClass().getResource("category.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            var node = (VBox) fxmlLoader.load();
            Scene scene = new Scene(node);

            var controller = (BaseController) fxmlLoader.getController();
            controller.setVue(this);

            this.show();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

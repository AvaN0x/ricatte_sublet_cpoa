package views.javafx;

import java.net.URL;

import controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class BaseView extends Stage {
    public BaseView(String filename) {
        try {
            URL fxmlURL = getClass().getResource(filename);
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

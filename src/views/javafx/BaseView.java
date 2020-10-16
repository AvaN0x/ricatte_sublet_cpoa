package views.javafx;

import java.io.IOException;
import java.net.URL;

import controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BaseView extends Stage {
    protected BaseController _controller;

    public BaseView(String filename) throws IOException {
        URL fxmlURL = getClass().getResource(filename);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        var node = (VBox) fxmlLoader.load();

        _controller = (BaseController) fxmlLoader.getController();
        _controller.setVue(this);

        this.setScene(new Scene(node, 600, 500));
        this.initModality(Modality.APPLICATION_MODAL);
    }
}

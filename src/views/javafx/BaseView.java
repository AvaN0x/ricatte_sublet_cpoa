package views.javafx;

import java.io.IOException;
import java.net.URL;

import controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class BaseView extends Stage {
    protected BaseController _controller;

    public BaseView(String filename) throws IOException {
        URL fxmlURL = getClass().getResource(filename);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        var node = (ScrollPane) fxmlLoader.load();

        _controller = (BaseController) fxmlLoader.getController();
        _controller.setVue(this);

        this.setScene(new Scene(node));
        this.getIcons().add(new Image("file:res/icon.png"));
        this.initModality(Modality.APPLICATION_MODAL);
    }
}

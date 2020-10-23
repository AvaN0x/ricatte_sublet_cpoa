package views.javafx;

import java.io.IOException;

public class NewCommandView extends BaseView {

    public NewCommandView() throws IOException {
        super("NewCommandView.fxml");
        this.setTitle("Création d'une commande");
    }

    public NewCommandView(models.Command cmd) throws IOException {
        this();
        ((controllers.CommandController) _controller).setCommand(cmd);
    }

}

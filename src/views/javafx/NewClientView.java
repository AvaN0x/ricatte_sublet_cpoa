package views.javafx;

import java.io.IOException;

public class NewClientView extends BaseView {

    public NewClientView() throws IOException {
        super("NewClientView.fxml");
        this.setTitle("Création d'un client");
    }

    public NewClientView(models.Client cli) throws IOException {
        this();
        ((controllers.ClientController) _controller).setClient(cli);
    }
}

package views.javafx;

import java.io.IOException;

public class NewProductView extends BaseView {

    public NewProductView() throws IOException {
        super("NewProductView.fxml");
        this.setTitle("Création d'un produit");
    }

    public NewProductView(models.Product prod) throws IOException {
        super("NewProductView.fxml");
        this.setTitle("Edition d'un produit");
        ((controllers.ProductController) _controller).setProduct(prod);
    }

}

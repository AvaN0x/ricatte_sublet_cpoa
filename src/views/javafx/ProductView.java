package views.javafx;

import java.io.IOException;

public class ProductView extends BaseView {

    public ProductView() throws IOException {
        super("NewProductView.fxml");
        this.setTitle("Création d'un produit");
    }

    public ProductView(models.Product prod) throws IOException {
        super("NewProductView.fxml");
        this.setTitle("Edition d'un produit");
        ((controllers.ProductController) _controller).setProduct(prod);
    }

}

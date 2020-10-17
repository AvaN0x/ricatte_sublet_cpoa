package views.javafx;

import java.io.IOException;

public class NewCategoryView extends BaseView {

    public NewCategoryView() throws IOException {
        super("NewCategoryView.fxml");
        this.setTitle("Création d'une catégorie");
    }

    public NewCategoryView(models.Category prod) throws IOException {
        this();
        ((controllers.CategoryController) _controller).setCategory(prod);
    }

}

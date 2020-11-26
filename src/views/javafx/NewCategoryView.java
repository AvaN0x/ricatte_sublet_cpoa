package views.javafx;

import java.io.IOException;

public class NewCategoryView extends BaseView {

    public NewCategoryView() throws IOException {
        super("NewCategoryView.fxml");
        this.setTitle("Création d'une catégorie");
        this.setMinWidth(300);
        this.setMinHeight(200);
    }

    public NewCategoryView(models.Category categ) throws IOException {
        this();
        ((controllers.CategoryController) _controller).setCategory(categ);
    }

}

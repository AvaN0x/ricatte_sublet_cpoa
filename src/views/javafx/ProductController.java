package views.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProductController {
    @FXML
    private Label lbl_result;

    public void createClick() {
        try {
            var categ = new models.Category("", "");
        } catch (IllegalArgumentException e) {
            lbl_result.setText(e.getMessage());
        }
    }
}

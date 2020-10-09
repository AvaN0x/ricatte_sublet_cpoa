package views.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProductController {
    @FXML
    private Label lbl_result;

    public void createClick() {
        lbl_result.setText("test");
    }
}

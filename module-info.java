module game {
    requires javafx.controls;
    requires javafx.fxml;

    // allow FXMLLoader to reflectively inject @FXML fields/methods
    opens src to javafx.fxml;
}
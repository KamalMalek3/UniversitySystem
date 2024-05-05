import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Popup {
    public void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An Error Occurred");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info ");
        alert.setHeaderText("INFO");
        alert.setContentText(message);
        alert.showAndWait();
    }

}

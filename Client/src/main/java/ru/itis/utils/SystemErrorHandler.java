package ru.itis.utils;

import javafx.scene.control.Alert;

public class SystemErrorHandler {

    public void handleError(String errorMessage, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(null); // default title
        alert.setHeaderText(errorMessage);
        alert.showAndWait();
    }

    public void handleError(String errorMessage) {
        handleError(errorMessage, Alert.AlertType.INFORMATION);
    }
}

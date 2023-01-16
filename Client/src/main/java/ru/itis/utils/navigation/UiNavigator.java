package ru.itis.utils.navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ru.itis.MainApplication;
import ru.itis.utils.exceptions.InvalidStageStateException;

import java.io.IOException;

public class UiNavigator {
    private static final String PATH_STYLE_CSS = "/screens/style.css";
    private static final String PATH_START_SCREEN = "/screens/start-screen.fxml";
    private final Stage currentStage;
    private String css = MainApplication.class.getResource(PATH_STYLE_CSS).toExternalForm();

    public UiNavigator(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Object navigateToScreen(ActionEvent event, String resPathString) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(resPathString));
        Scene scene = new Scene(fxmlLoader.load(), currentStage.getWidth(), currentStage.getHeight());
        scene.getStylesheets().add(css);
        currentStage.setScene(scene);
        return fxmlLoader.getController();
    }

    public Object navigateFromCurrentStage(String resPathString) throws InvalidStageStateException, IOException {
        if (currentStage == null) {
            throw new InvalidStageStateException("Current stage is null");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(resPathString));
            Scene scene = new Scene(fxmlLoader.load(), currentStage.getWidth(), currentStage.getHeight());
            scene.getStylesheets().add(css);
            currentStage.setScene(scene);
            return fxmlLoader.getController();
        }
    }

    public void navigateToStartScreen(ActionEvent event) throws IOException {
        navigateToScreen(event, PATH_START_SCREEN);
    }

    public void exit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Do you want to exit?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currStage.close();
        }
    }
}

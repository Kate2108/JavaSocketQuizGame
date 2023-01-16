package ru.itis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.itis.utils.SystemErrorHandler;
import ru.itis.utils.exceptions.NavigatorNotInitializedException;
import ru.itis.utils.navigation.UiNavigatorHolder;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

    @FXML
    private ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/ic_quiz.jpg")));
        imageView.setImage(image);
        centerImage();
    }

    @FXML
    protected void navigateToJoinRoomScene(ActionEvent event) {
        try {
            UiNavigatorHolder.getUiNavigator().navigateToScreen(event, "/screens/join-room.fxml");
        } catch (IOException | NavigatorNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    protected void navigateToCreateRoomScene(ActionEvent event) {
        try {
            UiNavigatorHolder.getUiNavigator().navigateToScreen(event, "/screens/create-room.fxml");
        } catch (IOException | NavigatorNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        try {
            UiNavigatorHolder.getUiNavigator().exit(event);
        } catch (Exception e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();
            double reducCoeff = Math.min(ratioX, ratioY);
            double w = img.getWidth() * reducCoeff;
            double h = img.getHeight() * reducCoeff;
            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);
        }
    }

}

package ru.itis.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import ru.itis.connection.ConnectionHolder;
import ru.itis.protocol.message.client.InitUsernameMessage;
import ru.itis.protocol.message.client.JoinRoomMessage;
import ru.itis.utils.SystemErrorHandler;
import ru.itis.utils.exceptions.ConnectionNotInitializedException;
import ru.itis.utils.exceptions.NavigatorNotInitializedException;
import ru.itis.utils.navigation.UiNavigatorHolder;

import java.io.IOException;

public class JoinRoomController {
    @FXML
    private ProgressIndicator bar;

    @FXML
    private TextField tfRoomCode;

    @FXML
    private Label labelRoomCodeError;

    @FXML
    private TextField tfUsername;

    @FXML
    protected void joinRoom() {
        try {
            int roomCode = Integer.parseInt(tfRoomCode.getText());
            int playerId = ConnectionHolder.getConnection().getId();

            bar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            bar.setVisible(true);

            Timeline loadingTimer = new Timeline(new KeyFrame(Duration.seconds(3), (ActionEvent e) -> bar.setVisible(false)));
            loadingTimer.play();

            ConnectionHolder.getConnection().send(new InitUsernameMessage(playerId, tfUsername.getText()));
            ConnectionHolder.getConnection().send(new JoinRoomMessage(playerId, roomCode));
        } catch (NumberFormatException ex) {
            labelRoomCodeError.setVisible(true);
        } catch (IOException | ConnectionNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void onBackButtonClick(ActionEvent event) {
        try {
            UiNavigatorHolder.getUiNavigator().navigateToStartScreen(event);
        } catch (IOException | NavigatorNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}

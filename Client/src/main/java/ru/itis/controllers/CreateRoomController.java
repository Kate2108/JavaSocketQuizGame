package ru.itis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.itis.connection.ConnectionHolder;
import ru.itis.constants.RoomPreferences;
import ru.itis.protocol.message.client.CreateRoomMessage;
import ru.itis.protocol.message.client.InitUsernameMessage;
import ru.itis.utils.SystemErrorHandler;
import ru.itis.utils.exceptions.ConnectionNotInitializedException;
import ru.itis.utils.exceptions.NavigatorNotInitializedException;
import ru.itis.utils.navigation.UiNavigatorHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomController implements Initializable {

    @FXML
    private ProgressIndicator bar;

    @FXML
    private TextField tfUsername;

    @FXML
    private Spinner<Integer> spinnerMaxMembers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(RoomPreferences.MIN_ROOM_MEMBER, RoomPreferences.MAX_ROOM_MEMBER);
        valueFactory.setValue(RoomPreferences.MIN_ROOM_MEMBER);
        spinnerMaxMembers.setValueFactory(valueFactory);
    }

    @FXML
    protected void createRoom() {
        try {
            bar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            bar.setVisible(true);

            int playerId = ConnectionHolder.getConnection().getId();
            ConnectionHolder.getConnection().getPlayer().setUsername(tfUsername.getText());
            ConnectionHolder.getConnection().send(new InitUsernameMessage(playerId, tfUsername.getText()));
            ConnectionHolder.getConnection().send(new CreateRoomMessage(spinnerMaxMembers.getValue(), playerId));
        } catch (IOException | ConnectionNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage());
        }
    }

    public void navigateBack(ActionEvent event) {
        try {
            UiNavigatorHolder.getUiNavigator().navigateToStartScreen(event);
        } catch (IOException | NavigatorNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}

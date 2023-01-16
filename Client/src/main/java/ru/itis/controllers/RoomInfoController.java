package ru.itis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import ru.itis.connection.ConnectionHolder;
import ru.itis.constants.RoomPreferences;
import ru.itis.models.Player;
import ru.itis.models.Room;
import ru.itis.protocol.message.client.PlayerLeaveRoomMessage;
import ru.itis.protocol.message.client.StartGameMessage;
import ru.itis.utils.SystemErrorHandler;
import ru.itis.utils.exceptions.ConnectionNotInitializedException;
import ru.itis.utils.exceptions.NavigatorNotInitializedException;
import ru.itis.utils.navigation.UiNavigatorHolder;

import java.io.IOException;
import java.util.stream.Collectors;

public class RoomInfoController {
    @FXML
    private ProgressIndicator bar;

    @FXML
    private Label labelRoomNumber;

    @FXML
    private Label labelRoomMaxMembers;

    @FXML
    private Label labelRoomCreator;

    @FXML
    private Label labelError;

    @FXML
    private ListView<String> listActiveMembers;

    private Room room;

    public void initRoomInfo(Room room) {
        this.room = room;
        labelRoomNumber.setText("Room №" + room.getId());
        labelRoomMaxMembers.setText("Max members: " + room.getCapacity());
        labelRoomCreator.setText("Creator: " + room.getCreatorUsername());
        initListView(room);
        bar.setVisible(false);
    }

    public void onStartQuizClick() throws IOException {
        int activeMembers = room.getCurrentSize();
        if (activeMembers < RoomPreferences.MIN_ROOM_MEMBER) {
            labelError.setText("Not enough participants. " + "The minimum number is " + RoomPreferences.MIN_ROOM_MEMBER);
        } else {
            try {
                int roomId = ConnectionHolder.getConnection().getPlayer().getRoomId();
                bar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
                bar.setVisible(true);
                ConnectionHolder.getConnection().send(new StartGameMessage(roomId));
            } catch (ConnectionNotInitializedException e) {
                new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    public void onLeaveRoomClick(ActionEvent event) {
        try {
            int playerId = ConnectionHolder.getConnection().getPlayer().getId();
            ConnectionHolder.getConnection().send(new PlayerLeaveRoomMessage(playerId));
            UiNavigatorHolder.getUiNavigator().navigateToStartScreen(event);
        } catch (IOException | ConnectionNotInitializedException | NavigatorNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void initListView(Room room) {
        ObservableList<String> activePlayers = FXCollections.observableArrayList(
                room.getPlayers().values().
                        stream()
                        .map(Player::getUsername)
                        .collect(Collectors.toList()));
        listActiveMembers.setItems(activePlayers);
    }
}

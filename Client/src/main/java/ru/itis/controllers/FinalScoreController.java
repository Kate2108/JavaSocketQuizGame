package ru.itis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import ru.itis.connection.ConnectionHolder;
import ru.itis.models.Player;
import ru.itis.models.Room;
import ru.itis.utils.additional.Drawer;
import ru.itis.utils.SystemErrorHandler;
import ru.itis.utils.exceptions.ConnectionNotInitializedException;
import ru.itis.utils.exceptions.NavigatorNotInitializedException;
import ru.itis.utils.navigation.UiNavigatorHolder;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FinalScoreController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Label labelYouWon;

    @FXML
    private Canvas canvas;

    private Room playerRoom;

    public void showStats(Room room) {
        this.playerRoom = room;
        List<Player> playerList = room.getAllPlayers();

        // 1  | score | Username
        initGridConstraints();

        // sorting players by points
        playerList.sort(Comparator.comparingInt(Player::getPoints));
        Collections.reverse(playerList);
        // drawing crown if user won
        drawCrown(playerList);

        // set data to grid
        Label labelPlace = addCreateStyledLabel("Place");
        Label labelScore = addCreateStyledLabel("Score");
        Label labelUsername = addCreateStyledLabel("Username");
        gridPane.add(labelPlace, 0, 0);
        gridPane.add(labelScore, 1, 0);
        gridPane.add(labelUsername, 2, 0);

        for (int i = 1; i <= playerList.size(); i++) {
            Player currPlayer = playerList.get(i - 1);
            Label labelPlayerPlace = createCenteredLabel(Integer.toString(i));
            Label labelPlayerScore = createCenteredLabel(Integer.toString(currPlayer.getPoints()));
            Label labelPlayerUsername = createCenteredLabel(currPlayer.getUsername());
            gridPane.add(labelPlayerPlace, 0, i);
            gridPane.add(labelPlayerScore, 1, i);
            gridPane.add(labelPlayerUsername, 2, i);
        }
    }

    public void backToRoomInfo(ActionEvent event) {
        try {
            RoomInfoController controller = (RoomInfoController) UiNavigatorHolder.getUiNavigator().navigateToScreen(event, "/screens/room-info.fxml");
            if (playerRoom != null) {
                controller.initRoomInfo(playerRoom);
            }
        } catch (IOException | NavigatorNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void drawCrown(List<Player> playerList) {
        try {
            int playerId = ConnectionHolder.getConnection().getPlayer().getId();
            if (playerId == playerList.get(0).getId()) {
                Drawer drawer = new Drawer(canvas);
                drawer.drawCrown();
                canvas.setVisible(true);
                labelYouWon.setVisible(true);
            }
        } catch (ConnectionNotInitializedException e) {
            new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void initGridConstraints() {
        // defining columns
        gridPane.setPadding(new Insets(5, 10, 5, 10));

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        gridPane.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        gridPane.getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(70);
        gridPane.getColumnConstraints().add(column3);

        // defining rows
        double minH = 20.0;
        double maxH = 30.0;
        double prefH = 25.0;
        RowConstraints row1 = new RowConstraints(minH, prefH, maxH);
        gridPane.getRowConstraints().add(row1);

        gridPane.setGridLinesVisible(true);
    }

    private Label addCreateStyledLabel(String labelTitle) {
        Label label = new Label(labelTitle);
        GridPane.setHalignment(label, HPos.CENTER);
        label.getStyleClass().add("label-important-small");
        return label;
    }

    private Label createCenteredLabel(String labelTitle) {
        Label label = new Label(labelTitle);
        GridPane.setHalignment(label, HPos.CENTER);
        return label;
    }
}

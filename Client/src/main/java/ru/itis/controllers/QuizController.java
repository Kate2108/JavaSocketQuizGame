package ru.itis.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ru.itis.connection.ConnectionHolder;
import ru.itis.constants.GameSettings;
import ru.itis.models.Player;
import ru.itis.models.Question;
import ru.itis.protocol.message.client.RightAnswerMessage;
import ru.itis.utils.SystemErrorHandler;
import ru.itis.utils.additional.CountdownTimer;
import ru.itis.utils.exceptions.ConnectionNotInitializedException;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class QuizController {
    @FXML
    private Label labelTimer;

    @FXML
    private VBox vboxAnswerVariants;
    @FXML
    private Label labelQuiz;

    @FXML
    private Label labelWarning;

    @FXML
    private Button btnAnswer;

    private Question currentQuestion;
    private CountdownTimer timer;

    public void initQuestion(Question question) {
        btnAnswer.setDisable(false);
        labelWarning.setVisible(false);

        currentQuestion = question;

        labelQuiz.setText(question.getQuestion());
        ToggleGroup group = new ToggleGroup();

        for (int i = 0; i < question.getAnswers().length; i++) {
            RadioButton variant = new RadioButton(question.getAnswers()[i]);
            variant.setId(String.valueOf(i));
            variant.setToggleGroup(group);
            vboxAnswerVariants.getChildren().add(variant);
        }

        startQuizTimer();
        startQuizWarningTimer();
    }

    private void startQuizTimer() {
        timer = new CountdownTimer(labelTimer);
        timer.start();
    }

    private void startQuizWarningTimer() {
        Timer timer = new Timer(GameSettings.WARNING_TIME_UP_DELAY, e -> Platform.runLater(this::showWarning));
        timer.start();
    }

    private void showWarning() {
        labelWarning.setVisible(true);
        labelWarning.setText("5 seconds left");
    }

    // if player clicks on button Answer
    public void answerQuestion() {
        ObservableList<Node> nodesOnScreen = vboxAnswerVariants.getChildren();

        Optional<RadioButton> selectedRb = nodesOnScreen.stream()
                .filter(it -> it instanceof RadioButton)
                .map(it -> (RadioButton) it)
                .filter(ToggleButton::isSelected)
                .findAny();

        if (selectedRb.isPresent()) {
            btnAnswer.getStyleClass().add("btn-primary-answered");
            btnAnswer.setText("Answer saved");
            btnAnswer.setDisable(true);
            vboxAnswerVariants.setDisable(true);
            try {
                Player player = ConnectionHolder.getConnection().getPlayer();
                if (Objects.equals(selectedRb.get().getId(), String.valueOf(currentQuestion.getCorrectAnsId()))) {
                    // count points
                    int points = currentQuestion.getPoints() * timer.getLeftSeconds();
                    // update points
                    ConnectionHolder.getConnection().getPlayer().setPoints(player.getPoints() + points);
                    // notify if answer is right
                    ConnectionHolder.getConnection().send(new RightAnswerMessage(player.getId(), points));

                    selectedRb.get().getStyleClass().add("right-answer");
                } else {
                    selectedRb.get().getStyleClass().add("wrong-answer");
                }
            } catch (ConnectionNotInitializedException | IOException e) {
                new SystemErrorHandler().handleError(e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            labelWarning.setVisible(true);
            labelWarning.setText("Select answer");
        }

    }

    public void timeUp() {
        labelWarning.setText("Time is up");
        labelWarning.setVisible(true);
        btnAnswer.setDisable(true);
    }
}

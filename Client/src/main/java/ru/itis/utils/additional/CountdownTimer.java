package ru.itis.utils.additional;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;
import ru.itis.constants.GameSettings;

public class CountdownTimer extends Label {
    private Integer counter;
    private boolean started;
    private final SimpleIntegerProperty observableCounter;

    public CountdownTimer(Label label) {
        this.started = false;
        this.counter = GameSettings.TIME_FOR_QUESTION / 1000;
        label.setText(String.valueOf(counter));
        observableCounter = new SimpleIntegerProperty(counter);
        observableCounter.addListener((observableValue, number, t1) -> label.setText(observableCounter.getValue().toString()));
    }

    public Integer getLeftSeconds(){
        return counter;
    }

    public void start() {
        if (started) {
            return;
        }
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),
                event -> {
                    counter--;
                    observableCounter.set(counter);
                    if (counter <= 0) {
                        timeline.stop();
                    }
                });
        timeline.getKeyFrames().add(kf);
        timeline.playFromStart();
        started = true;
    }

}

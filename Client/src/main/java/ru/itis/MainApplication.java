package ru.itis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.connection.ConnectionHolder;
import ru.itis.utils.navigation.UiNavigatorHolder;

import java.io.IOException;

public class MainApplication extends Application {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(MainApplication
                        .class.getResource("/screens/start-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);

        String css = MainApplication.class.getResource("/screens/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("QuizGame");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

        stage.minWidthProperty().bind(scene.widthProperty());
        stage.show();

        ConnectionHolder.initializeConnection();
        UiNavigatorHolder.initNavigator(stage);
    }

    @Override
    public void stop() {
        try {
            ConnectionHolder.closeConnection();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}

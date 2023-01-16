package ru.itis.utils.navigation;

import javafx.stage.Stage;
import ru.itis.utils.exceptions.NavigatorNotInitializedException;

public class UiNavigatorHolder {
    private static UiNavigator navigator;

    public static void initNavigator(Stage currentStage) {
        if (navigator == null) {
            navigator = new UiNavigator(currentStage);
        }
    }

    public static UiNavigator getUiNavigator() throws NavigatorNotInitializedException {
        if (navigator == null) {
            throw new NavigatorNotInitializedException("Navigator is not initialized");
        }
        return navigator;
    }
}

package ru.itis.connection;

import ru.itis.protocol.message.client.PlayerDisconnectedMessage;
import ru.itis.utils.exceptions.ConnectionNotInitializedException;

public class ConnectionHolder {
    private static ClientConnection connection;

    public static ClientConnection getConnection() throws ConnectionNotInitializedException {
        if (connection == null) {
            throw new ConnectionNotInitializedException("Connection lost");
        }
        return connection;
    }

    public static void initializeConnection() {
        // static because we want singleton
        if (connection == null) {
            connection = new ClientConnection();
        }
    }

    public static void closeConnection() throws Exception {
        if (connection != null) {
            if (connection.getPlayer() != null) {
                int playerId = connection.getId();
                System.out.println("Disconnecting from server");
                connection.send(new PlayerDisconnectedMessage(playerId));
            }
            connection.close();
        }
        // if user open and then immediately close window
    }
}

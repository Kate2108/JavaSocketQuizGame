package ru.itis.server.listeners;

import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class PlayerDisconnectedListener implements ClientEventListener {
    Server server;

    @Override
    public void handMessage(PlayerConnection connection) {
        server.removeConnection(connection.getId());
    }

    @Override
    public void initServer(Server server) {
        this.server = server;
    }
}

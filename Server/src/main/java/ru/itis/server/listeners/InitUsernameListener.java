package ru.itis.server.listeners;

import ru.itis.protocol.message.client.InitUsernameMessage;
import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class InitUsernameListener implements ClientEventListener {
    private Server server;
    private InitUsernameMessage message;

    public InitUsernameListener(InitUsernameMessage message) {
        this.message = message;
    }

    public void initServer(Server server){
        this.server = server;
    }

    @Override
    public void handMessage(PlayerConnection connection) {
        server.initUsername(connection.getId(), message.getContent());
    }
}

package ru.itis.server.listeners;

import ru.itis.protocol.message.client.JoinRoomMessage;
import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class JoinRoomListener implements ClientEventListener{
    private Server server;
    private JoinRoomMessage message;

    public JoinRoomListener(JoinRoomMessage message) {
        this.message = message;
    }

    public void initServer(Server server){
        this.server = server;
    }

    @Override
    public void handMessage(PlayerConnection connection) {
        server.joinRoom(message.getContent(), connection.getId());
    }
}

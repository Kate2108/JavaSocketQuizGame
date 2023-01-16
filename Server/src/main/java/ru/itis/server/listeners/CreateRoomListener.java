package ru.itis.server.listeners;

import ru.itis.protocol.message.client.CreateRoomMessage;
import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class CreateRoomListener implements ClientEventListener{
    private Server server;
    private CreateRoomMessage message;

    public CreateRoomListener(CreateRoomMessage message) {
        this.message = message;
    }

    @Override
    public void initServer(Server server){
        this.server = server;
    }
    @Override
    public void handMessage(PlayerConnection connection) {
        server.createRoom(connection.getId(), message.getContent());
    }
}

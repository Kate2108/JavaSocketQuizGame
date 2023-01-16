package ru.itis.server.listeners;


import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class LeaveRoomListener  implements ClientEventListener {
    private Server server;

    @Override
    public void initServer(Server server) {
        this.server = server;
    }

    @Override
    public void handMessage(PlayerConnection connection) {
        server.leaveRoom(connection.getPlayer().getRoomId(), connection.getId());
    }
}


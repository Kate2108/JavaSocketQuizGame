package ru.itis.server.listeners;
import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public interface ClientEventListener {
    void handMessage(PlayerConnection connection);
    void initServer(Server server);
}

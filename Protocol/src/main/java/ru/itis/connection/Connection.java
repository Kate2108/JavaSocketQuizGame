package ru.itis.connection;

import ru.itis.models.Player;
import ru.itis.protocol.message.BasicMessage;

import java.io.IOException;

public interface Connection {

    void send(BasicMessage message) throws IOException;

    Player getPlayer();

    void close();

    int getId();

    boolean isConnected();
}

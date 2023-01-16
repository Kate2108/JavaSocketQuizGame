package ru.itis.connection;

import ru.itis.connection.MessageListener;
import ru.itis.constants.ConnectionPreferences;
import ru.itis.models.Player;
import ru.itis.protocol.message.BasicMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection {
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;
    private Player player;

    public ClientConnection() {
        try {
            socket = new Socket(InetAddress.getByName(ConnectionPreferences.host), ConnectionPreferences.port);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            MessageListener messageListenerThread = new MessageListener(socket);
            messageListenerThread.start();
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void send(BasicMessage message) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(message);
        objOut.flush();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return player.getId();
    }

    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}

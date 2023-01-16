package ru.itis.server;

import ru.itis.connection.Connection;
import ru.itis.constants.MessageTypes;
import ru.itis.models.Player;
import ru.itis.protocol.message.BasicMessage;
import ru.itis.server.listeners.ClientEventListener;
import ru.itis.server.listeners.ServerEventListener;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class PlayerConnection implements Connection, Runnable {
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;
    private final Server server;
    private final Player player;

    public PlayerConnection(Socket socket, Server server, Player player) {
        this.socket = socket;
        this.server = server;
        this.player = player;
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void send(BasicMessage message) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(message);
        objOut.flush();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch (IOException ignored) {
        }
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                int b = in.available();
                if (b != 0) {
                    ObjectInputStream objIn = new ObjectInputStream(in);
                    BasicMessage message = (BasicMessage) objIn.readObject();
                    //
                    System.out.println(message.toString());
                    //
                    ClientEventListener listener = ServerEventListener.getListener(message.getType(), message);
                    listener.initServer(server);
                    listener.handMessage(this);
                }
                else {
                    Thread.sleep(200);
                }
            }
        }
        catch (SocketException e){

        }
        catch (IOException | ClassNotFoundException | InterruptedException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getId() {
        return player.getId();
    }

    @Override
    public boolean isConnected() {
        return !socket.isClosed();
    }
}

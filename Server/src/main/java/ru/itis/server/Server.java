package ru.itis.server;

import ru.itis.connection.Connection;
import ru.itis.constants.ConnectionPreferences;
import ru.itis.models.Player;
import ru.itis.models.Room;
import ru.itis.protocol.message.ContentMessage;

import ru.itis.protocol.message.server.CreateRoomStatusMessage;
import ru.itis.protocol.message.server.PlayerAcceptedStatusMessage;
import ru.itis.protocol.message.server.RoomWasUpdatedMessage;
import ru.itis.protocol.message.server.SystemMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private final HashMap<Integer, Connection> connections;

    private final ServerSocket serverSocket;

    private final HashMap<Integer, RoomService> rooms;

    private int userId = 1;

    private int roomId = 1;

    public Server() {
        connections = new HashMap<>();
        rooms = new HashMap<>();
        try {
            serverSocket = new ServerSocket(ConnectionPreferences.port);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void start() {
        System.out.println("Server started");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("add connection");
                addConnection(socket);
            }
            catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public void addConnection(Socket socket) {
        Player player = Player.builder()
                .id(userId++)
                .build();
        PlayerConnection connection = new PlayerConnection(socket, this, player );
        connections.put(connection.getId(), connection);
        new Thread(connection).start();
        sendToConnection(connection.getId(), new PlayerAcceptedStatusMessage(connection.getId(), connection.getPlayer()));
        System.out.println("User: " + connection.getId());
    }

    private void sendToConnection(int connectionId, ContentMessage<?> message) {
        try {
            Connection con = connections.get(connectionId);
            if (con.isConnected()) {
                con.send(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void initUsername(int id, String username){
        Connection connection = connections.get(id);
        connection.getPlayer().setUsername(username);
    }

    public void removeConnection(int id) {
        Connection connection = connections.get(id);
        if (connection == null) {
            return;
        }
        RoomService roomService = rooms.get(connection.getPlayer().getRoomId());
        if (roomService != null) {
            leaveRoom(connection.getPlayer().getRoomId(), id);
        }
        connections.remove(id);
    }
    public void createRoom(int creatorId, int maxSize){
        Room room = Room.builder()
                .id(roomId)
                .creatorUsername(connections.get(creatorId).getPlayer().getUsername())
                .capacity(maxSize)
                .currentSize(0)
                .build();
        rooms.put(roomId, new RoomService(room, this));
        rooms.get(roomId).addConnection(connections.get(creatorId));
        sendToConnection(creatorId, new CreateRoomStatusMessage(room, creatorId));
        roomId++;
    }
    public void joinRoom(int roomId, int senderId) {
        RoomService roomService = rooms.get(roomId);
        if (roomService == null){
            sendToConnection(senderId, new SystemMessage("This room does not exist", -1));
        }
        else if (roomService.getGame() != null){
            sendToConnection(senderId, new SystemMessage("Game has already started", -1));
        }
        else if(roomService.checkUsernames(connections.get(senderId).getPlayer().getUsername())){
            sendToConnection(senderId, new SystemMessage("Player with that username is already in the room", -1));
        }
        else if(roomService.isClosed()){
            sendToConnection(senderId, new SystemMessage("This room does not exist", -1));
        }
        else if(roomService.getRoom().getCurrentSize() < roomService.getRoom().getCapacity()) {
            Connection connection = connections.get(senderId);
            roomService.addConnection(connection);
            roomService.sendToConnections(new RoomWasUpdatedMessage(roomService.getRoom(), -1));
        }
        else {
            sendToConnection(senderId, new SystemMessage("Room is full", -1));
        }
    }
    public void leaveRoom(int roomId, int senderId) {
        RoomService roomService = rooms.get(roomId);
        roomService.removeConnection(senderId);
        roomService.sendToConnections(new SystemMessage(connections.get(senderId).getPlayer().getUsername() + " disconnect", -1));
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        roomService.sendToConnections(new RoomWasUpdatedMessage(roomService.getRoom(), -1));
    }

    public void startGame(int roomId){
        RoomService roomService = rooms.get(roomId);
        if (roomService.getGame() == null){
            roomService.startGame();
        }
    }
    public void handRightAnswer(int senderId, int points){
        RoomService roomService = rooms.get(connections.get(senderId).getPlayer().getRoomId());
        if (roomService.getGame() != null){
            roomService.getGame().rightAnswer(senderId, points);
        }
    }
}

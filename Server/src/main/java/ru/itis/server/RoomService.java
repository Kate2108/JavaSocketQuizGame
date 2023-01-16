package ru.itis.server;

import lombok.Getter;
import ru.itis.connection.Connection;
import ru.itis.models.Player;
import ru.itis.models.Room;
import ru.itis.protocol.message.BasicMessage;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

@Getter
public class RoomService {
    private final HashMap<Integer, Connection> connections;
    private final Server server;
    private final Room room;
    private Game game;
    private boolean closed;

    public RoomService(Room room, Server server) {
        this.room = room;
        connections = new HashMap<>();
        this.server = server;
        closed = false;
    }

    public void finishGame(){
        game = null;
        for (Player player: room.getAllPlayers()){
            player.setPoints(0);
        }
    }

    public void startGame() {
        game = new Game();
        game.start(this, getPlayers());
    }

    private HashMap<Integer, Player> getPlayers() {
        HashMap<Integer, Player> hashMap = new HashMap<>();
        Collection<Connection> collection = connections.values();
        for (Connection connection : collection) {
            hashMap.put(connection.getId(), Player.builder().id(connection.getId()).username(connection.getPlayer().getUsername()).points(0).build());
        }
        return hashMap;
    }

    public void sendToConnections(BasicMessage message){
        for (Connection con : connections.values()) {
            try {
                con.send(message);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean checkUsernames(String username){
        for (Player player: room.getPlayers().values()){
            if(player.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public void removeConnection(int connectionId) {
        Connection connection = connections.get(connectionId);
        if (connection == null) {
            return;
        }
        if (game != null) {
            game.playerDisconnected(connectionId);
        }
        room.removePlayer(connectionId);
        room.setCurrentSize(room.getCurrentSize() - 1);
        connections.get(connectionId).getPlayer().setRoomId(-1);
        connections.remove(connectionId);
        if (game != null && room.getCurrentSize() == 1){
            game.gameOver();
        }
        if (room.getCurrentSize() == 0) {
            closed = true;
        }
    }

    public void addConnection(Connection connection) {
        connection.getPlayer().setRoomId(room.getId());
        connections.put(connection.getId(), connection);
        room.addPlayer(connection.getPlayer());
        room.setCurrentSize(room.getCurrentSize() + 1);
    }
}

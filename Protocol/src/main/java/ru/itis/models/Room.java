package ru.itis.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

@Builder
@Setter
@Getter
public class Room implements Serializable {
    private final String name;
    private final int capacity;
    private final HashMap<Integer, Player> players = new HashMap<>();
    private int id;
    private int currentSize;
    private String creatorUsername;

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public void updatePlayerPoints(int id, int points){
        players.get(id).setPoints(points);
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }

    public void removePlayer(int id) {
        players.remove(id);
    }
}

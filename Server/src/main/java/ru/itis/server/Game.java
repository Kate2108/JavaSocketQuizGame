package ru.itis.server;

import ru.itis.models.Player;
import ru.itis.protocol.message.server.GameOverMessage;
import ru.itis.protocol.message.server.NextQuestionMessage;
import ru.itis.protocol.message.server.TimeUpMessage;
import ru.itis.repository.QuestionRepositoryImpl;

import java.util.HashMap;

import static ru.itis.constants.GameSettings.TIME_FOR_QUESTION;

public class Game implements Runnable{
    private RoomService room;
    private HashMap<Integer, Player> players;

    private QuestionRepositoryImpl repository;

    private int currentQ = 1;

    private boolean gameStatus = true;

    public void start(RoomService room, HashMap<Integer,Player> connections) {
        this.room = room;
        this.players = connections;
        repository = new QuestionRepositoryImpl();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while(currentQ < 10 && gameStatus){
                room.sendToConnections(new NextQuestionMessage(repository.getQuestion(), -1));
                Thread.sleep(TIME_FOR_QUESTION);
                if (!gameStatus){
                    break;
                }
                currentQ++;
                room.sendToConnections(new TimeUpMessage(-1));
            }
            if (gameStatus){
                gameOver();
            }
            room.finishGame();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void gameOver(){
        gameStatus = false;
        room.sendToConnections(new GameOverMessage(room.getRoom(), room.getRoom().getId()));
    }
    public void playerDisconnected(int id){
        if (players.get(id) == null){
            return;
        }
        players.remove(id);
    }
    public void rightAnswer(int id, int points){
        players.get(id).setPoints(players.get(id).getPoints() + points);
        room.getRoom().updatePlayerPoints(id, players.get(id).getPoints());
    }
}

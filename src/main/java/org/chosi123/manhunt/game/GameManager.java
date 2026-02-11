package org.chosi123.manhunt.game;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class GameManager implements Listener{
    private static final int MAP_SIZE;
    public static GameManager INSTANCE;
    private static UUID hunterId;
    private boolean isGameStart = false;

    private GameManager(){}

    public void startGame(Player player){
        //술래가 될 플레이어 입력받음.
        hunterId = player.getUniqueId();
        isGameStart = true;



    }

    public void endGame(){
    }


    static{
        MAP_SIZE = 3000;
        INSTANCE = new GameManager();
    }
}

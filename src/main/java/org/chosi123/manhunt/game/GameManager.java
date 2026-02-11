package org.chosi123.manhunt.game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.time.Duration;
import java.util.UUID;

public class GameManager implements Listener{
    private static final int MAP_SIZE;
    public static GameManager INSTANCE;
    private static UUID hunterId;
    private boolean isGameStart = false;

    private GameManager(){}

    public void startGame(Player player){
        //술래가 될 플레이어 입력받음.
        if(isGameStart) {

            return;
        }

        hunterId = player.getUniqueId();
        isGameStart = true;

        World world = Bukkit.getWorld("world");
        if (world == null) return;

        WorldBorder border = world.getWorldBorder();

        border.setCenter(0, 0);
        border.setSize(MAP_SIZE * 2);


    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        if(hunterId.equals(event.getPlayer().getUniqueId())){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.showTitle(
                        Title.title(
                                Component.text("게임이 종료되었습니다!"),
                                Component.empty(),
                                Title.Times.times(
                                        Duration.ofSeconds(1),
                                        Duration.ofSeconds(4),
                                        Duration.ofSeconds(1)
                                )
                        )
                );
            }
            isGameStart = false;
        }
    }

    public void endGame(){
    }

    public boolean getGameStatus(){
        return isGameStart;
    }


    static{
        MAP_SIZE = 3000;
        INSTANCE = new GameManager();
    }
}

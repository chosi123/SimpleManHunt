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
import java.util.stream.Collectors;

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

        for(Player p : Bukkit.getOnlinePlayers()){
            p.showTitle(
                    Title.title(
                            Component.text("게임이 시작되었습니다!"),
                            Component.empty(),
                            Title.Times.times(
                                    Duration.ofSeconds(1),
                                    Duration.ofSeconds(2),
                                    Duration.ofSeconds(1)
                            )
                    )
            );
        }


    }

    @EventHandler
    public void onPickUpDragonEgg(EntityPickupItemEvent event){
        if(!isGameStart) return;
        if(!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();

        if(event.getItem().getItemStack().getType() == org.bukkit.Material.DRAGON_EGG){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.showTitle(
                        Title.title(
                                Component.text("게임이 종료되었습니다!"),
                                Component.text("도망자 승리!"),
                                Title.Times.times(
                                        Duration.ofSeconds(1),
                                        Duration.ofSeconds(4),
                                        Duration.ofSeconds(1)
                                )
                        )
                );
            }
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if(!isGameStart) return;
        Player diedPlayer = event.getPlayer();
        //헌터가 죽음: 게임 오버
        if(hunterId.equals(diedPlayer.getUniqueId())){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.showTitle(
                        Title.title(
                                Component.text("게임이 종료되었습니다!"),
                                Component.text("도망자 승리!"),
                                Title.Times.times(
                                        Duration.ofSeconds(1),
                                        Duration.ofSeconds(4),
                                        Duration.ofSeconds(1)
                                )
                        )
                );
            }
            diedPlayer.setGameMode(org.bukkit.GameMode.SPECTATOR);
            isGameStart = false;
            event.deathMessage(Component.text(diedPlayer.getName() + "이 탈락하셨습니다."));
        }
        //도망자가 죽음
        else{
            diedPlayer.setGameMode(org.bukkit.GameMode.SPECTATOR);
            event.deathMessage(Component.text(
                    diedPlayer.getName() +
                    "이 게임에서 탈락하셨습니다. " +
                            (Bukkit.getOnlinePlayers().stream()
                            .filter(p->p.getGameMode() == org.bukkit.GameMode.SURVIVAL).toList().size() - 1) +
                            "명 남았습니다."));
            diedPlayer.kick(Component.text("게임에서 탈락하셨습니다! 재접속 시 관전하실 수 있습니다."));


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

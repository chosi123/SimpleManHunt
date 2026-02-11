package org.chosi123.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.chosi123.manhunt.game.GameManager;
import org.jetbrains.annotations.NotNull;

public class MCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(command.getName().equalsIgnoreCase("gamestart")){
            if(args.length == 0){
                sender.sendMessage("술래의 닉네임을 입력해 주십시오.");
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null){
                sender.sendMessage("해당 플레이어를 찾을 수 없습니다.");
                return false;
            }

            GameManager.INSTANCE.startGame(target);
            if(!GameManager.INSTANCE.getGameStatus()){
                sender.sendMessage("게임이 이미 진행 중입니다.");
            }

            return true;
        }

        if(command.getName().equalsIgnoreCase("")){
            return false;
        }

        return true;
    }
}

package org.chosi123.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.chosi123.manhunt.commands.MCommands;
import org.chosi123.manhunt.game.GameManager;

public final class ManHunt extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("gamestart").setExecutor(new MCommands());
        Bukkit.getPluginManager().registerEvents(GameManager.INSTANCE, this);
        getLogger().info("술래잡기 플러그인이 활성화되었습니다.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ManHunt getPlugin(){
        return this;
    }
}

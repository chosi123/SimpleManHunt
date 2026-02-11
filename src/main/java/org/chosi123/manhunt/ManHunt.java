package org.chosi123.manhunt;

import org.bukkit.plugin.java.JavaPlugin;
import org.chosi123.manhunt.commands.MCommands;

public final class ManHunt extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("gamestart").setExecutor(new MCommands());
        getLogger().info("헌팅게임 플러그인이 활성화되었습니다.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ManHunt getPlugin(){
        return this;
    }
}

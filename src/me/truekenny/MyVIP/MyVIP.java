package me.truekenny.MyVIP;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MyVIP extends JavaPlugin implements iMyVIP {
    public Logger log = Logger.getLogger("Minecraft");
    public PlayerListener playerListener;
    public FileConfiguration config;

    public Players players;

    public void onEnable() {
        defaultConfig();

        players = new Players(this);

        PluginManager pm = getServer().getPluginManager();

        playerListener = new PlayerListener(this);
        pm.registerEvents(playerListener, this);

        getCommand("vip").setExecutor(new VipCommand(this));
    }

    public void onDisable() {
    }

    public void defaultConfig() {
        config = getConfig();

        config.addDefault("default.days", 30);

        config.options().copyDefaults(true);
        saveConfig();
    }

    public boolean isVip(String nick) {
        return true;
    }

    public boolean isVip(Player player) {
        return isVip(player.getDisplayName());
    }
}

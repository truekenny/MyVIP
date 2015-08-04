package me.truekenny.MyVIP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.logging.Logger;

/**
 * Класс-слушатель событий происходящих с пользователем
 *
 * @author truekenny
 */
@SuppressWarnings("deprecation")
public class PlayerListener implements Listener {
    /**
     * Экземпляр главного класса плагина
     */
    private final MyVIP plugin;

    /**
     * Объект для логирования сообщений плагина
     */
    Logger log = Logger.getLogger("Minecraft");

    /**
     * Сохранение объекта главного класса
     *
     * @param instance Экземпляр плагина
     */
    public PlayerListener(MyVIP instance) {
        plugin = instance;
    }

    /**
     * Обрабытывает вход пользователя
     *
     * @param event Событие
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();

        if (plugin.players.vip(player.getDisplayName())) {
            plugin.log.info("Allow login: " + player.getDisplayName());
            event.allow();
            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                public void run() {
                    player.sendMessage(ChatColor.GREEN + "Vip <= " + plugin.players.getDate(player.getDisplayName()));
                }
            });

        } else {
            plugin.log.info("Default login: " + player.getDisplayName());
        }
    }
}

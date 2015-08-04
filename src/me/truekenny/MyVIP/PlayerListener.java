package me.truekenny.MyVIP;

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
        if (event.getPlayer().hasPermission("myvip.vip")) {
            plugin.log.info("Allow login: " + event.getPlayer().getDisplayName());
            event.allow();
        } else {
            plugin.log.info("Default login: " + event.getPlayer().getDisplayName());
        }
    }
}

package me.truekenny.MyVIP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VipCommand implements CommandExecutor {
    /**
     * Экземпляр плагина
     */
    private final MyVIP plugin;

    public VipCommand(MyVIP plugin) {
        this.plugin = plugin;
    }

    /**
     * Обрабатывает команду /vip
     *
     * @param sender  Отправитель команды
     * @param command ?
     * @param label   ?
     * @param split   Параметры команды
     * @return Результат
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if ((sender instanceof Player) && (!sender.isOp())) {

            return false;
        }

        plugin.players.test();


        return true;
    }



}

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
        if (split.length == 0) {
            String date = plugin.players.getDate(sender.getName());
            if (date.equals("")) {
                sender.sendMessage("U have no VIP");
            } else {
                sender.sendMessage("Vip <= " + date);
            }

            return true;
        }

        if ((sender instanceof Player) && (!sender.isOp())) {

            return false;
        }

        if (split.length >= 2) {
            String nick = split[1];
            int days = (split.length >= 3) ? Integer.parseInt(split[2]) : plugin.config.getInt("default.days");

            if (split[0].equals("add")) {
                plugin.players.add(nick, days);

            } else if (split[0].equals("delete") || split[0].equals("del") || split[0].equals("remove") || split[0].equals("rem")) {
                plugin.players.delete(nick);
            }
        }

        if (split.length == 1 && split[0].equals("list")) {
            plugin.players.list(sender);
        }

        if (split.length == 1 && split[0].equals("test")) {
            plugin.players.test();
        }

        return true;
    }


}

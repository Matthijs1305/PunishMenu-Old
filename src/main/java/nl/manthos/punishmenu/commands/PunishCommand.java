package nl.manthos.punishmenu.commands;

import nl.manthos.punishmenu.PunishMenu;
import nl.manthos.punishmenu.config.PlayerConfig;
import nl.manthos.punishmenu.panel.Panel;
import nl.manthos.punishmenu.util.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import site.phoenixthedev.panelapi.PhoPanelManager;
import site.phoenixthedev.panelapi.session.SessionData;

public class PunishCommand implements CommandExecutor {

    private PunishMenu main;
    private PlayerConfig playerConfig;
    private PhoPanelManager phoPanelManager;

    public PunishCommand(PunishMenu main) {
        this.main = main;
        this.phoPanelManager = main.phoPanelManager;
        this.playerConfig = main.playerConfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (player.hasPermission("punishmenu.access")) {
                    this.helpMethod(player);
                } else {
                    player.sendMessage(Formatting.format("&6Deze server maakt gebruik van &e&lPunishMenu &7(" + main.getDescription().getVersion() + "&7) &6door &eManthos&6!"));
                }
            } else if (args.length == 1) {
                if (player.hasPermission("punishmenu.access")) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                    main.nameSave.put(player.getUniqueId(), target.getUniqueId());
                    this.playerConfig.addPlayer(target);
                    phoPanelManager.openPanel(new SessionData(player), Panel.HOME_PANEL.getKey());
                } else {
                    player.sendMessage(Formatting.format("&6Deze server maakt gebruik van &e&lPunishMenu &7(" + main.getDescription().getVersion() + "&7) &6door &eManthos&6!"));
                }
            }
        }
        return true;
    }

    void helpMethod(CommandSender player) {
        player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Gebruik &6/punish <naam> "));
    }
}

package nl.manthos.punishmenu;

import nl.manthos.punishmenu.commands.PunishCommand;
import nl.manthos.punishmenu.config.PlayerConfig;
import nl.manthos.punishmenu.panel.Panel;
import nl.manthos.punishmenu.panel.admin.HomePanel;
import nl.manthos.punishmenu.panel.admin.ChatPanel;
import nl.manthos.punishmenu.util.ConsoleLogger;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import site.phoenixthedev.panelapi.PhoPanelManager;
import site.phoenixthedev.phologger.loggers.PhoConsoleLogger;
import site.phoenixthedev.phologger.loggers.PhoLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class PunishMenu extends JavaPlugin {

    public HashMap<UUID, UUID> nameSave;
    public HashMap<UUID, ArrayList<String>> name;

    private ConsoleLogger logger;
    public PlayerConfig playerConfig;
    public PhoPanelManager phoPanelManager;
    public PhoLogger phoLogger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.nameSave = new HashMap<>();
        this.name = new HashMap<>();

        this.logger = new ConsoleLogger("" + ChatColor.WHITE + "[" + ChatColor.YELLOW + ChatColor.BOLD + "PunishMenu" + ChatColor.WHITE + "]");
        this.playerConfig = new PlayerConfig(this);
        this.phoLogger = new PhoConsoleLogger("" + ChatColor.WHITE + ChatColor.BOLD + "[" + ChatColor.YELLOW + ChatColor.BOLD + "PunishMenu" + ChatColor.WHITE + ChatColor.BOLD + "]");
        this.phoPanelManager = new PhoPanelManager(phoLogger);

        getCommand("punish").setExecutor(new PunishCommand(this));

        phoPanelManager.registerPanel(Panel.HOME_PANEL.getKey(), new HomePanel(this.phoPanelManager, this));
        phoPanelManager.registerPanel(Panel.MUTE_PANEL.getKey(), new ChatPanel(this.phoPanelManager, this));
        phoPanelManager.register(this);
    }

    @Override
    public void onDisable() {

        this.nameSave.clear();
    }
}

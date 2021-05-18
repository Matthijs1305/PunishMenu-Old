package nl.manthos.punishmenu.config;

import nl.manthos.punishmenu.PunishMenu;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {

    private String fileName = "playerdata.yml";

    private PunishMenu main;
    private File file;
    private YamlConfiguration config;

    public PlayerConfig(PunishMenu main) {

        file = new File(main.getDataFolder(), fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        this.save();
    }

    //          GLOBAL

    public void addPlayer(OfflinePlayer player) {
        String header = "players.";
        this.config.set(header + player.getUniqueId() + ".chat.ziektes", 0);
        this.config.set(header + player.getUniqueId() + ".chat.spam", 0);
        this.config.set(header + player.getUniqueId() + ".chat.reclame", 0);
        this.config.set(header + player.getUniqueId() + ".chat.taalgebruik", 0);
        save();
    }

    public boolean exists(OfflinePlayer player) {
        String header = "players.";
        return this.config.contains(header + player.getUniqueId());
    }

    //          ZIEKTES

    public void addZiekte(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        int current = this.config.getInt(header + ".chat.ziektes");
        int newNumber = (current + 1);
        this.config.set(header + ".chat.ziektes", newNumber);
        save();
    }

    public int getZiekte(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        return this.config.getInt(header + ".chat.ziektes");
    }

    //          SPAM

    public void addSpam(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        int current = this.config.getInt(header + ".chat.spam");
        int newNumber = current + 1;
        this.config.set(header + ".chat.spam", newNumber);
        save();
    }

    public int getSpam(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        return this.config.getInt(header + ".chat.spam");
    }

    //          RECLAME

    public void addReclame(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        int current = this.config.getInt(header + ".chat.reclame");
        int newNumber = current + 1;
        this.config.set(header + ".chat.reclame", newNumber);
        save();
    }

    public int getReclame(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        return this.config.getInt(header + ".chat.reclame");
    }

    //          ONGEPAST TAALGEBRUIK

    public void addTaalgebruik(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        int current = this.config.getInt(header + ".chat.taalgebruik");
        int newNumber = current + 1;
        this.config.set(header + ".chat.taalgebruik", newNumber);
        save();
    }

    public int getTaalgebruik(OfflinePlayer player) {
        String header = "players." + player.getUniqueId();
        return this.config.getInt(header + ".chat.taalgebruik");
    }

    //          SAVE

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

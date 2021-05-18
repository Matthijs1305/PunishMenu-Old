package nl.manthos.punishmenu.panel.admin;

import nl.manthos.punishmenu.PunishMenu;
import nl.manthos.punishmenu.config.PlayerConfig;
import nl.manthos.punishmenu.util.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import site.phoenixthedev.panelapi.PhoPanelManager;
import site.phoenixthedev.panelapi.session.BaseSessionData;
import site.phoenixthedev.panelapi.types.PhoPanel;

public class ChatPanel extends PhoPanel {

    private PunishMenu main;
    private PlayerConfig playerConfig;
    private String panelName = Formatting.format("&a&lChat Moderation");

    public ChatPanel(PhoPanelManager phoPanelManager, PunishMenu main) {
        super(phoPanelManager);
        this.main = main;
        this.playerConfig = main.playerConfig;
    }

    @Override
    public void initialize() {
        defaultPanel = null;
        panelSize = 54;
    }

    @Override
    public Inventory createPanel(BaseSessionData baseSessionData) {
        Inventory inv = Bukkit.createInventory(null, panelSize, panelName);

        inv.setItem(11, reclame());
        inv.setItem(13, ziektes());
        inv.setItem(15, spam());
        inv.setItem(31, ongepasttaalgebruik());
        inv.setItem(53, closeButton());

        return inv;
    }

    @Override
    public void onClickEvent(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        if (!isInstance(inv)) return;
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (e.getRawSlot() == -999) return;
        if (e.getCurrentItem() == null) return;
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        OfflinePlayer target = Bukkit.getOfflinePlayer(this.main.nameSave.get(player.getUniqueId()));
        ItemStack current = e.getCurrentItem();
        String currentName = current.getItemMeta().getDisplayName();

        if (currentName.equals(closeButton().getItemMeta().getDisplayName())) {

            main.nameSave.remove(player.getUniqueId());
            player.closeInventory();
        } else if (currentName.equals(ziektes().getItemMeta().getDisplayName())) {

            if (this.playerConfig.exists(target)) {
                if (this.playerConfig.getZiekte(target) == 0) {

                    String cmd = "mute " + target.getName() + " 6h Schelden met Ziektes (" + player.getName() + ") -s";
                    playerConfig.addZiekte(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor schelden met ziektes!"));
                    player.closeInventory();
                } else if (this.playerConfig.getZiekte(target) == 1 || this.playerConfig.getZiekte(target) == 2) {

                    String cmd = "mute " + target.getName() + " 12h Schelden met Ziektes (" + player.getName() + ") -s";
                    playerConfig.addZiekte(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor schelden met ziektes!"));
                    player.closeInventory();
                } else if (this.playerConfig.getZiekte(target) == 3) {

                    String cmd = "mute " + target.getName() + " 1d Schelden met Ziektes (" + player.getName() + ") -s";
                    playerConfig.addZiekte(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor schelden met ziektes!"));
                    player.closeInventory();
                } else if (this.playerConfig.getZiekte(target) >= 4) {

                    String cmd = "ban " + target.getName() + " 1d Schelden met Ziektes (" + player.getName() + ") -s";
                    playerConfig.addZiekte(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor schelden met ziektes!"));
                    player.closeInventory();
                }
            } else {
                this.playerConfig.addPlayer(target);
                this.playerConfig.addZiekte(target);
                String cmd = "mute " + target.getName() + " 6h Schelden met Ziektes (" + player.getName() + ") -s";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor schelden met ziektes!"));
                player.closeInventory();
            }
        } else if (currentName.equals(spam().getItemMeta().getDisplayName())) {

            if (this.playerConfig.exists(target)) {
                if (this.playerConfig.getSpam(target) <= 1) {

                    String cmd = "warn " + target.getName() + " Spamming (" + player.getName() + ") -s";
                    this.playerConfig.addSpam(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor spam!"));
                    player.closeInventory();
                } else if (this.playerConfig.getSpam(target) == 2) {

                    String cmd = "mute " + target.getName() + " 1h Spamming (" + player.getName() + ") -s";
                    this.playerConfig.addSpam(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor spam!"));
                    player.closeInventory();
                } else if (this.playerConfig.getSpam(target) >= 3) {

                    String cmd = "mute " + target.getName() + " 6h Spamming (" + player.getName() + ") -s";
                    this.playerConfig.addSpam(target);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor spam!"));
                    player.closeInventory();
                }
            } else {
                this.playerConfig.addPlayer(target);
                this.playerConfig.addSpam(target);
                String cmd = "warn " + target.getName() + " Spamming (" + player.getName() + ") -s";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor spam!"));
                player.closeInventory();
            }
        } else if (currentName.equals(reclame().getItemMeta().getDisplayName())) {

            if (this.playerConfig.exists(target)) {
                if (this.playerConfig.getReclame(target) == 0) {

                    this.playerConfig.addReclame(target);
                    String cmd = "ban " + target.getName() + " 7d Reclame (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor reclame!"));
                    player.closeInventory();
                } else if (this.playerConfig.getReclame(target) == 1) {

                    this.playerConfig.addReclame(target);
                    String cmd = "ban " + target.getName() + " 14d Reclame (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor reclame!"));
                    player.closeInventory();
                } else if (this.playerConfig.getReclame(target) == 2) {

                    this.playerConfig.addReclame(target);
                    String cmd = "ban " + target.getName() + " 30d Reclame (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor reclame!"));
                    player.closeInventory();
                } else if (this.playerConfig.getReclame(target) == 3) {

                    this.playerConfig.addReclame(target);
                    String cmd = "ban " + target.getName() + " Reclame (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor reclame!"));
                    player.closeInventory();
                }
            } else {
                this.playerConfig.addPlayer(target);
                this.playerConfig.addReclame(target);
                String cmd = "ban " + target.getName() + " 7d Reclame (" + player.getName() + ") -s";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor reclame!"));
                player.closeInventory();
            }
        } else if (currentName.equals(ongepasttaalgebruik().getItemMeta().getDisplayName())) {

            if (this.playerConfig.exists(target)) {
                if (this.playerConfig.getTaalgebruik(target) == 0) {

                    this.playerConfig.addTaalgebruik(target);
                    String cmd = "warn " + target.getName() + " Ongepast Taalgebruik (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor ongepast taalgebruik!"));
                    player.closeInventory();
                } else if (this.playerConfig.getTaalgebruik(target) == 1) {

                    this.playerConfig.addTaalgebruik(target);
                    String cmd = "mute " + target.getName() + " 2h Ongepast Taalgebruik (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor ongepast taalgebruik!"));
                    player.closeInventory();
                } else if (this.playerConfig.getTaalgebruik(target) == 2) {

                    this.playerConfig.addTaalgebruik(target);
                    String cmd = "mute " + target.getName() + " 6h Ongepast Taalgebruik (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor ongepast taalgebruik!"));
                    player.closeInventory();
                } else if (this.playerConfig.getTaalgebruik(target) >= 3) {

                    this.playerConfig.addTaalgebruik(target);
                    String cmd = "mute " + target.getName() + " 8h Ongepast Taalgebruik (" + player.getName() + ") -s";
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor ongepast taalgebruik!"));
                    player.closeInventory();
                }
            } else {

                this.playerConfig.addPlayer(target);
                this.playerConfig.addTaalgebruik(target);
                String cmd = "warn " + target.getName() + " Ongepast Taalgebruik (" + player.getName() + ") -s";
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                player.sendMessage(Formatting.format("&e&lPUNISHMENU &7- Je hebt &6" + target.getName() + "&7 gestraft voor ongepast taalgebruik!"));
                player.closeInventory();
            }
        }
    }

    private ItemStack ziektes() {
        ItemStack is = new ItemStack(Material.NETHERITE_INGOT);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&b&lSchelden Met Ziektes"));
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    private ItemStack spam() {
        ItemStack is = new ItemStack(Material.COBWEB);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&2&lSpammen"));
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    private ItemStack reclame() {
        ItemStack is = new ItemStack(Material.BOOK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&e&lReclame"));
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    private ItemStack ongepasttaalgebruik() {
        ItemStack is = new ItemStack(Material.ARROW);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&d&lOngepast Taalgebruik"));
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    private ItemStack closeButton() {
        ItemStack is = new ItemStack(Material.BARRIER);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&c&lSluiten"));
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }
}

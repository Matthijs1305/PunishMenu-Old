package nl.manthos.punishmenu.panel.admin;

import net.md_5.bungee.api.chat.ComponentBuilder;
import nl.manthos.punishmenu.PunishMenu;
import nl.manthos.punishmenu.config.PlayerConfig;
import nl.manthos.punishmenu.panel.Panel;
import nl.manthos.punishmenu.util.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import site.phoenixthedev.panelapi.PhoPanelManager;
import site.phoenixthedev.panelapi.session.BaseSessionData;
import site.phoenixthedev.panelapi.session.SessionData;
import site.phoenixthedev.panelapi.types.PhoPanel;

import java.util.Arrays;
import java.util.List;

public class HomePanel extends PhoPanel {

    private PunishMenu main;
    private PlayerConfig playerConfig;
    private final String panelName = Formatting.format("&e&lPunish Menu");
    private String prefix = Formatting.format("");

    public HomePanel(PhoPanelManager phoPanelManager, PunishMenu main) {
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

        Player player = baseSessionData.owner;
        OfflinePlayer target = Bukkit.getOfflinePlayer(this.main.nameSave.get(player.getUniqueId()));

        inv.setItem(13, chat());
        inv.setItem(50, info(Arrays.asList(Formatting.format("&6Stafflid: &e" + player.getName()), Formatting.format("&6Huidige Speler: &e" + target.getName()))));
        inv.setItem(53, closeButton());

        return inv;
    }

    List<String> lore = Arrays.asList("");

    @Override
    public void onClickEvent(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        if (!isInstance(inv)) return;
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (e.getRawSlot() == -999) return;
        if (e.getCurrentItem() == null) return;
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        OfflinePlayer target = Bukkit.getOfflinePlayer(main.nameSave.get(player.getUniqueId()));
        ItemStack current = e.getCurrentItem();
        String currentName = current.getItemMeta().getDisplayName();

        if (currentName.equals(closeButton().getItemMeta().getDisplayName())) {

            main.nameSave.remove(player.getUniqueId());
            player.closeInventory();
        } else if (currentName.equals(chat().getItemMeta().getDisplayName())) {

            phoPanelManager.openPanel(new SessionData(player), Panel.MUTE_PANEL.getKey());
        }
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

    private ItemStack chat() {
        ItemStack is = new ItemStack(Material.FEATHER);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&a&lChat Moderation"));
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    private ItemStack last(List<String> lore) {
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&c&lLaatste Straf:"));
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 999, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }

    private ItemStack info(List<String> lore) {
        ItemStack is = new ItemStack(Material.PAPER);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Formatting.format("&b&lInfo"));
        im.setLore(lore);
        im.addEnchant(Enchantment.LUCK, 999, false);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(im);
        return is;
    }
}

package gg.corn.seed2ban;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class AnvilListener implements Listener {
    private final String seedRegex;

    public AnvilListener(String seedRegex) {
        this.seedRegex = seedRegex;
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() instanceof AnvilInventory) {
            AnvilInventory anvil = (AnvilInventory) event.getInventory();
            ItemStack result = anvil.getItem(2); // Slot 2 is the result slot in an anvil

            if (result != null && result.hasItemMeta()) {
                ItemMeta meta = result.getItemMeta();
                if (meta.hasDisplayName()) {
                    HumanEntity player = event.getWhoClicked();
                    String playerName = player.getName();
                    String convertedItemName = SpecialCharacters.convertNumberChars(meta.getDisplayName());

                    // Debugging
                    System.out.println("Converted Item Name: " + convertedItemName);
                    System.out.println("Seed Regex: " + seedRegex);

                    if (convertedItemName.matches("(.*|^)" + seedRegex + "(.*|$)")) {
                        if (!player.hasPermission("seed2ban.exempt")) {
                            player.sendMessage("You cannot use the world seed in item names!");
                            event.setCancelled(true);
                            Seed2ban.commandOnDetection(playerName);
                        }
                    }
                }
            }
        }
    }
}
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
                HumanEntity player = event.getWhoClicked();
                String playerName = player.getName();
                String convertedItemName = SpecialCharacters.convertNumberChars(meta.getDisplayName());

                if (meta.hasDisplayName() && convertedItemName.matches(".*\\b" + seedRegex + "\\b.*")) {
                    if (!event.getWhoClicked().hasPermission("seed2ban.exempt")) {
                        event.getWhoClicked().sendMessage("You cannot use the world seed in item names!");
                        event.setCancelled(true);
                        if (player.hasPermission("seed2ban.exempt")) {
                            return; // Player is exempt, do nothing
                        }
                        // Schedule the command to be run on the main server thread
                        Bukkit.getScheduler().runTask(Seed2ban.plugin, () -> {
                            // Construct and execute the command

                            String command = "ipban " + playerName + " Attempting to leak the world seed.";
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                        });

                    }
                }
            }
        }
    }
}
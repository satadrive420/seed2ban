package gg.corn.seed2ban;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.entity.Player;

public class CommandListener implements Listener {
    private final String seedRegex;

    public CommandListener(String seedRegex) {
        this.seedRegex = seedRegex;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        String playerName = player.getName();
        String convertedCommand = SpecialCharacters.convertNumberChars(command);
        // Check if the command contains the world seed
        if (convertedCommand.matches(".*\\b" + seedRegex + "\\b.*")) {
            // Perform your action here
            player.sendMessage("You cannot use the world seed in commands!");
            event.setCancelled(true);

            if (player.hasPermission("seed2ban.exempt")) {
                return; // Player is exempt, do nothing
            }

            Bukkit.getScheduler().runTask(Seed2ban.plugin, () -> {
                // Construct and execute the command
                String banCommand = "ipban " + playerName + " Attempting to leak the world seed.";
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), banCommand);
            });
        }
    }
}

package gg.corn.seed2ban;

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
        if (convertedCommand.matches("(.*|^)" + seedRegex + "(.*|$)")) {
            // Perform your action here
            player.sendMessage("You cannot use the world seed in commands!");
            event.setCancelled(true);

            if (player.hasPermission("seed2ban.exempt")) {
                return; // Player is exempt, do nothing
            }

            Seed2ban.commandOnDetection(playerName);
        }
    }
}

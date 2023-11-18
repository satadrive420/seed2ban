package gg.corn.seed2ban;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;


public class SignChangeListener implements Listener {
    private final String seedRegex;

    public SignChangeListener(String seedRegex) {
        this.seedRegex = seedRegex;
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (event.getPlayer().hasPermission("seed2ban.exempt")) {
            return; // Player is exempt, do nothing
        }

        StringBuilder signText = new StringBuilder();
        for (String line : event.getLines()) {
            signText.append(line);
        }

        String convertedSignText = SpecialCharacters.convertNumberChars(signText.toString());

        if (convertedSignText.matches(".*\\b" + seedRegex + "\\b.*")) {
            event.getPlayer().sendMessage("You cannot use the world seed on signs!");
            event.setCancelled(true);
            if (player.hasPermission("seed2ban.exempt")) {
                return; // Player is exempt, do nothing
            }
            // Schedule the command to be run on the main server thread

            Seed2ban.commandOnDetection(playerName);

        }
    }
}
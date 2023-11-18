package gg.corn.seed2ban;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ChatListener implements Listener {
    private String regex;

    public ChatListener(String seedRegex) {
        regex = seedRegex;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        // Convert special number characters to standard numerals
        String convertedMessage = SpecialCharacters.convertNumberChars(message);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(convertedMessage);

        if (matcher.find()) {
            Player player = event.getPlayer();
            String playerName = player.getName();
            player.sendMessage("You cannot use the world seed in the chat!");
            event.setCancelled(true);

            if (player.hasPermission("seed2ban.exempt")) {
                return; // Player is exempt, do nothing
            }

            Seed2ban.commandOnDetection(playerName);
        }
    }
}
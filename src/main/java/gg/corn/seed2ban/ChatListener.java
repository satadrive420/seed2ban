package gg.corn.seed2ban;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;




public class ChatListener implements Listener {
    private final String seedRegex;

    public ChatListener(String seedRegex) {
        this.seedRegex = seedRegex;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        // Convert special number characters to standard numerals
        String convertedMessage = SpecialCharacters.convertNumberChars(message);

        if (convertedMessage.matches("(.*|^)" + seedRegex + "(.*|$)")) {
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
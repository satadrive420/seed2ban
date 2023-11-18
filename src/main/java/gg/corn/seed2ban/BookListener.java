package gg.corn.seed2ban;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class BookListener implements Listener {
    private final String seedRegex;

    public BookListener(String seedRegex) {
        this.seedRegex = seedRegex;
    }

    @EventHandler
    public void onBookEdit(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        BookMeta bookMeta = event.getNewBookMeta();
        List<String> pages = bookMeta.getPages();

        for (String page : pages) {
            String convertedPage = SpecialCharacters.convertNumberChars(page);
            if (convertedPage.matches("(.*|^)" + seedRegex + "(.*|$)")) {
                player.sendMessage("You cannot use the world seed in books!");
                event.setCancelled(true);
                if (player.hasPermission("seed2ban.exempt")) {
                    return; // Player is exempt, do nothing
                }
                // Schedule the command to be run on the main server thread

                Seed2ban.commandOnDetection(playerName);
            }
        }
    }
}
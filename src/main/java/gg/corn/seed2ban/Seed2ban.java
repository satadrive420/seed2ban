package gg.corn.seed2ban;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Pattern;

public final class Seed2ban extends JavaPlugin {
    public static Seed2ban plugin;
    private String seedRegex;

    @Override
    public void onEnable() {
        plugin = this;
        // Get the main world
        World world = Bukkit.getServer().getWorlds().get(0);
        // Get the seed of the world
        long seed = world.getSeed();
        // Convert the seed to a regex pattern
        seedRegex = createFlexibleRegexPattern(seed);
        // Register the listener
        getServer().getPluginManager().registerEvents(new ChatListener(seedRegex), plugin);
        getServer().getPluginManager().registerEvents(new CommandListener(seedRegex), plugin);
        getServer().getPluginManager().registerEvents(new SignChangeListener(seedRegex), plugin);
        getServer().getPluginManager().registerEvents(new AnvilListener(seedRegex), plugin);
        getServer().getPluginManager().registerEvents(new BookListener(seedRegex), plugin);
    }

    public String createFlexibleRegexPattern(long seed) {
        String seedStr = Long.toString(seed);
        StringBuilder regex = new StringBuilder();

        for (int i = 0; i < seedStr.length(); i++) {
            regex.append(Pattern.quote(String.valueOf(seedStr.charAt(i))));
            if (i < seedStr.length() - 1) {
                regex.append(".*"); // Matches any character any number of times (including zero)
            }
        }

        return regex.toString();
    }


    public static void commandOnDetection(String playerName){

        Bukkit.getScheduler().runTask(Seed2ban.plugin, () -> {
            // Construct and execute the command
            String commandTemplate = Seed2ban.plugin.getConfig().getString("command-on-detection", "ban %player% Attempting to leak the world seed.");
            String command = commandTemplate.replace("%player%", playerName);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        });

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

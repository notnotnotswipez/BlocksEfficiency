package me.swipez.blocksefficiency;

import me.swipez.blocksefficiency.listeners.Mine;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlocksEfficiency extends JavaPlugin {

    @Override
    public void onEnable() {

        new Mine(this);
        getServer().getLogger().info("Plugin Enabled.");

    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Plugin Disabled.");
    }


}

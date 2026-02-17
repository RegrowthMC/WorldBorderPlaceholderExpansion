package org.lushplugins.worldborderplaceholderexpansion;

import org.bukkit.plugin.java.JavaPlugin;

public final class WorldBorderPlaceholderExpansion extends JavaPlugin {
    private static WorldBorderPlaceholderExpansion plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static WorldBorderPlaceholderExpansion getInstance() {
        return plugin;
    }
}

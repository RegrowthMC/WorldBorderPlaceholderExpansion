package org.lushplugins.worldborderplaceholderexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public final class WorldBorderPlaceholderExpansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getAuthor() {
        return "RegrowthMC";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "worldborder";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        int splitIndex = params.indexOf('_');
        String argument;
        World world;
        if (splitIndex < 0) {
            argument = params;
            world = Bukkit.getWorld("world");
        } else {
            argument = params.substring(0, splitIndex);
            world = Bukkit.getWorld(params.substring(splitIndex + 1));
        }

        if (world == null) {
            return null;
        }

        WorldBorder border = world.getWorldBorder();
        return switch(argument) {
            case "center" -> {
                Location center = border.getCenter();
                yield "%s, %s, %s".formatted(
                    center.getX(),
                    center.getY(),
                    center.getZ()
                );
            }
            case "damage", "damage-amount" -> String.valueOf(border.getDamageAmount());
            case "damage-buffer" -> String.valueOf(border.getDamageBuffer());
            case "size" -> new DecimalFormat("0").format(border.getSize());
            case "size-formatted" -> {
                NumberFormat format = NumberFormat.getCompactNumberInstance(Locale.UK, NumberFormat.Style.SHORT);
                format.setMaximumFractionDigits(1);
                yield format.format(border.getSize()).toLowerCase();
            }
            default -> null;
        };
    }
}

/*
 * File: CmdSpec.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfeed.utils.commands;

import dev.meinel.leo.vitalfeed.VitalFeed;
import dev.meinel.leo.vitalfeed.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CmdSpec {

    private static final VitalFeed main = JavaPlugin.getPlugin(VitalFeed.class);
    private static final HashMap<UUID, Long> cooldownMap = new HashMap<>();

    private CmdSpec() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isInvalidCmd(@NotNull CommandSender sender, Player player,
            @NotNull String perm) {
        return Cmd.isNotPermitted(sender, perm) || Cmd.isInvalidPlayer(sender, player)
                || isOnCooldown(sender);
    }

    public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {
        return Cmd.isNotPermitted(sender, perm) || isOnCooldown(sender);
    }

    private static void clearMap(@NotNull CommandSender sender) {
        Player senderPlayer = (Player) sender;
        cooldownMap.remove(senderPlayer.getUniqueId());
    }

    private static void doTiming(@NotNull CommandSender sender) {
        new BukkitRunnable() {

            @Override
            public void run() {
                clearMap(sender);
            }
        }.runTaskLaterAsynchronously(main, (main.getConfig().getLong("cooldown.time") * 20L));
    }

    private static boolean isOnCooldown(@NotNull CommandSender sender) {
        Player senderPlayer = (Player) sender;
        boolean isOnCooldown = main.getConfig().getBoolean("cooldown.enabled")
                && !sender.hasPermission("vitalfeed.cooldown.bypass")
                && cooldownMap.containsKey(senderPlayer.getUniqueId());
        if (isOnCooldown) {
            String timeRemaining = String.valueOf(cooldownMap.get(senderPlayer.getUniqueId())
                    - System.currentTimeMillis() / 1000);
            Chat.sendMessage(sender, Map.of("%time-left%", timeRemaining), "cooldown-active");
            return true;
        }
        cooldownMap.put(senderPlayer.getUniqueId(),
                main.getConfig().getLong("cooldown.time") + System.currentTimeMillis() / 1000);
        doTiming(sender);
        return false;
    }
}

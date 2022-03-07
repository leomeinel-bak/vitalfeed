/*
 * VitalFeed is a Spigot Plugin that gives players the ability to fill their hunger.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalFeed/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalfeed.utils.commands;

import com.google.common.collect.ImmutableMap;
import com.tamrielnetwork.vitalfeed.VitalFeed;
import com.tamrielnetwork.vitalfeed.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class CmdSpec {

	private static final VitalFeed main = JavaPlugin.getPlugin(VitalFeed.class);
	private static final HashMap<UUID, Long> cooldownMap = new HashMap<>();

	public static boolean isInvalidCmd(@NotNull CommandSender sender, Player player, @NotNull String perm) {

		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}
		if (Cmd.isInvalidPlayer(sender, player)) {
			return true;
		}
		return isOnCooldown(sender);
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {

		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}
		return isOnCooldown(sender);
	}

	public static void doFeed(@NotNull CommandSender sender, @NotNull Player player) {

		Chat.sendMessage(sender, ImmutableMap.of("%player%", player.getName()), "player-fed");
		player.setFoodLevel(20);
	}

	public static void doFeed(@NotNull Player senderPlayer) {

		Chat.sendMessage(senderPlayer, "fed");
		senderPlayer.setFoodLevel(20);
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

		boolean isOnCooldown = main.getConfig().getBoolean("cooldown.enabled") && !sender.hasPermission("vitalfeed.cooldown.bypass") && cooldownMap.containsKey(senderPlayer.getUniqueId());

		if (isOnCooldown) {
			String timeRemaining = String.valueOf(cooldownMap.get(senderPlayer.getUniqueId()) - System.currentTimeMillis() / 1000);
			Chat.sendMessage(sender, ImmutableMap.of("%time-left%", timeRemaining), "cooldown-active");
			return true;
		}
		cooldownMap.put(senderPlayer.getUniqueId(), main.getConfig().getLong("cooldown.time") + System.currentTimeMillis() / 1000);
		doTiming(sender);
		return false;
	}

}

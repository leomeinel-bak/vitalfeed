/*
 * VitalFeed is a Spigot Plugin that gives players the ability to fill their hunger.
 * Copyright © 2022 Leopold Meinel
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

package com.tamrielnetwork.vitalfeed.commands;

import com.google.common.collect.ImmutableMap;
import com.tamrielnetwork.vitalfeed.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class VitalFeedCmd implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		// Check args length
		if (args.length > 1) {
			Utils.sendMessage(sender, "invalid-option");
			return true;
		}
		// Toggle Crafting Interface
		feedPlayer(sender, args);
		return true;

	}

	private void feedPlayer(CommandSender sender, String[] args) {
		// Check if command sender is a player
		if (!(sender instanceof Player)) {
			Utils.sendMessage(sender, "player-only");
			return;
		}
		// Check perms
		if (!sender.hasPermission("vitalfeed.feed")) {
			Utils.sendMessage(sender, "no-perms");
			return;
		}

		// Check args length
		if (args.length == 1) {
			// Check perms
			if (!sender.hasPermission("vitalfeed.feed.others")) {
				Utils.sendMessage(sender, "no-perms");
				return;
			}
			if (Bukkit.getPlayer(args[0]) == null) {
				Utils.sendMessage(sender, "invalid-player");
				return;
			}
			Player player = Bukkit.getPlayer(args[0]);
			boolean isOnline = Objects.requireNonNull(player).isOnline();
			if (!isOnline) {
				Utils.sendMessage(sender, "not-online");
				return;
			}
			Utils.sendMessage(sender, ImmutableMap.of("%player%", player.getName()), "player-fed");
			player.setFoodLevel(20);
			return;
		}
		Utils.sendMessage(sender,"fed");
		((Player) sender).setFoodLevel(20);
	}
}
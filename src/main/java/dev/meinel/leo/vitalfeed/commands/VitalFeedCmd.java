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
 * along with this program. If not, see https://github.com/LeoMeinel/VitalFeed/blob/main/LICENSE
 */

package dev.meinel.leo.vitalfeed.commands;

import dev.meinel.leo.vitalfeed.utils.Chat;
import dev.meinel.leo.vitalfeed.utils.commands.Cmd;
import dev.meinel.leo.vitalfeed.utils.commands.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VitalFeedCmd
		implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	                         @NotNull String[] args) {
		if (Cmd.isArgsLengthGreaterThan(sender, args, 1)) {
			return false;
		}
		doFeed(sender, args);
		return true;
	}

	private void doFeed(@NotNull CommandSender sender, @NotNull String[] args) {
		if (Cmd.isInvalidSender(sender)) {
			return;
		}
		Player senderPlayer = (Player) sender;
		if (args.length == 1) {
			Player player = Bukkit.getPlayer(args[0]);
			if (CmdSpec.isInvalidCmd(senderPlayer, player, "vitalfeed.feed.others")) {
				return;
			}
			assert player != null;
			Chat.sendMessage(sender, Map.of("%player%", player.getName()), "player-fed");
			player.setFoodLevel(20);
			return;
		}
		if (CmdSpec.isInvalidCmd(sender, "vitalfeed.feed")) {
			return;
		}
		Chat.sendMessage(senderPlayer, "fed");
		senderPlayer.setFoodLevel(20);
	}
}
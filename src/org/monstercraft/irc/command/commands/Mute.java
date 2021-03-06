package org.monstercraft.irc.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.monstercraft.irc.command.Command;
import org.monstercraft.irc.util.Variables;

public class Mute extends Command {

	public Mute(org.monstercraft.irc.IRC plugin) {
		super(plugin);
	}

	@Override
	public boolean canExecute(CommandSender sender, String[] split) {
		return split[0].contains("irc") && split[1].equalsIgnoreCase("mute");
	}

	@Override
	public boolean execute(CommandSender sender, String[] split) {
		if (sender instanceof Player) {
			if (!plugin.perms.hasCommandPerms(((Player) sender), this)) {
				sender.sendMessage("[IRC] You don't have permission to preform that command.");
				return false;
			}
		}
		if (!Variables.muted.contains(split[2].toString().toLowerCase())) {
			Variables.muted.add(split[2].toString().toLowerCase());
			plugin.settings.saveMuteConfig();
			sender.sendMessage("Player " + split[2].toString()
					+ " has been muted from talking via IRC.");
		} else {
			sender.sendMessage("Player " + split[2].toString()
					+ "is already muted from talking via IRC.");
			return false;
		}
		return Variables.muted.contains(split[2].toString().toLowerCase());
	}

	@Override
	public String getPermissions() {
		return "irc.mute";
	}

}

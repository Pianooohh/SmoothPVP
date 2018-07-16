package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCmds implements CommandExecutor {

	private Main plugin;
	public ChatCmds(Main plugin) {
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(msg.equalsIgnoreCase("cc") || msg.equalsIgnoreCase("clearchat")) {
			if(sender.hasPermission("perm.mod")) {
				for (int i = 1; i <= 100; i++) {
					Bukkit.broadcastMessage("");
				}
				Bukkit.broadcastMessage("§3The chat has been cleared by an admin.");
				plugin.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §ecleared the chat.");
			} else {
				sender.sendMessage(Prefixs.Noperm);
				return true;
			}
		}
		if(msg.equalsIgnoreCase("mc")) {
			if(sender.hasPermission("perm.mod")) {
				if(plugin.mutechat == true) {
					plugin.mutechat = false;
					Bukkit.broadcastMessage("§3The chat has been unlocked by an admin.");
					plugin.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §eunlocked the chat.");
				} else {
					plugin.mutechat = true;
					Bukkit.broadcastMessage("§3The chat has been locked by an admin.");
					plugin.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §elocked the chat.");
					return true;
				}
			} else {
				sender.sendMessage(Prefixs.Noperm);
				return true;
			}
		}
		return false;
	}

}

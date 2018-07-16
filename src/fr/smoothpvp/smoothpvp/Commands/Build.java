package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Build implements CommandExecutor {
	
	private Main plugin;
	public Build(Main plugin) {
		this.plugin = plugin;
	}
	
	public String getBuildStatue() {
		
		if(plugin.build == false) {
			return "desactivated.";
		} else return "activated.";
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender.hasPermission("perm.admin")) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("off")) {
					if(plugin.build == true) {
						plugin.build = false;
						sender.sendMessage("§3The build is now desactivated.");
						plugin.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §edesactivated the build.");
					} else {
						sender.sendMessage("§cThe build is already desactivated!");
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("on")) {
					if(plugin.build == false) {
						plugin.build = true;
						sender.sendMessage("§3The build is now activated.");
						plugin.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §eactivated the build.");
					} else {
						sender.sendMessage("§cThe build is already activated!");
						return true;
					}
				} else {
					sender.sendMessage(Prefixs.Usage + "/build <on/off>");
					return true;
				}
			} else {
				sender.sendMessage(Prefixs.Usage + "/build <on/off>");
				sender.sendMessage("§bThe build is " + getBuildStatue());
				return true;
			}
		} else {
			sender.sendMessage(Prefixs.Noperm);
			return true;
		}
		return false;
	}
}

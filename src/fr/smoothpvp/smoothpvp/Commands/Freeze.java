package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze implements CommandExecutor {

	private Main pl;
	public Freeze(Main pl) {
		this.pl = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender.hasPermission("perm.mod")) {
			if (args.length == 1) {
				if (pl.ismod.contains(sender)) {
					Player t = Bukkit.getPlayerExact(args[0]);
					if (pl.isfreeze.contains(t)) {
						pl.isfreeze.remove(t);
						t.sendMessage("§3You have been unfreeze by an admin.");
						sender.sendMessage("§3You have unfreeze " + t.getDisplayName());
						pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §ehave unfreeze " + t.getName() + ".");
					} else {
						pl.isfreeze.add(t);
						t.sendMessage("§cYou have been frozen by an admin, you can't moove anymore!");
						sender.sendMessage("§3You have frozen " + t.getDisplayName());
						pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " §ehave frozen " + t.getName() + ".");
						return true;
					}
				} else {
					sender.sendMessage(Prefixs.NOMOD);
					return true;
				}
			} else {
				sender.sendMessage(Prefixs.Usage + "/freeze <player>");
				return true;
			}
		} else {
			sender.sendMessage(Prefixs.Noperm);
			return true;
		}
		return false;
	}

}

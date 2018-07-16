package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Prefixs.RCON);
			return true;
		}
		Player p = (Player) sender;
		if(p.hasPermission("perm.admin")) {
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
				if(args.length == 0) {
					p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
					p.sendMessage("§3You have set the spawn location to: §8X: " + p.getLocation().getBlockX() + ", Y: " + p.getLocation().getBlockY() + ", Z: " + p.getLocation().getBlockZ());
					Main.getInstance().sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " set " + p.getWorld().getName() + "'s spawn to: §7X:" + p.getLocation().getBlockX() + ", Y: " + p.getLocation().getBlockY() + ", Z: " + p.getLocation().getBlockZ());
				}
			}
			if(cmd.getName().equalsIgnoreCase("spawn")) {
				if(args.length == 0) {
					p.teleport(p.getWorld().getSpawnLocation());
				}
			}
		} else {
			p.sendMessage(Prefixs.Noperm);
		}
		return false;
	}
}

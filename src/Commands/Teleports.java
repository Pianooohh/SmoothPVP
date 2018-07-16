package Commands;

import Main.Main;
import Utils.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleports implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Prefixs.RCON);
			return true;
		}
		Player p = (Player) sender;
		if(p.hasPermission("perm.mod")) {
			if(cmd.getName().equalsIgnoreCase("tp")){
				if(!Main.getInstance().ismod.contains(p)) {
					p.sendMessage(Prefixs.NOMOD);
					return true;
				}
				if(args.length == 1) {
					Player t = Bukkit.getPlayerExact(args[0]);
					if(t != null) {
						p.teleport(t.getLocation());
						p.sendMessage("�3You have been teleported to " + t.getName());
						Main.getInstance().sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " teleported to " + t.getName() + ".");
					} else {
						p.sendMessage("�c" + args[0] + " is not online!");
					}
				} else {
					p.sendMessage(Prefixs.Usage + "/tp <player>");
				}
			}
			if(cmd.getName().equalsIgnoreCase("tphere")) {
				if(args.length == 1) {
					Player t = Bukkit.getPlayerExact(args[0]);
					if(t != null) {
						t.teleport(p.getLocation());
						t.sendMessage("�3You have been teleported by a staff.");
						p.sendMessage("�3You have teleported " + t.getName());
						Main.getInstance().sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " teleported " + t.getName() + " to him.");
					} else {
						p.sendMessage("�c" + args[0] + " is not online!");
					}
				} else {
					p.sendMessage(Prefixs.Usage + "/tphere <player>");
				}
			}
		} else {
			p.sendMessage(Prefixs.Noperm);
		}
		return false;
	}
}

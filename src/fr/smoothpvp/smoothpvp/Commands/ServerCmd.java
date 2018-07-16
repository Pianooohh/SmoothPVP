package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerCmd implements CommandExecutor {

	private Main pl;
	public ServerCmd(Main pl) {
		this.pl = pl;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("perm.admin")) {
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("open")) {
					if(args[1].equalsIgnoreCase("FFA")) {
						if(pl.FFA == false) {
							pl.FFA = true;
							Bukkit.broadcastMessage("§3§lFFA: §bThe server FFA is now §aOnline.");
							pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " turned ON the FFA Server.");
							for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
						} else {
							sender.sendMessage("§CThe server is already open!");
							return true;
						}
					}
					else if(args[1].equalsIgnoreCase("Duels")) {
						if(pl.Duels == false) {
							pl.Duels = true;
							Bukkit.broadcastMessage("§3§lDuels: §bThe server Duels is now §aOnline.");
							pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " turned ON the Duels Server.");
							for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
						} else {
							sender.sendMessage("§cThe server is already open!");
							return true;
						}
					} else {
						sender.sendMessage(Prefixs.Usage + "/server <open|close> <Duels|FFA>");
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("close")) {
					if(args[1].equalsIgnoreCase("FFA")) {
						if(pl.FFA == true) {
							pl.FFA = false;
							Bukkit.broadcastMessage("§3§lFFA: §bThe server FFA is now §cOffline.");
							pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " turned OFF the FFA Server.");
							for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
						} else {
							sender.sendMessage("§CThe server is already close!");
							return true;
						}
					}
					else if(args[1].equalsIgnoreCase("Duels")) {
						if(pl.Duels == true) {
							pl.Duels = false;
							Bukkit.broadcastMessage("§3§lDuels: §bThe server Duels is now §cOffline.");
							pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " turned OFF the Duels Server.");
							for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
						} else {
							sender.sendMessage("§cThe server is already close!");
							return true;
						}
					} else {
						sender.sendMessage(Prefixs.Usage + "/server <open|close> <Duels|FFA>");
						return true;
					}
				} else {
					sender.sendMessage(Prefixs.Usage + "/server <open|close> <Duels|FFA>");
					return true;
				}
			} else {
				sender.sendMessage(Prefixs.Usage + "/server <open|close> <Duels|FFA>");
				return true;
			}
		} else {
			sender.sendMessage(Prefixs.Noperm);
			return true;
		}
		return false;
	}
	
}

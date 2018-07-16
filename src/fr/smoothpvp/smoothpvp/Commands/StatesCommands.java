package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.KDR;
import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StatesCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        KDR kdr = new KDR();
		if(cmd.getName().equalsIgnoreCase("stats")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Prefixs.RCON);
				return true;
			}
			Player p = (Player) sender;
			File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
	        YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
			if(args.length == 0){
				if(!floc.exists()){
					p.sendMessage("§cYour stats are missing in the database!");
					return true;
				}
				p.sendMessage("§8§m-----------§8[§3Stats§8]§m-----------");
		        p.sendMessage("§bKills: §7"+yloc.getInt("Kills"));
		        p.sendMessage("§bDeaths: §7"+yloc.getInt("Deaths"));
		        p.sendMessage("§bMoney: §7"+yloc.getInt("Argent") + "$");
		        p.sendMessage("§bKDR: §7"+kdr.getKDR(p));
		        p.sendMessage("§bKillstreak: §7"+yloc.getInt("Killstreak"));
		        p.sendMessage("§bBest Killstreak: §7"+yloc.getInt("MaxKillstreak"));
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("reset")){
					if(!p.hasPermission("perm.mod")){
						p.sendMessage(Prefixs.Noperm);
						return true;
					}
					if(yloc.getInt("StatsResetCount") >= 1){
						yloc.set("Kills", Integer.valueOf("0"));
						yloc.set("Deaths", Integer.valueOf("0"));
						yloc.set("StatsResetCount", yloc.getInt("StatsResetCount")-1);
						yloc.set("MaxKillstreak", Integer.valueOf("0"));
				    	yloc.set("Killstreak", Integer.valueOf("0"));
						try {
							yloc.save(floc);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage("§bYou have reset your stats.");
						Main.getInstance().sendRconMsg("§3Player Logs §7§l> §b" + sender.getName() + " has reset his stats.");
						return true;
					}
					if(yloc.getInt("StatsResetCount") <= 0){
						p.sendMessage("§cYou must have at least 1 stats reset to reset your stats!");
						return true;
					}
				}
				Player t = Bukkit.getPlayerExact(args[0]);
				if(t != null){
					File floct = new File("plugins//SmoothPVP//Players//" + t.getUniqueId() + ".yml");
					YamlConfiguration yloct = YamlConfiguration.loadConfiguration(floct);
					if(!floct.exists()){
						p.sendMessage("§cThe stats of "+args[0]+" couldn't be found in the database!");
						return true;
					}
					p.sendMessage("§8§m-------§8[§3"+t.getName()+"§3's Stats§8]§m-------");
					p.sendMessage("§bKills: §7"+yloct.getInt("Kills"));
					p.sendMessage("§bDeaths: §7"+yloct.getInt("Deaths"));
					p.sendMessage("§bMoney: §7"+yloct.getInt("Argent") + "$");
					p.sendMessage("§bKDR: §7"+kdr.getKDR(t));
					p.sendMessage("§bKillstreak: §7"+yloct.getInt("Killstreak"));
			        p.sendMessage("§bBest Killstreak: §7"+yloct.getInt("MaxKillstreak"));
				} else {
					p.sendMessage("§c" + args[0] + " is not online!");
					return true;
				}
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("reset")){
					if(p.hasPermission("perm.mod")){
						Player t = Bukkit.getPlayerExact(args[1]);
						if(t == null){
							p.sendMessage("§c" + args[1] + " is not online!");
							return true;
						}
						File floct = new File("plugins//SmoothPVP//Players//" + t.getUniqueId() + ".yml");
						YamlConfiguration yloct = YamlConfiguration.loadConfiguration(floct);
						yloct.set("Kills", Integer.valueOf("0"));
						yloct.set("Deaths", Integer.valueOf("0"));
						yloct.set("MaxKillstreak", Integer.valueOf("0"));
				    	yloct.set("Killstreak", Integer.valueOf("0"));
						try {
							yloct.save(floct);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage("§bYou have reset "+t.getName()+"'s stats.");
						t.sendMessage("§bYour stats have been reset by a staff.");
						Main.getInstance().sendRconMsg("§6Staff Logs §7§l> §e" + p.getName() + " has reset " + t.getName() + "'s stats.");
						return true;
					} else {
						p.sendMessage(Prefixs.Noperm);
						return true;
					}
				}
			}
			if(args.length >= 2 && !args[0].equalsIgnoreCase("reset")){
				p.sendMessage(Prefixs.Usage + "/stats <player>");
				return true;
			}
			if(args.length >= 3 && !args[0].equalsIgnoreCase("reset")){
				p.sendMessage(Prefixs.Usage + "/stats <player>");
				return true;
			}
			if(args.length >= 3 && args[0].equalsIgnoreCase("reset")){
				if(p.hasPermission("perm.mod")){
					p.sendMessage(Prefixs.Usage + "/stats reset <player>");
					return true;
				} else {
					p.sendMessage(Prefixs.Noperm);
					return true;
				}
			}
		}
		return false;
	}
}

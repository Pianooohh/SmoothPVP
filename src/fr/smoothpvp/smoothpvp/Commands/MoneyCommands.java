package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.NotInt;
import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MoneyCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("money")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Prefixs.RCON);
				return true;
			}
			Player p = (Player) sender;
			File floc = new File("plugins//SmoothPVP//Players" + p.getUniqueId() + ".yml");
	        YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
	        NotInt notInt = new NotInt();
			if(args.length == 0){
				if(!p.hasPermission("perm.mod")){
					p.sendMessage("§bMoney: §7"+yloc.getInt("Argent")+"$");
					return true;
				}
				if(p.hasPermission("perm.mod")){
					p.sendMessage("§bMoney: §7"+yloc.getInt("Argent")+"$");
					p.sendMessage("§7To set/add money to aplayer, use: §b/money add/set <player> <value>.");
					return true;
				}
			}
			if(!p.hasPermission("perm.mod")){
				p.sendMessage(Prefixs.Noperm);
				return true;
			}
			if(args.length <= 2){
				p.sendMessage(Prefixs.Usage + "/money add/set <player> <value>.");
				return true;
			}
			if(args.length == 3){
				if(args[0].equalsIgnoreCase("add")){
					Player t = Bukkit.getPlayerExact(args[1]);
					if(t == null){
						p.sendMessage("§c" + args[1] + " is not online!");
						return true;
					}
					if(!notInt.isInt(args[2])){
						p.sendMessage("§cPlease, use only numbers!");
						return true;
					}
					File floct = new File("plugins//SmoothPVP//Players" + Bukkit.getPlayer(args[1]).getUniqueId() + ".yml");
					YamlConfiguration yloct = YamlConfiguration.loadConfiguration(floct);
					int integer = Integer.parseInt(args[2]);
					yloct.set("Argent", yloct.getInt("Argent") + integer);
					try {
						yloct.save(floct);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage("§bYou have add "+integer+"$ to "+t.getName()+".");
					t.sendMessage("§bA staff add you "+integer+"$ in your stats.");
				}
				if(args[0].equalsIgnoreCase("set")){
					Player t = Bukkit.getPlayerExact(args[1]);
					if(t == null){
						p.sendMessage("§c" + args[1] + " is not online!");
						return true;
					}
					if(!notInt.isInt(args[2])){
						p.sendMessage("§cPlease, use only numbers!");
						return true;
					}
					File floct = new File("plugins//SmoothPVP//Players" + Bukkit.getPlayer(args[1]).getUniqueId() + ".yml");
					YamlConfiguration yloct = YamlConfiguration.loadConfiguration(floct);
					int integer = Integer.parseInt(args[2]);
					yloct.set("Argent", integer);
					try {
						yloct.save(floct);
					} catch (IOException e) {
						e.printStackTrace();
					}
					p.sendMessage("§bYou have add "+integer+"$ to "+t.getName()+".");
					t.sendMessage("§bA staff add you "+integer+"$ in your stats.");
				}
			}
			if(args.length >= 4) {
				if(args[0].equalsIgnoreCase("add")){
					p.sendMessage(Prefixs.Usage + "/money add/set <player> <value>.");
					return true;
				}
				if(args[0].equalsIgnoreCase("set")){
					p.sendMessage(Prefixs.Usage + "/money add/set <player> <value>.");
					return true;
				}
			}
		}
		return false;
	}
}

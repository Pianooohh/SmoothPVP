package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.entity.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ArenaCmds implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Prefixs.RCON);
			return true;
		}
		Player p = (Player) sender;
		if(!p.hasPermission("perm.admin")) {
			p.sendMessage(Prefixs.Noperm);
			return true;
		}
		if(args.length > 2) {
			p.sendMessage(Prefixs.Usage + "/arenas create/setspawn1/setspawn2/remove <name>");
			return true;
		}
		if(args.length <= 1) {
			p.sendMessage(Prefixs.Usage + "/arenas create/setspawn1/setspawn2/remove <name>");
			return true;
		}
		File file = new File("plugins//SmoothPVP//Arenas//" + args[1] + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(file);
		int spawnNb = 1;
		if(args[0].startsWith("setspawn")) {
			spawnNb = Integer.valueOf(args[0].replabce("setspawn", ""));
			args[0] = "setspawn"; //Switch dosent work like ifs, so we cant just make the check at the case statement.
		}
		
		switch(args[0]) {
		case "create": 
			if(Arena.findArena(args[1]) != null) {
				Arena.createArena(args[1]);
				p.sendMessage("§bThe arena " + args[1] + " has been successfully created.");
			}else {
				p.sendMessage("§cThe arena " + args[1] + " already exist!");
				return true;
			} 
			return false;
			
			
		
		case "remove":
			if(!Arena.removeArena(args[1])) {
				p.sendMessage("§cThe arena " + args[1] + " doesn't exist!");
				return true;
			} else {
				p.sendMessage("§bThe arena " + args[1] + " has been successfully removed.");
			}
			return false;
			
			
		case "setspawn":
			Arena arena = Arena.findArena(args[1]);
			if(arena != null) {
				arena.setSpawnPoint(p.getLocation(), spawnNb); //FIXME: Player's pitch will be used for tping to this spawnpoint until Arena cache flush.
				p.sendMessage("§3Spawn 1 has been set to: §7X: " + p.getLocation().getX() + " Y: " + p.getLocation().getY() + 1 + " Z: " + p.getLocation().getZ() + ".");
				return false;
			} else {
				p.sendMessage("§cThe arena " + args[1] + " doesn't exist!");
			}
			return true;
			
		default:
			p.sendMessage("Error: Unknown command. type help for help.");
		}
		return true;
	}
	
	
}

package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.PlayerWrapper;
import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class SeeCmd implements CommandExecutor {
	
	public static HashMap<Player, PlayerWrapper> verifiers = new HashMap<Player, PlayerWrapper>();
	public String perm;
	
	public SeeCmd(String perm) {
		this.perm = perm;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Prefixs.RCON);
			return true;
		}
		if(!sender.hasPermission("perm.mod")) {
			sender.sendMessage(Prefixs.Noperm);
			return true;
		}
		if(!Main.getInstance().ismod.contains(sender)) {
			sender.sendMessage(Prefixs.NOMOD);
			return true;
		}
		if(args.length != 1) {
			sender.sendMessage(Prefixs.Usage + "/see <player>");
			return true;
		}
		String target = args[0];
		if(Bukkit.getPlayer(target) != null) {
			Inventory i = Bukkit.createInventory(null, 54, "§3See: §8" + target);
			verifiers.put((Player)sender, PlayerWrapper.getByPlayer(Bukkit.getPlayer(target)));
			((Player) sender).openInventory(i);
			Main.getInstance().sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " perform /see command on " + Bukkit.getPlayer(target).getName() + ".");
		} else {
			sender.sendMessage("§c" + target + " is not online!");
			return true;
		}
		return false;
		
	}

}

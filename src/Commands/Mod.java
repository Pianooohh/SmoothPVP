package Commands;

import Main.Main;
import Utils.InvUtils;
import Utils.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mod implements CommandExecutor {

	private Main plugin;
	public Mod(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("perm.mod")) {
				if (!plugin.ismod.contains(p)) {
					plugin.ismod.add(p);
					p.sendMessage("�3You switch to moderator mod.");
					InvUtils.giveModItems(p);
					p.setGameMode(GameMode.CREATIVE);
					plugin.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " switch into moderator mod.");
					for (Player all : Bukkit.getOnlinePlayers()) {
						all.hidePlayer(p);
					}
					return true;
				} else {
					plugin.ismod.remove(p);
					p.teleport(p.getWorld().getSpawnLocation());
					p.sendMessage("�3You switch to player mod.");
					InvUtils.giveSpawnItems(p);
					p.setGameMode(GameMode.SURVIVAL);
					plugin.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " switch into player mod.");
					for (Player all : Bukkit.getOnlinePlayers()) {
						all.showPlayer(p);
					}
				}
			} else {
				p.sendMessage(Prefixs.Noperm);
				return true;
			}
		} else {
			sender.sendMessage(Prefixs.RCON);
			return true;
		}
		return false;

	}
}

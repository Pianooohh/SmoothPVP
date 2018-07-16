package Commands;

import Main.Main;
import Utils.PlayerWrapper;
import Utils.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class PlayerInfos implements CommandExecutor {

	private Main pl;
	public PlayerInfos(Main pl) {
		this.pl = pl;
	}
	public static HashMap<Player, PlayerWrapper> players = new HashMap<Player, PlayerWrapper>();
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("perm.mod")) {
				if(pl.ismod.contains(p)) {
					if(args.length == 1) {
						if(!pl.ismod.contains(p)) { p.sendMessage("�cYou must be in moderator mod!"); return true; }
						String name = args[0];
						if(Bukkit.getPlayer(name) != null) {
							Inventory inv = Bukkit.createInventory(null, 54, "�3Infos: �8" + Bukkit.getPlayer(name).getDisplayName());
							players.put(p, PlayerWrapper.getByPlayer(Bukkit.getPlayer(name)));
							p.openInventory(inv);
							pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " perform /pinfos command on " + Bukkit.getPlayer(name).getName() + ".");
						} else {
							p.sendMessage("�c" + name + " is not online!");
							return true;
						}
					} else {
						p.sendMessage(Prefixs.Usage + "/pinfos <player>");
						return true;
					}
				} else {
					p.sendMessage(Prefixs.NOMOD);
					return true;
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

package Commands;

import Main.Main;
import Utils.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
	
	private Main pl;
	public Gamemode(Main pl) {
		this.pl = pl;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(msg.equalsIgnoreCase("gm") || msg.equalsIgnoreCase("gamemode")) {
			if(sender.hasPermission("perm.mod")) {
				if(args.length == 1) {
					Player p = (Player) sender;
					if(args[0].equalsIgnoreCase("0")) {
						p.setGameMode(GameMode.SURVIVAL);
						pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " turn into Survival mode.");
						if(p.isOp() && p.getAllowFlight() == false) {
							p.setAllowFlight(true);
						}
					} else if(args[0].equalsIgnoreCase("1")) {
						p.setGameMode(GameMode.CREATIVE);
						pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " turn into Creative mode.");
					} else if(args[0].equalsIgnoreCase("2")) {
						pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " turn into Adventure mode.");
						p.setGameMode(GameMode.ADVENTURE);
					}
					return true;
				}
				if(args.length == 2) {
					Player target = Bukkit.getPlayer(args[1]);
					if(target != null) {
						if(args[0].equalsIgnoreCase("0")){
							target.setGameMode(GameMode.SURVIVAL);
							pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " turn " + target.getName() + " into Survival mode.");
						} else if(args[0].equalsIgnoreCase("1")){
							target.setGameMode(GameMode.CREATIVE);
							pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " turn " + target.getName() + " into Creative mode.");
						} else if(args[0].equalsIgnoreCase("2")){
							pl.sendRconMsg("�6Staff Logs �7�l> �e" + sender.getName() + " turn " + target.getName() + " into Adventure mode.");
							target.setGameMode(GameMode.ADVENTURE);
						}
						return true;
					} else {
						sender.sendMessage("�c" + args[1] + " can not be found!");
						return true;
					}
				} else {
					sender.sendMessage(Prefixs.Usage + "/(gm)gamemode <0-1-2> <player>");
					return true;
				}
			} else {
				sender.sendMessage(Prefixs.Noperm);
				return true;
			}
		}
		return false;
		
	}

}

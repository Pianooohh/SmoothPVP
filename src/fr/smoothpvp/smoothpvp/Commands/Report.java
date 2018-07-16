package fr.smoothpvp.smoothpvp.Commands;

import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Report implements CommandExecutor {

	private Main pl;
	Date now = new Date();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private HashMap<Player, Integer> cooldowntime = new HashMap<Player, Integer>();
	private HashMap<Player, BukkitRunnable> cooldowntask = new HashMap<Player, BukkitRunnable>();

	public Report(Main pl) {
		this.pl = pl;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length <= 1) {
				p.sendMessage(Prefixs.Usage + "/report <player> <reason>");
				return true;
			}
			if (cooldowntime.containsKey(p)) {
				p.sendMessage("§cYou must wait " + cooldowntime.get(p) + " seconds before sending a new report!");
				return true;
			}
			String t = args[0];
			if (Bukkit.getPlayer(t) != null) {
				for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
					if (staff.hasPermission("perm.mod")) {
						staff.sendMessage("§8§m-------------§8[§3Report§8]§8§m---------------");
						staff.sendMessage("§bSender: §7" + p.getName());
						staff.sendMessage("§bSuspect: §7" + t);
						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++) {
							sb.append(args[i]);
							if (i < args.length) {
								sb.append(" ");
							}
						}
						staff.sendMessage("§bReason: §7" + sb.toString());
						staff.playSound(staff.getLocation(), Sound.CLICK, 10, 10);
						pl.sendRconMsg("§6Staff Logs §7§l> §e" + sender.getName() + " has reported " + t + " for: §7" + sb.toString() + ".");
					}
				}
				cooldowntime.put(p, 60);
				cooldowntask.put(p, new BukkitRunnable() {
					public void run() {
						cooldowntime.put(p, cooldowntime.get(p) - 1);
						if (cooldowntime.get(p) == 0) {
							cooldowntime.remove(p);
							cooldowntask.remove(p);
							cancel();
						}
					}
				});
				cooldowntask.get(p).runTaskTimer(pl, 20, 20);
			} else {
				p.sendMessage("§c" + args[0] + " is not online!");
				return true;
			}
		} else {
			sender.sendMessage(Prefixs.RCON);
			return true;
		}
		return false;
	}
}

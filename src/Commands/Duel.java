package Commands;

import Main.Main;
import Utils.Prefixs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Duel implements CommandExecutor {
	
	private HashMap<Player, Integer> cooldowntime = new HashMap<Player, Integer>();
	private HashMap<Player, BukkitRunnable> cooldowntask = new HashMap<Player, BukkitRunnable>();
	
	Player player1;
	Player player2;
	
	private Main pl;
	public Duel(Main pl) {
		this.pl = pl;
	}
	
	public Duel(Player player1, Player player2) {
	    this.player1 = player1;
	    this.player2 = player2;
	}
	
	public Player getPlayer1() {
	    return player1;
	}
	
	public void setPlayer1(Player player1) {
	    this.player1 = player1;
	}
	
	public Player getPlayer2() {
	    return player2;
	}
	
	public void setPlayer2(Player player2) {
	    this.player2 = player2;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("�cYou can't do that command!");
			return true;
		}
		Player p = (Player) sender;
		if(cooldowntime.containsKey(p)) {
			sender.sendMessage("�CYou must wait " + cooldowntime.get(p) + "s before sending a new request!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("duel")) {
			if(args.length == 1) {
				String name = args[0];
				Player target = Bukkit.getPlayerExact(name);
				if(!pl.induel.contains(p)) {
					if(target != null) {
						if(!pl.induel.contains(target)){
							if(!pl.duelreq.containsKey(target)) {
								setPlayer1(p);
								setPlayer2(Bukkit.getPlayer(name));
								pl.duelreq.put(target, p);
								p.sendMessage("�bDuel request send to " + name);
								System.out.println("fdfd " + p + target + getPlayer1() + getPlayer2());
								target.sendMessage("�b" + p.getDisplayName() + " request to duel you in 1v1.\nType /accept to accept the duel.");
								cooldowntime.put(p, 10);
								cooldowntask.put(p, new BukkitRunnable(){
									public void run() {
										cooldowntime.put(p, cooldowntime.get(p) -1);
										if(cooldowntime.get(p) == 0) {
											cooldowntime.remove(p);
											cooldowntask.remove(p);
											cancel();
										}
									}
								});
								cooldowntask.get(p).runTaskTimer(pl, 20, 20);
								return true;
							} else {
								p.sendMessage("�c" + name + " has already a duel request!");
								return true;
							}
						} else {
							p.sendMessage("�c" + name + " is already in a duel!");
							return true;
						}
					} else {
						p.sendMessage("�c" + name + " is not online!");
						return true;
					}
				} else {
					p.sendMessage("�cYou are already in a duel!");
					return true;
				}
			} else {
				sender.sendMessage(Prefixs.Usage + "/duel <player>");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("accept")) {
			if(args.length == 0) {
				if(pl.duelreq.containsKey(p)) {
					Player player1 = getPlayer1();
					Player player2 = getPlayer2();
					System.out.println("debug" + player1 + player2);
					pl.duelreq.remove(pl.duelreq.get(p));
				} else {
					p.sendMessage("nn");
					return true;
				}
			}
		}
		return false;
	}

}

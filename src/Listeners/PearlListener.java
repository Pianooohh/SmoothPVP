package Listeners;

import Main.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PearlListener implements Listener {
	
	private Main pl;
	public PearlListener(Main pl) {
		this.pl = pl;
	}
	private HashMap<Player, Integer> cooldowntime = new HashMap<Player, Integer>();
	private HashMap<Player, BukkitRunnable> cooldowntask = new HashMap<Player, BukkitRunnable>();

	@EventHandler
	public void pearlInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.AIR) return;
			if (p.getItemInHand().getType() == Material.ENDER_PEARL && p.getGameMode() != GameMode.CREATIVE) {
				if(cooldowntime.containsKey(p)) { p.sendMessage("�cYou need to wait " + cooldowntime.get(p) + " seconds before using an Enderpearl!"); e.setCancelled(true); p.updateInventory(); return; }
				cooldowntime.put(p, 20);
				p.setLevel(0);
				cooldowntask.put(p, new BukkitRunnable(){
					public void run() {
						cooldowntime.put(p, cooldowntime.get(p) -1);
						p.setLevel(0);
						p.setLevel(cooldowntime.get(p));
						if(cooldowntime.get(p) == 0) {
							cooldowntime.remove(p);
							cooldowntask.remove(p);
							p.setLevel(0);
							p.sendMessage("�bYou can use an Enderpearl again.");
							cancel();
						}
					}
				});
				cooldowntask.get(p).runTaskTimer(pl, 20, 20);
			}
		}
	}
}

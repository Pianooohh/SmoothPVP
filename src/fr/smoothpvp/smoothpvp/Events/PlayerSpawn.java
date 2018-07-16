package fr.smoothpvp.smoothpvp.Events;

import fr.smoothpvp.smoothpvp.Utils.InvUtils;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerSpawn implements Listener {
	
	private Main pl;
	public PlayerSpawn(Main pl) {
		this.pl = pl;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSpawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if(p.isOp() && p.getAllowFlight() == false) { 
			p.setAllowFlight(true);
		}
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.setHealthScale(20);
		p.getInventory().setBoots(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setHelmet(null);
		p.getInventory().setLeggings(null);
		if(pl.Duels == true) {
		}
		else {
		}
		InvUtils.giveSpawnItems(p);
		for(Player all : pl.ismod) {
			p.hidePlayer(all);
		}
	}

}

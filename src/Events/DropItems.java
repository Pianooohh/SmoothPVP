package Events;

import Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class DropItems implements Listener {
	
	Main plugin;
	
	public DropItems(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "�3Ranked 1v1") { e.setCancelled(true); }
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "�bUnranked 1v1") { e.setCancelled(true); }
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "�eSwitch to moderator mod") { e.setCancelled(true); }
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "�aWarps") { e.setCancelled(true); }
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "�6Edit Kit") { e.setCancelled(true); }
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == "�cRules") { e.setCancelled(true); }
		if(plugin.ismod.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();
		if(plugin.ismod.contains(p)) {
			e.setCancelled(true);
		}
	}

}

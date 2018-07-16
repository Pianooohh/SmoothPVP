package Events;

import Main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockBreak implements Listener {
	
	private Main plugin;
	public BlockBreak(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onIgnite(BlockIgniteEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(plugin.build == false) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(plugin.build == false) {
			e.setCancelled(true);
		}
	}

}

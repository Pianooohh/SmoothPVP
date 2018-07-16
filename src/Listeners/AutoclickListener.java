package Listeners;

import Utils.PlayerWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AutoclickListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(PlayerInteractEvent e){
		if (e.getAction() == Action.LEFT_CLICK_AIR){
			Player player = e.getPlayer();
			PlayerWrapper wp = PlayerWrapper.getByPlayer(player);
			if ((player.getTargetBlock(null, 100).getLocation().distance(player.getLocation()) < 6.0D) && (wp.lastBlockInteraction > System.currentTimeMillis()) && (wp.clicks >= 10)) {
				e.setCancelled(true);
			}
			if (wp.clicks > wp.maxClicks) {
				wp.maxClicks = wp.clicks;
			}
			wp.clicks += 1;
		} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Player player = e.getPlayer();
			PlayerWrapper wp = PlayerWrapper.getByPlayer(player);
			wp.lastBlockInteraction = (System.currentTimeMillis() + 5000L);
		}
	}
}
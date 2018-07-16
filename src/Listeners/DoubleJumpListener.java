package Listeners;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJumpListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFlightAttempt(PlayerToggleFlightEvent event) {
		 Player p = event.getPlayer();  
		 if(p.isOp() && p.getGameMode() != GameMode.CREATIVE) {
	         p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 10, -10);
	         event.setCancelled(true);
	         Vector v = p.getLocation().getDirection().multiply(1.2).setY(1.1);
	         p.playEffect(p.getLocation(), Effect.SMOKE, 0);
	         p.setVelocity(v);
		 }
	}
}

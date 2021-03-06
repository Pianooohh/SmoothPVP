package fr.smoothpvp.smoothpvp.Events;

import fr.smoothpvp.smoothpvp.Utils.PlayerUtils;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {
	
	@EventHandler
	public void onFoodLevel(FoodLevelChangeEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			PlayerUtils pu = new PlayerUtils(Main.getInstance());
			if(pu.isInMatch(p) == false) {
				e.setCancelled(true);
			}
		}
	}
}

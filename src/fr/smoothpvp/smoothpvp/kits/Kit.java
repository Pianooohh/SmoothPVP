package fr.smoothpvp.smoothpvp.kits;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public interface Kit {
	String name;
	private Set<? extends Kit> kits = Collections.newSetFromMap(new HashMap<E implements Kit, Boolean>()) //Putain j'arrive pas
			
	/**
	 * Apply the kit to {@code player}. The proper way to reset it is to use {@link #reset(Player)}.
	 * @param player
	 */
	void supply(Player player);
	
	/**
	 * Reset the player of all things which are applied by {@link #supply(Player)}.
	 * @param player The player to reset.
	 */
	default void reset(Player player) {
		player.getInventory().clear();
	}
	
	default String getName() {
		return name; 
	}
}

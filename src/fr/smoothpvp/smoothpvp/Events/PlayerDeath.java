package fr.smoothpvp.smoothpvp.Events;

import fr.smoothpvp.smoothpvp.main.Main;
import net.minecraft.server.v1_7_R4.EnumClientCommand;
import net.minecraft.server.v1_7_R4.PacketPlayInClientCommand;
import org.bukkit.Effect;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class PlayerDeath implements Listener {

	private Main plugin;

	public PlayerDeath(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void RespawnScreen(PlayerDeathEvent e) {
		final Player p = e.getEntity().getPlayer();
		final Player k = e.getEntity().getKiller();
		e.setDeathMessage(null);
		if (plugin.isranked.contains(p) || plugin.isunranked.contains(p)) return;
		if (k instanceof Player) {
			p.sendMessage("§7You have been killed by §b" + e.getEntity().getKiller().getDisplayName());
			p.sendMessage("§7You lose §c$20.");
			k.sendMessage("§7You have killed §b" + e.getEntity().getDisplayName());
			k.sendMessage("§7You win §a$20.");
			File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
			YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
			if(floc.exists()) {
				yloc.set("Argent", yloc.getInt("Argent") - 20);
	        	yloc.set("Deaths", yloc.getInt("Deaths") + 1);
	        	yloc.set("MaxKillstreak", yloc.getInt("Killstreak"));
	        	yloc.set("Killstreak", Integer.valueOf("0"));
				try {
					yloc.save(floc);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			File floc2 = new File("plugins//SmoothPVP//Players//" + k.getUniqueId() + ".yml");
			YamlConfiguration yloc2 = YamlConfiguration.loadConfiguration(floc2);
			if(floc2.exists()) {
				yloc2.set("Argent", yloc2.getInt("Argent") + 20);
	        	yloc2.set("Kills", yloc2.getInt("Kills") + 1);
	        	yloc2.set("Killstreak", yloc2.getInt("Killstreak") + 1);
				try {
					yloc2.save(floc2);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			if (plugin.ismod.contains(p)) {
				e.setDroppedExp(0);
				e.getDrops().clear();
			}
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					if (p.isDead()) {
						((CraftPlayer) p).getHandle().playerConnection
								.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
					}
				}
			});
		}
	}

	@EventHandler
	public void dropDeath(final ItemSpawnEvent e) {
		new BukkitRunnable() {
			public void run() {
				if (e.getEntity().isDead())
					return;
				e.getEntity().remove();
				e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SMOKE, 0);
			}
		}.runTaskLater(plugin, 100L);

	}
}

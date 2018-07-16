package Events;

import Main.Main;
import Utils.InvUtils;
import Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerMove implements Listener {
	
	private List<Player> isfallingffa = new ArrayList<Player>();
	
	private Main pl;
	public PlayerMove(Main pl) {
		this.pl = pl;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMoove(PlayerMoveEvent e) throws IOException {
		if(pl.isfreeze.contains(e.getPlayer())) {
			e.getPlayer().teleport(e.getPlayer().getLocation());
		}
		if(e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.CARPET || e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.CARPET) {
			File floc = new File("plugins//SmoothPVP//Players//" + e.getPlayer().getUniqueId() + ".yml");
			YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
			InvUtils invu = new InvUtils();
			PlayerUtils pu = new PlayerUtils(pl);
			Player p = e.getPlayer();
			if(!isfallingffa.contains(p)) { isfallingffa.add(p); }
			for(Player players : Bukkit.getOnlinePlayers()) {
				p.showPlayer(players);
				if(pl.ismod.contains(players)) {
					p.hidePlayer(players);
				}
			}
			if(yloc.getString("KitNoDebuff.content").equals("none")) {
				invu.giveDefaultNodebuff(e.getPlayer());
			} else {
				pu.getKit(e.getPlayer(), "KitNoDebuff");
				return;
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
	        if(isfallingffa.contains(player)) {
	        	if (e.getCause() == DamageCause.FALL){
		            e.setCancelled(true);
		            isfallingffa.remove(player);
		            return;
	        	}
	        }
		}
	}

}

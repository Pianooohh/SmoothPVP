package Utils;

import Main.Main;
import entity.Arena;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerUtils {
	
	public String ismodr;
	public String freeze;
	public String statue;
	public String world;
	public String path = "plugins//SmoothPVP//Players//";
	public boolean isinmatch = false;
	
	private Main pl;
	public PlayerUtils(Main pl) {
		this.pl = pl;
	}
	
	public String getModStatue(Player p) {
		if(pl.ismod.contains(p)) { ismodr = "�aYes."; }
		else { ismodr = "�cNo."; }
		return ismodr;
	}
	
	public String getFreezeStatue(Player p) {
		if(Main.getInstance().isfreeze.contains(p)) { freeze = "�aYes."; }
		else { freeze = "�cNo."; }
		return freeze;
	}
	
	public String getStatue(Player p) {
		if(pl.isranked.contains(p) || pl.isunranked.contains(p)) { statue = "�aYes."; }
		else { statue = "�cNo."; }
		return statue;
	}
	
	public String getWorld(Player p) {
		this.world = p.getWorld().getName();
		return world;
	}
	
	@SuppressWarnings("unchecked")
	public void getKit(Player p, String kit) throws IOException {
		File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
		if(!yloc.getString(kit).equals("none")) {
	        ItemStack[] content = ((List<ItemStack>) yloc.get(kit + ".armor")).toArray(new ItemStack[0]);
	        p.getInventory().setArmorContents(content);
	        content = ((List<ItemStack>) yloc.get(kit + ".content")).toArray(new ItemStack[0]);
	        p.getInventory().setContents(content);
		}
    }
	
	public boolean isInMatch(Player p) {
		return isinmatch;
	}
	
	public void setInMatch(Player p, Player p2, boolean valeur, String type) {
		isinmatch = valeur;
		if(type.equals("NoDebuff")) { pl.nodebuff.put(p, p2); }
		if(type.equals("Debuff")) { pl.debuff.put(p, p2); }
		if(type.equals("Combo")) { pl.combo.put(p, p2); }
		if(type.equals("Soup")) { pl.soup.put(p, p2); }
		if(type.equals("HungerGames")) { pl.hg.put(p, p2); }
		if(type.equals("Archer")) { pl.arrow.put(p, p2); }
	}
	public void teleportPlayersMatch(Player p, Player p2) {

		Arena randomArena = Arena.getArenas()[ThreadLocalRandom.current ().nextInt(0, Arena.getArenas().length+ 1)];
		int random1 = 1;
		int random2 = 2;
		if(randomArena.getSpawnPoints().size() >2 ) {
			do {
				random1= ThreadLocalRandom.current().nextInt(0, randomArena.getSpawnPoints().size() + 1);
				random2= ThreadLocalRandom.current().nextInt(0, randomArena.getSpawnPoints().size() + 1);
			//While both random are equal (this happen A LOT when there is a low number of spawn points, repick a random (expect if there is only one spawnpoint BTW))
			} while(random1 == random2 && (randomArena.getSpawnPoints().size() > 1));
		}

        p.teleport(randomArena.getSpawnPoints().get(random1));
        p2.teleport(randomArena.getSpawnPoints().get(random2));
    }
	public void saveKit(Player p, String kit) throws IOException {
		File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
		YamlConfiguration c = YamlConfiguration.loadConfiguration(floc);
		c.set(kit + ".armor", p.getInventory().getArmorContents());
		c.set(kit + ".content", p.getInventory().getContents());
		c.save(floc);
		p.sendMessage("�bYour kit has been saved.");
		p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
		p.closeInventory();
	}
}

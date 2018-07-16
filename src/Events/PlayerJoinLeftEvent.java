package Events;

import Commands.PlayerInfos;
import Commands.SeeCmd;
import Main.Main;
import Utils.InvUtils;
import Utils.PlayerUtils;
import Utils.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerJoinLeftEvent implements Listener {

	private Main plugin;
	private Date now = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private ItemInteract it;

	public PlayerJoinLeftEvent(Main plugin, ItemInteract it) {
		this.plugin = plugin;
		this.it = it;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		plugin.sendRconMsg("�3Players Log �7�l> �b" + p.getName() + " joined: (Date: " + format.format(now) + " IP: "
				+ p.getAddress().getHostString() + ")");
		new PlayerWrapper(p);
		if (SeeCmd.verifiers.containsKey(p)) {
			SeeCmd.verifiers.remove(p);
		}
		if (PlayerInfos.players.containsKey(p)) {
			PlayerInfos.players.remove(p);
		}
		if (it.opener.contains(p)) {
			it.opener.remove(p);
		}
		if (p.isOp() && p.getAllowFlight() == false) {
			p.setAllowFlight(true);
		}
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.setHealthScale(20);
		p.setFoodLevel(20);
		p.getInventory().setBoots(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setHelmet(null);
		p.getInventory().setLeggings(null);
		p.teleport(Bukkit.getWorld("world").getSpawnLocation());
		p.removePotionEffect(PotionEffectType.SPEED);

		File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
		if (!floc.exists()) {
			try {
				floc.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			yloc.set("Argent", Integer.valueOf("50"));
			yloc.set("Kills", Integer.valueOf("0"));
			yloc.set("Deaths", Integer.valueOf("0"));
			yloc.set("StatsResetCount", Integer.valueOf("1"));
			yloc.set("Killstreak", Integer.valueOf("0"));
			yloc.set("MaxKillstreak", Integer.valueOf("0"));
			
			yloc.set("KitNoDebuff", String.valueOf("none"));
			yloc.set("KitDebuff", String.valueOf("none"));
			yloc.set("KitSoup", String.valueOf("none"));
			yloc.set("KitCombo", String.valueOf("none"));
			yloc.set("KitHG", String.valueOf("none"));
			yloc.set("KitArcher", String.valueOf("none"));
			
			yloc.set("KitNoDebuff.content", String.valueOf("none"));
			yloc.set("KitDebuff.content", String.valueOf("none"));
			yloc.set("KitSoup.content", String.valueOf("none"));
			yloc.set("KitCombo.content", String.valueOf("none"));
			yloc.set("KitHG.content", String.valueOf("none"));
			yloc.set("KitArcher.content", String.valueOf("none"));
			
			yloc.set("KitNoDebuff.armor", String.valueOf("none"));
			yloc.set("KitDebuff.armor", String.valueOf("none"));
			yloc.set("KitSoup.armor", String.valueOf("none"));
			yloc.set("KitCombo.armor", String.valueOf("none"));
			yloc.set("KitHG.armor", String.valueOf("none"));
			yloc.set("KitArcher.armor", String.valueOf("none"));
			if(yloc.get("IP") != p.getAddress().getHostString()) {
				yloc.set("IP", p.getAddress().getHostString());
			}
			try {
				yloc.save(floc);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (players.hasPermission("perm.mod")) {
				players.sendMessage(
						"�3Players Log: �b" + p.getName() + " joined: (IP: " + p.getAddress().getHostString() + ")");
			}
		}
		InvUtils.giveSpawnItems(p);
		for (Player all : plugin.ismod) {
			p.hidePlayer(all);
		}
		if (plugin.ismod.contains(p)) {
			plugin.ismod.remove(p);
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.showPlayer(p);
			}
		}

	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLeft(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		PlayerUtils utils = new PlayerUtils(Main.getInstance());
		plugin.sendRconMsg("�3Players Log �7�l> �b" + p.getName() + " left: (Date: " + format.format(now) + " IP: "
				+ p.getAddress().getHostString() + ")");
		if (SeeCmd.verifiers.containsKey(p)) {
			SeeCmd.verifiers.remove(p);
		}
		if (PlayerInfos.players.containsKey(p)) {
			PlayerInfos.players.remove(p);
		}
		if (it.opener.contains(p)) {
			it.opener.remove(p);
		}
		PlayerWrapper.removePlayer(p);
		File floc = new File(utils.path + p.getUniqueId() + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
		if(floc.exists()) {
			yloc.set("Killstreak", Integer.valueOf("0"));
			yloc.set("MaxKillstreak", Integer.valueOf("0"));
			if(yloc.get("IP") != p.getAddress().getHostString()) {
				yloc.set("IP", p.getAddress().getHostString());
			}
			try {
				yloc.save(floc);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		p.getInventory().clear();
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (players.hasPermission("perm.mod")) {
				players.sendMessage("�3Players Log: �b" + p.getName() + " left: (IP: " + p.getAddress().getHostString() + ")");
			}
		}
		if (plugin.ismod.contains(p)) {
			plugin.ismod.remove(p);
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.showPlayer(p);
			}
		}
	}

}

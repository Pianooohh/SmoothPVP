package Managers;

import Main.Main;
import Utils.InvUtils;
import Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuelsManager {
	
	private Map<List<Player>, Integer> cooldowntime = new HashMap<>();
	private Map<List<Player>, BukkitRunnable> cooldowntask = new HashMap<>();
	
	private Main pl;
	public DuelsManager(Main pl) {
		this.pl = pl;
	}
	@SuppressWarnings("deprecation")
	public void putInDuel(Player p, Player player2, String name) throws IOException {
		InvUtils invu = new InvUtils();
		PlayerUtils pu = new PlayerUtils(pl);
		File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
		File floc2 = new File("plugins//SmoothPVP//Players//" + player2.getUniqueId() + ".yml");
		YamlConfiguration yloc2 = YamlConfiguration.loadConfiguration(floc2);
		if(name.equals("NoDebuff")) {
			pl.qnodebuff.remove(p);
			pl.qnodebuff.remove(player2);
			p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked NoDebuff.");
			player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked NoDebuff.");
			if(yloc.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(p);
				 p.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(p, "KitNoDebuff");
			 }
			 if(yloc2.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(player2);
				 player2.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(player2, "KitNoDebuff");
			 }
			 pu.setInMatch(p, player2, true, "NoDebuff");
		}
		if(name.equals("Debuff")) {
			pl.qdebuff.remove(p);
			pl.qdebuff.remove(player2);
			p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked Debuff.");
			player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked Debuff.");
			if(yloc.getString("KitDebuff.content").equals("none")) {
				 invu.giveDefaultDebuff(p);
				 p.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(p, "KitDebuff");
			 }
			 if(yloc2.getString("KitDebuff.content").equals("none")) {
				 invu.giveDefaultDebuff(player2);
				 player2.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(player2, "KitDebuff");
			 }
			 pu.setInMatch(p, player2, true, "Debuff");
		}
		if(name.equals("Combo")) {
			pl.qcombo.remove(p);
			pl.qcombo.remove(player2);
			p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked Combo.");
			player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked Combo.");
			if(yloc.getString("KitCombo.content").equals("none")) {
				 invu.giveDefaultCombo(p);
				 p.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(p, "KitCombo");
			 }
			 if(yloc2.getString("KitCombo.content").equals("none")) {
				 invu.giveDefaultCombo(player2);
				 player2.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(player2, "KitCombo");
			 }
			 pu.setInMatch(p, player2, true, "Combo");
		}
		if(name.equals("Soup")) {
			pl.qsoup.remove(p);
			pl.qsoup.remove(player2);
			p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked Soup.");
			player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked NoDebuff.");
			if(yloc.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(p);
				 p.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(p, "KitNoDebuff");
			 }
			 if(yloc2.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(player2);
				 player2.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(player2, "KitNoDebuff");
			 }
			 pu.setInMatch(p, player2, true, "NoDebuff");
		}
		if(name.equals("Archer")) {
			pl.qnodebuff.remove(p);
			pl.qnodebuff.remove(player2);
			p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked NoDebuff.");
			player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked NoDebuff.");
			if(yloc.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(p);
				 p.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(p, "KitNoDebuff");
			 }
			 if(yloc2.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(player2);
				 player2.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(player2, "KitNoDebuff");
			 }
			 pu.setInMatch(p, player2, true, "NoDebuff");
		}
		if(name.equals("HungerGames")) {
			pl.qnodebuff.remove(p);
			pl.qnodebuff.remove(player2);
			p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked NoDebuff.");
			player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked NoDebuff.");
			if(yloc.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(p);
				 p.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(p, "KitNoDebuff");
			 }
			 if(yloc2.getString("KitNoDebuff.content").equals("none")) {
				 invu.giveDefaultNodebuff(player2);
				 player2.sendMessage("�7You don't have custom kit, you receive the default kit.");
			 } else {
				 pu.getKit(player2, "KitNoDebuff");
			 }
			 pu.setInMatch(p, player2, true, "NoDebuff");
		}
		for(Player all : Bukkit.getOnlinePlayers()) {
			p.hidePlayer(all);
			player2.hidePlayer(all);
			p.showPlayer(player2);
			player2.showPlayer(p);
		}
		cooldowntime.put(Arrays.asList(p, player2), 4);
		cooldowntask.put(Arrays.asList(p, player2), new BukkitRunnable() {
			public void run() {
				cooldowntime.put(Arrays.asList(p, player2), cooldowntime.get(Arrays.asList(p, player2)) - 1);
				if (cooldowntime.get(Arrays.asList(p, player2)) != 0) {
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
					player2.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
					p.sendMessage(
							"�bThe match will start in " + cooldowntime.get(Arrays.asList(p, player2)) + " seconds.");
					player2.sendMessage(
							"�3The match will start in " + cooldowntime.get(Arrays.asList(p, player2)) + " seconds.");
				} else {
					cancel();
					cooldowntime.remove(Arrays.asList(p, player2));
					cooldowntask.remove(Arrays.asList(p, player2));
					p.sendMessage("�bThe match begin, good luck!");
					player2.sendMessage("�bThe match begin, good luck!");
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 5, 5);
					player2.playSound(p.getLocation(), Sound.NOTE_PLING, 5, 5);
				}
			}
		});
		cooldowntask.get(Arrays.asList(p, player2)).runTaskTimer(pl, 20, 20);
		pu.teleportPlayersMatch(p, player2);
	}

}

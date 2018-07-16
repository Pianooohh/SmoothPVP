package Events;

import Commands.PlayerInfos;
import Commands.SeeCmd;
import Main.Main;
import Utils.GuiUtils;
import Utils.InvUtils;
import Utils.PlayerUtils;
import Utils.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryInteract implements Listener {

	private Map<List<Player>, Integer> cooldowntime = new HashMap<>();
	private Map<List<Player>, BukkitRunnable> cooldowntask = new HashMap<>();

	private Main pl;
	private ItemInteract it;

	public InventoryInteract(Main pl, ItemInteract it) {
		this.pl = pl;
		this.it = it;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventory(InventoryClickEvent e) throws IOException {
		if(!(e.getWhoClicked() instanceof Player)) return;
		EntityInteract et = new EntityInteract(pl);
		InvUtils invu = new InvUtils();
		PlayerUtils pu = new PlayerUtils(pl);
		Player p = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == null) return;
		if(e.getClickedInventory().getType() == InventoryType.PLAYER || e.getClickedInventory().getType() == InventoryType.CRAFTING) return;
		if(e.getCurrentItem().getType() == Material.AIR) return;
		File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
		if(e.getInventory().getName().startsWith("�3Save ki")) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Load default")) {
				e.setCancelled(true);
				if(pl.editarcher.contains(p)) {
					invu.giveDefaultArcher(p);
				}
				if(pl.editcombo.contains(p)) {
					invu.giveDefaultCombo(p);
				}
				if(pl.editdebuff.contains(p)) {
					invu.giveDefaultDebuff(p);
				}
				if(pl.editnodebuff.contains(p)) {
					invu.giveDefaultNodebuff(p);
				}
				if(pl.edithg.contains(p)) {
					invu.giveDefaultHG(p);
				}
				if(pl.editsoup.contains(p)) {
					invu.giveDefaultSoup(p);
				}
				p.sendMessage("�bDefault kit loaded.");
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Clear inventory")) {
				e.setCancelled(true);
				p.getInventory().clear();
				p.sendMessage("�bInventory cleared.");
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Save curr")) {
				e.setCancelled(true);
				if(pl.editarcher.contains(p)) {
					pu.saveKit(p, "KitArcher");
				}
				if(pl.editcombo.contains(p)) {
					pu.saveKit(p, "KitCombo");
				}
				if(pl.editdebuff.contains(p)) {
					pu.saveKit(p, "KitDebuff");
				}
				if(pl.editnodebuff.contains(p)) {
					pu.saveKit(p, "KitNoDebuff");
				}
				if(pl.edithg.contains(p)) {
					pu.saveKit(p, "KitHG");
				}
				if(pl.editsoup.contains(p)) {
					pu.saveKit(p, "KitSoup");
				}
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Load saved")) {
				e.setCancelled(true);
				if(pl.editarcher.contains(p)) {
					if(yloc.getString("KitArcher.content").equals("none")){ 
						invu.giveDefaultArcher(p);
					} else {
						pu.getKit(p, "KitArcher");
					}
				}
				if(pl.editcombo.contains(p)) {
					if(yloc.getString("KitCombo.content").equals("none")){ 
						invu.giveDefaultCombo(p);
					} else {
						pu.getKit(p, "KitCombo");
					}
				}
				if(pl.editdebuff.contains(p)) {
					if(yloc.getString("KitDebuff.content").equals("none")){ 
						invu.giveDefaultDebuff(p);
					} else {
						pu.getKit(p, "KitDebuff");
					}
				}
				if(pl.editnodebuff.contains(p)) {
					if(yloc.getString("KitNoDebuff.content").equals("none")){ 
						invu.giveDefaultNodebuff(p);
					} else {
						pu.getKit(p, "KitNoDebuff");
					}
				}
				if(pl.edithg.contains(p)) {
					if(yloc.getString("KitHG.content").equals("none")){ 
						invu.giveDefaultHG(p);
					} else {
						pu.getKit(p, "KitHG");
					}
				}
				if(pl.editsoup.contains(p)) {
					if(yloc.getString("KitSoup.content").equals("none")){ 
						invu.giveDefaultSoup(p);
					} else {
						pu.getKit(p, "KitSoup");
					}
				}
				p.sendMessage("�bKit loaded.");
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")) {
				e.setCancelled(true);
			}
			p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
		}
		if(e.getInventory().getName().startsWith("�3Choose the")) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3NoDebuf")) {
				e.setCancelled(true);
				p.closeInventory();
				pl.editnodebuff.add(p);
				Location editkitloc = new Location(p.getWorld(), -41, 37, 1347);
				p.teleport(editkitloc);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				if(yloc.getString("KitNoDebuff.content").equals("none")){ 
					invu.giveDefaultNodebuff(p);
				} else {
					pu.getKit(p, "KitNoDebuff");
				}
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Debuf")) {
				e.setCancelled(true);
				p.closeInventory();
				pl.editdebuff.add(p);
				Location editkitloc = new Location(p.getWorld(), -41, 37, 1347);
				p.teleport(editkitloc);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				if(yloc.getString("KitDebuff.content").equals("none")){ 
					invu.giveDefaultDebuff(p);
				} else {
					pu.getKit(p, "KitDebuff");
				}
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Comb")) {
				e.setCancelled(true);
				p.closeInventory();
				pl.editcombo.add(p);
				Location editkitloc = new Location(p.getWorld(), -41, 37, 1347);
				p.teleport(editkitloc);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				if(yloc.getString("KitCombo.content").equals("none")){ 
					invu.giveDefaultCombo(p);
				} else {
					pu.getKit(p, "KitCombo");
				}
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Arch")) {
				e.setCancelled(true);
				p.closeInventory();
				pl.editarcher.add(p);
				Location editkitloc = new Location(p.getWorld(), -41, 37, 1347);
				p.teleport(editkitloc);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				if(yloc.getString("KitArcher.content").equals("none")){ 
					invu.giveDefaultArcher(p);
				} else {
					pu.getKit(p, "KitArcher");
				}
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Soup")) {
				e.setCancelled(true);
				p.closeInventory();
				pl.editsoup.add(p);
				Location editkitloc = new Location(p.getWorld(), -41, 37, 1347);
				p.teleport(editkitloc);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				if(yloc.getString("KitSoup.content").equals("none")){ 
					invu.giveDefaultSoup(p);
				} else {
					pu.getKit(p, "KitSoup");
				}
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�3Hung")) {
				e.setCancelled(true);
				p.closeInventory();
				pl.edithg.add(p);
				Location editkitloc = new Location(p.getWorld(), -41, 37, 1347);
				p.teleport(editkitloc);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				if(yloc.getString("KitHG.content").equals("none")){ 
					invu.giveDefaultHG(p);
				} else {
					pu.getKit(p, "KitHG");
				}
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
		}
		if(e.getInventory().getName().startsWith("�3Unranked")) {
			ItemStack leave = new ItemStack(Material.REDSTONE);
			ItemMeta leavem = leave.getItemMeta();
			leavem.setDisplayName("�cLeave the queue.");
			leave.setItemMeta(leavem);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3NoDebuff")) {
				if(!pl.qnodebuff.contains(p)) {
					pl.qnodebuff.add(p);
					p.closeInventory();
					p.getInventory().clear();
					p.getInventory().setItem(8, leave);
					p.updateInventory();
					p.sendMessage("�bYou have been added in the Unranked NoDebuff queue.");
					if(pl.qnodebuff.size() >= 2) {
						Player player2 = pl.qnodebuff.get(0);
						File floc2 = new File("plugins//SmoothPVP//Players//" + player2.getUniqueId() + ".yml");
						YamlConfiguration yloc2 = YamlConfiguration.loadConfiguration(floc2);
						pl.qnodebuff.remove(p);
						pl.qnodebuff.remove(player2);
						p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked NoDebuff.");
						player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked NoDebuff.");
						for(Player all : Bukkit.getOnlinePlayers()) {
							p.hidePlayer(all);
							player2.hidePlayer(all);
							p.showPlayer(player2);
							player2.showPlayer(p);
						}
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
						 cooldowntime.put(Arrays.asList(p, player2), 4);
						 cooldowntask.put(Arrays.asList(p, player2), new BukkitRunnable(){
							 public void run() {
								 cooldowntime.put(Arrays.asList(p, player2), cooldowntime.get(Arrays.asList(p, player2)) -1);
								 if(cooldowntime.get(Arrays.asList(p, player2)) != 0) {
									 p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									 player2.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									 p.sendMessage("�bThe match will start in " + cooldowntime.get(Arrays.asList(p, player2)) + " seconds.");
									 player2.sendMessage("�3The match will start in " + cooldowntime.get(Arrays.asList(p, player2)) + " seconds.");
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
				} else {
					p.closeInventory();
					p.updateInventory();
					p.sendMessage("�cYou are already in the Unranked NoDebuff queue!");
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Debuff")) {
				if(!pl.qdebuff.contains(p)) {
					pl.qdebuff.add(p);
					p.closeInventory();
					p.getInventory().clear();
					p.getInventory().setItem(8, leave);
					p.updateInventory();
					p.sendMessage("�bYou have been added in the Unranked Debuff queue.");
					if(pl.qdebuff.size() >= 2) {
						Player player2 = pl.qdebuff.get(0);
						File floc2 = new File("plugins//SmoothPVP//Players//" + player2.getUniqueId() + ".yml");
						YamlConfiguration yloc2 = YamlConfiguration.loadConfiguration(floc2);
						pl.qdebuff.remove(p);
						pl.qdebuff.remove(player2);
						p.sendMessage("�7You are now dueling �b" + player2.getDisplayName() + " �7in Unranked Debuff.");
						player2.sendMessage("�7You are now dueling �b" + p.getDisplayName() + " �7in Unranked Debuff.");
						for(Player all : Bukkit.getOnlinePlayers()) {
							p.hidePlayer(all);
							player2.hidePlayer(all);
							p.showPlayer(player2);
							player2.showPlayer(p);
						}
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
						 cooldowntime.put(Arrays.asList(p, player2), 4);
						 cooldowntask.put(Arrays.asList(p, player2), new BukkitRunnable(){
							 public void run() {
								 cooldowntime.put(Arrays.asList(p, player2), cooldowntime.get(Arrays.asList(p, player2)) -1);
								 if(cooldowntime.get(Arrays.asList(p, player2)) != 0) {
									 p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									 player2.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
									 p.sendMessage("�bThe match will start in " + cooldowntime.get(Arrays.asList(p, player2)) + " seconds.");
									 player2.sendMessage("�3The match will start in " + cooldowntime.get(Arrays.asList(p, player2)) + " seconds.");
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
					}
				} else {
					p.closeInventory();
					p.updateInventory();
					p.sendMessage("�cYou are already in the Unranked Debuff queue!");
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Combo")) {
				if(!pl.qcombo.contains(p)) {
					pl.qcombo.add(p);
					p.closeInventory();
					p.getInventory().clear();
					p.getInventory().setItem(8, leave);
					p.updateInventory();
					p.sendMessage("�bYou have been added in the Unranked Combo queue.");
				} else {
					p.closeInventory();
					p.updateInventory();
					p.sendMessage("�cYou are already in the Unranked Combo queue!");
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Soup")) {
				if(!pl.qsoup.contains(p)) {
					pl.qsoup.add(p);
					p.closeInventory();
					p.getInventory().clear();
					p.getInventory().setItem(8, leave);
					p.updateInventory();
					p.sendMessage("�bYou have been added in the Unranked Soup queue.");
				} else {
					p.closeInventory();
					p.updateInventory();
					p.sendMessage("�cYou are already in the Unranked Soup queue!");
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Archer")) {
				if(!pl.qarrow.contains(p)) {
					pl.qarrow.add(p);
					p.closeInventory();
					p.getInventory().clear();
					p.getInventory().setItem(8, leave);
					p.updateInventory();
					p.sendMessage("�bYou have been added in the Unranked Archer queue.");
				} else {
					p.closeInventory();
					p.updateInventory();
					p.sendMessage("�cYou are already in the Unranked Archer queue!");
				}
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Hunger Games")) {
				if(!pl.qhg.contains(p)) {
					pl.qhg.add(p);
					p.closeInventory();
					p.getInventory().clear();
					p.getInventory().setItem(8, leave);
					p.updateInventory();
					p.sendMessage("�bYou have been added in the Unranked Hunger Games queue.");
				} else {
					p.closeInventory();
					p.updateInventory();
					p.sendMessage("�cYou are already in the Unranked Hunger Games queue!");
				}
			}
		}
		if(e.getInventory().getName().startsWith("�3Knockback Sword") && pl.ismod.contains(p)) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Knockback Level: �b1")) {
				p.closeInventory();
				ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        		sword.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.KNOCKBACK, 1);
        		GuiUtils.setItem(p.getInventory(), sword, 0, "�3Knockback Sword �7- �bLevel�f: 1");
        		p.sendMessage("�bThe level of your Knockback Sword has been adjusted to 1");
        		p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Knockback Level: �b2")) {
				p.closeInventory();
				ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        		sword.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.KNOCKBACK, 2);
        		GuiUtils.setItem(p.getInventory(), sword, 0, "�3Knockback Sword �7- �bLevel�f: 2");
        		p.sendMessage("�bThe level of your Knockback Sword has been adjusted to 2");
        		p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Knockback Level: �b3")) {
				p.closeInventory();
				ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        		sword.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.KNOCKBACK, 3);
        		GuiUtils.setItem(p.getInventory(), sword, 0, "�3Knockback Sword �7- �bLevel�f: 3");
        		p.sendMessage("�bThe level of your Knockback Sword has been adjusted to 3");
        		p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�3Knockback Level: �b4")) {
				p.closeInventory();
				ItemStack sword = new ItemStack(Material.WOOD_SWORD);
        		sword.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.KNOCKBACK, 4);
        		GuiUtils.setItem(p.getInventory(), sword, 0, "�3Knockback Sword �7- �bLevel�f: 4");
        		p.sendMessage("�bThe level of your Knockback Sword has been adjusted to 4");
        		p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Custom")) {
				p.closeInventory();
				pl.istextingkb.add(p);
				p.sendMessage("�bEnter a value between 1 & 20.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
		}
		if(e.getInventory().getName().startsWith("�3Server") && pl.ismod.contains(p)) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("�cClose")) {
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("FFA is currently: �cOffline")) {
				pl.FFA = true;
				Bukkit.broadcastMessage("�3�lFFA: �bThe server FFA is now �aOnline.");
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " turned ON the FFA Server.");
				for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("FFA is currently: �aOnline")) {
				pl.FFA = false;
				Bukkit.broadcastMessage("�3�lFFA: �bThe server FFA is now �cOffline.");
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " turned OFF the FFA Server.");
				for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Duels is currently: �cOffline")) {
				pl.Duels = true;
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " turned ON the Duels Server.");
				Bukkit.broadcastMessage("�3�lDuels: �bThe server Duels is now �aOnline.");
				for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Duels is currently: �aOnline")) {
				pl.Duels = false;
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " turned OFF the Duels Server.");
				Bukkit.broadcastMessage("�3�lDuels: �bThe server Duels is now �cOffline.");
				for(Player all : Bukkit.getOnlinePlayers()) { all.playSound(all.getLocation(), Sound.CLICK, 10, 10); }
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�bTeleport to the FFA")) {
				p.closeInventory();
				p.sendMessage("�cThere is no tp for the moment!");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("�bTeleport to the Duels")) {
				p.closeInventory();
				p.sendMessage("�cThere is no tp for the moment!");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
			}
		}
		if(e.getInventory().getName().startsWith("�3Infos") && pl.ismod.contains(p)) {
			e.setCancelled(true);
			String tar = e.getClickedInventory().getTitle().split(": �8")[1];
			Player suspect = Bukkit.getPlayer(tar);
			if(e.getCurrentItem().getType() == Material.REDSTONE) {
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.FEATHER) {
				suspect.getInventory().clear();
				p.sendMessage("�3Successfully cleared " + suspect.getDisplayName() + "'s inventory.");
				suspect.sendMessage("�3Your inventory has been cleared by a staff.");
				suspect.getInventory().setBoots(null);
				suspect.getInventory().setHelmet(null);
				suspect.getInventory().setLeggings(null);
				suspect.getInventory().setChestplate(null);
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " cleared " + suspect.getName() + "'s inventory.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.ENDER_PEARL) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				p.closeInventory();
				p.teleport(suspect.getLocation());
				p.sendMessage("�3You have been teleported to " + suspect.getDisplayName());
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " teleported to " + suspect.getName() + ".");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			}
			if(e.getCurrentItem().getType() == Material.NETHER_STAR) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				p.performCommand("freeze " + suspect.getDisplayName());
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				p.closeInventory();
				et.openGestionMenu(p, suspect);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
				suspect.teleport(suspect.getWorld().getSpawnLocation());
				p.sendMessage("�3You have respawn " + suspect.getDisplayName());
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " have respawned " + suspect.getName() + ".");
				suspect.sendMessage("�3You have been respawn by a staff.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			}
			if(e.getCurrentItem().getType() == Material.ARROW) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				suspect.setHealth(0);
				p.closeInventory();
				p.sendMessage("�3You have killed "+suspect.getDisplayName());
				suspect.sendMessage("�cYou have been killed by a staff!");
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " has forcekilled " + suspect.getName() + ".");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.PAPER) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				SeeCmd.verifiers.put(p, PlayerWrapper.getByPlayer(suspect));
				p.closeInventory();
				Inventory i = Bukkit.createInventory(null, 54, "�3See: �8" + suspect.getDisplayName());
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " perform /see command on " + suspect.getName() + ".");
				p.openInventory(i);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
		}
		if(e.getInventory().getName().startsWith("�3See") && pl.ismod.contains(p)) {
			e.setCancelled(true);
			String tar = e.getClickedInventory().getTitle().split(": �8")[1];
			Player suspect = Bukkit.getPlayer(tar);
			if(e.getCurrentItem().getType() == Material.NETHER_STAR) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				p.performCommand("freeze " + suspect.getDisplayName());
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.BLAZE_ROD) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				p.closeInventory();
				et.openGestionMenu(p, suspect);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.EYE_OF_ENDER) {
				suspect.teleport(suspect.getWorld().getSpawnLocation());
				p.sendMessage("�3You have respawn " + suspect.getDisplayName());
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " have respawned " + suspect.getName() + ".");
				suspect.sendMessage("�3You have been respawn by a staff.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			}
			if(e.getCurrentItem().getType() == Material.ARROW) {
				if(PlayerInfos.players.containsKey(p)) { PlayerInfos.players.remove(p); }
				suspect.setHealth(0);
				p.closeInventory();
				p.sendMessage("�3You have killed "+suspect.getDisplayName());
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " has forcekilled " + suspect.getName() + ".");
				suspect.sendMessage("�cYou have been killed by a staff!");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.FEATHER) {
				suspect.getInventory().clear();
				p.sendMessage("�3Successfully cleared " + suspect.getDisplayName() + "'s inventory.");
				suspect.sendMessage("�3Your inventory has been cleared by a staff.");
				suspect.getInventory().setBoots(null);
				suspect.getInventory().setHelmet(null);
				suspect.getInventory().setLeggings(null);
				suspect.getInventory().setChestplate(null);
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " cleared " + suspect.getName() + "'s inventory.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				if(SeeCmd.verifiers.containsKey(p)) { SeeCmd.verifiers.remove(p); }
				PlayerInfos.players.put(p, PlayerWrapper.getByPlayer(suspect));
				p.closeInventory();
				Inventory i = Bukkit.createInventory(null, 54, "�3Infos: �8" + suspect.getDisplayName());
				p.openInventory(i);
				pl.sendRconMsg("�6Staff Logs �7�l> �e" + p.getName() + " perform /pinfos command on " + suspect.getName() + "'s inventory.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.REDSTONE) {
				if(SeeCmd.verifiers.containsKey(p)) {
					SeeCmd.verifiers.remove(p);
				}
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
		}
		if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
			e.setCancelled(true);
		}
		if(e.getInventory().getName().contains("�3Manage: ") && pl.ismod.contains(p)) {
			e.setCancelled(true);
			if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�cClose the menu")) {
				e.getWhoClicked().closeInventory();
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.TNT){
				String tar = e.getClickedInventory().getTitle().split(": �8")[1];
				Player suspect = Bukkit.getPlayer(tar);
				e.getWhoClicked().closeInventory();
				Inventory inv = Bukkit.createInventory(null, 9*3, "�3Ban: �8" + suspect.getDisplayName());
				
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)15);
				ItemStack wool1 = new ItemStack(Material.WOOL, 1, (byte)14);
				ItemStack wool2 = new ItemStack(Material.WOOL, 1, (byte)5);
				ItemStack wool3 = new ItemStack(Material.WOOL, 1, (byte)9);
				ItemStack wool4 = new ItemStack(Material.WOOL, 1, (byte)15);
				ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
				
				ItemMeta glassm = glass.getItemMeta();
				ItemMeta woolm1 = wool1.getItemMeta();
				ItemMeta woolm2 = wool2.getItemMeta();
				ItemMeta woolm3 = wool3.getItemMeta();
				ItemMeta woolm4 = wool4.getItemMeta();
				ItemMeta redm = red.getItemMeta();
				
				glassm.setDisplayName(" ");
				woolm1.setDisplayName("�cCheat");
				woolm2.setDisplayName("�aNo fair-play");
				woolm3.setDisplayName("�3Injuring");
				woolm4.setDisplayName("�7Other reason");
				redm.setDisplayName("�cReturn to the menu");
				
				glass.setItemMeta(glassm);
				wool1.setItemMeta(woolm1);
				wool2.setItemMeta(woolm2);
				wool3.setItemMeta(woolm3);
				wool4.setItemMeta(woolm4);
				red.setItemMeta(redm);
				
				for(int i = 0; i < 9*3; i++) {
					inv.setItem(i, glass);
				}
				
				inv.setItem(10, wool1);
				inv.setItem(12, wool2);
				inv.setItem(14, wool3);
				inv.setItem(16, wool4);
				inv.setItem(22, red);
				
				e.getWhoClicked().openInventory(inv);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.ANVIL){
				String tar = e.getClickedInventory().getTitle().split(": �8")[1];
				Player suspect = Bukkit.getPlayer(tar);
				e.getWhoClicked().closeInventory();
				Inventory inv = Bukkit.createInventory(null, 9*3, "�3Kick: �8" + suspect.getDisplayName());
				
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
				ItemStack wool1 = new ItemStack(Material.WOOL, 1, (byte)14);
				ItemStack wool2 = new ItemStack(Material.WOOL, 1, (byte)5);
				ItemStack wool3 = new ItemStack(Material.WOOL, 1, (byte)9);
				ItemStack wool4 = new ItemStack(Material.WOOL, 1, (byte)15);
				ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
				
				ItemMeta glassm = glass.getItemMeta();
				ItemMeta woolm1 = wool1.getItemMeta();
				ItemMeta woolm2 = wool2.getItemMeta();
				ItemMeta woolm3 = wool3.getItemMeta();
				ItemMeta woolm4 = wool4.getItemMeta();
				ItemMeta redm = red.getItemMeta();
				
				glassm.setDisplayName(" ");
				woolm1.setDisplayName("�cSpamming");
				woolm2.setDisplayName("�aNo fair-play");
				woolm3.setDisplayName("�3Injuring");
				woolm4.setDisplayName("�7Other reason");
				redm.setDisplayName("�cReturn to the menu");
				
				glass.setItemMeta(glassm);
				wool1.setItemMeta(woolm1);
				wool2.setItemMeta(woolm2);
				wool3.setItemMeta(woolm3);
				wool4.setItemMeta(woolm4);
				red.setItemMeta(redm);
				
				for(int i = 0; i < 9*3; i++) {
					inv.setItem(i, glass);
				}
				
				inv.setItem(10, wool1);
				inv.setItem(12, wool2);
				inv.setItem(14, wool3);
				inv.setItem(16, wool4);
				inv.setItem(22, red);
				
				e.getWhoClicked().openInventory(inv);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getType() == Material.WATCH){
				String tar = e.getClickedInventory().getTitle().split(": �8")[1];
				Player suspect = Bukkit.getPlayer(tar);
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				p.performCommand("freeze " + suspect.getDisplayName());
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
		}
		if(e.getInventory().getName().contains("�3Ban: ") && pl.ismod.contains(p)) {
			e.setCancelled(true);
			String tar = e.getClickedInventory().getTitle().split(": �8")[1];
			Player suspect = Bukkit.getPlayer(tar);
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "�cCheat"){
				e.getWhoClicked().closeInventory();
				pl.performCommand("ban " + suspect.getName() + " Cheating.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "�aNo fair-play"){
				e.getWhoClicked().closeInventory();
				pl.performCommand("ban " + suspect.getName() + " No fair-play.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "�3Injuring"){
				e.getWhoClicked().closeInventory();
				pl.performCommand("ban " + suspect.getName() + " Injuring.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "�7Other reason"){
				e.getWhoClicked().closeInventory();
				pl.istextingban.add((Player) e.getWhoClicked());
				p.sendMessage("�3Enter the reason of the ban.");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "�cReturn to the menu"){
				e.getWhoClicked().closeInventory();
				e.setCancelled(true);
				et.openGestionMenu(p, suspect);
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (SeeCmd.verifiers.containsKey(e.getPlayer()) && e.getInventory().getName().startsWith("�3See")) {
			SeeCmd.verifiers.remove(e.getPlayer());
		}
		if (PlayerInfos.players.containsKey(e.getPlayer()) && e.getInventory().getName().startsWith("�3Infos")) {
			PlayerInfos.players.remove(e.getPlayer());
		}
		if (it.opener.contains(e.getPlayer()) && e.getInventory().getName().startsWith("�3Server")) {
			it.opener.remove(e.getPlayer());
		}
	}

}

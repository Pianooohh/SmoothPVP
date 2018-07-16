package fr.smoothpvp.smoothpvp.Events;

import fr.smoothpvp.smoothpvp.Utils.InvUtils;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ItemInteract implements Listener {
	
	public ArrayList<Player> opener = new ArrayList<Player>();
	private Main plugin;
	InvUtils invu = new InvUtils();
	public ItemInteract(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
    public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[SPAWN]")) {
			e.setLine(0, "§8-*-");
            e.setLine(1, "§3Back to");
            e.setLine(2, "§bSpawn");
            e.setLine(3, "§8-*-");
            return;
		}
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void interact2(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK){ 
			if (e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if (s.getLine(1).equalsIgnoreCase("§3Back to")) {
					e.setCancelled(true);
					p.teleport(p.getWorld().getSpawnLocation());
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
					for(Player players : Bukkit.getOnlinePlayers()) {
						p.showPlayer(players);
						if(plugin.ismod.contains(players)) {
							p.hidePlayer(players);
						}
					}
					p.getInventory().clear();
					p.closeInventory();
					p.updateInventory();
					InvUtils.giveSpawnItems(p);
					plugin.editarcher.remove(p);
					plugin.editcombo.remove(p);
					plugin.editdebuff.remove(p);
					plugin.editnodebuff.remove(p);
					plugin.edithg.remove(p);
					plugin.editsoup.remove(p);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onRefill(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock() == null) return;
			File floc = new File("plugins//SmoothPVP//Players//" + p.getUniqueId() + ".yml");
			YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
			if(e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 54, "§3Refill chest");
				inv.clear();
				for(int i = 0; i < inv.getSize(); i++) { inv.addItem(new ItemStack(Material.POTION, 1, (short) 16421)); }
				p.openInventory(inv);
				p.playSound(p.getLocation(), Sound.PORTAL, 1, 1);
			}
			if(e.getClickedBlock().getType().equals(Material.ANVIL)) {
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9*3, "§3Save kit");
				ItemStack glasses = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
				ItemStack die = new ItemStack(Material.INK_SACK, 1, (short)10);
				ItemStack books = new ItemStack(Material.BOOK);
				
				ItemMeta glassesm = glasses.getItemMeta();
				ItemMeta diem = die.getItemMeta();
				ItemMeta booksm = books.getItemMeta();
				
				glassesm.setDisplayName(" ");
				glasses.setItemMeta(glassesm);
				for(int i = 9; i < 18; i++) {
					inv.setItem(i, glasses);
				}
				diem.setDisplayName("§3Save current kit:");
				diem.setLore(Arrays.asList("§bClick to save your current kit."));
				die.setItemMeta(diem);
				inv.setItem(1, die);
				if(plugin.editarcher.contains(p)) {
					if(yloc.getString("KitArcher.content").equals("none")){ 
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(19, books);
					} else {
						booksm.setDisplayName("§3Load saved kit:");
						booksm.setLore(Arrays.asList("§bClick to load your saved kit."));
						booksm.addEnchant(Enchantment.DURABILITY, 10, true);
						books.setItemMeta(booksm);
						inv.setItem(19, books);
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(25, books);
					}
				}
				if(plugin.editcombo.contains(p)) {
					if(yloc.getString("KitCombo.content").equals("none")){ 
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(19, books);
					} else {
						booksm.setDisplayName("§3Load saved kit:");
						booksm.setLore(Arrays.asList("§bClick to load your saved kit."));
						booksm.addEnchant(Enchantment.DURABILITY, 10, true);
						books.setItemMeta(booksm);
						inv.setItem(19, books);
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(25, books);
					}
				}
				if(plugin.editdebuff.contains(p)) {
					if(yloc.getString("KitDebuff.content").equals("none")){ 
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(19, books);
					} else {
						booksm.setDisplayName("§3Load saved kit:");
						booksm.setLore(Arrays.asList("§bClick to load your saved kit."));
						booksm.addEnchant(Enchantment.DURABILITY, 10, true);
						books.setItemMeta(booksm);
						inv.setItem(19, books);
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(25, books);
					}
				}
				if(plugin.editnodebuff.contains(p)) {
					if(yloc.getString("KitNoDebuff.content").equals("none")){ 
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(19, books);
					} else {
						booksm.setDisplayName("§3Load saved kit:");
						booksm.setLore(Arrays.asList("§bClick to load your saved kit."));
						booksm.addEnchant(Enchantment.DURABILITY, 10, true);
						books.setItemMeta(booksm);
						inv.setItem(19, books);
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(25, books);
					}
				}
				if(plugin.edithg.contains(p)) {
					if(yloc.getString("KitHG.content").equals("none")){ 
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(19, books);
					} else {
						booksm.setDisplayName("§3Load saved kit:");
						booksm.setLore(Arrays.asList("§bClick to load your saved kit."));
						booksm.addEnchant(Enchantment.DURABILITY, 10, true);
						books.setItemMeta(booksm);
						inv.setItem(19, books);
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(25, books);
					}
				}
				if(plugin.editsoup.contains(p)) {
					if(yloc.getString("KitSoup.content").equals("none")){ 
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(19, books);
					} else {
						booksm.setDisplayName("§3Load saved kit:");
						booksm.setLore(Arrays.asList("§bClick to load your saved kit."));
						booksm.addEnchant(Enchantment.DURABILITY, 10, true);
						books.setItemMeta(booksm);
						inv.setItem(19, books);
						booksm.setDisplayName("§3Load default kit:");
						booksm.setLore(Arrays.asList("§bClick to load the default kit."));
						books.setItemMeta(booksm);
						inv.setItem(25, books);
					}
				}
				ItemStack red = new ItemStack(Material.REDSTONE_BLOCK);
				ItemMeta redm = red.getItemMeta();
				redm.setDisplayName("§3Clear inventory");
				redm.setLore(Arrays.asList("§bClick to clear your inventory."));
				red.setItemMeta(redm);
				inv.setItem(7, red);
				p.openInventory(inv);
				
			}
			if(e.getClickedBlock().getType().equals(Material.CHEST)) {
				if(plugin.editnodebuff.contains(p)) {
					e.setCancelled(true);
					Inventory inv = Bukkit.createInventory(null, 54, "§3NoDebuff Contents:");
					inv.clear();
					ItemStack heal = new ItemStack(Material.POTION, 1, (short)16421);
					ItemStack speed = new ItemStack(Material.POTION, 1, (short)8226);
					ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
					ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 16);
					ItemStack beef = new ItemStack(Material.COOKED_BEEF, 64);
					ItemStack pork = new ItemStack(Material.GRILLED_PORK, 64);
					ItemStack carot = new ItemStack(Material.GOLDEN_CARROT, 64);
					ItemStack fire = new ItemStack(Material.POTION, 1, (short)8259);
					
					ItemMeta swordm = sword.getItemMeta();
					swordm.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
					swordm.addEnchant(Enchantment.DURABILITY, 3, true);
					swordm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);					
					sword.setItemMeta(swordm);
					for(int i = 0; i < inv.getSize(); i++) { 
						inv.addItem(heal);
					}
					inv.setItem(7, speed);
					inv.setItem(8, speed);
					inv.setItem(16, speed);
					inv.setItem(17, speed);
					inv.setItem(25, fire);
					inv.setItem(26, fire);
					inv.setItem(34, sword);
					inv.setItem(43, pearl);
					inv.setItem(35, beef);
					inv.setItem(44, pork);
					inv.setItem(53, carot);
					p.openInventory(inv);
				}
				else if(plugin.editdebuff.contains(p)) {
					e.setCancelled(true);
					Inventory inv = Bukkit.createInventory(null, 54, "§3Debuff Contents:");
					inv.clear();
					ItemStack heal = new ItemStack(Material.POTION, 1, (short)16421);
					ItemStack speed = new ItemStack(Material.POTION, 1, (short)8226);
					ItemStack poison = new ItemStack(Material.POTION, 1, (short)16388);
					ItemStack damage = new ItemStack(Material.POTION, 1, (short)16426);
					ItemStack fire = new ItemStack(Material.POTION, 1, (short)8259);
					ItemStack beef = new ItemStack(Material.COOKED_BEEF, 64);
					ItemStack pork = new ItemStack(Material.GRILLED_PORK, 64);
					ItemStack carot = new ItemStack(Material.GOLDEN_CARROT, 64);
					ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
					ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 16);
					ItemMeta swordm = sword.getItemMeta();
					swordm.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
					swordm.addEnchant(Enchantment.DURABILITY, 3, true);
					swordm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
					for(int i = 0; i < inv.getSize(); i++) { 
						inv.addItem(heal);
					}
					inv.setItem(6, sword);
					inv.setItem(7, speed);
					inv.setItem(8, speed);
					inv.setItem(15, pearl);
					inv.setItem(16, speed);
					inv.setItem(17, speed);
					inv.setItem(25, fire);
					inv.setItem(26, fire);
					inv.setItem(34, damage);
					inv.setItem(33, poison);
					inv.setItem(35, beef);
					inv.setItem(43, damage);
					inv.setItem(42, poison);
					inv.setItem(44, pork);
					inv.setItem(52, damage);
					inv.setItem(51, poison);
					inv.setItem(53, carot);
					p.openInventory(inv);
				} else {
					e.setCancelled(true);
					p.sendMessage("§cThis kit contents cannot be edited!");
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		ItemMeta itemm = item.getItemMeta();
		if(e.getAction() == Action.RIGHT_CLICK_AIR  || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(item.getType() == Material.AIR) return;
			if(itemm == null) return;
			if(itemm.getDisplayName() == null) return;
			if(itemm.getDisplayName().contains("Edit Ki")) {
				invu.chooseEditKit(p);
			}
			if(itemm.getDisplayName().contains("Hide")) {
				ItemStack show = new ItemStack(Material.INK_SACK, 1, (short)8);
				ItemMeta sho = show.getItemMeta();
				sho.setDisplayName("§6Show players §7(Right-Click)");
				show.setItemMeta(sho);
				p.sendMessage("§bYou have hidden all players.");
				p.getInventory().setItem(8, show);
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(players);
				}
			}
			if(itemm.getDisplayName().contains("Show")) {
				ItemStack show = new ItemStack(Material.INK_SACK, 1, (short)10);
				ItemMeta sho = show.getItemMeta();
				p.sendMessage("§bYou can see all players now.");
				sho.setDisplayName("§6Hide players §7(Right-Click)");
				show.setItemMeta(sho);
				p.getInventory().setItem(8, show);
				for(Player players : Bukkit.getOnlinePlayers()) {
					p.showPlayer(players);
				}
				for(Player mods : plugin.ismod) {
					p.hidePlayer(mods);
				}
			}
			if(itemm.getDisplayName() == "§cLeave the queue.") {
				if(plugin.qarrow.contains(p)) {
					plugin.qarrow.remove(p);
					InvUtils.giveSpawnItems(p);
					p.sendMessage("§bYou have left the queue.");
					p.updateInventory();
				}
				else if(plugin.qcombo.contains(p)) {
					plugin.qcombo.remove(p);
					InvUtils.giveSpawnItems(p);
					p.sendMessage("§bYou have left the queue.");
					p.updateInventory();
				}
				else if(plugin.qnodebuff.contains(p)) {
					plugin.qnodebuff.remove(p);
					InvUtils.giveSpawnItems(p);
					p.sendMessage("§bYou have left the queue.");
					p.updateInventory();
				}
				else if(plugin.qdebuff.contains(p)) {
					plugin.qdebuff.remove(p);
					InvUtils.giveSpawnItems(p);
					p.sendMessage("§bYou have left the queue.");
					p.updateInventory();
				}
				else if(plugin.qsoup.contains(p)) {
					plugin.qsoup.remove(p);
					InvUtils.giveSpawnItems(p);
					p.sendMessage("§bYou have left the queue.");
					p.updateInventory();
				}
				else if(plugin.qhg.contains(p)) {
					plugin.qhg.remove(p);
					InvUtils.giveSpawnItems(p);
					p.sendMessage("§bYou have left the queue.");
					p.updateInventory();
				}
			}
			if(itemm.getDisplayName() == "§bUnranked 1v1") {
				if(plugin.Duels == false && !p.hasPermission("perm.admin")) {
					p.sendMessage("§cThe server is currently offline!");
					return;
				}
				if(plugin.Duels == false && p.hasPermission("perm.admin")) {
					invu.unrankedInv(p);
					return;
				}
				if(plugin.Duels == true) {
					invu.unrankedInv(p);
					return;
				}
			}
			if(itemm.getDisplayName() == ("§eSwitch to moderator mod") && !plugin.ismod.contains(p)) {
				e.setCancelled(true);
				p.performCommand("mod");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
			}
			if(plugin.ismod.contains(p)) {
				if(itemm.getDisplayName().contains("Knockback")) {
					e.setCancelled(true);
					invu.kbMenu(p);
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
				if(itemm.getDisplayName() == "§3Server Teleport") {
					e.setCancelled(true);
					Inventory i = Bukkit.createInventory(null, 9*4, "§3Server List:");
					opener.add(p);
					p.openInventory(i);
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
				if(itemm.getDisplayName() == "§3Random Teleport") {
					e.setCancelled(true);
					Random r = new Random();
			        ArrayList<Player> players = new ArrayList<Player>();
			        for(Player online : Bukkit.getServer().getOnlinePlayers()) {
			            players.add(online);
			        }
			        int index = r.nextInt(players.size());
			        Player loc = players.get(index);
			        if(loc == null) return;
			        if(loc == p) { p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F); p.sendMessage("§cThe random teleport fell on you, you were not teleported."); return; }
			        p.teleport(loc);
			        p.sendMessage("§3You have been teleported to " + loc.getDisplayName());
			        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			        plugin.sendRconMsg("§6Staff Logs §7§l> §e" + p.getName() + " randomly telported to " + loc.getName() + ".");
				}
				if(itemm.getDisplayName() == "§cLeave the moderator mod") {
					e.setCancelled(true);
					p.performCommand("mod");
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
			}
		}
	}
}
